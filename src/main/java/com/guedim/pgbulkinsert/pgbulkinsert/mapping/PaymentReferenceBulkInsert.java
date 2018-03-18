package com.guedim.pgbulkinsert.pgbulkinsert.mapping;

import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReference;

import de.bytefish.pgbulkinsert.PgBulkInsert;

public class PaymentReferenceBulkInsert extends PgBulkInsert<PaymentReference> {

  public PaymentReferenceBulkInsert() {

    super("pps", "referencia_pago");
 
    mapInteger("referencia_pago_id", PaymentReference::getId);
    mapInteger("usuario_id", PaymentReference::getMerchantId);
    mapInteger("cuenta_id", PaymentReference::getAccountId);
    mapString("codigo_referencia_pago", PaymentReference::getPaymentReferenceCode);
    mapString("tipo_referencia_pago", PaymentReference::getPaymentReferenceTypeAsString);
    mapString("descripcion", PaymentReference::getDescription);
    mapString("estado", PaymentReference::getPaymentReferenceStateAsString);
    mapString("pagador_nombre", PaymentReference::getPayerName);
    mapString("pagador_email", PaymentReference::getPayerEmail);
    mapString("solicitud_tarjeta_id", PaymentReference::getPaymentCardRequestId);
    mapTimeStamp("fecha_creacion", PaymentReference::getCreationDate);
    mapTimeStamp("fecha_vencimiento", PaymentReference::getExpirationDate);
    mapBoolean("multipago", PaymentReference::getMultiPayment);
    mapLong("lease", PaymentReference::getLease);
    mapString("unidad_negocio", PaymentReference::getChargeSolutionBusinessUnitAsString);
    mapString("tipo_creacion", PaymentReference::getPaymentReferenceCreationTypeAsString);
    mapString("frecuencia_recordatorio", PaymentReference::getReminderFrequencyAsString);
    mapTimeStamp("fecha_proximo_recordatorio", PaymentReference::getNextReminderDate);
    mapString("referencia", PaymentReference::getReference);
    mapBoolean("migrado", PaymentReference::getMigrated);
    mapString("inventario_tarjeta_cobranza_id", PaymentReference::getPaymentCardsStockId);
    mapTimeStamp("fecha_asociacion", PaymentReference::getAssociationDate);
  }
}