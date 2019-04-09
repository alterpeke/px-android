package com.mercadopago.android.px.internal.viewmodel;

public class ConfirmButtonViewModel {

    private final boolean disabled;

    public ConfirmButtonViewModel(final boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }
}