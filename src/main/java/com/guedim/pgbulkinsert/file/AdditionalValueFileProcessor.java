package com.guedim.pgbulkinsert.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.ParseEnum;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.guedim.pgbulkinsert.pgbulkinsert.model.BaseEntity;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceAddicionalValueName;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceAdditionalValue;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceCurrency;

public final class AdditionalValueFileProcessor extends FileCellProcessor {
  
  @Override
  public List<BaseEntity> readWithCsvBeanReader(String file) throws Exception {

    List<BaseEntity> additionalValueList = null;
    ICsvBeanReader beanReader = null;
    try {
      File fileDir = new File(file);
      BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
      CsvPreference cp = new CsvPreference.Builder(',', ';', "\n").build();

      beanReader = new CsvBeanReader(in, cp);
      
      // the header elements are used to map the values to the bean (names must match)
      beanReader.getHeader(false);
      final String[] header = getHeaders();
      final CellProcessor[] processors = getProcessors();
      additionalValueList = new ArrayList<>();
      
      PaymentReferenceAdditionalValue additionalValue;
      while ((additionalValue = beanReader.read(PaymentReferenceAdditionalValue.class, header, processors)) != null) {
        log(beanReader);  
        additionalValueList.add(additionalValue);
      }

    } catch (Exception e) {
      logger.error("error validando archivo additional value:" +  ((SuperCsvConstraintViolationException)e).getCsvContext());
    }  {
      if (beanReader != null) {
        beanReader.close();
      }
    }
    return additionalValueList;
  }
  
  
  private static String[] getHeaders() {
    return new String[] {"id", "currency", "value", "name"};
  }

  private static CellProcessor[] getProcessors() {

    final CellProcessor[] processors = new CellProcessor[] {
        new NotNull(new RemoveDotsCellProcessor(new ParseInt())),               // referencia_pago_id (must be unique)
        new NotNull(new ParseEnum(PaymentReferenceCurrency.class)),             // moneda
        new NotNull(new RemoveDotsCellProcessor(new ParseBigDecimal())),        // valor
        new NotNull(new ParseEnum(PaymentReferenceAddicionalValueName.class))   // nombre
    };
    return processors;
  }
}