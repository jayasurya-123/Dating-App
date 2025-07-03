package com.dating.app.entity;

import java.util.List;

import com.dating.app.util.UserGender;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	@ElementCollection
	private List<String> interests;

}
