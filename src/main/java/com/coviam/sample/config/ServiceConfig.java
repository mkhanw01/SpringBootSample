package com.coviam.sample.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "stringAuditorAware")
public class ServiceConfig {

  @Bean
  public static DeserializationProblemHandlerImpl deserializationProblemHandler() {
    return new DeserializationProblemHandlerImpl();
  }

  @Bean
  public Mapper dozerMapper() {
    return new DozerBeanMapper();
  }

  @Bean
  public AuditorAware<String> stringAuditorAware() {
    return () -> java.util.Optional.of(MDC.get("userName").toString());
  }


  @Bean
  public FilterRegistrationBean registerMandatoryRequestParameterFilter() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new MandatoryRequestParameterFilter());
    filterRegistrationBean.addUrlPatterns("/api/*");
    filterRegistrationBean.setName("mandatoryRequestParameterFilter");
    filterRegistrationBean.setOrder(1);
    return filterRegistrationBean;
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
    mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
    mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
    mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    mapper.addHandler(ServiceConfig.deserializationProblemHandler());
    return mapper;
  }
}
