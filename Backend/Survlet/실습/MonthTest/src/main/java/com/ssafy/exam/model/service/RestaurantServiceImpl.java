package com.ssafy.exam.model.service;

import java.util.List;

import com.ssafy.exam.model.dao.RestaurantDao;
import com.ssafy.exam.model.dao.RestaurantDaoImpl;
import com.ssafy.exam.model.dto.Member;
import com.ssafy.exam.model.dto.Restaurant;

public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantDao dao = RestaurantDaoImpl.getInstance();

    private static RestaurantServiceImpl service = new RestaurantServiceImpl();

    private RestaurantServiceImpl() {
    	
    }

    public static RestaurantServiceImpl getService() {
        return service;
    }

	///////////////////////////////////////////////////////
	//------아래 메소드를 확인하고 코드를 완성하세요.---------------//
	///////////////////////////////////////////////////////
	

	@Override
	public Member login(String email, String password) {
		return dao.login(email, password);
	}

	@Override
	public List<Restaurant> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Restaurant selectByCode(String code) {
		return dao.selectByCode(code);
	}

	@Override
	public int insert(Restaurant restaurant) {
		return dao.insert(restaurant);
	}

	@Override
	public int deleteByCode(String code) {
		return dao.deleteByCode(code);
	}
    

}
