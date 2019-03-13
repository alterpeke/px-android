package com.mercadopago.android.px.internal.features.disable_payment_method;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;
import com.mercadolibre.android.ui.widgets.MeliDialog;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.model.PaymentTypes;

public class DisabledPaymentMethodDetailDialog extends MeliDialog {

    private static final String TAG = DisabledPaymentMethodDetailDialog.class.getName();
    private static final String ARG_TITLE_RES_ID = "arg_title_res_id";

    public static void showDialog(@NonNull final FragmentManager supportFragmentManager,
        @NonNull final String paymentMethodType) {
        final DisabledPaymentMethodDetailDialog disabledPaymentMethodDetailDialog =
            new DisabledPaymentMethodDetailDialog();
        final Bundle arguments = new Bundle();
        arguments.putInt(ARG_TITLE_RES_ID, getTitleResourceId(paymentMethodType));
        disabledPaymentMethodDetailDialog.setArguments(arguments);
        disabledPaymentMethodDetailDialog.show(supportFragmentManager, TAG);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View closeButton = view.findViewById(R.id.ui_melidialog_close_button);
        closeButton.setVisibility(View.GONE);
        final TextView title = view.findViewById(R.id.px_dialog_detail_payment_method_disable_title);
        title.setText(getArguments().getInt(ARG_TITLE_RES_ID));
        final View linkText = view.findViewById(R.id.px_dialog_detail_payment_method_disable_link);
        linkText.setOnClickListener(v -> dismiss());
    }

    @Override
    public int getContentView() {
        return R.layout.px_dialog_detail_payment_method_disable;
    }

    @StringRes
    private static int getTitleResourceId(final String paymentMethodType) {
        return PaymentTypes.isAccountMoney(paymentMethodType)
            ? R.string.px_dialog_detail_payment_method_disable_account_money_title
            : R.string.px_dialog_detail_payment_method_disable_card_title;
    }
}