package com.zxf.bean.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateBean extends BaseBean {

    private Date beginDay;
    private Date endDay;

    public Date getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(Date beginDay) {
        this.beginDay = beginDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    @JsonIgnore
    public String getJSON() throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            return mapper.writeValueAsString(this).replace("\"", "'");
        }catch (Exception e){
            return null;
        }
    }
}
