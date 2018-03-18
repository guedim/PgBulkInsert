package com.guedim.pgbulkinsert.pgbulkinsert.model;

public enum PaymentReferenceType {

  /**
   * A Payment Coupon is a bar code or reference given in a paper with a design by the merchant to
   * payers.
   */
  PAYMENT_COUPON,

  /**
   * A Payment Card is a real plastic card with the payment information given by the merchant to
   * payers.
   */
  PAYMENT_CARD;
}
