package com.guedim.pgbulkinsert.pgbulkinsert.model;

import java.io.Serializable;
import java.time.LocalDate;

public class PaymentReference implements Serializable {

  /**
   * Generated serial version UID.
   */
  private static final long serialVersionUID = 7403996081303850760L;

  /**
   * The Payment Reference identifier.
   */
  private Integer id;

  /**
   * The merchant ID owner of the reference.
   */
  private Integer merchantId;

  /**
   * The account ID where the payments are associated.
   */
  private Integer accountId;

  /**
   * The code associated to the Payment Reference.
   */
  private String paymentReferenceCode;

  /**
   * The Payment Reference type.
   */
  private PaymentReferenceType paymentReferenceType;

  /**
   * The Payment Reference description.
   */
  private String description;

  /**
   * The Payment Reference state.
   */
  private PaymentReferenceState paymentReferenceState;

  /**
   * The name of the payer associated to the Payment Reference.
   */
  private String payerName;

  /**
   * The email of the payer associated to the Payment Reference.
   */
  private String payerEmail;

  /**
   * The Payment Card Request ID where the Payment Reference was requested.
   */
  private String paymentCardRequestId;

  /**
   * The creation date.
   */
  private LocalDate creationDate;

  /**
   * The expiration date.
   */
  private LocalDate expirationDate;

  /**
   * If the Payment Request supports multiple payments.
   */
  private Boolean multiPayment;

  /**
   * The lease value. Zero by default
   */
  private Long lease = 0L;

  /**
   * The business unit associated to the Payment Reference.
   */
  private ChargeSolutionBusinessUnit chargeSolutionBusinessUnit;

  /**
   * The Payment Reference creation type.
   */
  private PaymentReferenceCreationType paymentReferenceCreationType;

  /**
   * The reminder sending frequency
   */
  private ReminderFrequency reminderFrequency;

  /**
   * The next sending reminder date.
   */
  private LocalDate nextReminderDate;

  /**
   * 
   */
  private String reference;

  /**
   * The Payment Reference is migrated.
   */
  private Boolean migrated;

  /**
   * The {@link PaymentCardsStock} id associated to current {@link PaymentReference}.
   */
  private String paymentCardsStockId;

  /**
   * 
   */
  private LocalDate associationDate;


  public Integer getId() {
    return id;
  }

  public Integer getMerchantId() {
    return merchantId;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public String getPaymentReferenceCode() {
    return paymentReferenceCode;
  }

  public PaymentReferenceType getPaymentReferenceType() {
    return paymentReferenceType;
  }
  
  public String getPaymentReferenceTypeAsString() {
    return paymentReferenceType!=null?paymentReferenceType.toString():null;
  }
  

  public String getDescription() {
    return description;
  }

  public PaymentReferenceState getPaymentReferenceState() {
    return paymentReferenceState;
  }
  
  public String getPaymentReferenceStateAsString() {
    return paymentReferenceState!=null?paymentReferenceState.toString():null;
  }

  public String getPayerName() {
    return payerName;
  }

  public String getPayerEmail() {
    return payerEmail;
  }

  public String getPaymentCardRequestId() {
    return paymentCardRequestId;
  }

  public LocalDate getCreationDate() {
    return creationDate == null ? null : creationDate;
  }

  public LocalDate getExpirationDate() {
    return expirationDate == null ? null : expirationDate;
  }

  public Boolean getMultiPayment() {
    return multiPayment;
  }

  public Long getLease() {
    return lease;
  }

  public ChargeSolutionBusinessUnit getChargeSolutionBusinessUnit() {
    return chargeSolutionBusinessUnit;
  }
  
  public String getChargeSolutionBusinessUnitAsString() {
    return chargeSolutionBusinessUnit!=null?chargeSolutionBusinessUnit.toString():null;
  }

  public PaymentReferenceCreationType getPaymentReferenceCreationType() {
    return paymentReferenceCreationType;
  }

  public String getPaymentReferenceCreationTypeAsString() {
    return paymentReferenceCreationType!=null?paymentReferenceCreationType.toString():PaymentReferenceCreationType.EMAIL.toString();
  }
  
  public ReminderFrequency getReminderFrequency() {
    return reminderFrequency;
  }
  
  public String getReminderFrequencyAsString() {
    return reminderFrequency!=null?reminderFrequency.toString():null;
  }

  public LocalDate getNextReminderDate() {
    return nextReminderDate == null ? null : nextReminderDate;
  }

  public String getReference() {
    return reference;
  }

  public Boolean getMigrated() {
    return migrated;
  }

  public String getPaymentCardsStockId() {
    return paymentCardsStockId;
  }

  public LocalDate getAssociationDate() {
    return associationDate == null ? null : associationDate;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public void setPaymentReferenceCode(String paymentReferenceCode) {
    this.paymentReferenceCode = paymentReferenceCode;
  }

  public void setPaymentReferenceType(PaymentReferenceType paymentReferenceType) {
    this.paymentReferenceType = paymentReferenceType;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPaymentReferenceState(PaymentReferenceState paymentReferenceState) {
    this.paymentReferenceState = paymentReferenceState;
  }

  public void setPayerName(String payerName) {
    this.payerName = payerName;
  }

  public void setPayerEmail(String payerEmail) {
    this.payerEmail = payerEmail;
  }

  public void setPaymentCardRequestId(String paymentCardRequestId) {
    this.paymentCardRequestId = paymentCardRequestId;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate == null ? null : creationDate;

    if (this.creationDate != null && associationDate == null) {
      associationDate = this.creationDate;
    }
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate == null ? null : expirationDate;
  }

  public void setMultiPayment(Boolean multiPayment) {
    this.multiPayment = multiPayment;
  }

  public void setLease(Long lease) {
    this.lease = lease;
  }

  public void setChargeSolutionBusinessUnit(ChargeSolutionBusinessUnit chargeSolutionBusinessUnit) {
    this.chargeSolutionBusinessUnit = chargeSolutionBusinessUnit;
  }

  public void setPaymentReferenceCreationType(
      PaymentReferenceCreationType paymentReferenceCreationType) {
    this.paymentReferenceCreationType = paymentReferenceCreationType;
  }

  public void setReminderFrequency(ReminderFrequency reminderFrequency) {
    this.reminderFrequency = reminderFrequency;
  }

  public void setNextReminderDate(LocalDate nextReminderDate) {
    this.nextReminderDate = nextReminderDate == null ? null : nextReminderDate;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public void setMigrated(Boolean migrated) {
    this.migrated = migrated;
  }

  public void setPaymentCardsStockId(String paymentCardsStockId) {
    this.paymentCardsStockId = paymentCardsStockId;
  }

  public void setAssociationDate(LocalDate associationDate) {
    this.associationDate = associationDate == null ? null : associationDate;
  }

}
