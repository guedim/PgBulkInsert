package com.guedim.pgbulkinsert.pgbulkinsert.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guedim.pgbulkinsert.pgbulkinsert.mapping.PaymentReferenceBulkInsert;
import com.guedim.pgbulkinsert.pgbulkinsert.model.BaseEntity;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReference;

import de.bytefish.pgbulkinsert.pgsql.processor.BulkProcessor;
import de.bytefish.pgbulkinsert.pgsql.processor.handler.BulkWriteHandler;

@Component
public class PaymentReferenceInsertService extends InsertService {

  @Override
  public void importData(List<BaseEntity> data) throws Exception {
    // Create a new BulkProcessor:
    try (BulkProcessor<PaymentReference> bulkProcessor = new BulkProcessor<>(
        new BulkWriteHandler<>(new PaymentReferenceBulkInsert(), () -> dataSource.getConnection()), bulkSize)) {
      // Now process them with the BulkProcessor:
      for (BaseEntity pr : data) {
        bulkProcessor.add((PaymentReference) pr);
      }
    }
  }
}
