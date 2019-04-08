package com.mercadopago.android.px.internal.viewmodel.drawables;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.mercadopago.android.px.model.AccountMoneyMetadata;

public class AccountMoneyDrawableFragmentItem implements DrawableFragmentItem {

    @NonNull public final AccountMoneyMetadata metadata;
    public final boolean disabled;

    public AccountMoneyDrawableFragmentItem(@NonNull final AccountMoneyMetadata metadata, final boolean disabled) {
        this.metadata = metadata;
        this.disabled = disabled;
    }

    @Override
    public Fragment draw(@NonNull final PaymentMethodFragmentDrawer drawer) {
        return drawer.draw(this);
    }
}
