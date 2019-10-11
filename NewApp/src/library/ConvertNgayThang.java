/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Minh_PC
 */
public class ConvertNgayThang {
    public static Timestamp connvertDatetoTimeStamp(Date date){
            Timestamp date_create= new Timestamp(date.getTime());
            return date_create;
    }
}
