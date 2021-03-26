package com.autumntechcreation.click4panditcustomer.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonError {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("error_description")
    @Expose
    private String errorDescription;

    public void setError(String error) {
        this.error = error;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

}
