/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbacess;

import basicobj.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author thinh
 */
public class PlantDao {

    //hàm để lấy plant
    public static ArrayList<Plant> getPlants(String name, String searchby) throws Exception {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "";
            if (searchby.equalsIgnoreCase("byname")) {
                sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName from Plants join Categories on Plants.CateID = Categories.CateID where Plants.PName like ?";
            } else {
                sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName from Plants join Categories on Plants.CateID = Categories.CateID where CateName like ?";
            }
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + name + "%");
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int PID = table.getInt("PID");
                    String PName = table.getString("PName");
                    int price = table.getInt("price");
                    String imgPath = table.getString("imgPath");
                    String description = table.getString("description");
                    int status = table.getInt("status");
                    int CateID = table.getInt("CateID");
                    String CateName = table.getString("CateName");
                    Plant plant = new Plant(PID, PName, price, imgPath, description, status, CateID, CateName);
                    list.add(plant);
                }
            }
            cn.close();
        }
        return list;
    }

    //hàm này dùng để lấy tất cả account trong bang Accounts
    public static ArrayList<Plant> getAllPlants() throws Exception {
        ArrayList<Plant> list = new ArrayList<>();
        //step 1: tạo connection 
        Connection cn = mylib.DBUtils.makeConnection();
        if (cn != null) {
            //Step 2: viết sql 
            String sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName from Plants join Categories on Plants.CateID = Categories.CateID ";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            //Step 3: xử lý kết quả step2 
            if (table != null) {
                while (table.next()) {
                    int PID = table.getInt("PID");
                    String PName = table.getString("PName");
                    int price = table.getInt("price");
                    String imgPath = table.getString("imgPath");
                    String description = table.getString("description");
                    int status = table.getInt("status");
                    int CateID = table.getInt("CateID");
                    String CateName = table.getString("CateName");
                    Plant plant = new Plant(PID, PName, price, imgPath, description, status, CateID, CateName);
                    list.add(plant);
                }// hết while 
            }//hết if
            cn.close();
        }
        return list;
    }

    public static Plant getPlant(int pid) throws Exception {
        Plant plant = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName from Plants join Categories on Plants.CateID = Categories.CateID where PID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setInt(1, pid);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                String PName = table.getString("PName");
                int price = table.getInt("price");
                String imgPath = table.getString("imgPath");
                String description = table.getString("description");
                int status = table.getInt("status");
                int CateID = table.getInt("CateID");
                String CateName = table.getString("CateName");
                plant = new Plant(pid, PName, price, imgPath, description, status, CateID, CateName);
            }
            cn.close();
        }
        return plant;
    }

    public static boolean updatePlant(String pid, String plantName, String plantPrice, String plantImg,
            String plantDescription, String plantStatus, String cateID) {
        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int plantId = Integer.parseInt(pid);
                cn.setAutoCommit(false);
                //get acc by email

                if (!plantName.isEmpty()) {
                    String sql = "update Plants\n"
                            + "set PName = ?\n"
                            + "where PID = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, plantName);
                    pst.setInt(2, plantId);
                    pst.executeUpdate();
                }

                if (!plantPrice.isEmpty()) {
                    int price = Integer.parseInt(plantPrice);
                    String sql = "update Plants\n"
                            + "set price = ?\n"
                            + "where PID = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setInt(1, price);
                    pst.setInt(2, plantId);
                    pst.executeUpdate();
                }

                if (!plantImg.isEmpty()) {
                    String sql = "update Plants\n"
                            + "set imgPath = ?\n"
                            + "where PID = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, plantImg);
                    pst.setInt(2, plantId);
                    pst.executeUpdate();
                }

                if (!plantDescription.isEmpty()) {
                    String sql = "update Plants\n"
                            + "set description = ?\n"
                            + "where PID = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, plantDescription);
                    pst.setInt(2, plantId);
                    pst.executeUpdate();
                }

                if (!plantStatus.isEmpty()) {
                    int status = Integer.parseInt(plantStatus);
                    String sql = "update Plants\n"
                            + "set status = ?\n"
                            + "where PID = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setInt(1, status);
                    pst.setInt(2, plantId);
                    pst.executeUpdate();
                }

                if (!cateID.isEmpty()) {
                    int categoryID = Integer.parseInt(cateID);
                    String sql = "update Plants\n"
                            + "set CateID = ?\n"
                            + "where PID = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setInt(1, categoryID);
                    pst.setInt(2, plantId);
                    pst.executeUpdate();
                }

                cn.commit();
                cn.setAutoCommit(true);
                return true;
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean insertPlant(String plantName, String plantPrice, String plantImg, String plantDescription, String plantStatus, String cateID) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            int price = Integer.parseInt(plantPrice);
            int status = Integer.parseInt(plantStatus);
            int cateid = Integer.parseInt(cateID);
            String sql = "insert into Plants (PName, price, imgPath, description, status, CateID)\n"
                    + "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, plantName);
            pst.setInt(2, price);
            pst.setString(3, plantImg);
            pst.setString(4, plantDescription);
            pst.setInt(5, status);
            pst.setInt(6, cateid);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    //get the newest added order = biggest orderid
    public static int getNewestPlantId() throws Exception {
        int id = 0;
        Connection cn = DBUtils.makeConnection();

        String sql = "select top 1 PID from Plants order by PID desc";
        PreparedStatement pst = cn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs != null && rs.next()) {
            id = rs.getInt("PID");
        }
        return id;
    }

}
