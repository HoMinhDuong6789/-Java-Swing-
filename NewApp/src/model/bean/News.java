/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Timestamp;

/**
 *
 * @author Minh_PC
 */
public class News {
    private int id;
    private String name;
    private String previiew_text;
    private String picture;
    private Timestamp date_create;
    private String detail_text;
    
    private int id_cat;
    private String cname;

    public News() {
    
    }

    public News(int id) {
        this.id = id;
    }
    

    public News(String name, String previiew_text, String detail_text, int id_cat, String picture, Timestamp date_create) {
        this.name = name;
        this.previiew_text = previiew_text;
        this.picture = picture;
        this.date_create = date_create;
        this.detail_text = detail_text;
        this.id_cat = id_cat;
        this.cname = cname;
    }
    
    public News(int id, String name, String previiew_text, String detail_text, int id_cat, String picture, Timestamp date_create) {
        this.id=id;
        this.name = name;
        this.previiew_text = previiew_text;
        this.picture = picture;
        this.date_create = date_create;
        this.detail_text = detail_text;
        this.id_cat = id_cat;
    }

    public News(int id, String name, String previiew_text, String detail_text, int id_cat, String picture, Timestamp date_create, String cname) {
        this.id=id;
        this.name = name;
        this.previiew_text = previiew_text;
        this.picture = picture;
        this.date_create = date_create;
        this.detail_text = detail_text;
        this.id_cat = id_cat;
        this.cname = cname;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreviiew_text() {
        return previiew_text;
    }

    public void setPreviiew_text(String previiew_text) {
        this.previiew_text = previiew_text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Timestamp getDate_create() {
        return date_create;
    }

    public void setDate_create(Timestamp date_create) {
        this.date_create = date_create;
    }

    public String getDetail_text() {
        return detail_text;
    }

    public void setDetail_text(String detail_text) {
        this.detail_text = detail_text;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", name=" + name + ", previiew_text=" + previiew_text + ", picture=" + picture + ", date_create=" + date_create + ", detail_text=" + detail_text + ", id_cat=" + id_cat + ", cname=" + cname + '}';
    }
    
    
    
    
}
