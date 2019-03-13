package com.mercadopago.android.px.internal.features.paymentresult.props;

import android.support.annotation.NonNull;
import java.math.BigInteger;

/**
 * Created by vaserber on 27/11/2017.
 */

public class BodyErrorProps {

    public final String status;
    public final String statusDetail;
    public final String paymentMethodName;
    public final BigInteger paymentAmount;

    public BodyErrorProps(@NonNull final Builder builder) {
        status = builder.status;
        statusDetail = builder.statusDetail;
        paymentMethodName = builder.paymentMethodName;
        paymentAmount = builder.paymentAmount;
    }

    public Builder toBuilder() {
        return new Builder()
            .setStatus(status)
            .setStatusDetail(statusDetail)
            .setPaymentMethodName(paymentMethodName)
            .setPaymentAmount(paymentAmount);
    }

    public static class Builder {

        public String status;
        public String statusDetail;
        public String paymentMethodName;
        public BigInteger paymentAmount;

        public Builder setStatus(@NonNull final String status) {
            this.status = status;
            return this;
        }

        public Builder setStatusDetail(@NonNull final String statusDetail) {
            this.statusDetail = statusDetail;
            return this;
        }

        public Builder setPaymentMethodName(final String paymentMethodName) {
            this.paymentMethodName = paymentMethodName;
            return this;
        }

        public Builder setPaymentAmount(final BigInteger paymentAmount) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public BodyErrorProps build() {
            return new BodyErrorProps(this);
        }
    }
}
