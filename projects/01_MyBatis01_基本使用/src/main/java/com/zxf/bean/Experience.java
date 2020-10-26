package com.zxf.bean;

import java.util.Date;

public class Experience extends BaseBean {
    private String job;
    private String intro;
    private Date beginDay;
    private Date endDay;
    private Company company;

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getCompanyId(){
        return company.getId();
    }

    @Override
    public String toString() {
        return "Experience{" +
                "job='" + job + '\'' +
                ", intro='" + intro + '\'' +
                ", beginDay=" + beginDay +
                ", endDay=" + endDay +
                ", company=" + company +
                '}';
    }
}
