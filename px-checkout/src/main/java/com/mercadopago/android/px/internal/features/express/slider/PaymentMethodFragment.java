package com.mercadopago.android.px.internal.features.express.slider;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.di.Session;
import com.mercadopago.android.px.internal.features.disable_payment_method.DisabledPaymentMethodDetailDialog;
import com.mercadopago.android.px.internal.repository.DisabledPaymentMethodRepository;
import com.mercadopago.android.px.internal.util.ViewUtils;
import com.mercadopago.android.px.internal.viewmodel.drawables.DrawableFragmentItem;

public abstract class PaymentMethodFragment extends Fragment {

    protected static final String ARG_MODEL = "ARG_MODEL";
    protected static final String ARG_PM_TYPE = "ARG_PM_TYPE";

    private View badge;
    private CardView card;
    private DrawableFragmentItem item;
    private String paymentMethodType;

    @CallSuper
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        final Bundle arguments = getArguments();
        badge = view.findViewById(R.id.px_disabled_badge);
        card = view.findViewById(R.id.payment_method);
        if (arguments != null && arguments.containsKey(ARG_MODEL)
            && arguments.containsKey(ARG_PM_TYPE)) {
            item = (DrawableFragmentItem) arguments.getSerializable(ARG_MODEL);
            paymentMethodType = arguments.getString(ARG_PM_TYPE);
        } else {
            throw new IllegalStateException("PaymentMethodFragment does not contain model info");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final DisabledPaymentMethodRepository disabledPaymentMethodRepository =
            Session.getSession(getContext()).getConfigurationModule().getDisabledPaymentMethodRepository();

        item.setDisabled(disabledPaymentMethodRepository.hasPaymentMethodId(item.getId()));

        if (item.isDisabled()) {
            badge.setVisibility(View.VISIBLE);
            ViewUtils.grayScaleViewGroup(card);
            card.setOnClickListener(
                v -> DisabledPaymentMethodDetailDialog.showDialog(getChildFragmentManager(), paymentMethodType));
        }
    }
}