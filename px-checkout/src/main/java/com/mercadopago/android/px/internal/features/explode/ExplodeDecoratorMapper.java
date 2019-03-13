package com.mercadopago.android.px.internal.features.explode;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.features.PaymentResultViewModelFactory;
import com.mercadopago.android.px.internal.features.paymentresult.PaymentResultDecorator;
import com.mercadopago.android.px.internal.viewmodel.mappers.Mapper;
import com.mercadopago.android.px.model.IPayment;

public class ExplodeDecoratorMapper extends Mapper<IPayment, ExplodeDecorator> {

    @Override
    public ExplodeDecorator map(@NonNull final IPayment val) {
        final PaymentResultDecorator decorator = PaymentResultViewModelFactory.createPaymentResultDecorator(val);
        return new ExplodeDecorator(decorator.getPrimaryColor(), decorator.getPrimaryDarkColor(),
            decorator.getStatusIcon());
    }
}
