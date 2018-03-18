package com.guedim.pgbulkinsert.pgbulkinsert.model;

import java.math.BigDecimal;

public class PaymentReferenceAdditionalValue {
  
  private Integer id;
  
  private PaymentReferenceCurrency currency;
  
  private BigDecimal value;
  
  private PaymentReferenceAddicionalValueName name;

  public Integer getId() {
    return id;
  }

  public PaymentReferenceCurrency getCurrency() {
    return currency;
  }
  
  public String getCurrencyAsString() {
    return currency!=null?currency.toString():null;
  }

  public BigDecimal getValue() {
    return value;
  }

  public PaymentReferenceAddicionalValueName getName() {
    return name;
  }
  
  public String getNameAsString() {
    return name!=null?name.toString():null;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setCurrency(PaymentReferenceCurrency currency) {
    this.currency = currency;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public void setName(PaymentReferenceAddicionalValueName name) {
    this.name = name;
  }
}