/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Entities;

/**
 *
 * @author kods
 */
public class Pays {
    
    private int id;
    private String paysName;
    private String paysCode;

    public Pays() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaysName() {
        return paysName;
    }

    public void setPaysName(String paysName) {
        this.paysName = paysName;
    }

    public String getPaysCode() {
        return paysCode;
    }

    public void setPaysCode(String paysCode) {
        this.paysCode = paysCode;
    }
    
}
