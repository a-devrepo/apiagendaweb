package br.com.nca.apiagendaweb.handlers;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Requisição Inválida");
        problemDetail.setType(URI.create("https://api.agenda.com/errors/invalid-request"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, "Um ou mais campos estão inválidos.");
        problemDetail.setTitle("Erro de Validação");
        problemDetail.setType(URI.create("https://api.agenda.com/errors/validacao"));

        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (mensagemExistente, mensagemNova) -> mensagemExistente + " " + mensagemNova
                ));

        problemDetail.setProperty("fields", errors);
        problemDetail.setProperty("timestamp", Instant.now());

        return ResponseEntity.status(status).body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado no servidor.");
        problemDetail.setTitle("Erro Interno no Servidor");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}