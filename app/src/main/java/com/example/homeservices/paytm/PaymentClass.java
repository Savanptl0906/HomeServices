package com.example.homeservices.paytm;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.homeservices.paytm.models.Checksum;
import com.example.homeservices.paytm.models.Paytm;
import com.example.homeservices.paytm.utils.WebServiceCaller;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentClass {

    Context c;

    public PaymentClass(Context c) {
        this.c = c;
    }

    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public void processPaytm(String amount) {

        String custID = generateString();
        String orderID = generateString();
        String callBackurl = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=" + orderID;

        final Paytm paytm = new Paytm(
                "vCdKWi72467205186732",
                "WAP",
                amount,
                "WEBSTAGING",
                callBackurl,
                "Retail",
                orderID,
                custID
        );

        WebServiceCaller.getClient().getChecksum(paytm.getmId(), paytm.getOrderId(), paytm.getCustId()
                , paytm.getChannelId(), paytm.getTxnAmount(), paytm.getWebsite(), paytm.getCallBackUrl(), paytm.getIndustryTypeId()
        ).enqueue(new Callback<Checksum>() {
            @Override
            public void onResponse(Call<Checksum> call, Response<Checksum> response) {
                if (response.isSuccessful()) {
                    processToPay(response.body().getChecksumHash(), paytm);
                }
            }

            @Override
            public void onFailure(Call<Checksum> call, Throwable t) {

            }
        });


    }

    private void processToPay(String checksumHash, Paytm paytm) {
        PaytmPGService Service = PaytmPGService.getStagingService();

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("MID", paytm.getmId());
// Key in your staging and production MID available in your dashboard
        paramMap.put("ORDER_ID", paytm.getOrderId());
        paramMap.put("CUST_ID", paytm.getCustId());
        paramMap.put("CHANNEL_ID", paytm.getChannelId());
        paramMap.put("TXN_AMOUNT", paytm.getTxnAmount());
        paramMap.put("WEBSITE", paytm.getWebsite());
// This is the staging value. Production value is available in your dashboard
        paramMap.put("INDUSTRY_TYPE_ID", paytm.getIndustryTypeId());
// This is the staging value. Production value is available in your dashboard
        paramMap.put("CALLBACK_URL", paytm.getCallBackUrl());
        paramMap.put("CHECKSUMHASH", checksumHash);
        PaytmOrder Order = new PaytmOrder(paramMap);
        Service.initialize(Order, null);

        Service.startPaymentTransaction(c, true, true, new PaytmPaymentTransactionCallback() {
            /*Call Backs*/
            public void someUIErrorOccurred(String inErrorMessage) {
            }

            public void onTransactionResponse(Bundle inResponse) {
                Toast.makeText(c, inResponse.toString(), Toast.LENGTH_SHORT).show();

            }

            public void networkNotAvailable() {
            }

            public void clientAuthenticationFailed(String inErrorMessage) {
            }

            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
            }

            public void onBackPressedCancelTransaction() {
            }

            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
            }
        });


    }
}
