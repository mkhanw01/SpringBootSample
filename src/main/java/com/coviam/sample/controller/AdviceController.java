package com.coviam.sample.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.coviam.sample.model.BusinessException;
import com.coviam.sample.response.BaseResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Slf4j
@RestControllerAdvice
public class AdviceController {

  @ExceptionHandler(Throwable.class)
  BaseResponse<Object> throwable(Throwable e) {
    log.error(e.getClass().getName(), e);
    return BaseResponse.builder().errorCode(e.getClass().getName()).errorMessage(e.getMessage())
        .success(false).build();
  }

  @ExceptionHandler(BusinessException.class)
  BaseResponse<Object> illegalArgumentException(BusinessException be) {
    log.error(be.getClass().getName(), be);
    return BaseResponse.builder().errorCode(be.getCode()).errorMessage(be.getMessage())
        .success(false).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  BaseResponse<Object> methodArgumentNotValidException(MethodArgumentNotValidException manve) {
    log.error(manve.getClass().getName(), manve.getBindingResult());
    StringBuilder msg = new StringBuilder("[");

    manve.getBindingResult().getAllErrors().forEach(x -> {
      msg.append(x.getDefaultMessage()).append(", ");
    });

    return BaseResponse.builder().errorCode(manve.getClass().getName())
        .errorMessage(msg.append(']').toString()).success(false).build();
  }
}
