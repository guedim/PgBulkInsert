package com.guedim.pgbulkinsert.pgbulkinsert.services;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.guedim.pgbulkinsert.pgbulkinsert.EntityToImport;
import com.guedim.pgbulkinsert.pgbulkinsert.model.BaseEntity;

@Component
public class InsertService {
  
  private static final Logger logger = LoggerFactory.getLogger(InsertService.class);

  protected final int bulkSize = 10000;
  @Autowired
  protected DataSource dataSource;
  @Autowired
  ApplicationContext applicationContext;

  public void importData(List<BaseEntity> data) throws Exception {};


  public InsertService getInsertService(EntityToImport entity) {

    if (EntityToImport.PAYMENT_REFERENCE.equals(entity)) {
      logger.info("prepare to import a payment reference entity");
      return applicationContext.getBean(PaymentReferenceInsertService.class);
    }

    if (EntityToImport.EXTRA_PARAMETER.equals(entity)) {
      logger.info("prepare to import an extra parameter entity");
      return applicationContext.getBean(ExtraParameterInsertService.class);
    }

    if (EntityToImport.ADDITIONAL_VALUE.equals(entity)) {
      logger.info("prepare to import an additional value reference entity");
      return applicationContext.getBean(AdditionalValueInsertService.class);
    }
    logger.error("there is not a valid service to import data");
    return null;
  }
}