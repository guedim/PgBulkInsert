package com.guedim.pgbulkinsert.pgbulkinsert.model;

import java.time.LocalDate;

public class Person {

  private String firstName;

  private String lastName;

  private LocalDate birthDate;

  public Person() {}

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

}
