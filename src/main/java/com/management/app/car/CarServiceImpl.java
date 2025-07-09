package com.management.app.car;

import com.management.app.car.dto.CarSearchDto;
import com.management.app.car.response.CarResponse;
import com.management.app.common.entities.Car;
import com.management.app.common.entities.User;
import com.management.app.common.repositories.CarRepository;
import com.management.app.common.repositories.UserRepository;
import com.management.app.common.utilies.Check;
import com.management.app.common.utilies.GlobalConstants;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

	private final CarRepository carRepository;
	private final UserRepository userRepository;

	public List<CarResponse> getCarsByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow();
		return CarMapper.mapToCarResponseList(carRepository.findByUser(user), username);
	}

	public CarResponse addCar(String username, Car car) {
		User user = userRepository.findByUsername(username).orElseThrow();
		car.setUser(user);
		return CarMapper.mapToCarResponse(carRepository.save(car), username);
	}

	public Optional<CarResponse> updateCar(Long id, Car updatedCar, String username) {
		return carRepository.findById(id).map(car -> {
			if (!car.getUser().getUsername().equals(username)) return null;
			car.setBrand(updatedCar.getBrand());
			car.setModel(updatedCar.getModel());
			car.setYear(updatedCar.getYear());
			car.setLicensePlate(updatedCar.getLicensePlate());
			car.setColor(updatedCar.getColor());
			return CarMapper.mapToCarResponse(carRepository.save(car), username);
		});
	}

	public boolean deleteCar(Long id, String username) {
		return carRepository.findById(id).map(car -> {
			if (!car.getUser().getUsername().equals(username)) return false;
			carRepository.delete(car);
			return true;
		}).orElse(false);
	}

	@Override
	public List<CarResponse> searchCarsByCriteria(CarSearchDto searchDto, String username) {
		return CarMapper.mapToCarResponseList(carRepository.findAll(getCarSpecification(searchDto, username)), null);
	}

	private Specification<Car> getCarSpecification(CarSearchDto searchDto, String username) {
		return (Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (Check.isNotEmptyString(username)) {
				predicates.add(cb.equal(cb.lower(root.get("user").get("username")), username.toLowerCase()));
			}
			if (Check.isNotEmptyString(searchDto.getModel())) {
				predicates.add(cb.like(cb.lower(root.get("model")), GlobalConstants.PERCENTAGE + searchDto.getModel().toLowerCase() + GlobalConstants.PERCENTAGE));
			}
			if (Check.isNotEmptyString(searchDto.getLicensePlate())) {
				predicates.add(cb.like(cb.lower(root.get("licensePlate")), GlobalConstants.PERCENTAGE + searchDto.getLicensePlate().toLowerCase() + GlobalConstants.PERCENTAGE));
			}
			if (Check.isNotNull(searchDto.getYear())) {
				predicates.add(cb.equal(root.get("year"), searchDto.getYear()));
			}
			if (Check.isNotEmptyString(searchDto.getBrand())) {
				predicates.add(cb.like(cb.lower(root.get("brand")), GlobalConstants.PERCENTAGE + searchDto.getBrand().toLowerCase() + GlobalConstants.PERCENTAGE));
			}
			if (Check.isNotEmptyString(searchDto.getColor())) {
				predicates.add(cb.like(cb.lower(root.get("color")), GlobalConstants.PERCENTAGE + searchDto.getColor().toLowerCase() + GlobalConstants.PERCENTAGE));
			}
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
