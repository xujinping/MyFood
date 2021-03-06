package com.xjp.myapp.beans.Index;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据详情可参考 (http://www.juhe.cn/docs/api/id/46)
 * Create by xjp
 */
public class Datum implements Serializable {

    private String id;

    private String title;

    private String tags;

    private String imtro;

    private String ingredients;

    private String burden;

    private List<String> albums = new ArrayList<String>();

    private List<Step> steps = new ArrayList<Step>();

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags The tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return The imtro
     */
    public String getImtro() {
        return imtro;
    }

    /**
     * @param imtro The imtro
     */
    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    /**
     * @return The ingredients
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients The ingredients
     */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @return The burden
     */
    public String getBurden() {
        return burden;
    }

    /**
     * @param burden The burden
     */
    public void setBurden(String burden) {
        this.burden = burden;
    }

    /**
     * @return The albums
     */
    public List<String> getAlbums() {
        return albums;
    }

    /**
     * @param albums The albums
     */
    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    /**
     * @return The steps
     */
    public List<Step> getSteps() {
        return steps;
    }

    /**
     * @param steps The steps
     */
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }


    @Override
    public String toString() {
        return "Datum{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", imtro='" + imtro + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", burden='" + burden + '\'' +
                ", albums=" + albums +
                ", steps=" + steps +
                '}';
    }
}
