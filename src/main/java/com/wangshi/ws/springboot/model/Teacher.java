package com.wangshi.ws.springboot.model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Icemayor
 * @since 2019-04-07
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String couse;
    private String gender;
    private Double weight;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouse() {
        return couse;
    }

    public void setCouse(String couse) {
        this.couse = couse;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Teacher{" +
        ", id=" + id +
        ", couse=" + couse +
        ", gender=" + gender +
        ", weight=" + weight +
        "}";
    }
}
