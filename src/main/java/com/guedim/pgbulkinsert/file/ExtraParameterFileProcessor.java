package com.guedim.pgbulkinsert.file;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.guedim.pgbulkinsert.pgbulkinsert.model.BaseEntity;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceExtraParameter;

public final class ExtraParameterFileProcessor extends FileCellProcessor {
  
  public List<BaseEntity>  readWithCsvBeanReader(String file) throws Exception {

    List<BaseEntity> extraParameterList = null;
    ICsvBeanReader beanReader = null;
    try {
      beanReader = new CsvBeanReader(new FileReader(file), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

      // the header elements are used to map the values to the bean (names must match)
      beanReader.getHeader(false);
      final String[] header = getHeaders();
      final CellProcessor[] processors = getProcessors();
      extraParameterList = new ArrayList<>();
      
      PaymentReferenceExtraParameter extraParameter;
      while ((extraParameter = beanReader.read(PaymentReferenceExtraParameter.class, header, processors)) != null) {
        log(beanReader);  
        extraParameterList.add(extraParameter);
      }

    } catch (Exception e) {
      logger.error("error validando archivo extra parametro:" +  ((SuperCsvConstraintViolationException)e).getCsvContext());
    } finally {
      if (beanReader != null) {
        beanReader.close();
      }
    }
    return extraParameterList;
  }
  
  
  private static String[] getHeaders() {
    return new String[] {"id", "type", "value", "name"};
  }

  private static CellProcessor[] getProcessors() {

    final CellProcessor[] processors = new CellProcessor[] {
        new NotNull(new RemoveDotsCellProcessor(new ParseInt())),               // referencia_pago_id (must be unique)
        new Optional(new RemoveDotsCellProcessor()),                            // tipo
        new NotNull(),                                                          // valor
        new ExtraParameternameCellProcessor()                                   // nombbre
    };
    return processors;
  }
}