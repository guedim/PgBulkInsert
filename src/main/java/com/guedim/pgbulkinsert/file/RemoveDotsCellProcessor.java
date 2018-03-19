package com.guedim.pgbulkinsert.file;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CsvContext;

public class RemoveDotsCellProcessor extends CellProcessorAdaptor {

  public RemoveDotsCellProcessor() {
    super();
  }

  public RemoveDotsCellProcessor(CellProcessor next) {
    // this constructor allows other processors to be chained after ParseDay
    super(next);
  }

  @Override
  public <T> T execute(Object value, CsvContext context) {

    validateInputNotNull(value, context); // throws an Exception if the input is null

    String valueStr = String.valueOf(value);
    valueStr = valueStr.replace(",", "");
    valueStr = valueStr.replace(".", "");
    return next.execute(valueStr, context);
  }

}
