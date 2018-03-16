package com.guedim.pgbulkinsert.pgbulkinsert;

import static java.lang.System.exit;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guedim.pgbulkinsert.pgbulkinsert.mapping.PersonBulkInsert;
import com.guedim.pgbulkinsert.pgbulkinsert.model.Person;

import de.bytefish.pgbulkinsert.pgsql.processor.BulkProcessor;
import de.bytefish.pgbulkinsert.pgsql.processor.handler.BulkWriteHandler;


@SpringBootApplication
public class PgbulkinsertApplication implements CommandLineRunner {

  @Autowired
  DataSource dataSource;

  public static void main(String[] args) {
    SpringApplication.run(PgbulkinsertApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    // Bulk Actions after which the batched entities are written:
    final int bulkSize = 10000;
    // Create a new BulkProcessor:
    try (BulkProcessor<Person> bulkProcessor = new BulkProcessor<>(
        new BulkWriteHandler<>(new PersonBulkInsert(), ()->dataSource.getConnection()), bulkSize)) {
      // Create some Test data:
      List<Person> thousandPersons = PersonSample.getPersonList(10000000);
      // Now process them with the BulkProcessor:
      for (Person p : thousandPersons) {
        bulkProcessor.add(p);
      }
    }
    exit(0);
  }
}