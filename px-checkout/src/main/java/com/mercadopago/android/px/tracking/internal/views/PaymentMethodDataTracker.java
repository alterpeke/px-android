package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mercadopago.android.px.model.PaymentMethod;
import com.mercadopago.android.px.tracking.internal.model.PaymentMethodData;

import java.util.Map;

public abstract class PaymentMethodDataTracker extends ViewTracker {

    @NonNull
    private PaymentMethodData paymentMethodData;

    PaymentMethodDataTracker(@Nullable PaymentMethod model) {
        paymentMethodData = PaymentMethodData.from(model);
    }

    @NonNull
    @Override
    public Map<String, Object> getData() {
        return paymentMethodData.toMap();
    }
}
