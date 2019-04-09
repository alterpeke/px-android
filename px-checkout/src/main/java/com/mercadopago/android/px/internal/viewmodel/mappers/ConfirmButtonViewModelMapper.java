package com.mercadopago.android.px.internal.viewmodel.mappers;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.repository.DisabledPaymentMethodRepository;
import com.mercadopago.android.px.internal.viewmodel.ConfirmButtonViewModel;
import com.mercadopago.android.px.model.ExpressMetadata;
import java.util.List;

public class ConfirmButtonViewModelMapper extends Mapper<ExpressMetadata, ConfirmButtonViewModel> {
    private final DisabledPaymentMethodRepository disabledPaymentMethodRepository;

    public ConfirmButtonViewModelMapper(
        @NonNull final DisabledPaymentMethodRepository disabledPaymentMethodRepository) {
        this.disabledPaymentMethodRepository = disabledPaymentMethodRepository;
    }

    @Override
    public ConfirmButtonViewModel map(@NonNull final ExpressMetadata val) {
        if (val.isCard()) {
            return new ConfirmButtonViewModel(
                disabledPaymentMethodRepository.hasPaymentMethodId(val.getCard().getId()));
        } else {
            return new ConfirmButtonViewModel(
                disabledPaymentMethodRepository.hasPaymentMethodId(val.getPaymentMethodId()));
        }
    }

    @Override
    public List<ConfirmButtonViewModel> map(@NonNull final Iterable<ExpressMetadata> val) {
        final List<ConfirmButtonViewModel> result = super.map(val);
        result.add(new ConfirmButtonViewModel(true));
        return result;
    }
}