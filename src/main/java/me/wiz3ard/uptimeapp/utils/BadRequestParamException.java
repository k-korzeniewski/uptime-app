package me.wiz3ard.uptimeapp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Wrong request param")
public class BadRequestParamException extends RuntimeException {
}
