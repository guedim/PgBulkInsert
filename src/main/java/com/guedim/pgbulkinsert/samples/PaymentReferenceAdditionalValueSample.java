package com.guedim.pgbulkinsert.samples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceAddicionalValueName;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceAdditionalValue;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceCurrency;

public class PaymentReferenceAdditionalValueSample {

  
  public static List<PaymentReferenceAdditionalValue> getPaymentReferenceAdditionalValueList(int numValues) {
    List<PaymentReferenceAdditionalValue> values = new ArrayList<>();

    for (int pos = 0; pos < numValues; pos++) {
      PaymentReferenceAdditionalValue prAv = new PaymentReferenceAdditionalValue();

      prAv.setId(pos);
      prAv.setCurrency(PaymentReferenceCurrency.ARS);
      prAv.setValue(new BigDecimal(10000.999));
      prAv.setName(PaymentReferenceAddicionalValueName.PM_NETWORK_VALUE);
      
      values.add(prAv);
            
    }

    return values;
  }
}
