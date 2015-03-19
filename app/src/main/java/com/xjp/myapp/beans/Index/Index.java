package com.xjp.myapp.beans.Index;

public class Index {

    private String resultcode;
    private String reason;
    private Result result;
    private Integer errorCode;

    /**
     * @return The resultcode
     */
    public String getResultcode() {
        return resultcode;
    }

    /**
     * @param resultcode The resultcode
     */
    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    /**
     * @return The reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason The reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return The result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * @return The errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode The error_code
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }


    @Override
    public String toString() {
        return "Index{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", errorCode=" + errorCode +
                '}';
    }
}
