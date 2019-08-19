package com.coviam.sample.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import com.coviam.sample.request.MandatoryRequestParam;

import lombok.extern.slf4j.Slf4j;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Slf4j
public class MandatoryRequestParameterFilter implements Filter {

  private static final String KEY_STORE_ID = "storeId";
  private static final String KEY_USERNAME = "userName";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // nothing to do
  }

  @Override
  public void destroy() {
 // No Implementation
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    MandatoryRequestParam mandatoryRequestParam;
    try {
      mandatoryRequestParam = generateParametr(httpRequest.getParameter(KEY_STORE_ID));
      mandatoryRequestParam.setUserName(servletRequest.getParameter(KEY_USERNAME));
    } catch (Exception e) {
      log.error("Unable to process the mandatory request parameters. Cause: {}", e.getMessage(), e);
      throw new ServletException("Unable to process the mandatory request parameters.", e);
    }
    this.populateMandatoryParams(mandatoryRequestParam);
    try {
      filterChain.doFilter(servletRequest, servletResponse);
    } finally {
      this.clearMandatoryParams();
    }
  }

  MandatoryRequestParam generateParametr(String storeId) {
    MandatoryRequestParam mandatoryRequestParam = new MandatoryRequestParam();
    if (!StringUtils.isBlank(storeId)) {
      mandatoryRequestParam.setStoreId(storeId);
    } else {
      throw new IllegalArgumentException("Mandatory base request param validation exception");
    }
    return mandatoryRequestParam;
  }


  private void populateMandatoryParams(MandatoryRequestParam mandatoryRequestParam) {
    MDC.put(KEY_STORE_ID, mandatoryRequestParam.getStoreId());
    MDC.put(KEY_USERNAME, mandatoryRequestParam.getUserName());
  }

  private void clearMandatoryParams() {
    MDC.remove(KEY_STORE_ID);
    MDC.remove(KEY_USERNAME);
  }
}
