/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Minh_PC
 */
public class ReadPropLibrary {

    public static Properties getProperties() {
        Properties props = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream("D:\\_JavaCode\\Swing\\NewApp\\config.properties");
            props.load(is);
        } catch (Exception e) {
            System.out.println(e);
            e.getMessage();
        }

        return props;
    }
}
