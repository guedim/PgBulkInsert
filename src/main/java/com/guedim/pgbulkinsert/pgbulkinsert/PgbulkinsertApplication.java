package com.guedim.pgbulkinsert.pgbulkinsert;

import static java.lang.System.exit;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guedim.pgbulkinsert.file.FileCellProcessor;
import com.guedim.pgbulkinsert.pgbulkinsert.model.BaseEntity;
import com.guedim.pgbulkinsert.pgbulkinsert.services.InsertService;


@SpringBootApplication
public class PgbulkinsertApplication implements CommandLineRunner {
  
  private static final Logger logger = LoggerFactory.getLogger(PgbulkinsertApplication.class);    
  
  private EntityToImport entity;
  private File file;
  
  @Autowired
  private InsertService insertService;
  
  public static void main(String[] args) {
    SpringApplication.run(PgbulkinsertApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    logger.info("Application started with arguments:" + args);

    if (!isValidArgs(args)) {
      logger.error("**************************************************************************************************************************");
      logger.error("java -jar pgbulkinsert-1.0.0.jar  {PAYMENT_REFERENCE | EXTRA_PARAMETER | ADDITIONAL_VALUE}  {'filename'}");
      logger.error("    PAYMENT_REFERENCE: Set to import the data into PaymentReference entity.");
      logger.error("    EXTRA_PARAMETER: Set to import the data into PaymentReference Extra Parameter entity.");
      logger.error("    ADDITIONAL_VALUE: Set to import the data into PaymentReference Additional values entity.");
      logger.error("    'filename': The absolute path name of the input file.");
      logger.error("**************************************************************************************************************************");
      exit(1);
    } 

    
    logger.info("Starting processing file...");
    FileCellProcessor fileProcessor = FileCellProcessor.getFileCellProcessor(entity);
    List<BaseEntity> data = fileProcessor.readWithCsvBeanReader(file.getAbsolutePath());
    
    logger.info("Starting copy data into database...");
    InsertService service =  insertService.getInsertService(entity);
    service.importData(data);
    
    logger.info("Completed processing...");
    exit(0);
  }
  
  private boolean isValidArgs(String... args) {
    if (args == null || args.length != 2) {
      logger.error("Error in parameter values. Please provide just 2 parameter: {TABLE} and {FILE_NAME} !!!");
      return false;
    }
    
    try {
      entity =  EntityToImport.valueOf(args[0].trim());
    } catch (Exception e) {
      logger.error("First parameter is not Valid: {"+ args[0]+ "}.  Please use: {PAYMENT_REFERENCE | EXTRA_PARAMETER | ADDITIONAL_VALUE} ");
      return false;
    }
    file = new File(args[1].trim());
    if(!file.exists() || file.isDirectory()) { 
      logger.error("The file name is not valid.");
      return false;
    } 
    return true;
  }   
}