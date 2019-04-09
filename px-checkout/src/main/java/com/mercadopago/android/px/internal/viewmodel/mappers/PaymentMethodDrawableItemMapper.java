package com.mercadopago.android.px.internal.viewmodel.mappers;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.viewmodel.drawables.AccountMoneyDrawableFragmentItem;
import com.mercadopago.android.px.internal.viewmodel.drawables.AddNewCardFragmentDrawableFragmentItem;
import com.mercadopago.android.px.internal.viewmodel.drawables.DrawableFragmentItem;
import com.mercadopago.android.px.internal.viewmodel.drawables.SavedCardDrawableFragmentItem;
import com.mercadopago.android.px.model.ExpressMetadata;
import com.mercadopago.android.px.model.PaymentTypes;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDrawableItemMapper extends Mapper<List<ExpressMetadata>, List<DrawableFragmentItem>> {

    @Override
    public List<DrawableFragmentItem> map(@NonNull final List<ExpressMetadata> val) {
        final List<DrawableFragmentItem> result = new ArrayList<>();

        for (final ExpressMetadata expressMetadata : val) {
            if (expressMetadata.isCard()) {
                result.add(new SavedCardDrawableFragmentItem(expressMetadata.getPaymentMethodId(),
                    expressMetadata.getCard().getDisplayInfo(), expressMetadata.getCard().getId()));
            } else if (PaymentTypes.isAccountMoney(expressMetadata.getPaymentMethodId())) {
                result.add(new AccountMoneyDrawableFragmentItem(expressMetadata.getAccountMoney(),
                    expressMetadata.getPaymentMethodId()));
            }
        }

        result.add(new AddNewCardFragmentDrawableFragmentItem());

        return result;
    }
}