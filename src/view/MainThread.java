package view;

import BigModel.BigModel;
import entity.Customer;
import entity.Student;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainThread {

    public static void main(String[] args) throws Exception {
        ArrayList<Student> listStudent = new ArrayList<>();

        //Genetic type.
        BigModel<Student> studentModel = new BigModel<>(Student.class);
        BigModel<Customer> customerModel = new BigModel<>(Customer.class);
        studentModel.insert(new Student("Hollo", "Aloha", "0012", "as"));
    }
}
