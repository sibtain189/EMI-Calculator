package com.connection.Practice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmiErrorResponce {
	private Integer status;
	private String message;
}
