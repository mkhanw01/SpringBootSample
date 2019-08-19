package com.coviam.sample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviam.sample.service.ConverterService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Service
@Slf4j
public class ConverterServiceImpl implements ConverterService {
  @Autowired
  private Mapper mapper;

  @Override
  public <M, T> T convert(M entity, Class<T> clasz) throws Exception {
    return this.mapper.map(entity, clasz);
  }

  @Override
  public <M, T> List<T> convert(List<M> entities, Class<T> clasz) throws Exception {
    List<T> responseList = new ArrayList<T>();
    for (M entity : entities) {
      T response = this.mapper.map(entity, clasz);
      responseList.add(response);
    }
    return responseList;
  }
}
