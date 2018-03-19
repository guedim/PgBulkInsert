package com.guedim.pgbulkinsert.pgbulkinsert.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guedim.pgbulkinsert.pgbulkinsert.mapping.PaymentReferenceAdditionalValueBulkInsert;
import com.guedim.pgbulkinsert.pgbulkinsert.model.BaseEntity;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceAdditionalValue;

import de.bytefish.pgbulkinsert.pgsql.processor.BulkProcessor;
import de.bytefish.pgbulkinsert.pgsql.processor.handler.BulkWriteHandler;

@Component
public class AdditionalValueInsertService extends InsertService {

  @Override
  public void importData(List<BaseEntity> data) throws Exception {
    // Create a new BulkProcessor:
    try (BulkProcessor<PaymentReferenceAdditionalValue> bulkProcessor = new BulkProcessor<>(
        new BulkWriteHandler<>(new PaymentReferenceAdditionalValueBulkInsert(), () -> dataSource.getConnection()), bulkSize)) {
      // Now process them with the BulkProcessor:
      for (BaseEntity prAv : data) {
        bulkProcessor.add((PaymentReferenceAdditionalValue) prAv);
      }
    }
  }
}
