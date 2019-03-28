package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mercadopago.android.px.model.PaymentMethod;

public final class CPFViewTracker extends PaymentMethodDataTracker {

    public static final String PATH = BASE_VIEW_PATH + PAYMENTS_PATH + "/select_method/ticket/cpf";

    public CPFViewTracker(@Nullable PaymentMethod model) {
        super(model);
    }

    @NonNull
    @Override
    public String getViewPath() {
        return PATH;
    }
}
