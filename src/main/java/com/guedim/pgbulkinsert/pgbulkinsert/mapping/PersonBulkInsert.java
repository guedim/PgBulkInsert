package com.guedim.pgbulkinsert.pgbulkinsert.mapping;

import com.guedim.pgbulkinsert.pgbulkinsert.model.Person;

import de.bytefish.pgbulkinsert.PgBulkInsert;

public class PersonBulkInsert extends PgBulkInsert<Person> {

  public PersonBulkInsert() {
    super("pps", "person_example");

    mapString("first_name", Person::getFirstName);
    mapString("last_name", Person::getLastName);
    mapDate("birth_date", Person::getBirthDate);
  }
}
