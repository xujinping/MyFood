package com.xjp.myapp.beans.Index;

import java.io.Serializable;

public class Step implements Serializable {

    private String img;
    private String step;

    /**
     * @return The img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img The img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return The step
     */
    public String getStep() {
        return step;
    }

    /**
     * @param step The step
     */
    public void setStep(String step) {
        this.step = step;
    }


    @Override
    public String toString() {
        return "Step{" +
                "img='" + img + '\'' +
                ", step='" + step + '\'' +
                '}';
    }
}
