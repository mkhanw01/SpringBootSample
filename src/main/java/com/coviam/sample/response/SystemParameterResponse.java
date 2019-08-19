package com.coviam.sample.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemParameterResponse implements Serializable {
    private static final long serialVersionUID = 4456034704316780157L;

    private String parameterName;
    private String parameterValue;
    private String parameterDescription;
}
