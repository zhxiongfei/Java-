package com.zxf;

import java.sql.*;

public class Main{

    private static final String URL = "jdbc:mysql://localhost:3306/xmg";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static void test1(){
        try{
            // 利用 DriverManager 创建数据库连接
            // 利用 connection 创建语句
            try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                Statement statement = connection.createStatement();){
                // 4. 利用 statement 对象执行 sql语句
                int cnt = statement.executeUpdate("UPDATE student SET age = 200 WHERE id=1");
                System.out.println(cnt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 查询
    static void test2(){
        try {
            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 Statement statement = connection.createStatement();) {
                String sql = "SELECT id myId,name myName,age myAge FROM student";

                ResultSet res = statement.executeQuery(sql);
                // 指向第一条记录
                while (res.next()) {
                    System.out.println(res.getString("myId"));
                    System.out.println(res.getString("myName"));
                    System.out.println(res.getString("myAge"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 模拟登录
    private static void login(String username, String password) throws Exception{

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();) {
            String sql = "SELECT * FROM user WHERE username = '" + username + "'AND password = '" + password + "'";

            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败，用户名或密码不正确");
            }
        }
    }

    private static void login2(String username, String password) throws Exception {
        /*
        SELECT * FROM user WHERE username = '' AND password = '\' OR \'1\' = \'1'
        SELECT * FROM user WHERE username = "" AND password = "' OR '1' = '1"
         */
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // 3：编译、解析、优化
            // 设置参数值
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 执行SQL语句
            ResultSet rs = pstmt.executeQuery(); // 执行
            if (rs.next()) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败，用户名或密码不正确");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        login("123","455");
    }
}
