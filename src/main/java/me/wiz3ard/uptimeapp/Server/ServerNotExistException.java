package me.wiz3ard.uptimeapp.Server;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="This server not exist")
public class ServerNotExistException extends RuntimeException {
}
