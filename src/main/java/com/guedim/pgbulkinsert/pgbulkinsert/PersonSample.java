package com.guedim.pgbulkinsert.pgbulkinsert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.guedim.pgbulkinsert.pgbulkinsert.model.Person;

public final class PersonSample {

  public static List<Person> getPersonList(int numPersons) {
    List<Person> persons = new ArrayList<>();

    for (int pos = 0; pos < numPersons; pos++) {
      Person p = new Person();

      p.setFirstName("Philipp");
      p.setLastName("Wagner");
      p.setBirthDate(LocalDate.of(1986, 5, 12));

      persons.add(p);
    }

    return persons;
  }
}
