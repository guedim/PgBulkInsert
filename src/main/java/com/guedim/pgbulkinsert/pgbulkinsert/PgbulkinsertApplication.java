package com.guedim.pgbulkinsert.pgbulkinsert;

import static java.lang.System.exit;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guedim.pgbulkinsert.pgbulkinsert.mapping.PaymentReferenceBulkInsert;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReference;

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
    final int bulkSize = 1;
    // Create a new BulkProcessor:
    try (BulkProcessor<PaymentReference> bulkProcessor = new BulkProcessor<>(
        new BulkWriteHandler<>(new PaymentReferenceBulkInsert(), ()->dataSource.getConnection()), bulkSize)) {
      // Create some Test data:
      List<PaymentReference> values = PaymentReferenceSample.getPaymentReferenceList(1000); 
      // Now process them with the BulkProcessor:
      for (PaymentReference pr : values) {
        bulkProcessor.add(pr);
      }
    }
    exit(0);
  }
}