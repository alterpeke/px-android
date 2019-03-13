package com.mercadopago.android.px.internal.features.paymentresult;

import android.support.annotation.NonNull;

public final class PaymentResultDecorator {
    private final int primaryColor;
    private final int primaryDarkColor;
    private final int statusIconResId;


    PaymentResultDecorator(@NonNull final Builder builder) {
        primaryColor = builder.primaryColor;
        primaryDarkColor = builder.primaryDarkColor;
        statusIconResId = builder.statusIconResId;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public int getPrimaryDarkColor() {
        return primaryDarkColor;
    }

    public int getStatusIcon() {
        return statusIconResId;
    }

    public static class Builder {
        int primaryColor;
        int primaryDarkColor;
        int statusIconResId;

        public Builder setPrimaryColor(final int primaryColor) {
            this.primaryColor = primaryColor;
            return this;
        }

        public Builder setPrimaryDarkColor(final int primaryDarkColor) {
            this.primaryDarkColor = primaryDarkColor;
            return this;
        }

        public Builder setStatusIconResId(final int statusIconResId) {
            this.statusIconResId = statusIconResId;
            return this;
        }

        public PaymentResultDecorator build() {
            return new PaymentResultDecorator(this);
        }
    }
}
