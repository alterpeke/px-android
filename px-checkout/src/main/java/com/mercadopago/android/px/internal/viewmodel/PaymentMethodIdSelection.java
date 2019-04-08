package com.mercadopago.android.px.internal.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.model.ExpressMetadata;
import java.util.List;

public class PaymentMethodIdSelection implements Parcelable {

    public static final Creator<PaymentMethodIdSelection> CREATOR = new Creator<PaymentMethodIdSelection>() {
        @Override
        public PaymentMethodIdSelection createFromParcel(final Parcel in) {
            return new PaymentMethodIdSelection(in);
        }

        @Override
        public PaymentMethodIdSelection[] newArray(final int size) {
            return new PaymentMethodIdSelection[size];
        }
    };

    private final String[] paymentMethodIds;

    protected PaymentMethodIdSelection(final Parcel in) {
        paymentMethodIds = in.createStringArray();
    }

    public PaymentMethodIdSelection(final List<ExpressMetadata> expressMetadataList) {
        paymentMethodIds = new String[expressMetadataList.size() + 1];
        ExpressMetadata expressMetadata;
        for(int i = 0; i < expressMetadataList.size(); i++){
            expressMetadata = expressMetadataList.get(i);
            if(expressMetadata.isCard()){
                paymentMethodIds[i] = expressMetadata.getCard().getId();
            } else {
                paymentMethodIds[i] = expressMetadata.getPaymentMethodId();
            }
        }
        paymentMethodIds[expressMetadataList.size()] = TextUtil.EMPTY;
    }

    public String get(final int paymentMethodIndex) {
        return paymentMethodIds[paymentMethodIndex];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeStringArray(paymentMethodIds);
    }
}