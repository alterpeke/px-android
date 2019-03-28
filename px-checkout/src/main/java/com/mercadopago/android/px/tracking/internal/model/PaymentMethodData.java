package com.mercadopago.android.px.tracking.internal.model;

import android.support.annotation.Nullable;

import com.mercadopago.android.px.model.PaymentMethod;

public class PaymentMethodData extends TrackingMapModel {

    @Nullable private String paymentMethodId;

    private PaymentMethodData(@Nullable final String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    private PaymentMethodData() {
        // Empty Constructor
    }

    public static PaymentMethodData from(@Nullable final PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            return new PaymentMethodData();
        }

        return new PaymentMethodData(paymentMethod.getId());
    }
}
