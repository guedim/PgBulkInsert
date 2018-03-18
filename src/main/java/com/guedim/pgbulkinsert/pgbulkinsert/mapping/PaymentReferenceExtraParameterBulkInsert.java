package com.guedim.pgbulkinsert.pgbulkinsert.mapping;

import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceExtraParameter;

import de.bytefish.pgbulkinsert.PgBulkInsert;

public class PaymentReferenceExtraParameterBulkInsert
    extends PgBulkInsert<PaymentReferenceExtraParameter> {

  public PaymentReferenceExtraParameterBulkInsert() {

    super("pps", "referencia_pago_parametro_extra");
    
    mapInteger("referencia_pago_parametro_extra_id", PaymentReferenceExtraParameter::getId);
    mapString("tipo", PaymentReferenceExtraParameter::getType);
    mapString("valor", PaymentReferenceExtraParameter::getValue);
    mapString("nombre", PaymentReferenceExtraParameter::getNameAsString);
  }

}