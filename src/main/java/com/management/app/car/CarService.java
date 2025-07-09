package com.management.app.car;

import com.management.app.car.dto.CarSearchDto;
import com.management.app.car.response.CarResponse;
import com.management.app.common.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

	List<CarResponse> getCarsByUsername(String username);

	CarResponse addCar(String username, Car car);

	Optional<CarResponse> updateCar(Long id, Car updatedCar, String username);

	boolean deleteCar(Long id, String username);

	List<CarResponse> searchCarsByCriteria(CarSearchDto searchDto, String username);
}
