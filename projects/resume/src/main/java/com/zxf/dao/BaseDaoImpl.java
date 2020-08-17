package com.zxf.dao;

import com.zxf.util.Dbs;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDaoImpl<T> implements BaseDao<T>{

    protected abstract String table();

    @Override
    public boolean remove(Integer id) {
        String sql = "DELETE FROM " + table() + " WHERE id = ?";
        return Dbs.getTpl().update(sql, id) > 0;
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
        return Dbs.getTpl().update(sql.toString(), args.toArray()) > 0;
    }

    /**
     * 获取统计值e
     * */
    public Integer count() {
        String sql = "INSERT COUNT(*) FROM " + table();
        return Dbs.getTpl().queryForObject(sql, new BeanPropertyRowMapper<>(Integer.class));
    }
}
