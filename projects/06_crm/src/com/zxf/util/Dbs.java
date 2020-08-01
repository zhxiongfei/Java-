package com.zxf.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Dbs {

    private static DataSource ds;
    static {    // IO
        try (InputStream is = Dbs.class.getClassLoader().getResourceAsStream("druid.properties")){
            Properties properties = new Properties();
            properties.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 执行 DDL, DML语句
     * @param sql sql语句
     * @param args 可变参数
     * @return 影响的记录数
     */
    public static int update(String sql, Object...args){
        try {
            Connection conn = ds.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // 设置参数
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 1,args[i]);
                }
                return pstmt.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static <T> List<T> query(String sql,RowMapper<T> mapper, Object...args){
        if (mapper == null) return null;

        try {
            Connection conn = ds.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // 设置参数
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 1,args[i]);
                }
                // 执行
                ResultSet res = pstmt.executeQuery();
                List<T> array = new ArrayList<>();
                int row = 0;
                while (res.next()){
                    // res -> bean
                    array.add(mapper.map(res, row++));
                }
                res.close();
                return array;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用来执行每一行数据的映射
     * @param <T>
     */
    public interface RowMapper<T>{
        T map(ResultSet res, int row) throws Exception;
    }
}
