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
import model.bean.News;

/**
 *
 * @author Minh_PC
 */
public class NewsDAO {

    private ConnectDbLibrary lConnect = new ConnectDbLibrary();
    private Connection conn = lConnect.getConnectMySQL();
    private PreparedStatement pst;
    private ResultSet rs;

    public NewsDAO() {

    }

    public List<News> getList() {
        ArrayList<News> alItem = new ArrayList<>();
        String sql = "SELECT *,c.name FROM news "
                + "INNER JOIN category AS c USING(id_cat) ";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                //  News news1= new News(id,name,pre,det,idcat,picture,date);

                News news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"), rs.getTimestamp("date_create"), rs.getString("c.name"));
                alItem.add(news);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alItem;
    }

    /* public static void main(String[] args) {
        NewsDAO  dAO= new NewsDAO();
       List<News> newss= new ArrayList<>();
       newss=dAO.getList();
        for (News news : newss) {
            System.out.println(news.toString());
        }
    
    }
     */
    public int addItem(News objCat) {
        int last_id = 0;
        String sql = "INSERT INTO news(name,preview_text,detail_text,id_cat,picture) VALUES(?,?,?,?,?)";
        try {
            //tra ve id cua no
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, objCat.getName());
            pst.setString(2, objCat.getPreviiew_text());
            pst.setString(3, objCat.getDetail_text());
            pst.setInt(4, objCat.getId_cat());
            pst.setString(5, objCat.getPicture());
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

    public int delItem(News objNews) {
        String sql = "DELETE FROM news WHERE id_news=?";
        int last_id = 0;
        //String sql = "INSERT INTO news(name,preview_text,detail_text,id_cat,picture) VALUES(?,?,?,?,?)";
        try {
            //tra ve id cua no
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, objNews.getId());
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

    public int EditItem(News ojbNews) {
        int result = 1;
        String picture = "";
        if (!"".equals(ojbNews.getPicture())) {
            picture = ojbNews.getPicture();
        }
        String sql
                = "UPDATE news SET name=?, preview_text=?, detail_text=?,id_cat=?,picture=?,date_create=? WHERE id_news=?";
      
        try {
            //tra ve id cua no
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, ojbNews.getName());
            pst.setString(2, ojbNews.getPreviiew_text());
            pst.setString(3, ojbNews.getDetail_text());
            pst.setInt(4, ojbNews.getId_cat());
            pst.setString(5, ojbNews.getPicture());
            pst.setTimestamp(6, ojbNews.getDate_create());
            pst.setInt(7, ojbNews.getId_cat());
            result = pst.executeUpdate();
            // khi thay doi du lieu executeUpdate
            rs = pst.getGeneratedKeys();

        } catch (SQLException ex) {
            ex.getMessage();
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
