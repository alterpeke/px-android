package com.mercadopago.android.px.internal.features.express.slider;

import android.support.annotation.NonNull;
import com.mercadolibre.android.ui.widgets.MeliButton;
import com.mercadopago.android.px.internal.viewmodel.ConfirmButtonViewModel;
import java.util.List;

public class ConfirmButtonAdapter extends ViewAdapter<List<ConfirmButtonViewModel>, MeliButton> {

    public ConfirmButtonAdapter(final List<ConfirmButtonViewModel> models,
        @NonNull final MeliButton view) {
        super(models, view);
    }

    @Override
    public void updateData(final int currentIndex, final int payerCostSelected, final boolean userWantsToSplit) {
        if (data.get(currentIndex).isDisabled()) {
            view.setState(MeliButton.State.DISABLED);
        } else {
            view.setState(MeliButton.State.NORMAL);
        }
    }
}