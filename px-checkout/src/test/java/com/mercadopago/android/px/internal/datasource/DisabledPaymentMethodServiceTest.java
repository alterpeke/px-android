package com.mercadopago.android.px.internal.datasource;

import android.content.SharedPreferences;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.PaymentData;
import com.mercadopago.android.px.model.PaymentMethod;
import com.mercadopago.android.px.model.PaymentResult;
import com.mercadopago.android.px.model.PaymentTypes;
import com.mercadopago.android.px.model.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DisabledPaymentMethodServiceTest {

    private static final String CARD_ID = "123456789";
    private static final String ACCOUNT_MONEY_ID = "account_money";

    private DisabledPaymentMethodService disabledPaymentMethodService;
    @Mock private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        final SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        when(sharedPreferences.edit()).thenReturn(editor);
        when(editor.putStringSet(any(), any())).thenReturn(editor);
        disabledPaymentMethodService = new DisabledPaymentMethodService(sharedPreferences);
    }

    @Test
    public void whenPaymentRejectedWithDisableableStatusVerifyDisableablePaymentManaged() {
        final PaymentResult paymentResult = mock(PaymentResult.class);
        mockPayment(paymentResult, PaymentTypes.ACCOUNT_MONEY);

        disabledPaymentMethodService.handleDisableablePayment(paymentResult);
        verifyDisableablePaymentManaged(true);
    }

    @Test
    public void whenGenericPaymentRejectedWithDisableableStatusVerifyDisableablePaymentManaged() {
        final PaymentResult paymentResult = mock(PaymentResult.class);
        mockPayment(paymentResult, PaymentTypes.ACCOUNT_MONEY);

        disabledPaymentMethodService.handleDisableablePayment(paymentResult);

        verifyDisableablePaymentManaged(true);
    }

    @Test
    public void whenBusinessPaymentRejectedWithDisableableStatusVerifyDisableablePaymentManaged() {
        final PaymentResult paymentResult = mock(PaymentResult.class);
        mockPayment(paymentResult, PaymentTypes.ACCOUNT_MONEY);

        disabledPaymentMethodService.handleDisableablePayment(paymentResult);

        verifyDisableablePaymentManaged(true);
    }

    @Test
    public void whenPaymentRejectedWithDisableableStatusAndCardVerifyDisableablePaymentManaged() {
        final PaymentResult paymentResult = mock(PaymentResult.class);
        mockPayment(paymentResult, PaymentTypes.CREDIT_CARD);

        when(paymentResult.getPaymentData().getToken()).thenReturn(mock(Token.class));
        when(paymentResult.getPaymentData().getToken().getCardId()).thenReturn(CARD_ID);

        disabledPaymentMethodService.handleDisableablePayment(paymentResult);

        verifyDisableablePaymentManaged(true);
    }

    @Test
    public void whenPaymentRejectedWithDisableableStatusAndGuessingCardVerifyDisableablePaymentManaged() {
        final PaymentResult paymentResult = mock(PaymentResult.class);
        mockPayment(paymentResult, PaymentTypes.CREDIT_CARD);

        when(paymentResult.getPaymentData().getToken()).thenReturn(mock(Token.class));
        //Token of guessing card has no card id.
        //when(paymentResult.getPaymentData().getToken().getCardId()).thenReturn(CARD_ID);

        disabledPaymentMethodService.handleDisableablePayment(paymentResult);

        verifyDisableablePaymentManaged(false);
    }

    private void mockPayment(final PaymentResult paymentResult,
        final String paymentMethodType) {
        when(paymentResult.getPaymentStatus()).thenReturn(Payment.StatusCodes.STATUS_REJECTED);
        when(paymentResult.getPaymentStatusDetail())
            .thenReturn(Payment.StatusDetail.STATUS_DETAIL_CC_REJECTED_HIGH_RISK);
        when(paymentResult.getPaymentData()).thenReturn(mock(PaymentData.class));
        when(paymentResult.getPaymentData().getPaymentMethod()).thenReturn(mock(PaymentMethod.class));
        when(paymentResult.getPaymentData().getPaymentMethod().getPaymentTypeId()).thenReturn(paymentMethodType);
        when(paymentResult.getPaymentData().getPaymentMethod().getId()).thenReturn(ACCOUNT_MONEY_ID);
    }

    private void verifyDisableablePaymentManaged(final boolean shouldStorePaymentId) {
        if (shouldStorePaymentId) {
            verify(sharedPreferences.edit()).putStringSet(any(), any());
        } else {
            verify(sharedPreferences.edit(), times(0)).putStringSet(any(), any());
        }
    }
}