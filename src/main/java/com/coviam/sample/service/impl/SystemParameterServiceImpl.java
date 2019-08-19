package com.coviam.sample.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviam.sample.entity.SystemParameter;
import com.coviam.sample.model.Error;
import com.coviam.sample.repository.SystemParameterRepository;
import com.coviam.sample.service.SystemParameterService;
import com.coviam.sample.service.UtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Service
@Slf4j
public class SystemParameterServiceImpl implements SystemParameterService {

  @Autowired
  private SystemParameterRepository systemParameterRepository;

  @Override
  public SystemParameter save(SystemParameter systemParameter) {
    UtilService.checkError(systemParameter != null, Error.SYSTEM_PARAMETER_NULL);
    systemParameter.setStoreId(Integer.parseInt(MDC.get("storeId")));
    SystemParameter result = this.systemParameterRepository.save(systemParameter);
    UtilService.checkError(result != null, Error.SYSTEM_PARAMETER_NOT_SAVED);
    return result;
  }

  @Override
  public SystemParameter delete(int storeId, String parameterName) {

    UtilService.checkError(StringUtils.isNotBlank(parameterName), Error.SYSTEM_PARAMETER_NAME_NULL);
    log.debug("delete with parameterName: {}", parameterName);
    SystemParameter systemParameter = this.findByStoreIdAndParameterName(storeId, parameterName);
    systemParameter.setMarkForDelete(true);
    SystemParameter result = this.systemParameterRepository.save(systemParameter);
    UtilService.checkError(result != null, Error.SYSTEM_PARAMETER_NOT_DELETED);
    return result;
  }

  @Override
  public SystemParameter update(SystemParameter systemParameter) {
    UtilService.checkError(systemParameter != null, Error.SYSTEM_PARAMETER_NULL);
    int storeId = Integer.parseInt(MDC.get("storeId"));
    log.debug("update with systemParameter: {}", systemParameter);
    systemParameter.setStoreId(storeId);
    SystemParameter systemParam =
        this.findByStoreIdAndParameterName(storeId, systemParameter.getParameterName());
    systemParam.setParameterValue(systemParameter.getParameterValue());
    systemParam.setParameterDescription(systemParameter.getParameterDescription());
    SystemParameter result = this.systemParameterRepository.save(systemParam);
    UtilService.checkError(result != null, Error.SYSTEM_PARAMETER_NOT_UPDATED);
    return result;
  }

  @Override
  public SystemParameter findByStoreIdAndParameterName(int storeId, String parameterName) {
    UtilService.checkError(StringUtils.isNotBlank(parameterName), Error.SYSTEM_PARAMETER_NAME_NULL);
    log.debug("findByStoreIdAndParameterName with parameterName: {}", parameterName);
    SystemParameter result = this.systemParameterRepository
        .findOneByStoreIdAndParameterNameAndMarkForDeleteFalse(storeId, parameterName);
    UtilService.checkError(result != null, Error.SYSTEM_PARAMETER_NOT_FOUND);
    return result;
  }
}
