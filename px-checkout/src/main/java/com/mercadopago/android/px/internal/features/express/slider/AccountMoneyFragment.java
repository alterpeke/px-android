package com.mercadopago.android.px.internal.features.express.slider;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.internal.viewmodel.drawables.AccountMoneyDrawableFragmentItem;
import com.mercadopago.android.px.model.AccountMoneyMetadata;
import com.mercadopago.android.px.model.PaymentTypes;

public class AccountMoneyFragment extends PaymentMethodFragment {

    @NonNull
    public static Fragment getInstance(@NonNull final AccountMoneyDrawableFragmentItem item) {
        final AccountMoneyFragment accountMoneyFragment = new AccountMoneyFragment();
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
        return inflater.inflate(R.layout.px_fragment_account_money, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(ARG_MODEL)) {
            final AccountMoneyDrawableFragmentItem model =
                (AccountMoneyDrawableFragmentItem) arguments.getSerializable(ARG_MODEL);
            final TextView message = view.findViewById(R.id.label);
            setMessage(message, model.metadata);
        } else {
            throw new IllegalStateException("AccountMoneyFragment does not contain model info");
        }
    }

    private void setMessage(final TextView message,
        final AccountMoneyMetadata metadata) {
        if (TextUtil.isEmpty(metadata.displayInfo.message)) {
            message.setVisibility(View.GONE);
        } else {
            message.setVisibility(View.VISIBLE);
            message.setText(metadata.displayInfo.message);
        }
    }
}
