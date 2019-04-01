package com.mercadopago.android.px.internal.features.payment_vault;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.model.CustomSearchItem;
import com.mercadopago.android.px.model.PaymentMethodSearchItem;

public interface SearchItemOnClickListenerHandler {
    void selectItem(@NonNull final CustomSearchItem item);

    void selectItem(@NonNull final PaymentMethodSearchItem item);

    void showDisabledPaymentMethodDetailDialog(@NonNull final String paymentMethodType);
}