/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import basicobj.Account;
import basicobj.Order;
import basicobj.OrderDetail;
import basicobj.Plant;
import dbacess.AccountDao;
import dbacess.OrderDao;
import dbacess.PlantDao;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author thinh
 */
public class testFunc {

    public static void main(String[] args) throws Exception {
//        ArrayList<Plant> list = new ArrayList<>();
//        for (int i = 0; i < PlantDao.getPlants("van", "byname").size(); i++ ) {
//            System.out.println("name:" + PlantDao.getPlants("van", "byname").get(i).getDescription());
//        }
//        System.out.println(list.isEmpty());

//        Account acc = AccountDao.getAccount("thinh@gmail.com", "123");
//        System.out.println(acc == null);
//          ArrayList<Order> list = OrderDao.getOrders("hoa@gmail.com");
//          for (Order order : list) {
//              System.out.println(order.getAccID());
//        }
//            Plant plant = PlantDao.getPlant(2);
//            System.out.println(plant.getName());

        ArrayList<Order> list = OrderDao.getOrders("hoa@gmail.com");
        for (Order order : list) {
            Date todayDate = (Date) new SimpleDateFormat("MM-dd-yyyy").parse(order.getOrderDate());
            System.out.println(order.getOrderDate());
        }
    }
}
