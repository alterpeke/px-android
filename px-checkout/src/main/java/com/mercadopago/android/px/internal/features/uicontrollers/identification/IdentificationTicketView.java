package com.mercadopago.android.px.internal.features.uicontrollers.identification;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.util.MPCardMaskUtil;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.internal.view.MPTextView;

public class IdentificationTicketView extends IdentificationView {

    private String mName;
    private String mLastName;

    private MPTextView mNameTextView;
    private MPTextView mLastNameTextView;
    private MPTextView mIdentificationTypeIdTextView;
    private MPTextView mIdentificationPlaceholderContainer;
    private FrameLayout mLastNameContainer;

    public IdentificationTicketView(Context context) {
        super(context);
    }

    @Override
    public void initializeControls() {
        super.initializeControls();

        mNameTextView = mView.findViewById(R.id.mpsdkNameView);
        mLastNameTextView = mView.findViewById(R.id.mpsdkLastnameView);
        mIdentificationTypeIdTextView = mView.findViewById(R.id.mpsdkIdentificationTypeId);
        mLastNameContainer = mView.findViewById(R.id.mpsdkLastnameContainer);
        mIdentificationPlaceholderContainer = mView.findViewById(R.id.mpsdkIdentificationCardholderContainer);
        drawIdentificationTypeName();
    }

    @Override
    public View inflateInParent(ViewGroup parent, boolean attachToRoot) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.px_ticket_identification, parent, attachToRoot);
        return mView;
    }

    @Override
    public void draw() {
        if (mIdentificationNumber == null || mIdentificationNumber.length() == 0) {
            mIdentificationNumberTextView.setVisibility(View.INVISIBLE);
            mBaseIdNumberView.setVisibility(View.VISIBLE);
        } else {
            mBaseIdNumberView.setVisibility(View.INVISIBLE);
            mIdentificationNumberTextView.setVisibility(View.VISIBLE);

            String number =
                MPCardMaskUtil.buildIdentificationNumberWithMask(mIdentificationNumber, mIdentificationType);
            mIdentificationNumberTextView.setTextColor(ContextCompat.getColor(mContext, NORMAL_TEXT_VIEW_COLOR));
            mIdentificationNumberTextView.setText(number);
        }

        drawIdentificationName();
        drawIdentificationLastName();
        drawIdentificationTypeName();
    }

    private void drawIdentificationName() {
        if (TextUtil.isEmpty(mName)) {
            if (TextUtil.isEmpty(mLastName)) {
                configureAlphaColorNameText();
            } else {
                mNameTextView.setText(TextUtil.EMPTY);
            }
        } else {
            mNameTextView.setText(mName);
            configureNormalColorNameText();
            mLastNameContainer.setVisibility(View.VISIBLE);
        }
    }

    private void drawIdentificationLastName() {
        if (TextUtil.isEmpty(mLastName)) {
            mLastNameTextView.setText("");
        } else {
            configureNormalColorLastNameText();
            mLastNameTextView.setText(mLastName);
        }
    }

    public void drawIdentificationTypeName() {
        if (mIdentificationType != null && !TextUtil.isEmpty(mIdentificationType.getId())) {
            mIdentificationTypeIdTextView.setText(mIdentificationType.getId());
        }
    }

    public void drawNamePlaceholder() {
        final String name = mContext.getResources().getString(R.string.px_name_and_lastname_identification_label);
        mNameTextView.setText(name);
    }

    public void drawBusinessNamePlaceholder() {
        final String businessName = mContext.getResources().getString(R.string.px_business_name_identification_label);
        mNameTextView.setText(businessName);
    }

    public void drawCpfIdentificationNumberPlaceholder() {
        final String placeholder = mContext.getResources().getString(R.string.px_cpf_card_holder_identification_hint);
        mIdentificationPlaceholderContainer.setText(placeholder);
    }

    public void drawCnpjIdentificationNumberPlaceholder() {
        final String placeholder = mContext.getResources().getString(R.string.px_cnpj_card_holder_identification_hint);
        mIdentificationPlaceholderContainer.setText(placeholder);
    }

    public void setIdentificationName(String name) {
        mName = name;
    }

    public void setIdentificationLastName(String lastName) {
        mLastName = lastName;
    }

    public void configureNormalColorNameText() {
        mNameTextView.setTextColor(ContextCompat.getColor(mContext, NORMAL_TEXT_VIEW_COLOR));
    }

    public void configureNormalColorLastNameText() {
        mLastNameTextView.setTextColor(ContextCompat.getColor(mContext, NORMAL_TEXT_VIEW_COLOR));
    }

    public void configureAlphaColorNameText() {
        setAlphaColorText(mNameTextView);
    }

    public void configureAlphaColorLastNameText() {
        setAlphaColorText(mLastNameTextView);
    }

    private void setAlphaColorText(MPTextView mpTextView) {
        mpTextView.setTextColor(ContextCompat.getColor(mContext, ALPHA_TEXT_VIEW_COLOR));
    }
}
