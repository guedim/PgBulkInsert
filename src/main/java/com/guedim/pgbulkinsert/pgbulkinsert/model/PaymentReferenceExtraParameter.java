package com.guedim.pgbulkinsert.pgbulkinsert.model;

import java.io.Serializable;

public class PaymentReferenceExtraParameter extends BaseEntity implements Serializable{

  /**
   * Generated serial version UID.
   */
  private static final long serialVersionUID = 1L;

  private Integer id;

  private String type = "java.lang.String";

  private String value;

  private PaymentReferenceExtraParameterName name;

  public Integer getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  public PaymentReferenceExtraParameterName getName() {
    return name;
  }

  public String getNameAsString() {
    return name!=null?name.toString():null;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setName(PaymentReferenceExtraParameterName name) {
    this.name = name;
  }
}