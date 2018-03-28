package com.guedim.pgbulkinsert.file;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.ICsvBeanReader;

import com.guedim.pgbulkinsert.pgbulkinsert.EntityToImport;
import com.guedim.pgbulkinsert.pgbulkinsert.model.BaseEntity;

public class FileCellProcessor {
  
  protected static final Logger logger = LoggerFactory.getLogger(FileCellProcessor.class);

  private final int logRowNumber = 100000;
  
  public List<BaseEntity> readWithCsvBeanReader(String file) throws Exception {
    return null;
  }
  
  public void log(ICsvBeanReader beanReader) {
    if(beanReader.getLineNumber()%logRowNumber==0) {
      logger.info(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(), beanReader.getRowNumber(), this.getClass()));
    }
  }
  
  
  public static FileCellProcessor getFileCellProcessor(EntityToImport entity) {

    if (EntityToImport.PAYMENT_REFERENCE.equals(entity)) {
      logger.info("prepare to read a payment reference file");
      return new PaymentReferenceFileProcessor();
    }

    if (EntityToImport.EXTRA_PARAMETER.equals(entity)) {
      logger.info("prepare to read an extra parameter file");
      return new ExtraParameterFileProcessor();
    }

    if (EntityToImport.ADDITIONAL_VALUE.equals(entity)) {
      logger.info("prepare to read an additional value reference file");
      return new AdditionalValueFileProcessor();
    }
    logger.error("there is not a valid service to import data");
    return null;
  }

}
