package com.mercadopago.android.px.internal.viewmodel.drawables;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.mercadopago.android.px.model.AccountMoneyMetadata;

public class AccountMoneyDrawableFragmentItem implements DrawableFragmentItem {

    @NonNull public final AccountMoneyMetadata metadata;

    public AccountMoneyDrawableFragmentItem(@NonNull final AccountMoneyMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Fragment draw(@NonNull final PaymentMethodFragmentDrawer drawer) {
        return drawer.draw(this);
    }
}
