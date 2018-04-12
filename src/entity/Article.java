/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import annotation.FieldToModify;
import annotation.FieldValueFromGetterMethod;
import annotation.InsertField;
import annotation.SearchField;
import annotation.UpdateField;

/**
 *
 * @author nguye
 */
public class Article {
    @FieldToModify
    private int id;
    @InsertField
    private String url;
    @InsertField
    @SearchField
    private String title;
    @InsertField
    private String content;
    private int status = 0;
    
    @FieldValueFromGetterMethod
    public String getUrl() {
        return url;
    }
    
    @UpdateField
    public void setUrl(String url) {
        this.url = url;
    }
    
    @FieldValueFromGetterMethod
    public String getTitle() {
        return title;
    }
    
    @UpdateField
    public void setTitle(String title) {
        this.title = title;
    }
    
    @FieldValueFromGetterMethod
    public String getContent() {
        return content;
    }
    
    @UpdateField
    public void setContent(String content) {
        this.content = content;
    }
    
    
    public int getStatus() {
        return status;
    }
    
    @UpdateField
    public void setStatus(int status) {
        this.status = status;
    }

    public Article(String url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }
    
    
    
    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", url=" + url + ", title=" + title + ", status=" + status + '}';
    }
    
    
}
