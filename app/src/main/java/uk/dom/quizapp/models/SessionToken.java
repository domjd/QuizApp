
package uk.dom.quizapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionToken {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SessionToken() {
    }

    /**
     * 
     * @param responseMessage
     * @param responseCode
     * @param token
     */
    public SessionToken(Integer responseCode, String responseMessage, String token) {
        super();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.token = token;
    }

    /**
     * 
     * @return
     *     The responseCode
     */
    public Integer getResponseCode() {
        return responseCode;
    }

    /**
     * 
     * @param responseCode
     *     The response_code
     */
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * 
     * @return
     *     The responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * 
     * @param responseMessage
     *     The response_message
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     * @param token
     *     The token
     */
    public void setToken(String token) {
        this.token = token;
    }

}
