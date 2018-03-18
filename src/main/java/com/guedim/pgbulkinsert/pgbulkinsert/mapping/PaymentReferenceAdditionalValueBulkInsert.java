package com.guedim.pgbulkinsert.pgbulkinsert.mapping;

import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceAdditionalValue;

import de.bytefish.pgbulkinsert.PgBulkInsert;

public class PaymentReferenceAdditionalValueBulkInsert
    extends PgBulkInsert<PaymentReferenceAdditionalValue> {

  public PaymentReferenceAdditionalValueBulkInsert() {

    super("pps", "referencia_pago_valor_adicional");
   
    mapInteger("referencia_pago_valor_adicional_id", PaymentReferenceAdditionalValue::getId);
    mapString("moneda_iso_4217", PaymentReferenceAdditionalValue::getCurrencyAsString);
    mapNumeric("valor", PaymentReferenceAdditionalValue::getValue);
    mapString("nombre", PaymentReferenceAdditionalValue::getNameAsString);

  }
}