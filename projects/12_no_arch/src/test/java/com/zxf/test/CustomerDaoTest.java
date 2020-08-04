package com.zxf.test;

import com.zxf.Dao.CustomerDao;
import com.zxf.bean.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CustomerDaoTest {

    @Test
    public void testList(){
        CustomerDao dao = new CustomerDao();
        List<Customer> customers = dao.list();

        for (Customer customer : customers) {
            System.out.println(customer);
        }

        Assert.assertTrue(customers.size() > 0);
    }

}
