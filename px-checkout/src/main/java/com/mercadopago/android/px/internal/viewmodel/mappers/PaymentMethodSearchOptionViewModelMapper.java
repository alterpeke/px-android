package com.mercadopago.android.px.internal.viewmodel.mappers;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.features.payment_vault.SearchItemOnClickListenerHandler;
import com.mercadopago.android.px.internal.util.ResourceUtil;
import com.mercadopago.android.px.internal.viewmodel.PaymentMethodViewModel;
import com.mercadopago.android.px.model.PaymentMethodSearchItem;
import com.mercadopago.android.px.model.PaymentTypes;

public class PaymentMethodSearchOptionViewModelMapper
    extends SearchOptionViewModelMapper<PaymentMethodSearchItem, PaymentMethodViewModel> {

    private static final int COMMENT_MAX_LENGTH = 75;

    public PaymentMethodSearchOptionViewModelMapper(@NonNull final SearchItemOnClickListenerHandler handler) {
        super(handler);
    }

    @Override
    public PaymentMethodViewModel map(@NonNull final PaymentMethodSearchItem val) {
        return new PaymentMethodViewModel() {
            @Override
            public String getPaymentMethodId() {
                return val.getId();
            }

            @Override
            public String getDescription() {
                return val.getDescription();
            }

            @Override
            public String getDiscountInfo() {
                return null;
            }

            @Override
            public String getComment() {
                return !PaymentTypes.isCardPaymentType(val.getId()) && val.getComment() != null &&
                    val.getComment().length() < COMMENT_MAX_LENGTH
                    ? val.getComment() : null;
            }

            @Override
            @DrawableRes
            public int getIconResourceId(@NonNull final Context context) {
                final StringBuilder imageName = new StringBuilder();
                if (!val.getId().isEmpty()) {
                    if (needsTint(context, val)) {
                        imageName.append(ResourceUtil.TINT_PREFIX);
                    }
                    imageName.append(val.getId());
                }

                if (val.isIconRecommended()) {
                    return ResourceUtil.getIconResource(context, imageName.toString());
                } else {
                    return 0;
                }
            }

            @Override
            public int getBadgeResourceId(@NonNull final Context context) {
                return 0;
            }

            @Override
            public boolean isDisabled() {
                return false;
            }

            @Override
            public void tint(@NonNull final ImageView icon) {
                if (needsTint(icon.getContext(), val)) {
                    icon.setColorFilter(ContextCompat.getColor(icon.getContext(), R.color.px_paymentMethodTint),
                        PorterDuff.Mode.MULTIPLY);
                }
            }

            @Override
            public void handleOnClick() {
                handler.selectItem(val);
            }
        };
    }

    /* default */ boolean needsTint(@NonNull final Context context, @NonNull final PaymentMethodSearchItem item) {
        return !isMeliOrMpIntegration(context) && (item.isGroup() || item.isPaymentType());
    }

    private boolean isMeliOrMpIntegration(@NonNull final Context context) {
        final int mpMainColor = ContextCompat.getColor(context, R.color.px_mp_blue);
        final int meliMainColor = ContextCompat.getColor(context, R.color.meli_yellow);
        final int integrationColor = ContextCompat.getColor(context, R.color.px_paymentMethodTint);
        return (mpMainColor == integrationColor) || (meliMainColor == integrationColor);
    }
}