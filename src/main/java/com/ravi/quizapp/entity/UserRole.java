package com.ravi.quizapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userRoleId;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "roleId")
	private Role role;

}
