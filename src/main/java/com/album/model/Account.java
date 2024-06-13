package com.album.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="Account")
@Getter
@Setter
@ToString
public class Account {

		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE)
		private int id;
		private String email;
		private String password;
		public String Authority;
		
}
