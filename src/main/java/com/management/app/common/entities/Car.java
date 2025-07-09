package com.management.app.common.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "brand", nullable = false)
	private String brand;

	@Column(name = "model", nullable = false)
	private String model;

	@Min(1886)
	@Max(2100)
	@Column(name = "year", nullable = false)
	private int year;

	@Pattern(regexp = "^[A-Z0-9-]{6,10}$", message = "Formato de placa inválido")
	@Column(name = "license_plate", nullable = false)
	private String licensePlate;

	@Column(name = "color", nullable = false)
	private String color;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

}


