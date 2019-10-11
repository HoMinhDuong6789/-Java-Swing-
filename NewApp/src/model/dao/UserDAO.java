/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.ConnectDbLibrary;
import library.StringLibrary;
import model.bean.User;

/**
 *
 * @author Minh_PC
 */
public class UserDAO {

    private ConnectDbLibrary lConnect = new ConnectDbLibrary();
    private Connection conn = lConnect.getConnectMySQL();
    private PreparedStatement pst;
    private ResultSet rs;

    public UserDAO() {

    }

    public List<User> getList() {
        ArrayList<User> alItem = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
                alItem.add(user);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alItem;

    }

    public int addItem(User objCat) {
        int last_id = 0;
        String sql = "INSERT INTO users(username,passwordd,fullname,active) VALUES(?,?,?,?)";
        try {
            //tra ve id cua no
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, objCat.getUserName());
            pst.setString(2, objCat.getPassWord());
            pst.setString(3, objCat.getFullName());
            pst.setBoolean(4, objCat.isKichHoat());

            pst.executeUpdate();
            // khi thay doi du lieu executeUpdate
            rs = pst.getGeneratedKeys();
            while (rs.next()) {
                last_id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return last_id;

    }

    public void delItem(User ojbCat) {
        String sql = "DELETE FROM users WHERE username=?";
        try {
            //tra ve id cua no
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, ojbCat.getUserName());
            pst.executeUpdate();
            // khi thay doi du lieu executeUpdate
            rs = pst.getGeneratedKeys();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EditItem(User ojbUser) {
        String sql = "UPDATE users SET username=?, passwordd=?, fullname=?, active=? WHERE id_user=?";
        try {
            //tra ve id cua no
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, ojbUser.getUserName());
            pst.setString(2, ojbUser.getPassWord());
            pst.setString(3, ojbUser.getFullName());
            pst.setBoolean(4, ojbUser.isKichHoat());
            pst.setInt(5, ojbUser.getIdUser());
            pst.executeUpdate();
            // khi thay doi du lieu executeUpdate
            rs = pst.getGeneratedKeys();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User checklogin(String username, String pass) {
       User objUser=null;
        String sql = "SELECT * FROM users WHERE username=? && passwordd=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, StringLibrary.MD5(pass));
            rs = pst.executeQuery();
           // neu obj khac null ton tia nguoi dung nay
           if(rs.next()){
               objUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
           }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objUser;  
    }

}
