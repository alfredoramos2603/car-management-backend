package com.management.app.car;

import com.management.app.car.dto.CarSearchDto;
import com.management.app.car.response.CarResponse;
import com.management.app.common.entities.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars")
public class CarController {

	private final CarServiceImpl carService;

	@PostMapping("/search")
	public ResponseEntity<List<CarResponse>> searchCars(@RequestBody CarSearchDto searchDto, Authentication auth) {
		List<CarResponse> cars = carService.searchCarsByCriteria(searchDto, auth.getName());
		return ResponseEntity.ok(cars);
	}

	@GetMapping
	public List<CarResponse> getCars(Authentication auth) {
		return carService.getCarsByUsername(auth.getName());
	}

	@PostMapping
	public CarResponse addCar(@RequestBody Car car, Authentication auth) {
		return carService.addCar(auth.getName(), car);
	}

	@PutMapping("/{id}")
	public CarResponse updateCar(@PathVariable Long id, @RequestBody Car car, Authentication auth) {
		return carService.updateCar(id, car, auth.getName()).orElseThrow();
	}

	@DeleteMapping("/{id}")
	public void deleteCar(@PathVariable Long id, Authentication auth) {
		if (!carService.deleteCar(id, auth.getName())) {
			throw new RuntimeException("No autorizado para eliminar este auto");
		}
	}
}