package com.guedim.pgbulkinsert.pgbulkinsert;

import java.util.ArrayList;
import java.util.List;

import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceExtraParameter;
import com.guedim.pgbulkinsert.pgbulkinsert.model.PaymentReferenceExtraParameterName;

public class PaymentReferenceExtraParameterSample {

  
  public static List<PaymentReferenceExtraParameter> getPaymentReferenceExtraParameterList(int numValues) {
    List<PaymentReferenceExtraParameter> values = new ArrayList<>();

    for (int pos = 0; pos < numValues; pos++) {
      PaymentReferenceExtraParameter prEp = new PaymentReferenceExtraParameter();

      prEp.setId(pos);
      prEp.setType("tipo");
      prEp.setValue("value");
      prEp.setName(PaymentReferenceExtraParameterName.AUTHORIZATION_STATUS);
      
      values.add(prEp);
            
    }

    return values;
  }
}
