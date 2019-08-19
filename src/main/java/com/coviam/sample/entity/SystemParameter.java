package com.coviam.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author waseem.khan since 2019-08-19.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "system_parameter",
    uniqueConstraints = @UniqueConstraint(name = "system_parameter_uk_01",
        columnNames = {"store_id", "parameter_name"}))
public class SystemParameter extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_parameter_seq")
  @SequenceGenerator(name = "system_parameter_seq", sequenceName = "system_parameter_seq",
      allocationSize = 1)
  @Column(name = "parameter_id")
  private Long systemParameterId;

  @Column(name = "parameter_name", nullable = false)
  private String parameterName;

  @Column(name = "parameter_value", nullable = false)
  private String parameterValue;

  @Column(name = "parameter_description", nullable = false)
  private String parameterDescription;
}
