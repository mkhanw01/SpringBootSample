package com.coviam.sample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coviam.sample.entity.SystemParameter;
import com.coviam.sample.model.BusinessException;
import com.coviam.sample.model.Constants;
import com.coviam.sample.model.Error;
import com.coviam.sample.request.SystemParameterRequest;
import com.coviam.sample.response.BaseResponse;
import com.coviam.sample.response.SystemParameterResponse;
import com.coviam.sample.service.ConverterService;
import com.coviam.sample.service.SystemParameterService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author waseem.khan since 2019-08-19.
 */
@RestController
@RequestMapping(Constants.SYSTEM_PARAMETER)
@Slf4j
public class SystemParameterController {

  @Autowired
  private SystemParameterService systemParameterService;

  @Autowired
  private ConverterService converterService;

  @PostMapping(value = {Constants.SAVE}, produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public BaseResponse<SystemParameterResponse> save(@RequestParam int storeId,
      @RequestParam String userName,
      @Valid @RequestBody SystemParameterRequest systemParameterWebRequest) {
    log.info("save with systemParameterWebRequest: {}, storeId: {}, and userName: {}",
        systemParameterWebRequest, storeId, userName);

    SystemParameterResponse systemParameterWebResponse = null;
    try {
      SystemParameter systemParameter = this.systemParameterService
          .save(this.converterService.convert(systemParameterWebRequest, SystemParameter.class));
      systemParameterWebResponse =
          converterService.convert(systemParameter, SystemParameterResponse.class);

    } catch (DataIntegrityViolationException de) {
      log.error("Error in save systemParameter due to: {} ", de.getMessage(), de);
      return new BaseResponse(Error.SYSTEM_PARAMETER_ALREADY_EXIST.getCode(),
          Error.SYSTEM_PARAMETER_ALREADY_EXIST.getMessage(), false, systemParameterWebResponse);
    } catch (BusinessException be) {
      log.error("Error in save systemParameter due to: {} ", be.getMessage(), be);
      return new BaseResponse(be.getCode(), be.getMessage(), false, systemParameterWebResponse);
    } catch (Exception e) {
      log.error("Error in save systemParameter due to: {} ", e.getMessage(), e);
      return new BaseResponse(Error.SYSTEM_ERROR.getCode(), Error.SYSTEM_ERROR.getMessage(), false,
          systemParameterWebResponse);
    }
    return new BaseResponse(true, systemParameterWebResponse);
  }

  @DeleteMapping(value = {Constants.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public BaseResponse delete(@RequestParam int storeId, @RequestParam String userName,
      @RequestParam String parameterName) {
    log.info("delete with storeId: {}, parameterName: {} and userName: {}", storeId, parameterName,
        userName);
    SystemParameterResponse systemParameterWebResponse = null;
    try {
      SystemParameter systemParameter = this.systemParameterService.delete(storeId, parameterName);
      systemParameterWebResponse =
          this.converterService.convert(systemParameter, SystemParameterResponse.class);
    } catch (BusinessException be) {
      log.error("Error in delete systemParameter due to: {} ", be.getMessage(), be);
      return new BaseResponse(be.getCode(), be.getMessage(), false, systemParameterWebResponse);
    } catch (Exception e) {
      log.error("Error in delete systemParameter due to: {} ", e.getMessage(), e);
      return new BaseResponse(Error.SYSTEM_ERROR.getCode(), Error.SYSTEM_ERROR.getMessage(), false,
          systemParameterWebResponse);
    }
    return new BaseResponse(true);
  }

  @PostMapping(value = {Constants.UPDATE}, produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public BaseResponse update(@RequestParam int storeId, @RequestParam String userName,
      @Valid @RequestBody SystemParameterRequest systemParameterWebRequest) {
    log.info("update with systemParameterWebRequest: {}, storeId: {}, and userName: {}",
        systemParameterWebRequest, storeId, userName);
    SystemParameterResponse systemParameterWebResponse = null;
    try {
      SystemParameter systemParameter =
          this.converterService.convert(systemParameterWebRequest, SystemParameter.class);
      SystemParameter result = this.systemParameterService.update(systemParameter);
      systemParameterWebResponse =
          this.converterService.convert(result, SystemParameterResponse.class);
    } catch (BusinessException be) {
      log.error("Error in update systemParameter due to: {} ", be.getMessage(), be);
      return new BaseResponse(be.getCode(), be.getMessage(), false, systemParameterWebResponse);
    } catch (Exception e) {
      log.error("Error in update systemParameter due to: {} ", e.getMessage(), e);
      return new BaseResponse(Error.SYSTEM_ERROR.getCode(), Error.SYSTEM_ERROR.getMessage(), false,
          systemParameterWebResponse);
    }
    return new BaseResponse(true, systemParameterWebResponse);
  }

  @GetMapping(value = {Constants.FIND_BY_PARAMETER_NAME},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public BaseResponse<SystemParameterResponse> findByParameterName(@RequestParam int storeId,
      @RequestParam String userName, @RequestParam String parameterName) {
    log.info("findByName with storeId: {},  parameterName: {} and userName: {}", storeId,
        parameterName, userName);
    SystemParameterResponse systemParameterWebResponse = null;
    try {
      SystemParameter systemParameter =
          this.systemParameterService.findByStoreIdAndParameterName(storeId, parameterName);
      systemParameterWebResponse =
          this.converterService.convert(systemParameter, SystemParameterResponse.class);
    } catch (BusinessException be) {
      log.error("Error in findByName systemParameter due to: {} ", be.getMessage(), be);
      return new BaseResponse(be.getCode(), be.getMessage(), false, systemParameterWebResponse);
    } catch (Exception e) {
      log.error("Error in findByName systemParameter due to: {} ", e.getMessage(), e);
      return new BaseResponse(Error.SYSTEM_ERROR.getCode(), Error.SYSTEM_ERROR.getMessage(), false,
          systemParameterWebResponse);
    }
    return new BaseResponse<>(true, systemParameterWebResponse);
  }
}
