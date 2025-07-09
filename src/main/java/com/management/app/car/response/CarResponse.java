package com.management.app.car.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarResponse {
	private Long id;
	private String brand;
	private String model;
	private int year;
	private String licensePlate;
	private String color;
	private String user;
}
