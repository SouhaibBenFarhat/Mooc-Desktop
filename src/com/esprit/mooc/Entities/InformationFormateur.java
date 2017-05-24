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
public class InformationFormateur {
    
    private int id;	
    private int idFormateur;	
    private String specialite;	
    private String cv;	
    private String filename;	
    private String googlePlus;	
    private String siteWeb;	
    private String sex;	
    private String aPropos;	
    private String biographie;	
    private String miniBiographie;

    public InformationFormateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getGooglePlus() {
        return googlePlus;
    }

    public void setGooglePlus(String googlePlus) {
        this.googlePlus = googlePlus;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getaPropos() {
        return aPropos;
    }

    public void setaPropos(String aPropos) {
        this.aPropos = aPropos;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getMiniBiographie() {
        return miniBiographie;
    }

    public void setMiniBiographie(String miniBiographie) {
        this.miniBiographie = miniBiographie;
    }

    @Override
    public String toString() {
        return "InformationFormateur{" + "id=" + id + ", idFormateur=" + idFormateur + ", specialite=" + specialite + ", cv=" + cv + ", filename=" + filename + ", googlePlus=" + googlePlus + ", siteWeb=" + siteWeb + ", sex=" + sex + ", aPropos=" + aPropos + ", biographie=" + biographie + ", miniBiographie=" + miniBiographie + '}';
    }
    
    
}
