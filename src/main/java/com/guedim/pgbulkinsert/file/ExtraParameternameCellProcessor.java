package com.guedim.pgbulkinsert.file;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceExtraParameterName;

public class ExtraParameternameCellProcessor extends CellProcessorAdaptor {

  public ExtraParameternameCellProcessor() {
    super();
  }

  public ExtraParameternameCellProcessor(CellProcessor next) {
    // this constructor allows other processors to be chained after ParseDay
    super(next);
  }

  @Override
  public <T> T execute(Object value, CsvContext context) {

    validateInputNotNull(value, context); // throws an Exception if the input is null

    for (PaymentReferenceExtraParameterName name : PaymentReferenceExtraParameterName.values()) {
      if (name.getName().equalsIgnoreCase(value.toString())) {
        // passes the Day enum to the next processor in the chain
        return next.execute(name, context);
      }
    }

    throw new SuperCsvCellProcessorException(String.format("Could not parse '%s' as a extra parameter name", value),
        context, this);
  }
}
