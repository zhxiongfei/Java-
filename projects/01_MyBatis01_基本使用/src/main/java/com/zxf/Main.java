package com.zxf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) throws Exception{
        Connection connection = DriverManager.getConnection("");

        // 开启事物 : 不要默认提交
        connection.setAutoCommit(false);

        try {
            PreparedStatement statement = connection.prepareStatement("");
            statement.executeUpdate();

            PreparedStatement statement1 = connection.prepareStatement("");
            statement1.executeUpdate();

            // 结束事物 : 提交
            connection.commit();
        }catch (Exception e){

            e.printStackTrace();

            // 结束事物 ： 回滚
            if (connection != null) connection.rollback();
        }
    }
}
