package com.guedim.pgbulkinsert.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
import org.supercsv.exception.SuperCsvConstraintViolationException;
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
      //beanReader = new CsvBeanReader(new FileReader(file), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
      
      File fileDir = new File(file);
      BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
      CsvPreference cp = new CsvPreference.Builder(',', ';', "\n").build(); 
      
      //SkipBadColumnCountTokenizer tokenizer = new SkipBadColumnCountTokenizer(in, cp, 22);
      beanReader = new CsvBeanReader(in, cp);

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

    } catch (SuperCsvConstraintViolationException e) {
      logger.error("error validando archivo referencia pago:" +  ((SuperCsvConstraintViolationException)e).getCsvContext());
      throw e;
    } catch (Exception e) {
      logger.error("error validando archivo referencia pago:" +  e.getMessage());
      throw e;
    } 
    finally {
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

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        
        final CellProcessor[] processors = new CellProcessor[] {
            new NotNull(new RemoveDotsCellProcessor(new ParseInt())),           // referencia_pago_id (must be unique)
            new Optional(new RemoveDotsCellProcessor(new ParseInt())),          // usuario_id
            new Optional(new RemoveDotsCellProcessor(new ParseInt())),          // cuenta_id
            new NotNull(),                                                      // codigo_referencia_pago
            new NotNull(new ParseEnum(PaymentReferenceType.class)),             // tipo_referencia_pago
            new NotNull(new RemoveDotsCellProcessor()),                         // description
            new NotNull(new ParseEnum(PaymentReferenceState.class)),            // estado
            new Optional(new RemoveDotsCellProcessor()),                        // pagador_nombre
            new Optional(new RemoveDotsCellProcessor()),                        // pagador_email
            new Optional(new RemoveDotsCellProcessor()),                        // solicitud_tarjeta_id
            new NotNull(new ParseLocalDateTime(formatter)),                     // fecha_creacion
            new Optional(new ParseLocalDateTime(formatter)),                    // fecha_vencimiento
            new Optional(new ParseBool()),                                      // multipago
            new NotNull(new RemoveDotsCellProcessor(new ParseLong())),          // lease
            new NotNull(new ParseEnum(ChargeSolutionBusinessUnit.class)),       // unidad_de_negocio
            new NotNull(new ParseEnum(PaymentReferenceCreationType.class)),     // tipo creacion
            new Optional(new ParseEnum(ReminderFrequency.class)),               // frecuencia recordatorio
            new Optional(new ParseLocalDateTime(formatter)),                    // fecha_prox_recordatorio
            new Optional(new RemoveDotsCellProcessor()),                        // referencia
            new Optional(new ParseBool()),                                      // migrado
            new Optional(new RemoveDotsCellProcessor()),                        // inventario_tarjeta_cobranza_id
            new NotNull(new ParseLocalDateTime(formatter))                      // fecha_asociacion
        };
        return processors;
  }
}