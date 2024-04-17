package com.logic.spaza.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserDTO {
	private Integer id;

	private String firstName;

	private String lastName;

	private String userName;

	private String password;

	private String phoneNumber;

	private Boolean isActive;

	private Boolean deletedFlag;

	private Timestamp createdAt;

	private String createdBy;

	private Timestamp modifiedAt;

	private String modifiedBy;

}

