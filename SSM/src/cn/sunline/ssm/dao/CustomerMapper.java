package cn.sunline.ssm.dao;

import cn.sunline.ssm.bean.Customer;
import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer custId);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer custId);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);
}