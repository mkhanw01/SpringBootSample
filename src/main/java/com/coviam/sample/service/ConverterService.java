package com.coviam.sample.service;

import java.util.List;

/**
 * @author waseem.khan since 2019-08-19.
 */
public interface ConverterService {

    <M, T> T convert(M entity, Class<T> clasz) throws Exception;

    <M, T> List<T> convert(List<M> entities, Class<T> clasz) throws Exception;
}
