package com.my.spring.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.my.spring.DAO.ProductionDao;
import com.my.spring.DAO.UserDao;
import com.my.spring.model.Production;
import com.my.spring.model.ProductionPojo;
import com.my.spring.service.ProductionService;
import com.my.spring.utils.DataWrapper;


@Service("productionService")
public class ProductionServiceImpl implements ProductionService {
	@Autowired
	ProductionDao productionDao;
	@Autowired
	UserDao userDao;
	@Override
	public DataWrapper<Void> addProduction(String content, String token, String tel) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DataWrapper<Void> deleteProduction(String ids, String token) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DataWrapper<List<ProductionPojo>> getProductionList(Integer pageIndex, Integer pageSize,
			Production Production, String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


	

}
