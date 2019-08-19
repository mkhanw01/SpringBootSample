package com.coviam.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coviam.sample.entity.SystemParameter;

/**
 * @author waseem.khan since 2019-08-19.
 */
public interface SystemParameterRepository extends JpaRepository<SystemParameter, Long> {

  /**
   * find All By StoreId
   *
   * @param storeId unique indentifier of store configured
   * @return list of systemParameters
   */
  List<SystemParameter> findAllByStoreIdAndMarkForDeleteFalse(int storeId);


  /**
   * find One By StoreId And parameterName
   *
   * @param storeId unique indentifier of store configured
   * @param parameterName name of system parameter
   * @return reference of systemParameter
   */
  SystemParameter findOneByStoreIdAndParameterNameAndMarkForDeleteFalse(int storeId,
      String parameterName);
}
