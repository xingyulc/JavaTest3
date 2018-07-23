package com.hand;

import org.apache.tools.ant.taskdefs.SQLExec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException, SQLException {
        String username = "root";//用户名
        String password = "root";//密码
        String host = "jdbc:mysql://192.168.99.100:3306/sakila?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimeZone=GMT";
        Connection conn = DriverManager.getConnection(host,username,password);
        getCity(conn);
        getFilm(conn);
        conn.close();
    }

    public static void getCity( Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Statement statement = conn.createStatement();
//        String countryId = scanner.next();
        String countryId = "1";
        String selSql = "SELECT country,city.city_id FROM country\n" +
                "LEFT JOIN city on country.country_id = city.country_id\n" +
                "WHERE country.country_id = '"+countryId+"'";
        ResultSet rs =  statement.executeQuery(selSql);
        System.out.println("Country ID：");
        System.out.println("城市 ID | 城市名称");
        while (rs.next()){
            System.out.println(rs.getString("city_id")+"|"+rs.getString("country"));
        }
        statement.close();
    }

    public static void  getFilm(Connection conn) throws SQLException {
        System.out.println("Customer ID");
        Scanner scanner = new Scanner(System.in);
//        String customerId = scanner.next();
        String customerId = "1";
        String sql = "SELECT film.film_id,film.title,rental.rental_date FROM film\n" +
                "left JOIN inventory on film.film_id = inventory.film_id\n" +
                "LEFT JOIN rental on inventory.inventory_id = rental.inventory_id\n" +
                "WHERE rental.customer_id = '"+customerId+"' \n" +
                "ORDER BY rental.rental_date DESC";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        System.out.println("Film ID | Film 名称 | 租用时间");
        while (rs.next()){
            System.out.println(rs.getString("film_id")+"|"+
            rs.getString("title")+"|"+rs.getString("rental_date")
            );
        }
        statement.close();
    }
}

