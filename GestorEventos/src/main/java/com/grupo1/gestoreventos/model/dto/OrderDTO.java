package com.grupo1.gestoreventos.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDTO {

	private Double price;
	private String currency;
	private String method;
	private String intent;
	private Long idEvento;
	private String description;
	
	public OrderDTO() {
		this.price = 0.0D;
	}
}

