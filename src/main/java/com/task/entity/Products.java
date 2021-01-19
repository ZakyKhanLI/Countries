package com.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Products {
    @Id
    @Column
    private Long id;
    @Column
    private String title;
    @Column
    private String vendor;
    @Column
    private String product_type;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody_html() {
        return vendor;
    }

    public void setBody_html(String body_html) {
        this.vendor = body_html;
    }


    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }
}
