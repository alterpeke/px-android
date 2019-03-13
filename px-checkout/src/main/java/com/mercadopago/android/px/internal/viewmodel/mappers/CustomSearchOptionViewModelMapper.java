package com.mercadopago.android.px.internal.viewmodel.mappers;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.features.payment_vault.SearchItemOnClickListenerHandler;
import com.mercadopago.android.px.internal.repository.DisabledPaymentMethodRepository;
import com.mercadopago.android.px.internal.util.MercadoPagoUtil;
import com.mercadopago.android.px.internal.util.ViewUtils;
import com.mercadopago.android.px.internal.viewmodel.PaymentMethodViewModel;
import com.mercadopago.android.px.model.CustomSearchItem;
import java.util.Collections;
import java.util.List;

public class CustomSearchOptionViewModelMapper
    extends SearchOptionViewModelMapper<CustomSearchItem, PaymentMethodViewModel> {

    @NonNull /* default */ final DisabledPaymentMethodRepository disabledPaymentMethodRepository;

    public CustomSearchOptionViewModelMapper(@NonNull final SearchItemOnClickListenerHandler handler, @NonNull final
    DisabledPaymentMethodRepository disabledPaymentMethodRepository) {
        super(handler);
        this.disabledPaymentMethodRepository = disabledPaymentMethodRepository;
    }

    @Override
    public List<PaymentMethodViewModel> map(@NonNull final Iterable<CustomSearchItem> val) {
        final List<PaymentMethodViewModel> sortedList = super.map(val);
        Collections.sort(sortedList, (o1, o2) -> Boolean.compare(o1.isDisabled(), o2.isDisabled()));
        return sortedList;
    }

    @Override
    public PaymentMethodViewModel map(@NonNull final CustomSearchItem val) {
        return new PaymentMethodViewModel() {

            private final boolean disabled = disabledPaymentMethodRepository.hasPaymentMethodId(val.getId());

            @Override
            public String getPaymentMethodId() {
                return val.getPaymentMethodId();
            }

            @Override
            public String getDescription() {
                return val.getDescription();
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
            public int getBadgeResourceId(@NonNull final Context context) {
                return disabled ? R.drawable.px_badge_warning : 0;
            }

            @Override
            public boolean isDisabled() {
                return disabled;
            }

            @Override
            public void tint(@NonNull final ImageView icon) {
                if (disabled) {
                    ViewUtils.grayScaleView(icon);
                }
            }

            @Override
            public void handleOnClick() {
                if (disabled) {
                    handler.showDisabledPaymentMethodDetailDialog(val.getType());
                } else {
                    handler.selectItem(val);
                }
            }
        };
    }
}