package com.mercadopago.android.px.internal.viewmodel;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

public interface PaymentMethodViewModel {

    String getDescription();

    String getPaymentMethodId();

    String getDiscountInfo();

    String getComment();

    @DrawableRes
    int getIconResourceId(@NonNull final Context context);

    void handleOnClick();

    void tint(@NonNull final ImageView icon);
}