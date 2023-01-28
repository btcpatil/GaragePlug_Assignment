package com.garageplug.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {

    private LocalDateTime timestamp;
	private String message;
	private String details;
	
}
