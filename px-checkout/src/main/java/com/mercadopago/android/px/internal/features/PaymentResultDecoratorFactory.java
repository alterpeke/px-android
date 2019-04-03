package com.mercadopago.android.px.internal.features;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.features.paymentresult.PaymentResultDecorator;
import com.mercadopago.android.px.internal.viewmodel.PaymentResultViewModel;

final class PaymentResultDecoratorFactory {

    private PaymentResultDecoratorFactory() {
    }

    static PaymentResultDecorator createDecorator(@NonNull final PaymentResultViewModel viewModel) {
        return createPaymentResultDecorator(viewModel);
    }

    private static PaymentResultDecorator createPaymentResultDecorator(final PaymentResultViewModel viewModel) {
        final int image;
        // Whether payment result is approved or pending success, the colors remains the same
        int primaryColor = R.color.ui_components_success_color;
        int primaryDarkColor = R.color.px_green_status_bar;

        if (viewModel.isApprovedSuccess()) {
            image = R.drawable.px_ic_payment_success;
        } else if (viewModel.isPendingSuccess()) {
            image = R.drawable.px_ic_payment_pending;
        } else if (viewModel.isPendingWarning() || viewModel.isErrorRecoverable()) {
            // With a pending or a recoverable error, we need warning colors
            primaryColor = R.color.ui_components_warning_color;
            primaryDarkColor = R.color.px_orange_status_bar;

            //We need to differ the image whether is a pending warning or a recoverable error
            if (viewModel.isPendingWarning()) {
                image = R.drawable.px_ic_payment_pending;
            } else {
                image = R.drawable.px_ic_payment_warning;
            }
        } else {
            // Fallback with an error payment result
            image = R.drawable.px_ic_payment_error;
            primaryColor = R.color.ui_components_error_color;
            primaryDarkColor = R.color.px_red_status_bar;
        }

        return new PaymentResultDecorator.Builder()
            .setStatusIconResId(image)
            .setPrimaryColor(primaryColor)
            .setPrimaryDarkColor(primaryDarkColor)
            .build();
    }
}
