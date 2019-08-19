package com.coviam.sample.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Data
@NoArgsConstructor
@Builder
public class BaseResponse<T> implements Serializable {

  private static final long serialVersionUID = -2202435222425148959L;

  private String errorMessage;
  private String errorCode;
  private boolean success;
  private T data;

  public BaseResponse(boolean success) {
    this.success = success;
  }

  public BaseResponse(boolean success, T data) {
    this.success = success;
    this.data = data;
  }

  public BaseResponse(String errorCode, String errorMessage, boolean success, T data) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.success = success;
    this.data = data;
  }

  public BaseResponse(String errorCode, String errorMessage, boolean success) {
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
    this.success = success;
  }
}
