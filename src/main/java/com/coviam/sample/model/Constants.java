package com.coviam.sample.model;

/**
 * @author waseem.khan since 2019-08-19.
 */
public interface Constants {
  String API = "/api";
  String SYSTEM_PARAMETER = API + "/systemParameter";
  String SAVE = "/save";
  String DELETE = "/delete";
  String UPDATE = "/update";
  String FIND_BY_PARAMETER_NAME = "/findByName";

  String SYSTEM_PARAMETER_NAME_BLANK = "parameter name should not be blank";
  String INVALID_PARAMETER_NAME_LENGTH = "parameter name length invalid";
  String SYSTEM_PARAMETER_VALUE_BLANK = "parameter value should not be blank";
  String INVALID_PARAMETER_VALUE_LENGTH = "parameter value length invalid";
  String SYSTEM_PARAMETER_DESC_BLANK = "parameter description should not be blank";
  String INVALID_PARAMETER_DESCRIPTION_LENGTH = "parameter description length invalid";

  String STORE_ID = "store_id";
  String CREATED_DATE = "created_date";
  String CREATED_BY = "created_by";
  String UPDATED_BY = "updated_by";
  String MARK_FOR_DELETE = "mark_for_delete";
  String UPDATED_DATE = "updated_date";
}
