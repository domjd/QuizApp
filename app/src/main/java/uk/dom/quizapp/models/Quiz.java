
package uk.dom.quizapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quiz {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("results")
    @Expose
    private List<Question> results = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Quiz() {
    }

    /**
     * 
     * @param responseCode
     * @param results
     */
    public Quiz(Integer responseCode, List<Question> results) {
        super();
        this.responseCode = responseCode;
        this.results = results;
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
     *     The results
     */
    public List<Question> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Question> results) {
        this.results = results;
    }

}
