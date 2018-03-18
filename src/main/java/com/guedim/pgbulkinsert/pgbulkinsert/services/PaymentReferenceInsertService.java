package com.guedim.pgbulkinsert.pgbulkinsert.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guedim.pgbulkinsert.pgbulkinsert.mapping.PaymentReferenceBulkInsert;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReference;
import com.guedim.pgbulkinsert.samples.PaymentReferenceSample;

import de.bytefish.pgbulkinsert.pgsql.processor.BulkProcessor;
import de.bytefish.pgbulkinsert.pgsql.processor.handler.BulkWriteHandler;

@Component
public class PaymentReferenceInsertService extends InsertService {

  public void importData() throws Exception {
    // Create a new BulkProcessor:
    try (BulkProcessor<PaymentReference> bulkProcessor = new BulkProcessor<>(
        new BulkWriteHandler<>(new PaymentReferenceBulkInsert(), () -> dataSource.getConnection()), bulkSize)) {
      // Create some Test data:
      List<PaymentReference> values = PaymentReferenceSample.getPaymentReferenceList(1000);
      // Now process them with the BulkProcessor:
      for (PaymentReference pr : values) {
        bulkProcessor.add(pr);
      }
    }
  }
}
