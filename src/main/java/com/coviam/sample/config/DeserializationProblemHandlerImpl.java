package com.coviam.sample.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author waseem.khan since 2019-08-19.
 */
public class DeserializationProblemHandlerImpl
    extends com.fasterxml.jackson.databind.deser.DeserializationProblemHandler {

  private static final Logger LOG =
      LoggerFactory.getLogger(DeserializationProblemHandlerImpl.class);

  @Override
  public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp,
      JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName)
      throws IOException {
    DeserializationProblemHandlerImpl.LOG.warn("unknown field : {}", propertyName);
    return true;
  }
}
