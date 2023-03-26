package com.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	public Account(int i, String string) {
		this.id = i;
		this.name = string;
	}
	private Integer id;
	private String name;
}
