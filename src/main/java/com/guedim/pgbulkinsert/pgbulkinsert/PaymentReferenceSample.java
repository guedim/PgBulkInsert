package com.guedim.pgbulkinsert.pgbulkinsert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.guedim.pgbulkinsert.pgbulkinsert.model.ChargeSolutionBusinessUnit;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReference;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceCreationType;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceState;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceType;

public final class PaymentReferenceSample {

  public static List<PaymentReference> getPaymentReferenceList(int numPersons) {
    List<PaymentReference> references = new ArrayList<>();

    for (int pos = 0; pos < numPersons; pos++) {
      PaymentReference p = new PaymentReference();

      p.setId(pos);
      p.setPaymentReferenceCode("reference code");
      p.setPaymentReferenceType(PaymentReferenceType.PAYMENT_CARD);
      p.setDescription("description");
      p.setPaymentReferenceState(PaymentReferenceState.ENABLED);
      p.setCreationDate(LocalDate.now());
      p.setMultiPayment(Boolean.TRUE);
      p.setLease(0l);
      p.setChargeSolutionBusinessUnit(ChargeSolutionBusinessUnit.ORDERS);
      p.setPaymentReferenceCreationType(PaymentReferenceCreationType.CARD);
      p.setAssociationDate(LocalDate.now());
      references.add(p);
    }

    return references;
  }
}