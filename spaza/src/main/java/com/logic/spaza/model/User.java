package com.logic.spaza.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "deleted_flag")
  private Boolean deletedFlag;

  @Column(name = "created_at")
  @CreationTimestamp
  private Timestamp createdAt;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "modified_at")
  @UpdateTimestamp
  private Timestamp modifiedAt;

  @Column(name = "modified_by")
  private String modifiedBy;

}
