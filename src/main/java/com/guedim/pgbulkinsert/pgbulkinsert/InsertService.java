package com.guedim.pgbulkinsert.pgbulkinsert;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class InsertService {

  protected final int bulkSize = 1;
  @Autowired
  protected DataSource dataSource;
  @Autowired
  ApplicationContext applicationContext;

  void importData() throws Exception {};


  public InsertService getInsertService(EntityToImport entity) {

    if (EntityToImport.PAYMENT_REFERENCE.equals(entity)) {
      return applicationContext.getBean(PaymentReferenceInsert.class);
    }

    if (EntityToImport.EXTRA_PARAMETER.equals(entity)) {
      return applicationContext.getBean(ExtraParameterInsert.class);
    }

    if (EntityToImport.ADDITIONAL_VALUE.equals(entity)) {
      return applicationContext.getBean(AdditionalValueInsert.class);
    }
    return null;
  }
}