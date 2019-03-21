package com.mercadopago.android.px.internal.features.paymentresult.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewGroup;
import com.mercadopago.android.px.internal.viewmodel.PaymentResultViewModel;
import com.mercadopago.android.px.internal.features.PaymentResultViewModelFactory;
import com.mercadopago.android.px.internal.view.ActionDispatcher;
import com.mercadopago.android.px.internal.view.Button;
import com.mercadopago.android.px.internal.view.CompactComponent;
import com.mercadopago.android.px.internal.view.Footer;
import com.mercadopago.android.px.model.PaymentResult;
import javax.annotation.Nonnull;

public class FooterPaymentResult extends CompactComponent<PaymentResult, ActionDispatcher> {

    /* default */ FooterPaymentResult(@NonNull final PaymentResult props, @NonNull final ActionDispatcher dispatcher) {
        super(props, dispatcher);
    }

    @Override
    public View render(@Nonnull final ViewGroup parent) {
        return new Footer(getFooterProps(parent.getContext()), getActions()).render(parent);
    }

    @VisibleForTesting
    Footer.Props getFooterProps(@NonNull final Context context) {

        Button.Props buttonAction = null;
        Button.Props linkAction = null;

        final PaymentResultViewModel paymentResultViewModel = PaymentResultViewModelFactory
            .createPaymentResultViewModel(props);

        if (paymentResultViewModel.getLinkAction() != null) {
            linkAction = new Button.Props(paymentResultViewModel.getLinkActionTitle(context),
                paymentResultViewModel.getLinkAction());
        }

        if (paymentResultViewModel.getMainAction() != null) {
            buttonAction = new Button.Props(paymentResultViewModel.getMainActionTitle(context),
                paymentResultViewModel.getMainAction());
        }

        return new Footer.Props(buttonAction, linkAction);
    }
}