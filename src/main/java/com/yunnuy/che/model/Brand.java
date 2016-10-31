package com.yunnuy.che.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String logo;
    private String url;

    protected Brand() {}

    public Brand(String name, String logo, String url) {
        this.name = name;
        this.logo = logo;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, name='%s', logo='%s', url='%s']", id, name, logo, url);
    }
}
