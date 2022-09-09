/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbacess;

import basicobj.Order;
import basicobj.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import mylib.DBUtils;

/**
 *
 * @author thinh
 */
public class OrderDao {
    //hàm để lấy plant

    public static ArrayList<Order> getOrders(String email) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID, OrdDate, shipdate, Orders.status as 'status', Accounts.accID as 'accID'\n"
                    + "from Accounts join Orders on Accounts.accID = Orders.AccID\n"
                    + "where Accounts.email like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + email + "%");
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int OrderID = table.getInt("OrderID");
                    String OrdDate = table.getString("OrdDate");
                    String shipdate = table.getString("shipdate");
                    int status = table.getInt("status");
                    int accID = table.getInt("accID");
                    Order order = new Order(OrderID, OrdDate, shipdate, status, accID);
                    list.add(order);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getOrderByStatus(String status) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID, OrdDate, shipdate, status, AccID\n"
                    + "from Orders\n"
                    + "where status = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, status);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int OrderID = table.getInt("OrderID");
                    String OrdDate = table.getString("OrdDate");
                    String shipdate = table.getString("shipdate");
                    int newstatus = table.getInt("status");
                    int accID = table.getInt("AccID");
                    Order order = new Order(OrderID, OrdDate, shipdate, newstatus, accID);
                    list.add(order);
                }
            }
            cn.close();
        }
        return list;
    }

    public static boolean changeOrderShipdate(String orderID, String currentDate) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String dt = currentDate;  // Start date
            String sql = "update Orders\n"
                        + "set shipdate =? \n"
                        + "where OrderID =? ";
            if (currentDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(dt));
                c.add(Calendar.DATE, 7);  // number of days to add
                dt = sdf.format(c.getTime());  // dt is now the new date
            }
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dt);
            pst.setString(2, orderID);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean changeOrderStatus(String orderID, int newStatus) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update Orders\n"
                    + "set status =? \n"
                    + "where OrderID =? ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, newStatus);
            pst.setString(2, orderID);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<OrderDetail> getOrderDetails(int orderID) throws Exception {
        ArrayList<OrderDetail> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select DetailId, OrderID, PID, PName, price, imgPath, quantity\n"
                    + "from OrderDetails, Plants\n"
                    + "where OrderID = ? and OrderDetails.FID = Plants.PID";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, orderID);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int DetailId = table.getInt("DetailId");
                    int OrderID = table.getInt("OrderID");
                    int PlantId = table.getInt("PID");
                    String PlantName = table.getString("PName");
                    int price = table.getInt("price");
                    String imgPath = table.getString("imgPath");
                    int quantity = table.getInt("quantity");
                    OrderDetail order = new OrderDetail(DetailId, OrderID, PlantId, PlantName, price, imgPath, quantity);
                    list.add(order);
                }
            }
            cn.close();
        }
        return list;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                //get acc by email
                String sql = "select accID from Accounts where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                //insert a new order
                Date date = new Date(System.currentTimeMillis());
                sql = "insert Orders(OrdDate, status, AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, date);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                //get the newest added order = biggest orderid
                sql = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values (?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
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

    public static ArrayList<Order> getAllOrders() throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        //step 1: tạo connection 
        Connection cn = mylib.DBUtils.makeConnection();
        if (cn != null) {
            //Step 2: viết sql 
            String sql = "select OrderID, OrdDate, shipdate, status, AccID\n"
                    + "from Orders";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            //Step 3: xử lý kết quả step2 
            if (table != null) {
                while (table.next()) {
                    int OrderID = table.getInt("OrderID");
                    String OrdDate = table.getString("OrdDate");
                    String shipDate = table.getString("shipDate");
                    int status = table.getInt("status");
                    int AccID = table.getInt("AccID");
                    Order order = new Order(OrderID, OrdDate, shipDate, status, AccID);
                    list.add(order);
                }// hết while 
            }//hết if
            cn.close();
        }
        return list;
    }

}
