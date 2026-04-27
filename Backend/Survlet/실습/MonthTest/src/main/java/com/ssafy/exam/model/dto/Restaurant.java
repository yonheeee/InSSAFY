package com.ssafy.exam.model.dto;

import java.io.Serializable;

/*
 * 이 파일에는 수정할 부분이 없습니다.
 * */
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code; // 맛집 코드
	private String name; // 맛집 이름
	private String category; // 카테고리 (한식, 중식, 일식 등)
	private int rating; // 별점 (1~5점)
	private String review; // 대표 리뷰 내용
	private String regDate; // 등록일 (yyyy-MM-dd 형식)

	public Restaurant() {
	}

	public Restaurant(String code, String name, String category, int rating, String review, String regDate) {
		this.code = code;
		this.name = name;
		this.category = category;
		this.rating = rating;
		this.review = review;
		this.regDate = regDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "RestaurantDto [code="+code+", name=" + name + ", category=" + category + ", rating=" + rating + ", review=" + review
				+ ", regDate=" + regDate + "]";
	}
}