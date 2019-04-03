package com.mercadopago.android.px.internal.viewmodel.mappers;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.mercadopago.android.px.internal.features.payment_vault.SearchItemOnClickListenerHandler;
import com.mercadopago.android.px.internal.util.MercadoPagoUtil;
import com.mercadopago.android.px.internal.viewmodel.PaymentMethodViewModel;
import com.mercadopago.android.px.model.CustomSearchItem;

public class CustomSearchOptionViewModelMapper
    extends SearchOptionViewModelMapper<CustomSearchItem, PaymentMethodViewModel> {

    public CustomSearchOptionViewModelMapper(@NonNull final SearchItemOnClickListenerHandler handler) {
        super(handler);
    }

    @Override
    public PaymentMethodViewModel map(@NonNull final CustomSearchItem val) {
        return new PaymentMethodViewModel() {

            @Override
            public String getDescription() {
                return val.getDescription();
            }

            @Override
            public String getPaymentMethodId() {
                return val.getPaymentMethodId();
            }

            @Override
            public String getDiscountInfo() {
                return val.getDiscountInfo();
            }

            @Override
            public String getComment() {
                return null;
            }

            @Override
            @DrawableRes
            public int getIconResourceId(@NonNull final Context context) {
                return MercadoPagoUtil.getPaymentMethodSearchItemIcon(context, val.getPaymentMethodId());
            }

            @Override
            public void tint(@NonNull final ImageView icon) {
                //do nothing
            }

            @Override
            public void handleOnClick() {
                handler.selectItem(val);
            }
        };
    }
}