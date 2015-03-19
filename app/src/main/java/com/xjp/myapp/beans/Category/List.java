package com.xjp.myapp.beans.Category;

import java.io.Serializable;

public class List implements Serializable{

    private String id;
    private String name;
    private String parentId;

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
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId The parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    @Override
    public String toString() {
        return "List{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
