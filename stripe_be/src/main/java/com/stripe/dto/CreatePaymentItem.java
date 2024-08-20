package com.stripe.dto;

import com.google.gson.annotations.SerializedName;

public class CreatePaymentItem {
    @SerializedName("id")
    String id;
    @SerializedName("amount")
    Long amount;

    public Long getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

}
