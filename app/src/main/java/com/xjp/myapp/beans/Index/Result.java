package com.xjp.myapp.beans.Index;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private List<Datum> data = new ArrayList<Datum>();
    private String totalNum;
    private Integer pn;
    private Integer rn;

    /**
     *
     * @return
     *     The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The totalNum
     */
    public String getTotalNum() {
        return totalNum;
    }

    /**
     * 
     * @param totalNum
     *     The totalNum
     */
    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 
     * @return
     *     The pn
     */
    public Integer getPn() {
        return pn;
    }

    /**
     * 
     * @param pn
     *     The pn
     */
    public void setPn(Integer pn) {
        this.pn = pn;
    }

    /**
     * 
     * @return
     *     The rn
     */
    public Integer getRn() {
        return rn;
    }

    /**
     * 
     * @param rn
     *     The rn
     */
    public void setRn(Integer rn) {
        this.rn = rn;
    }


    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", totalNum='" + totalNum + '\'' +
                ", pn=" + pn +
                ", rn=" + rn +
                '}';
    }
}
