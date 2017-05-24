/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Entities;

import java.util.Date;

/**
 *
 * @author kods
 */
public class InformationEntreprise {
    
    private int id;
    private int entrepriseId;	
                
    private String specialite;	
    private String siteWeb;	
    private String abreviation;
    private String attestation;	
    private String filename;	
    private String description;
    private String adresse;	
    private String nationnalite;
    private String numTel	;
    private String matriculeFiscal;
    private String type;
                
    private String raisonInscription;
    private String dateCreation;

    public InformationEntreprise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(int entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getAttestation() {
        return attestation;
    }

    public void setAttestation(String attestation) {
        this.attestation = attestation;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNationnalite() {
        return nationnalite;
    }

    public void setNationnalite(String nationnalite) {
        this.nationnalite = nationnalite;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getMatriculeFiscal() {
        return matriculeFiscal;
    }

    public void setMatriculeFiscal(String matriculeFiscal) {
        this.matriculeFiscal = matriculeFiscal;
    }

    public String getRaisonInscription() {
        return raisonInscription;
    }

    public void setRaisonInscription(String raisonInscription) {
        this.raisonInscription = raisonInscription;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "InformationEntreprise{" + "id=" + id + ", entrepriseId=" + entrepriseId + ", specialite=" + specialite + ", siteWeb=" + siteWeb + ", abreviation=" + abreviation + ", attestation=" + attestation + ", filename=" + filename + ", description=" + description + ", adresse=" + adresse + ", nationnalite=" + nationnalite + ", numTel=" + numTel + ", matriculeFiscal=" + matriculeFiscal + ", type=" + type + ", raisonInscription=" + raisonInscription + ", dateCreation=" + dateCreation + '}';
    }
    
    
    
    
}
