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
import com.mercadopago.android.px.internal.viewmodel.drawables.DrawableFragmentItem;

public abstract class PaymentMethodFragment extends Fragment {

    protected static final String ARG_MODEL = "ARG_MODEL";
    protected static final String ARG_PM_TYPE = "ARG_PM_TYPE";

    protected OnClickPaymentMethodListener handler;
    private View badge;
    private CardView card;

    @CallSuper
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        badge = view.findViewById(R.id.px_disabled_badge);
        card = view.findViewById(R.id.payment_method);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        final Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(ARG_MODEL)
                && arguments.containsKey(ARG_PM_TYPE)) {
            final DrawableFragmentItem item = (DrawableFragmentItem) arguments.getSerializable(ARG_MODEL);
            handler.updateDrawableFragmentItem(item);
            final boolean disabled = item.isDisabled();
            final String paymentMethodType = arguments.getString(ARG_PM_TYPE);
            if (disabled) {
                badge.setVisibility(View.VISIBLE);
                ViewUtils.grayScaleViewGroup(card);
                card.setOnClickListener(v -> {
                    // It is not necessary to know the actual type of card
                    handler.onPaymentMethodClicked(paymentMethodType);
                });
            }
        }

    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        final Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof OnClickPaymentMethodListener) {
            handler = (OnClickPaymentMethodListener) parentFragment;
        }
    }

    public interface OnClickPaymentMethodListener {
        void onPaymentMethodClicked(@NonNull final String paymentMethodType);
        void updateDrawableFragmentItem(@NonNull DrawableFragmentItem item);
    }
}