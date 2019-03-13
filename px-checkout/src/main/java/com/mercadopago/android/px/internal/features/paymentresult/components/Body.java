package com.mercadopago.android.px.internal.features.paymentresult.components;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.features.PaymentResultViewModelFactory;
import com.mercadopago.android.px.internal.features.paymentresult.props.BodyErrorProps;
import com.mercadopago.android.px.internal.features.paymentresult.props.InstructionsProps;
import com.mercadopago.android.px.internal.features.paymentresult.props.PaymentResultBodyProps;
import com.mercadopago.android.px.internal.view.ActionDispatcher;
import com.mercadopago.android.px.internal.view.CompactComponent;
import com.mercadopago.android.px.internal.view.Component;
import com.mercadopago.android.px.internal.view.PaymentMethodBodyComponent;
import com.mercadopago.android.px.internal.view.Receipt;
import com.mercadopago.android.px.internal.viewmodel.PaymentResultViewModel;
import com.mercadopago.android.px.model.ExternalFragment;

public class Body extends Component<PaymentResultBodyProps, Void> {

    public Body(@NonNull final PaymentResultBodyProps props, @NonNull final ActionDispatcher dispatcher) {
        super(props, dispatcher);
    }

    public boolean hasInstructions() {
        return props.instruction != null;
    }

    public Instructions getInstructionsComponent() {
        final InstructionsProps instructionsProps = new InstructionsProps.Builder()
            .setInstruction(props.instruction)
            .setProcessingMode(props.processingMode)
            .build();
        return new Instructions(instructionsProps, getDispatcher());
    }

    public boolean hasBodyError() {
        final PaymentResultViewModel paymentResultViewModel =
            PaymentResultViewModelFactory.createPaymentResultViewModel(props.paymentResult);
        return paymentResultViewModel.hasBodyError();
    }

    /* default */ boolean isStatusApproved() {
        final PaymentResultViewModel paymentResultViewModel =
            PaymentResultViewModelFactory.createPaymentResultViewModel(props.paymentResult);
        return paymentResultViewModel.isApprovedSuccess();
    }

    public BodyError getBodyErrorComponent() {
        final BodyErrorProps bodyErrorProps = new BodyErrorProps.Builder()
            .setStatus(props.paymentResult.getPaymentStatus())
            .setStatusDetail(props.paymentResult.getPaymentStatusDetail())
            .setPaymentMethodName(props.paymentResult.getPaymentData().getPaymentMethod().getName())
            .setPaymentAmount(props.paymentResult.getPaymentData().getTransactionAmount().toBigInteger())
            .build();
        return new BodyError(bodyErrorProps, getDispatcher());
    }

    public boolean hasReceipt() {
        return props.paymentResult.getPaymentId() != null
            && isStatusApproved();
    }

    public Receipt getReceiptComponent() {
        return new Receipt(new Receipt.ReceiptProps(String.valueOf(props.paymentResult.getPaymentId())));
    }

    public boolean hasTopCustomComponent() {
        return props.paymentResultScreenConfiguration.hasTopFragment();
    }

    public boolean hasBottomCustomComponent() {
        return props.paymentResultScreenConfiguration.hasBottomFragment();
    }

    public ExternalFragment topFragment() {
        return props.paymentResultScreenConfiguration.getTopFragment();
    }

    public ExternalFragment bottomFragment() {
        return props.paymentResultScreenConfiguration.getBottomFragment();
    }

    /* default */ CompactComponent getPaymentMethodBody() {
        return new PaymentMethodBodyComponent(PaymentMethodBodyComponent.PaymentMethodBodyProp
            .with(props.paymentResult.getPaymentDataList(), props.currencyId, props.paymentResult.getPaymentStatus()));
    }
}