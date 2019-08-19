package com.coviam.sample.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.coviam.sample.model.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Data
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = 7655178685159919713L;

  @Column(name = Constants.STORE_ID, nullable = false)
  private int storeId;

  @CreatedDate
  @Column(name = Constants.CREATED_DATE, nullable = false, insertable = false, updatable = false)
  private Date createdDate;

  @CreatedBy
  @Column(name = Constants.CREATED_BY, nullable = false, updatable = false)
  private String createdBy;

  @LastModifiedDate
  @Column(name = Constants.CREATED_DATE, nullable = false)
  private Date updatedDate;

  @LastModifiedBy
  @Column(name = Constants.UPDATED_BY)
  private String updatedBy;

  @Column(name = Constants.MARK_FOR_DELETE, nullable = false)
  private boolean markForDelete;
}
