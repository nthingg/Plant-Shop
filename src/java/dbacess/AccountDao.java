/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbacess;

import basicobj.Account;
import basicobj.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author nnha3
 */
public class AccountDao {

    //input: email, password
    //out: tra 1 account
    public static Account getAccount(String email, String password) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID, email, password, fullname, phone, status, role\n"
                    + "from dbo.Accounts\n"
                    + "where status = 1 and email =? and password =? COLLATE Latin1_General_CS_AS";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accountID = table.getInt("accID");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accountID, email, password, fullname, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }
    
    public static ArrayList<Account> getAccountByEmail(String email) throws Exception {
        ArrayList<Account> list = new ArrayList<>();
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID, email, password, fullname, phone, status, role\n"
                    + "from Accounts\n"
                    + "where email like ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setString(1, "%" + email + "%");
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accountID = table.getInt("accID");
                String fullemail = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accountID, fullemail, password, fullname, phone, status, role);
                list.add(acc);
            }
            cn.close();
        }
        return list;
    }
    
    public static Account getAccountEmail(String email) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID, email, password, fullname, phone, status, role\n"
                    + "from Accounts\n"
                    + "where email like ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setString(1, email);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accountID = table.getInt("accID");
                String fullemail = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accountID, fullemail, password, fullname, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }

    public static Account getAccount(String token) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID, email, password, fullname, phone, status, role\n"
                    + "from dbo.Accounts\n"
                    + "where token =?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setString(1, token);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accountID = table.getInt("accID");
                String email = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accountID, email, password, fullname, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }

    //hàm này dùng để lấy tất cả account trong bang Accounts
    public static ArrayList<Account> getAllAccounts() throws Exception {
        ArrayList<Account> list = new ArrayList<>();
        //step 1: tạo connection 
        Connection cn = mylib.DBUtils.makeConnection();
        if (cn != null) {
            //Step 2: viết sql 
            String sql = "select accID, email, password, fullname, phone, status, role from Accounts";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //Step 3: xử lý kết quả step2 
            if (rs != null) {
                while (rs.next()) {
                    int accid = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    list.add(new Account(accid, email, password, fullname, phone, status, role));
                }// hết while 
            }//hết if
            cn.close();
        }
        return list;
    }

    public static boolean updateAccountStatus(String email, int status) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update Accounts\n"
                    + "set status =? \n"
                    + "where email =? ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setString(2, email);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateAccountToken(String email, String token) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update Accounts\n"
                    + "set token =? \n"
                    + "where email =? ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, token);
            pst.setString(2, email);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateAccount(String email, String newPass, String newFullName, String newPhone) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update Accounts\n"
                    + "set password =?, fullname =?, phone =?\n"
                    + "where email =?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newPass);
            pst.setString(2, newFullName);
            pst.setString(3, newPhone);
            pst.setString(4, email);
            result = pst.executeUpdate();
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateAccount(String email, String fullname, String phone) {
        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                //get acc by email

                if (!fullname.isEmpty() && !fullname.equals("")) {
                    String sql = "update Accounts\n"
                            + "set fullname = ?\n"
                            + "where email = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, fullname);
                    pst.setString(2, email);
                    pst.executeUpdate();
                }

                if (!phone.isEmpty() && !phone.equals("")) {
                    String sql = "update Accounts\n"
                            + "set phone = ?\n"
                            + "where email = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, phone);
                    pst.setString(2, email);
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
    //ham dung de insert 1 account moi vao list
    //input: 
    //output: 1 hoac 0
    public static boolean insertAccount(String email, String password, String fullname, String phone, int status, int role) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert into Accounts (email, password, fullname, phone, status, role)\n"
                    + "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, fullname);
            pst.setString(4, phone);
            pst.setInt(5, status);
            pst.setInt(6, role);
            result = pst.executeUpdate();
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

}
