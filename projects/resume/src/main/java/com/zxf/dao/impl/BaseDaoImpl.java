package com.zxf.dao.impl;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zxf.dao.BaseDao;
import com.zxf.util.Strings;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    protected static JdbcTemplate tpl;
    static {
        try (InputStream is = WebsiteDaoImpl.class.getClassLoader().getResourceAsStream("druid.properties")){
            // 获取连接池
            Properties properties = new Properties();
            properties.load(is);
            DataSource ds = DruidDataSourceFactory.createDataSource(properties);
            // 创建tpl
            tpl = new JdbcTemplate(ds);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected String table(){
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class beanCls = (Class) type.getActualTypeArguments()[0];
        return Strings.underlineClass(beanCls.getSimpleName());
    }

    @Override
    public boolean remove(Integer id) {
        String sql = "DELETE FROM " + table() + " WHERE id = ?";
        return tpl.update(sql, id) > 0;
    }

    @Override
    public boolean remove(List<Integer> ids){
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(table()).append(" WHERE id in (");
        List<Object> args = new ArrayList<>();
        for (Integer id : ids){
            args.add(id);
            sql.append("?, ");
        }
        sql.replace(sql.length() - 2, sql.length(), ")");
        return tpl.update(sql.toString(), args.toArray()) > 0;
    }

    /**
     * 获取统计值e
     * */
    public Integer count() {
        String sql = "INSERT COUNT(*) FROM " + table();
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Integer.class));
    }
}
