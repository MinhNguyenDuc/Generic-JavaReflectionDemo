package BigModel;

import annotation.*;
import entity.Animal;
import entity.Customer;
import entity.Employee;
import entity.Student;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class BigModel<T> {
    private Class<T> clazz;

    public Class<T> getClazz() {
        return clazz;
    }

    public BigModel(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getTableName(){
        return this.clazz.getSimpleName();
    }


    public String getAllFieldNameForInsert(){
        Field[] declareFields = this.clazz.getDeclaredFields();
        ArrayList<String> databaseAnnotationFieldNameSorted = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(Field f : declareFields){
            f.setAccessible(true);
            if(f.isAnnotationPresent(InsertField.class)){
                databaseAnnotationFieldNameSorted.add(f.getName());
            }
        }
        Collections.sort(databaseAnnotationFieldNameSorted);
        for(int i = 0 ; i < databaseAnnotationFieldNameSorted.size()-1;i++){
            sb.append(databaseAnnotationFieldNameSorted.get(i));
            sb.append(", ");
        }
        sb.append(databaseAnnotationFieldNameSorted.get(databaseAnnotationFieldNameSorted.size()-1));
        return sb.toString();
    }

    public String getAllFieldDatabaseValueForInsert(T obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();
        Class cls = obj.getClass();
        Method[] listMethod = cls.getDeclaredMethods();
        ArrayList<String> listMethodName = new ArrayList<String>();
        for(Method m : listMethod){
            m.setAccessible(true);
            if(m.isAnnotationPresent(FieldValueFromGetterMethod.class)){
                listMethodName.add(m.getName());
            }
        }
        Collections.sort(listMethodName);
        ArrayList<Method> databaseGetterValue = new ArrayList<Method>();
        for(String mName : listMethodName){
            for(Method m : listMethod){
                if(mName.equals(m.getName())){
                    databaseGetterValue.add(m);
                }
            }
        }
        for(int i = 0 ; i < databaseGetterValue.size()-1;i++){
            sb.append("'").append(databaseGetterValue.get(i).invoke(obj).toString()).append("', ");
        }
        sb.append("'").append((String)databaseGetterValue.get(databaseGetterValue.size()-1).invoke(obj)).append("'");
        return sb.toString();
    }

    //Insert 1 object into database
    public boolean insert(T obj) throws IllegalAccessException, InvocationTargetException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(getTableName());
        sb.append(" (");
        sb.append(this.getAllFieldNameForInsert());
        sb.append(") VALUES (");
        sb.append(getAllFieldDatabaseValueForInsert(obj));
        sb.append(")");
        String sql = sb.toString();
        Statement st = connection.createStatement();
        st.execute(sql);
        System.out.println(sql);
        return true;
    }

    public String modifyField() throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        Field[] listFields = this.clazz.getDeclaredFields();
        for(Field f : listFields){
            f.setAccessible(true);
            if(f.isAnnotationPresent(FieldToModify.class)){
                sb.append(f.getName());
            }
        }
        return sb.toString();
    }

    //Delete an object from database using a value of its field declared by annotation
    public boolean delete(String deleteValue)throws Exception{
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ");
            sb.append(getTableName());
            sb.append(" WHERE ");
            sb.append(modifyField());
            sb.append(" = '").append(deleteValue).append("';");
            Statement stm = connection.createStatement();
            stm.execute(sb.toString());
            System.out.println(sb.toString());
            System.out.println("Xóa thành công");
            return true;
        } catch(SQLException e){
            //e.printStackTrace();
            System.out.println("Xóa không thành công");
        }
        return false;
    }

    //Check if update field is declared using annotation
    public boolean checkFieldUpdateAnnotation(String fieldUpdate){
        StringBuilder sb = new StringBuilder();
        sb.append("set");
        sb.append(fieldUpdate);
        Method[] listMethods = this.clazz.getDeclaredMethods();
        for(Method m : listMethods){
            if(m.getName().toLowerCase().equals(sb.toString().toLowerCase()) && m.isAnnotationPresent(UpdateField.class)){
                return true;
            }
        }
        return false;
    }


    public boolean update(String fieldUpdate, String updateValue, String idOfModifiedField){
        try {
            if(checkFieldUpdateAnnotation(fieldUpdate)) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
                Statement stm = connection.createStatement();
                StringBuilder sb = new StringBuilder();
                sb.append("UPDATE ").append(this.clazz.getSimpleName());
                sb.append(" SET ");
                sb.append(fieldUpdate);
                sb.append(" = '");
                sb.append(updateValue);
                sb.append("' WHERE ");
                sb.append(modifyField()).append(" = '").append(idOfModifiedField).append("';");
                System.out.println(sb.toString());
                stm.execute(sb.toString());
                System.out.println("Cập nhật dữ liệu thành công");
                return true;
            }
            return false;
        } catch(SQLException e){
            System.out.println("Cập nhật dữ liệu không thành công");
            //e.printStackTrace();
        }
        return false;
    }

    public String getSearchField(){
        Field[] listFields = this.clazz.getDeclaredFields();
        for (Field f : listFields){
            f.setAccessible(true);
            if (f.isAnnotationPresent(SearchField.class)){
                return f.getName();
            }
        }
        return null;
    }

    //Search database with key is the field declared by annotation
    public String searchByKeyField(String key){
        StringBuilder sb = new StringBuilder();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stm = connection.createStatement();
            sb.append("SELECT * FROM ");
            sb.append(getTableName());
            sb.append(" WHERE ");
            sb.append(getSearchField());
            sb.append(" = '");
            sb.append(key);
            sb.append("' ");
            ResultSet rs = stm.executeQuery(sb.toString());
            if (rs.next()){
                Field[] listField = this.clazz.getDeclaredFields();
                for(int i = 0; i< listField.length; i++){
                    System.out.println(listField[i].getName() +": "+ rs.getString(i+1));
                }
            }
            System.out.println(sb.toString());
            System.out.println("Tìm kiếm thành công");
        } catch (SQLException e){
            //e.printStackTrace();
            System.out.println("Tìm kiếm không thành công");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BigModel<Student> bms = new BigModel<>(Student.class);
        BigModel<Customer> bmc = new BigModel<>(Customer.class);
        BigModel<Employee> bme = new BigModel<Employee>(Employee.class);
        BigModel<Animal> bma = new BigModel<Animal>(Animal.class);
        try {
            Employee e1 = new Employee(18, "Minh1", "Cau Giay", "AC1");
            Student st1 = new Student("Duc Minh", "Cau Giay", "1234", "ST123");
            Employee e2 = new Employee(20, "Huong", "Hung Yen", "E2");
            Employee e3 = new Employee(20, "Minh3", "Ha Noi", "AC3");
            Animal a1 = new Animal("Lu", 5, "Dogs", "AN1");
            Animal a2 = new Animal("Meow", 5, "Cat", "AN2");
            Animal a3 = new Animal("Jerry", 2, "Mouse", "AN3");
            //bme.insert(e3);
            bms.searchByKeyField("ST1");
            bme.searchByKeyField("E2");
            bma.update("price", "100", "AN3");
            bma.searchByKeyField("AN1");
            bma.insert(a3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
