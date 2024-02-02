package com.ravi.quizapp.entity;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String password;
	private String profile;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate crationDate;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updateDate;
	
	private Boolean acive_SW;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRole> userRoles;
}
