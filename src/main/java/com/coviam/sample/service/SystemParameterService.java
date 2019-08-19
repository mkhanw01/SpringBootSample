package com.coviam.sample.service;

import com.coviam.sample.entity.SystemParameter;

/**
 * @author waseem.khan since 2019-08-19.
 */
public interface SystemParameterService {
  /**
   * to save the system parameter object value
   * 
   * @param systemParameter object reference of system parameter
   * @return systemParameter/null
   */
  SystemParameter save(SystemParameter systemParameter);

  /**
   * delete system parameter
   *
   * @param parameterName name of system parameter
   * @return SystemParameter delete by setting markForDelete true
   */
  SystemParameter delete(int storeId, String parameterName);


  /**
   * update system parameter
   *
   * @param systemParameter systemParameter
   * @return SystemParameter updated
   */
  SystemParameter update(SystemParameter systemParameter);

  /**
   * find by storeId and parameterName
   *
   * @param parameterName name of system parameter
   * @return SystemParameter identified with given parameterName
   */
  SystemParameter findByStoreIdAndParameterName(int storeId, String parameterName);
}
