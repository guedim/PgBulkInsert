package com.guedim.pgbulkinsert.pgbulkinsert.mapping;

import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReference;

import de.bytefish.pgbulkinsert.PgBulkInsert;
import de.bytefish.pgbulkinsert.pgsql.handlers.IValueHandlerProvider;

public class PaymentReferenceBulkInsert extends PgBulkInsert<PaymentReference>  {

  public PaymentReferenceBulkInsert(IValueHandlerProvider provider, String schemaName,
      String tableName) {
    
    super("pps", "referencia_pago");
    mapInteger("referencia_pago_id", PaymentReference::getId);
    mapInteger("usuario_id", PaymentReference::getMerchantId);
    mapInteger("cuenta_id", PaymentReference::getAccountId);
    
    
    
  }

}
