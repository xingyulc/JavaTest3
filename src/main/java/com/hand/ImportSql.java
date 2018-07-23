package com.hand;


import jdk.nashorn.api.scripting.ScriptUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ImportSql {
    public static void main(String[] args) {
        String username = "root";//用户名
        String password = "root";//密码
        BufferedReader bufferedReader;
        String host = "jdbc:mysql://192.168.99.100:3306";//导入的目标数据库所在的主机
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(host,username,password);
            Statement statement = conn.createStatement();
             bufferedReader = new BufferedReader(new FileReader(
                    "docker/mysql/sakila-schema.sql"));
            String str = bufferedReader.readLine();
            String sql ="";
            while (str != null) {
                // 去掉一些注释，和一些没用的字符
                if (!str.startsWith("#") && !str.startsWith("/*")&&!str.startsWith("--")
                        && !str.startsWith("–") && !str.startsWith("\n")){
                    sql+=str;
                }

                str = bufferedReader.readLine();
            }
            System.out.println(sql);
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
