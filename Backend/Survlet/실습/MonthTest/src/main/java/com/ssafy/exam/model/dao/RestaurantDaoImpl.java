package com.ssafy.exam.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.exam.model.dto.Member;
import com.ssafy.exam.model.dto.Restaurant;

public class RestaurantDaoImpl implements RestaurantDao {
	private static final RestaurantDaoImpl instance = new RestaurantDaoImpl();
	private final File memberDataFile, restaurantDataFile;
	private List<Member> members;
	private List<Restaurant> restaurants;

	public static RestaurantDaoImpl getInstance() {
		return instance;
	}

	private RestaurantDaoImpl() {
		// file 초기화
		String path = RestaurantDaoImpl.class.getResource("/").getPath() + "member.dat";
		memberDataFile = new File(path);
		path = RestaurantDaoImpl.class.getResource("/").getPath() + "restaurant.dat";
		restaurantDataFile = new File(path);

		try {
			if (!memberDataFile.exists()) {
				memberDataFile.createNewFile();
			}
			if (!restaurantDataFile.exists()) {
				restaurantDataFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		try (ObjectInputStream memOis = new ObjectInputStream(new FileInputStream(memberDataFile));
				ObjectInputStream restaurantOis = new ObjectInputStream(new FileInputStream(restaurantDataFile))) {
			members = (List) memOis.readObject();
			restaurants = (List) restaurantOis.readObject();
			if (members.size() == 0)
				reset();
			if (restaurants.size() == 0)
				restaurantReset();
			System.out.println("회원 정보 로딩 완료: " + members.size() + "명");
			System.out.println("맛집 정보 로딩 완료:" + restaurants.size() + "개");
		} catch (Exception e) {
			System.out.println("저장된 정보가 없습니다.");
			members = Collections.synchronizedList(new ArrayList<>());
			restaurants = Collections.synchronizedList(new ArrayList<>());
		
			reset(); 
			restaurantReset();
		}
	}

	public void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(memberDataFile));
				ObjectOutputStream restaurantOos = new ObjectOutputStream(new FileOutputStream(restaurantDataFile))) {
			oos.writeObject(members);
			System.out.println("회원 정보 저장 완료");
			restaurantOos.writeObject(restaurants);
			System.out.println("맛집 정보 저장 완료");
		} catch (Exception e) {
			throw new RuntimeException("정보 저장 실패", e);
		}
	}

	public void reset() {
		synchronized (members) {
			int length = 10;
			members.clear();
			for (int i = 0; i < length; i++) {
				members.add(new Member(i, "테스트" + i, "test" + i + "@ssafy.com", "1234", "USER"));
			}
			members.add(new Member(100, "관리자", "admin@ssafy.com", "1234", "ADMIN"));
			System.out.println("멤버 초기화 완료 " + members.size() + " :" + members.get(0));
		}
	}

	public void restaurantReset() {
		synchronized (restaurants) {
			restaurants.clear();
			restaurants.add(new Restaurant("R001", "싸피식당", "한식", 5, "점심 메뉴가 아주 알차요!", "2026-04-23"));
			restaurants.add(new Restaurant("R002", "자바반점", "중식", 4, "짜장면 면발이 쫄깃합니다.", "2026-04-24"));
			restaurants.add(new Restaurant("R003", "서블릿스시", "일식", 3, "신선한 초밥을 즐길 수 있어요.", "2026-04-25"));
			System.out.println("맛집 정보 초기화 완료 " + restaurants.size() + " :" + restaurants.get(0));
		}
	}
	
	
	///////////////////////////////////////////////////////
	//------아래 메소드를 확인하고 코드를 완성하세요.---------------//
	///////////////////////////////////////////////////////
	@Override
	public Member login(String email, String password) {
		synchronized (members) {
			for(Member member : members) {
				if(member.getEmail().equals(email) && member.getPassword().equals(password)) {
					return member;
				}
			}
		}
		return null;
	}

	@Override
	public List<Restaurant> selectAll() {
		return restaurants;
	}

	@Override
	public Restaurant selectByCode(String code) {
		synchronized (restaurants) {
			for(Restaurant rest : restaurants) {
				if(rest.getCode().equals(code)) {
					return rest;
				}
			}
		}
		
		return null;
	}

	@Override
	public int insert(Restaurant restaurant) {
		synchronized (restaurant) {
			restaurants.add(restaurant);
			return 1;
		}
	}

	@Override
	public int deleteByCode(String code) {
		synchronized (restaurants) {
			for(int i = 0; i < restaurants.size(); i++) {
				if(restaurants.get(i).getCode().equals(code)) {
					restaurants.remove(i);
					return 1;
				}
			}
		}
		return 0;
	}
}
