package com.mercadopago.android.px.internal.features.paymentresult.components;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mercadopago.android.px.internal.features.PaymentResultViewModelFactory;
import com.mercadopago.android.px.internal.features.paymentresult.props.BodyErrorProps;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.internal.view.ActionDispatcher;
import com.mercadopago.android.px.internal.view.Component;
import com.mercadopago.android.px.internal.view.RecoverPaymentAction;
import com.mercadopago.android.px.internal.viewmodel.PaymentResultViewModel;

public class BodyError extends Component<BodyErrorProps, Void> {

    public BodyError(@NonNull final BodyErrorProps props,
        @NonNull final ActionDispatcher dispatcher) {
        super(props, dispatcher);
    }

    public String getTitle(final Context context) {
        final PaymentResultViewModel viewModel = PaymentResultViewModelFactory
            .createPaymentResultViewModel(props.status, props.statusDetail);
        if (viewModel.hasBodyTitle()) {
            return viewModel.getBodyTitle(context);
        }
        return TextUtil.EMPTY;
    }

    public String getDescription(final Context context) {
        final PaymentResultViewModel viewModel = PaymentResultViewModelFactory
            .createPaymentStatusWithProps(props.status, props.statusDetail, props);
        return viewModel.getDescription(context);
    }

    public String getTitleDescription(final Context context) {
        final PaymentResultViewModel viewModel = PaymentResultViewModelFactory
            .createPaymentStatusWithProps(props.status, props.statusDetail, props);
        return viewModel.getTitleDescription(context);
    }

    /* default */ void recoverPayment() {
        getDispatcher().dispatch(new RecoverPaymentAction());
    }
}
