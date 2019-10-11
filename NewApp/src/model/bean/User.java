/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Minh_PC
 */
public class User {
    private int idUser;
    private String userName;
    private String passWord;
    private String fullName;
    private boolean kichHoat;

   
    public User() {
    
    }

    public User(String userName, String passWord, String fullName, boolean kichHoat) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.kichHoat = kichHoat;
    }


    public User(int idUser, String userName, String passWord, String fullName, boolean kichHoat) {
        this.idUser = idUser;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.kichHoat = kichHoat;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isKichHoat() {
        return kichHoat;
    }

    public void setKichHoat(boolean kichHoat) {
        this.kichHoat = kichHoat;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", userName=" + userName + ", passWord=" + passWord + ", fullName=" + fullName + ", kichHoat=" + kichHoat + '}';
    }
    
    
    
    
    
}
