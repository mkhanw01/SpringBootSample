package com.coviam.sample.service;

import com.coviam.sample.model.BusinessException;
import com.coviam.sample.model.Error;

/**
 * @author waseem.khan since 2019-08-19.
 */
public interface UtilService {

  static void checkError(boolean cond, Error error) {
    if (!cond) {
      throw new BusinessException(error);
    }
  }
}
