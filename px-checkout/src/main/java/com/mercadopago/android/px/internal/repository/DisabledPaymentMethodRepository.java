package com.mercadopago.android.px.internal.repository;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.model.PaymentResult;

public interface DisabledPaymentMethodRepository {

    boolean hasPaymentMethodId(@NonNull final String paymentMethodId);

    void handleDisableablePayment(@NonNull final PaymentResult paymentResult);

    void reset();
}