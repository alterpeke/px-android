package com.mercadopago.android.px.internal.viewmodel.drawables;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.mercadopago.android.px.model.CardDisplayInfo;

public class SavedCardDrawableFragmentItem implements DrawableFragmentItem {

    @NonNull public final String paymentMethodId;
    @NonNull public final CardDisplayInfo card;
    public final boolean disabled;

    public SavedCardDrawableFragmentItem(@NonNull final String paymentMethodId, @NonNull final CardDisplayInfo card,
        final boolean disabled) {
        this.paymentMethodId = paymentMethodId;
        this.card = card;
        this.disabled = disabled;
    }

    @Override
    public Fragment draw(@NonNull final PaymentMethodFragmentDrawer drawer) {
        return drawer.draw(this);
    }
}
