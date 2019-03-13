package com.mercadopago.android.px.internal.viewmodel;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.widget.ImageView;

public interface PaymentMethodViewModel {

    @VisibleForTesting
    String getPaymentMethodId();

    String getDescription();

    String getDiscountInfo();

    String getComment();

    @DrawableRes
    int getIconResourceId(@NonNull final Context context);

    @DrawableRes
    int getBadgeResourceId(@NonNull final Context context);

    boolean isDisabled();

    void handleOnClick();

    void tint(@NonNull final ImageView icon);
}