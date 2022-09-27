package com.alkemy.ong.exception.handler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.CustomExceptionDetails;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.ForbiddenException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.NotLoggedUserException;
import com.alkemy.ong.exception.UnableToDeleteEntityException;
import com.alkemy.ong.exception.UnableToSaveEntityException;
import com.alkemy.ong.exception.UnableToUpdateEntityException;

@RestControllerAdvice
public class HandlerExceptionController {

    @ResponseStatus(OK)
    @ExceptionHandler({ EmptyListException.class })
    @ResponseBody
    public CustomExceptionDetails emptyList(HttpServletRequest request, Exception exception) {
        return new CustomExceptionDetails(exception, request.getRequestURI());
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler({ NotLoggedUserException.class,
            Forbidden.class,
            ForbiddenException.class })
    @ResponseBody
    public CustomExceptionDetails forbidden(HttpServletRequest request, Exception exception) {
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
            AlreadyExistsException.class
    })
    @ResponseBody
    public CustomExceptionDetails elementAlreadyExists(HttpServletRequest request, Exception exception) {
        return new CustomExceptionDetails(exception, request.getRequestURI());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({ BadRequestException.class,
            ArithmeticException.class,
            MissingRequestHeaderException.class,
            RequestRejectedException.class,
            MethodArgumentNotValidException.class,
            NullPointerException.class,
            IllegalArgumentException.class,
            IndexOutOfBoundsException.class,
            UsernameNotFoundException.class,
            BadCredentialsException.class
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
