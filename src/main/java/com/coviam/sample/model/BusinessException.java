package com.coviam.sample.model;

/**
 * @author waseem.khan since 2019-08-19.
 */
public class BusinessException extends RuntimeException {
  private final Error error;

  public BusinessException(Error errorCode) {
    this.error = errorCode;
  }

  public String getCode() {
    return this.error.getCode();
  }

  @Override
  public String getMessage() {
    return this.error.getMessage();
  }
}
