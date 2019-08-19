package com.coviam.sample.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.coviam.sample.model.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemParameterRequest implements Serializable {

  private static final long serialVersionUID = -3058999104454860186L;

  @NotBlank(message = Constants.SYSTEM_PARAMETER_NAME_BLANK)
  @Length(max = 255, message = Constants.INVALID_PARAMETER_NAME_LENGTH)
  private String parameterName;

  @NotBlank(message = Constants.SYSTEM_PARAMETER_VALUE_BLANK)
  @Length(max = 255, message = Constants.INVALID_PARAMETER_VALUE_LENGTH)
  private String parameterValue;

  @NotBlank(message = Constants.SYSTEM_PARAMETER_DESC_BLANK)
  @Length(max = 255, message = Constants.INVALID_PARAMETER_DESCRIPTION_LENGTH)
  private String parameterDescription;
}
