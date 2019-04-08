package com.mercadopago.android.px.internal.features.express.slider;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.util.ViewUtils;

public abstract class PaymentMethodFragment extends Fragment {

    protected static final String ARG_MODEL = "ARG_MODEL";
    protected static final String ARG_DISABLED = "ARG_DISABLED";
    protected static final String ARG_PM_TYPE = "ARG_PM_TYPE";

    protected OnClickPaymentMethodListener listener;

    @CallSuper
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        final Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(ARG_DISABLED) && arguments.containsKey(ARG_PM_TYPE)) {
            final boolean disabled = arguments.getBoolean(ARG_DISABLED);
            final String paymentMethodType = arguments.getString(ARG_PM_TYPE);
            if (disabled) {
                final View badge = view.findViewById(R.id.px_disabled_badge);
                badge.setVisibility(View.VISIBLE);
                final CardView card = view.findViewById(R.id.payment_method);
                ViewUtils.grayScaleViewGroup(card);
                card.setOnClickListener(v -> {
                    // It is not necessary to know the actual type of card
                    listener.onPaymentMethodClicked(paymentMethodType);
                });
            }
        }
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        final Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof OnClickPaymentMethodListener) {
            listener = (OnClickPaymentMethodListener) parentFragment;
        }
    }

    public interface OnClickPaymentMethodListener {
        void onPaymentMethodClicked(@NonNull final String paymentMethodType);
    }
}