package com.dating.app.dto;

import java.util.List;

import com.dating.app.util.UserGender;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
@Data
public class MatchingUser {
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	private List<String> interests;
	private int agediff;
	private int mic;
}
