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
public class Category {
    private int idCat;
    private String name;

    public Category() {
    }

    public Category(int idCat, String name) {
        this.idCat = idCat;
        this.name = name;
    }

    public Category(String name) {
       this.name = name;
    }

    
    
    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name ;
    }
     
}
