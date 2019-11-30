package cn.sunline.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sunline.ssm.bean.Customer;
import cn.sunline.ssm.dao.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public void saveCustomer(Customer customer) {
		customerMapper.insert(customer);
	}

}
