package com.management.app.car.dto;

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
public class CarSearchDto {
	private String brand;
	private String model;
	private Integer year;
	private String licensePlate;
	private String color;
}
