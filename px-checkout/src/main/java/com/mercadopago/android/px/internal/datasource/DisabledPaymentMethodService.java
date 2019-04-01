package com.mercadopago.android.px.internal.datasource;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.repository.DisabledPaymentMethodRepository;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.PaymentResult;
import com.mercadopago.android.px.model.PaymentTypes;
import java.util.HashSet;
import java.util.Set;

public final class DisabledPaymentMethodService implements DisabledPaymentMethodRepository {

    private static final String PREF_DISABLED_PAYMENT_METHODS_IDS = "PREF_DISABLED_PAYMENT_METHODS_IDS";

    @NonNull private final SharedPreferences sharedPreferences;

    public DisabledPaymentMethodService(@NonNull final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private void storeDisabledPaymentMethodId(@NonNull final String paymentMethodId) {
        final Set<String> disabledPaymentMethodIds = getDisabledPaymentMethodIds();
        disabledPaymentMethodIds.add(paymentMethodId);
        sharedPreferences.edit().putStringSet(PREF_DISABLED_PAYMENT_METHODS_IDS, disabledPaymentMethodIds).apply();
    }

    @NonNull
    private Set<String> getDisabledPaymentMethodIds() {
        return sharedPreferences.getStringSet(PREF_DISABLED_PAYMENT_METHODS_IDS, new HashSet<>());
    }

    @Override
    public void reset() {
        sharedPreferences.edit().remove(PREF_DISABLED_PAYMENT_METHODS_IDS).apply();
    }

    @Override
    public boolean hasPaymentMethodId(@NonNull final String paymentMethodId) {
        return getDisabledPaymentMethodIds().contains(paymentMethodId);
    }

    @Override
    public void handleDisableablePayment(@NonNull final PaymentResult paymentResult) {
        if (isDisableablePayment(paymentResult)) {
            final boolean isSplitPayment = paymentResult.getPaymentDataList().size() > 1;
            if (isSplitPayment && TextUtil.isNotEmpty(paymentResult.getPaymentMethodId())) {
                storeDisabledPaymentMethodId(paymentResult.getPaymentMethodId());
            } else if (PaymentTypes
                .isAccountMoney(paymentResult.getPaymentData().getPaymentMethod().getPaymentTypeId())) {
                storeDisabledPaymentMethodId(paymentResult.getPaymentData().getPaymentMethod().getId());
            } else if (PaymentTypes
                .isCardPaymentType(paymentResult.getPaymentData().getPaymentMethod().getPaymentTypeId()) &&
                paymentResult.getPaymentData().getToken() != null &&
                TextUtil.isNotEmpty(paymentResult.getPaymentData().getToken().getCardId())) {
                storeDisabledPaymentMethodId(paymentResult.getPaymentData().getToken().getCardId());
            }
        }
    }

    private boolean isDisableablePayment(@NonNull final PaymentResult paymentResult) {
        return Payment.StatusCodes.STATUS_REJECTED.equalsIgnoreCase(paymentResult.getPaymentStatus()) &&
            (Payment.StatusDetail.STATUS_DETAIL_REJECTED_HIGH_RISK
                .equalsIgnoreCase(paymentResult.getPaymentStatusDetail()) ||
                Payment.StatusDetail.STATUS_DETAIL_CC_REJECTED_HIGH_RISK
                    .equalsIgnoreCase(paymentResult.getPaymentStatusDetail()) ||
                Payment.StatusDetail.STATUS_DETAIL_CC_REJECTED_BLACKLIST
                    .equalsIgnoreCase(paymentResult.getPaymentStatusDetail()));
    }
}