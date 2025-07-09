package com.management.app.car;

import com.management.app.car.response.CarResponse;
import com.management.app.common.entities.Car;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CarMapper {
	public static CarResponse mapToCarResponse(final Car car, String username) {
		return CarResponse.builder()
				.brand(car.getBrand())
				.model(car.getModel())
				.year(car.getYear())
				.licensePlate(car.getLicensePlate())
				.color(car.getColor())
				.user(username)
				.build();
	}

	public static List<CarResponse> mapToCarResponseList(final List<Car> cars, String username) {
		return cars.stream().map(car -> {
			CarResponse response = new CarResponse();
			response.setId(car.getId());
			response.setBrand(car.getBrand());
			response.setModel(car.getModel());
			response.setYear(car.getYear());
			response.setLicensePlate(car.getLicensePlate());
			response.setColor(car.getColor());
			response.setUser(username);
			return response;
		}).toList();
	}
}
