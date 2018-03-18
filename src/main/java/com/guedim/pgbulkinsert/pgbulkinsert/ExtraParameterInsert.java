package com.guedim.pgbulkinsert.pgbulkinsert;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guedim.pgbulkinsert.pgbulkinsert.mapping.PaymentReferenceExtraParameterBulkInsert;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceExtraParameter;
import com.guedim.pgbulkinsert.samples.PaymentReferenceExtraParameterSample;

import de.bytefish.pgbulkinsert.pgsql.processor.BulkProcessor;
import de.bytefish.pgbulkinsert.pgsql.processor.handler.BulkWriteHandler;

@Component
public class ExtraParameterInsert extends InsertService {

  public void importData() throws Exception {
    // Create a new BulkProcessor:
    try (BulkProcessor<PaymentReferenceExtraParameter> bulkProcessor = new BulkProcessor<>(
        new BulkWriteHandler<>(new PaymentReferenceExtraParameterBulkInsert(), () -> dataSource.getConnection()), bulkSize)) {
      // Create some Test data:
      List<PaymentReferenceExtraParameter> values = PaymentReferenceExtraParameterSample.getPaymentReferenceExtraParameterList(1000);
      // Now process them with the BulkProcessor:
      for (PaymentReferenceExtraParameter prEp : values) {
        bulkProcessor.add(prEp);
      }
    }
  }
}
