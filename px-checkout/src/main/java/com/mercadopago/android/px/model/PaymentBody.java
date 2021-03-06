package com.mercadopago.android.px.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import com.mercadopago.android.px.preferences.CheckoutPreference;

public class PaymentBody {

    private String transactionId;
    private Integer installments;
    private Long issuerId;
    private String paymentMethodId;
    private String prefId;
    @SerializedName("token")
    private String tokenId;
    private Boolean binaryMode;
    private String publicKey;
    private String email;
    private String couponCode;
    private Payer payer;
    private Float couponAmount;
    private String campaignId;

    public PaymentBody(@NonNull final String transactionId,
        @NonNull final PaymentData paymentData,
        @NonNull final CheckoutPreference checkoutPreference) {
        setPrefId(checkoutPreference.getId());
        setPublicKey(publicKey);
        setPaymentMethodId(paymentData.getPaymentMethod().getId());
        setBinaryMode(binaryMode);
        final Payer payer = paymentData.getPayer();
        setPayer(payer);

        if (paymentData.getToken() != null) {
            setTokenId(paymentData.getToken().getId());
        }
        if (paymentData.getPayerCost() != null) {
            setInstallments(paymentData.getPayerCost().getInstallments());
        }
        if (paymentData.getIssuer() != null) {
            setIssuerId(paymentData.getIssuer().getId());
        }

        final Discount discount = paymentData.getDiscount();
        if (discount != null) {
            setCampaignId(discount.getId());
            setCouponAmount(discount.getCouponAmount().floatValue());
        }

        setTransactionId(transactionId);

        //TODO VER - CustomerId does not exists
        /*
        if (!TextUtils.isEmpty(customerId) &&
            MercadoPagoUtil.isCard(paymentData.getPaymentMethod().getPaymentTypeId())) {
            payer.setId(customerId);
        }
        */
    }

    public void setCouponAmount(Float couponAmount) {
        this.couponAmount = couponAmount;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Long getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Long issuerId) {
        this.issuerId = issuerId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPrefId() {
        return prefId;
    }

    public void setPrefId(String prefId) {
        this.prefId = prefId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public void setBinaryMode(Boolean binaryMode) {
        this.binaryMode = binaryMode;
    }

    public Boolean getBinaryMode() {
        return binaryMode;
    }

    public String getEmail() {
        return email;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public Payer getPayer() {
        return payer;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public Float getCouponAmount() {
        return couponAmount;
    }

    public String getCampaignId() {
        return campaignId;
    }
}
