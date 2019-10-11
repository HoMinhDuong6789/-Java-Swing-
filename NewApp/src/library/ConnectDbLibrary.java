/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


/**
 *
 * @author Minh_PC
 */
public class ConnectDbLibrary {
   
            
    
   public static Connection getConnectMySQL() {
		Connection conn = null;
		// String dbName = "bkit_khoa03_buoi03_quanlydiemthi";
		/*Properties props = ReadPropLibrary.getProperties();
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String url = props.getProperty("url");
                Properties props = ReadPropLibrary.getProperties();
		*/
                String user ="root";
		String password ="";
		String url ="jdbc:mysql://localhost:3306/NewsApp?useUnicode=true&CharacterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
   
  
}
