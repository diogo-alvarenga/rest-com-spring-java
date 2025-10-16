package br.com.diogo_alvarenga.Exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String datails) {}
