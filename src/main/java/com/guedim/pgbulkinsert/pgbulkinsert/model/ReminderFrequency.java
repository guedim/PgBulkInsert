package com.guedim.pgbulkinsert.pgbulkinsert.model;

import java.util.Calendar;

public enum ReminderFrequency {

  /**
   * Without reminder
   */
  NO_REMINDER(Calendar.DATE, 0),

  /**
   * Weekly reminder
   */
  WEEKLY(Calendar.DATE, 7),

  /**
   * Biweekly reminder
   */
  BIWEEKLY(Calendar.DATE, 14),

  /**
   * Monthly reminder
   */
  MONTHLY(Calendar.MONTH, 1),

  /**
   * Bimonthly reminder
   */
  BIMONTHLY(Calendar.MONTH, 2),

  /**
   * Quarterly reminder
   */
  QUARTERLY(Calendar.MONTH, 3);

  /**
   * The time field to add
   */
  private final int timeField;

  /**
   * The time quantity to add
   */
  private final int timeQuantity;

  /**
   * Default constructor
   * 
   * @param timeField
   * @param timeQuantity
   */
  private ReminderFrequency(int timeField, int timeQuantity) {

    this.timeField = timeField;
    this.timeQuantity = timeQuantity;
  }

  /**
   * @return the timeField
   */
  public int getTimeField() {

    return timeField;
  }

  /**
   * @return the timeQuantity
   */
  public int getTimeQuantity() {

    return timeQuantity;
  }

  /**
   * Gets the key property according to the value
   * 
   * @return key property
   */
  public String getKeyProperty() {
    StringBuilder sb = new StringBuilder(ReminderFrequency.class.getSimpleName());
    sb.append(".");

    return sb.append(this.name()).toString();

  }

}
