package com.zxf.bean;

import java.util.List;

public class ContactListResult extends ContactListParam {
    /**
     * 总数量
     */
    private Integer totalCount;
    /**
     * 总页数
     * */
    private Integer totalPages;
    private List<Contact> contacts;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
