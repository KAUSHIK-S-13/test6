package com.logic.spaza.exception;

import java.io.Serial;
public class CustomValidationException  extends RuntimeException{

@Serial
private static final long serialVersionUID = 1L;
public CustomValidationException(String msg) {
super(msg);
}

}

