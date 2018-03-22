package com.guedim.pgbulkinsert.pgbulkinsert.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guedim.pgbulkinsert.pgbulkinsert.mapping.PaymentReferenceExtraParameterBulkInsert;
import com.guedim.pgbulkinsert.pgbulkinsert.model.BaseEntity;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceExtraParameter;

import de.bytefish.pgbulkinsert.pgsql.processor.BulkProcessor;
import de.bytefish.pgbulkinsert.pgsql.processor.handler.BulkWriteHandler;

@Component
public class ExtraParameterInsertService extends InsertService {

  @Override
  public void importData(List<BaseEntity> data) throws Exception {
    // Create a new BulkProcessor:
    try (BulkProcessor<PaymentReferenceExtraParameter> bulkProcessor = new BulkProcessor<>(
        new BulkWriteHandler<>(new PaymentReferenceExtraParameterBulkInsert(), () -> dataSource.getConnection()), bulkSize)) {
      // Now process them with the BulkProcessor:
      for (BaseEntity prEp : data) {
        bulkProcessor.add((PaymentReferenceExtraParameter) prEp);
      }
    }
  }
}
