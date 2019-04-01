package com.castgroup.assignment3.model;

import java.io.Serializable;

public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = -4043415630555789814L;
	
	private Long id;
	private String name;
	private String street;
	private Integer number;
	private String neighborhood;
	private String city;
	private String state;
	private Integer cellPhone;
	private Integer phone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(Integer cellPhone) {
		this.cellPhone = cellPhone;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
}
