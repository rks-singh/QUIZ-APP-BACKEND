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
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	
	private String roleType;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate crationDate;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updateDate;
	

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserRole> userRoles;
}
