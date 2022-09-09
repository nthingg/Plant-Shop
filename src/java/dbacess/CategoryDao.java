/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbacess;

import basicobj.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author thinh
 */
public class CategoryDao {

    public static ArrayList<Category> getAllCategories() throws Exception {
        ArrayList<Category> list = new ArrayList<>();
        //step 1: tạo connection 
        Connection cn = mylib.DBUtils.makeConnection();
        if (cn != null) {
            //Step 2: viết sql 
            String sql = "select CateID, CateName\n"
                    + "from Categories";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            //Step 3: xử lý kết quả step2 
            if (table != null) {
                while (table.next()) {
                    int CateID = table.getInt("CateID");
                    String CateName = table.getString("CateName");
                    Category cate = new Category(CateID, CateName);
                    list.add(cate);
                }// hết while 
            }//hết if
            cn.close();
        }
        return list;
    }

    public static Category getCategory(int cid) throws Exception {
        Category cate = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select CateName\n"
                    + "from Categories\n"
                    + "where CateID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setInt(1, cid);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                String cateName = table.getString("CateName");
                cate = new Category(cid, cateName);
            }
            cn.close();
        }
        return cate;
    }

    public static int getNewestCateId() throws Exception {
        int id = 0;
        Connection cn = DBUtils.makeConnection();

        String sql = "select top 1 CateID from Categories order by CateID desc";
        PreparedStatement pst = cn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs != null && rs.next()) {
            id = rs.getInt("CateID");
        }
        return id;
    }

    public static boolean updateCategory(String cateID, String cateName) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            if (cateName != null && !cateName.equals("")) {
                int cid = Integer.parseInt(cateID);
                String sql = "update Categories\n"
                        + "set CateName = ?\n"
                        + "where CateID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, cateName);
                pst.setInt(2, cid);
                result = pst.executeUpdate();
            } else {
                result = 1;
            }
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean insertCate(String cateName) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert into Categories (CateName) values (?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, cateName);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}
