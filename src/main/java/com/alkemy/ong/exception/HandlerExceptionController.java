package com.alkemy.ong.exception;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;


import com.amazonaws.services.workdocs.model.EntityNotExistsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptionController {

    @ResponseStatus(OK)
    @ExceptionHandler({ EmptyListException.class })
    @ResponseBody
    public CustomExceptionDetails emptyList(HttpServletRequest request, Exception exception) {
        return new CustomExceptionDetails(exception, request.getRequestURI());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({ NotFoundException.class })
    @ResponseBody
    public CustomExceptionDetails notFoundRequest(HttpServletRequest request, Exception exception) {
        return new CustomExceptionDetails(exception, request.getRequestURI());
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({
            AlreadyExistsException.class,
    })
    @ResponseBody
    public CustomExceptionDetails elementAlreadyExists(HttpServletRequest request, Exception exception) {
        return new CustomExceptionDetails(exception, request.getRequestURI());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({ BadRequestException.class,
            ArithmeticException.class
    })
    @ResponseBody
    public CustomExceptionDetails badRequest(HttpServletRequest request, Exception exception) {
        return new CustomExceptionDetails(exception, request.getRequestURI());
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class,
            UnableToSaveEntityException.class,
            UnableToUpdateEntityException.class,
            UnableToDeleteEntityException.class
    })
    @ResponseBody
    public CustomExceptionDetails fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
        return new CustomExceptionDetails(exception, request.getRequestURI());
    }

}
