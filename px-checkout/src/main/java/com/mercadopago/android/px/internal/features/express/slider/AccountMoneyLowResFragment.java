package com.mercadopago.android.px.internal.features.express.slider;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.viewmodel.drawables.AccountMoneyDrawableFragmentItem;
import com.mercadopago.android.px.model.PaymentTypes;

public class AccountMoneyLowResFragment extends AccountMoneyFragment {

    @SuppressWarnings("TypeMayBeWeakened")
    @NonNull
    public static Fragment getInstance(@NonNull final AccountMoneyDrawableFragmentItem item) {
        final AccountMoneyLowResFragment accountMoneyFragment = new AccountMoneyLowResFragment();
        final Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_MODEL, item);
        bundle.putString(ARG_PM_TYPE, PaymentTypes.ACCOUNT_MONEY);
        accountMoneyFragment.setArguments(bundle);
        return accountMoneyFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
        @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.px_fragment_account_money_low_res, container, false);
    }
}
