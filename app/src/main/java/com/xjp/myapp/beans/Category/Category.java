package com.xjp.myapp.beans.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜谱分类信息 数据详情可参考 (http://www.juhe.cn/docs/api/id/46)
 * Create by xjp
 */
public class Category {

    private String resultcode;

    private String reason;

    private List<Result> result = new ArrayList<Result>();

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
    public List<Result> getResult() {
        return result;
    }

    /**
     * @param result The result
     */
    public void setResult(List<Result> result) {
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
        return "Category{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", errorCode=" + errorCode +
                '}';
    }
}
