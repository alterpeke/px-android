package com.mercadopago.android.px.services;

import com.mercadopago.android.px.model.BankDeal;
import com.mercadopago.android.px.services.adapters.MPCall;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mromar on 10/20/17.
 */

public interface BankDealService {

    @GET("/v1/payment_methods/deals")
    MPCall<List<BankDeal>> getBankDeals(@Query("public_key") String publicKey, @Query("access_token") String privateKey,
        @Query("locale") String locale);
}