package com.guedim.pgbulkinsert.file;

import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseEnum;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.time.ParseLocalDateTime;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.guedim.pgbulkinsert.pgbulkinsert.model.BaseEntity;
import com.guedim.pgbulkinsert.pgbulkinsert.model.ChargeSolutionBusinessUnit;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReference;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceCreationType;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceState;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceType;
import com.guedim.pgbulkinsert.pgbulkinsert.model.ReminderFrequency;

public final class PaymentReferenceFileProcessor extends FileCellProcessor {
  
  @Override
  public List<BaseEntity>  readWithCsvBeanReader(String file) throws Exception {

    List<BaseEntity> paymentReferenceList = null;
    ICsvBeanReader beanReader = null;
    try {
      beanReader = new CsvBeanReader(new FileReader(file), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

      // the header elements are used to map the values to the bean (names must match)
      beanReader.getHeader(false);
      final String[] header = getHeaders();
      final CellProcessor[] processors = getProcessors();
      paymentReferenceList = new ArrayList<>();
      
      PaymentReference paymentReference;
      while ((paymentReference = beanReader.read(PaymentReference.class, header, processors)) != null) {
        log(beanReader);
        paymentReferenceList.add(paymentReference);
      }

    } finally {
      if (beanReader != null) {
        beanReader.close();
      }
    }
    return paymentReferenceList;
  }
  
  
  private static String[] getHeaders() {
    return new String[] {"id", "merchantId", "accountId", "paymentReferenceCode",
        "paymentReferenceType", "description", "paymentReferenceState", "payerName", "payerEmail",
        "paymentCardRequestId", "creationDate", "expirationDate", "multiPayment", "lease",
        "chargeSolutionBusinessUnit", "paymentReferenceCreationType", "reminderFrequency",
        "nextReminderDate", "reference", "migrated", "paymentCardsStockId", "associationDate"};
  }

  private static CellProcessor[] getProcessors() {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        final CellProcessor[] processors = new CellProcessor[] {
            new NotNull(new RemoveDotsCellProcessor(new ParseInt())),                       // referencia_pago_id (must be unique)
            new Optional(new RemoveDotsCellProcessor(new ParseInt())),                      // usuario_id
            new Optional(new RemoveDotsCellProcessor(new ParseInt())),                      // cuenta_id
            new NotNull(),                                                                  // codigo_referencia_pago
            new NotNull(new ParseEnum(PaymentReferenceType.class)),                         // tipo_referencia_pago
            new NotNull(),                                                                  // description
            new NotNull(new ParseEnum(PaymentReferenceState.class)),                        // estado
            new Optional(),                                                                 // pagador_nombre
            new Optional(),                                                                 // pagador_email
            new Optional(),                                                                 // solicitud_tarjeta_id
            new NotNull(new ParseLocalDateTime(formatter)),                                 // fecha_creacion
            new Optional(new ParseLocalDateTime(formatter)),                                // fecha_vencimiento
            new NotNull(new ParseBool()),                                                   // multipago
            new NotNull(new RemoveDotsCellProcessor(new ParseLong())),                      // lease
            new NotNull(new ParseEnum(ChargeSolutionBusinessUnit.class)),                   // unidad_de_negocio
            new NotNull(new ParseEnum(PaymentReferenceCreationType.class)),         // tipo creacion
            new Optional(new ParseEnum(ReminderFrequency.class)),                   // frecuencia recordatorio
            new Optional(new ParseLocalDateTime(formatter)),                        // fecha_prox_recordatorio
            new Optional(),                                                         // referencia
            new Optional(new ParseBool()),                                          // migrado
            new Optional(),                                                         // inventario_tarjeta_cobranza_id
            new NotNull(new ParseLocalDateTime(formatter))                          // fecha_asociacion
        };
        return processors;
  }
}