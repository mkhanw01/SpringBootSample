
CREATE TABLE system_parameter
(
  parameter_id bigint NOT NULL,
  parameter_name character varying(255) NOT NULL,
  parameter_value character varying(255),
  parameter_description character varying(255),
  store_id int NOT NULL,
  mark_for_delete boolean NOT NULL,
  created_by character varying(255) NOT NULL,
  created_date timestamp without time zone NOT NULL,
  updated_by character varying(255),
  updated_date timestamp without time zone,
  CONSTRAINT system_parameter_pk PRIMARY KEY (parameter_id),
  CONSTRAINT system_parameter_uk_01 UNIQUE (store_id, parameter_name)
);

CREATE SEQUENCE system_parameter_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
