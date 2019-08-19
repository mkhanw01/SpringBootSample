package com.coviam.sample.request;

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
public class MandatoryRequestParam implements Serializable {
    private static final long serialVersionUID = 4267892533618849091L;

    private String storeId;
    private String userName;
}
