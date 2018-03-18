package com.guedim.pgbulkinsert.pgbulkinsert.model;

public enum PaymentReferenceState {

  /**
   * The Payment Reference has been created, but is note ready to receive payments.
   */
  NEW,

  /**
   * The Payment Reference is enabled to receive payments.
   */
  ENABLED,

  /**
   * The Payment Reference has been disabled and until it is enabled again, isn't able to receive
   * payments.<br>
   * <b>Note:</b> Only works with online payment networks, others can't be validated.
   */
  DISABLED,

  /**
   * The Payment Reference has been cancelled and is no longer able to receive any payments.<br>
   * <b>Note:</b> Only works with online payment networks, others can't be validated.
   */
  CANCELLED,

  /**
   * The Payment Reference has been expired and is no longer able to receive any payments.<br>
   * <b>Note:</b> Only works with online payment networks, others can't be validated.
   */
  EXPIRED,

  /**
   * An error has occur in the creation process, PaymentReference is not ready to receive payments.
   */
  ERROR;

  /**
   * Gets the key property according to the value
   * 
   * @return key property
   */
  public String getKeyProperty() {
    StringBuilder sb = new StringBuilder(PaymentReferenceState.class.getSimpleName());
    sb.append(".");

    return sb.append(this.name()).toString();

  }
}
