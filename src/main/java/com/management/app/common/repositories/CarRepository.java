package com.management.app.common.repositories;

import com.management.app.common.entities.Car;
import com.management.app.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
	List<Car> findByUser(User user);
}
