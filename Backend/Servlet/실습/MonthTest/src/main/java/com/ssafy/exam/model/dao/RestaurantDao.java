package com.ssafy.exam.model.dao;

import java.util.List;

import com.ssafy.exam.model.dto.Member;
import com.ssafy.exam.model.dto.Restaurant;

/*
 * 이 파일에는 수정할 부분이 없습니다.
 * */
public interface RestaurantDao {

    Member login(String email, String password);

	List<Restaurant> selectAll();

	Restaurant selectByCode(String code);

	int insert(Restaurant restaurant);

	int deleteByCode(String code);


}
