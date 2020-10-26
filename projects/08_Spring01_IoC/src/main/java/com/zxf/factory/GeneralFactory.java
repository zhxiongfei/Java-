package com.zxf.factory;

import com.zxf.dao.PersonDao;
import com.zxf.service.PersonService;

import java.io.InputStream;
import java.util.Properties;

public class GeneralFactory {

    private static Properties properties;
    static {
        try (InputStream is = GeneralFactory.class.getClassLoader().getResourceAsStream("factory.properties")){
            properties = new Properties();
            properties.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static <T> T get(String name){
        try{
            String clsName = properties.getProperty(name);
            Class cls = Class.forName(clsName);
            Object object = cls.newInstance();
            return (T)object;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    public static PersonService getService(){
//        return get("service");
//    }
//
//    public static PersonDao getDao(){
//        return get("dao");
//    }

}
