package com.guedim.pgbulkinsert.pgbulkinsert;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guedim.pgbulkinsert.pgbulkinsert.mapping.PaymentReferenceAdditionalValueBulkInsert;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceAdditionalValue;
import com.guedim.pgbulkinsert.samples.PaymentReferenceAdditionalValueSample;

import de.bytefish.pgbulkinsert.pgsql.processor.BulkProcessor;
import de.bytefish.pgbulkinsert.pgsql.processor.handler.BulkWriteHandler;

@Component
public class AdditionalValueInsert extends InsertService {

  public void importData() throws Exception {
    // Create a new BulkProcessor:
    try (BulkProcessor<PaymentReferenceAdditionalValue> bulkProcessor = new BulkProcessor<>(
        new BulkWriteHandler<>(new PaymentReferenceAdditionalValueBulkInsert(), () -> dataSource.getConnection()), bulkSize)) {
      // Create some Test data:
      List<PaymentReferenceAdditionalValue> values = PaymentReferenceAdditionalValueSample.getPaymentReferenceAdditionalValueList(1000);
      // Now process them with the BulkProcessor:
      for (PaymentReferenceAdditionalValue prAv : values) {
        bulkProcessor.add(prAv);
      }
    }
  }
}
