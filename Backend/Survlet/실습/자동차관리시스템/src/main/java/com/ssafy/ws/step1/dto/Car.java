package com.ssafy.ws.step1.dto;

public class Car {
	private String VIN;
	private String modeName;
	private String color;
	private int mileage;
	
	public Car() {
		
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public Car(String vIN, String modeName, String color, int mileage) {
		super();
		VIN = vIN;
		this.modeName = modeName;
		this.color = color;
		this.mileage = mileage;
	}
	@Override
	public String toString() {
		return "Car [VIN=" + VIN + ", modeName=" + modeName + ", color=" + color + ", mileage=" + mileage + "]";
	}
	
	
}
