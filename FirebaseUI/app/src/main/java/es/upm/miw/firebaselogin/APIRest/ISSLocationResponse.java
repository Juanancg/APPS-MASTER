package es.upm.miw.firebaselogin.APIRest;

import android.text.format.DateFormat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class ISSLocationResponse {

    @SerializedName("iss_position")
    @Expose
    private IssPosition issPosition;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public IssPosition getIssPosition() {
        return issPosition;
    }

    public void setIssPosition(IssPosition issPosition) {
        this.issPosition = issPosition;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTimestamp() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
        return timestamp;
    }

    public String getTimestampFormatted() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp * 1000L);
        String date = DateFormat.format("kk:mm:ss  dd-MM-yyyy", cal).toString(); //   hh:mm:ss
        return date;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

}