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
import model.bean.Category;

/**
 *
 * @author Minh_PC
 */
public class CategoryDAO {

    private ConnectDbLibrary lConnect = new ConnectDbLibrary();
    private Connection conn = lConnect.getConnectMySQL();
    private PreparedStatement pst;
    private ResultSet rs;

    public CategoryDAO() {

    }

    public List<Category> getList() {
        ArrayList<Category> alItem = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("id_cat"), rs.getString("name"));
                alItem.add(category);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alItem;

    }

    public int addItem(Category objCat) {
        int last_id=0;
        String sql="INSERT INTO category(name) VALUES(?)";
        try {
            //tra ve id cua no
            pst= conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1,objCat.getName());
            pst.executeUpdate();
            // khi thay doi du lieu executeUpdate
            rs=pst.getGeneratedKeys();
            while(rs.next()){
                last_id=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return last_id;
               
    }

    public void delItem(Category ojbCat) {
        String sql="DELETE FROM category WHERE id_cat=?";
        try {
            //tra ve id cua no
            pst= conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1,ojbCat.getIdCat());
            pst.executeUpdate();
            // khi thay doi du lieu executeUpdate
            rs=pst.getGeneratedKeys();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EditItem(Category ojbCategory) {
         String sql="UPDATE category SET name=? WHERE id_cat=?";
        try {
            //tra ve id cua no
            pst= conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, ojbCategory.getName());
            pst.setInt(2,ojbCategory.getIdCat());
            
            pst.executeUpdate();
            // khi thay doi du lieu executeUpdate
            rs=pst.getGeneratedKeys();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
