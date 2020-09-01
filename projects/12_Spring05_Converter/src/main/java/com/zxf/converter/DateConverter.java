package com.zxf.converter;

import org.springframework.core.convert.converter.Converter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String,Date> {

    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public Date convert(String s) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        try{
            return fmt.parse(s);
        }catch (Exception e){
            e.printStackTrace();;
            return null;
        }
    }
}
