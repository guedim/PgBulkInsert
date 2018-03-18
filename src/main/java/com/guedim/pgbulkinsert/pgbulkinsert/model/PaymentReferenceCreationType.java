package com.guedim.pgbulkinsert.pgbulkinsert.model;

public enum PaymentReferenceCreationType {

  /**
   * The Payment Reference is sent by email
   */
  EMAIL,

  /**
   * The Payment Reference is downloaded for print
   */
  PRINT,

  /**
   * Just the Payment number is generated
   */
  CODE,

  /**
   * The payment Reference is a PAYMENT_CARD
   */
  CARD;

  /**
   * Gets the key property according to the value
   * 
   * @return key property
   */
  public String getKeyProperty() {
    StringBuilder sb = new StringBuilder(PaymentReferenceCreationType.class.getSimpleName());
    sb.append(".");

    return sb.append(this.name()).toString();

  }

}
