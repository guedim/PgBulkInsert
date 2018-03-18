package com.guedim.pgbulkinsert.pgbulkinsert.model;

public enum ChargeSolutionBusinessUnit {

  /** The orders. */
  ORDERS("00"),
  
  /** The payment coupons. */
  PAYMENT_COUPONS("10"),
  
  /**
   * The generic payment cards for Colombia.
   */
  PAYMENT_CARDS_COLOMBIA("21"),
  
  /**
   * The customized payment cards for Colombia.
   */
  PAYMENT_CARDS_COLOMBIA_CU("22"),
  
  /**
   * The generic payment cards for Argentina online payment support.
   */
  PAYMENT_CARDS_ARGENTINA("23"),
  
  /**
   * The generic payment cards for Argentina offline payment support.
   */
  PAYMENT_CARDS_ARGENTINA_OFF("24"),
  
  /**
   * The customized payment cards for Argentina online payment support.
   */
  PAYMENT_CARDS_ARGENTINA_CU("25"),
  
  /**
   * The customized payment cards for Argentina offline payment support.
   */
  PAYMENT_CARDS_ARGENTINA_OFF_CU("26"),
  
  /**
   * The generic payment cards for Mexico.
   */
  PAYMENT_CARDS_MEXICO("27");
  
  /**  The business unit code. */
  private final String code;
  
  /**
   * Default constructor.
   *
   * @param code the code
   */
  private ChargeSolutionBusinessUnit (String code) {
      this.code = code;
  }

  /**
   * Gets the code.
   *
   * @return the code
   */
  public String getCode() {
      return code;
  }
  
  /**
   * Returns the ChargeSolutionBusinessUnit using its code
   * @param code the code
   * @return the charge solution business unit
   */
  public static ChargeSolutionBusinessUnit valueOfByCode(String code){
      for(ChargeSolutionBusinessUnit businessUnit : ChargeSolutionBusinessUnit.values()){
          if(businessUnit.getCode().equals(code)){
              return businessUnit;
          }
      }
      return null;
  }

}
