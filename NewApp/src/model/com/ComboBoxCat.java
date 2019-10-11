/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.com;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import model.bean.Category;
import model.dao.CategoryDAO;

/**
 *
 * @author Minh_PC
 */
public class ComboBoxCat implements ComboBoxModel {

    private Category catSelected;
    private CategoryDAO catDAO;
    private List<Category> allItem;

    public ComboBoxCat(boolean isSearch, Category catSelected) {
        catDAO = new CategoryDAO();
        allItem = catDAO.getList();
        if (isSearch) {
            //seach
            allItem.add(0, new Category(0, "-----------Chọn Danh Mục---------"));

        }
        if (catSelected == null) {//luc dau moi load len
            catSelected = allItem.get(0);
        }
        this.catSelected = catSelected;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.catSelected = (Category) anItem ;
    }

    @Override
    public Object getSelectedItem() {
        return this.catSelected;
    }

    @Override
    public int getSize() {
        return allItem.size();
    }

    @Override
    public Object getElementAt(int index) {
        return allItem.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

}
