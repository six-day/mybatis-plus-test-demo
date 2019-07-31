package com.example.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OptionVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object value;
	private String message;
}
