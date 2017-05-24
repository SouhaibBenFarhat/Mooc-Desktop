/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Entities;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Souhaib
 */
public class Sujet {

    private int id;
    private int idForum;
    private int idUtilisateur;
    private String titre;
    private String sousTitre;
    private String descriptionSujet;
    private int nombreMessage;
    private int etatSujet; //modifier ou non
    private String datePublication;
    private String dateModification;
    private int aimeSujet; // nombre de jaime
    private int lastPoster; //dernier idUtilisateur
    private int lasPost; // dernier message

    public Sujet() {
    }

    public Sujet(int idForum, int idUtilisateur, String titre, String sousTitre, String descriptionSujet, int nombreMessage, int etatSujet, String datePublication, String dateModification, int aimeSujet, int lastPoster, int lasPost) {
        this.idForum = idForum;
        this.idUtilisateur = idUtilisateur;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.descriptionSujet = descriptionSujet;
        this.nombreMessage = nombreMessage;
        this.etatSujet = etatSujet;
        this.datePublication = datePublication;
        this.dateModification = dateModification;
        this.aimeSujet = aimeSujet;
        this.lastPoster = lastPoster;
        this.lasPost = lasPost;
    }

    public Sujet(int id, int idForum, int idUtilisateur, String titre, String sousTitre, String descriptionSujet, int nombreMessage, int etatSujet, String datePublication, String dateModification, int aimeSujet, int lastPoster, int lasPost) {
        this.id = id;
        this.idForum = idForum;
        this.idUtilisateur = idUtilisateur;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.descriptionSujet = descriptionSujet;
        this.nombreMessage = nombreMessage;
        this.etatSujet = etatSujet;
        this.datePublication = datePublication;
        this.dateModification = dateModification;
        this.aimeSujet = aimeSujet;
        this.lastPoster = lastPoster;
        this.lasPost = lasPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getForum() {
        return idForum;
    }

    public void setForum(int forum) {
        this.idForum = forum;
    }

    public int getUtilisateur() {
        return idUtilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.idUtilisateur = utilisateur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getDescriptionSujet() {
        return descriptionSujet;
    }

    public void setDescriptionSujet(String descriptionSujet) {
        this.descriptionSujet = descriptionSujet;
    }

    public int getNombreMessage() {
        return nombreMessage;
    }

    public void setNombreMessage(int nombreMessage) {
        this.nombreMessage = nombreMessage;
    }

    public int getEtatSujet() {
        return etatSujet;
    }

    public void setEtatSujet(int etatSujet) {
        this.etatSujet = etatSujet;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getDateModification() {
        return dateModification;
    }

    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    public int getAimeSujet() {
        return aimeSujet;
    }

    public void setAimeSujet(int aimeSujet) {
        this.aimeSujet = aimeSujet;
    }

    public int getLastPoster() {
        return lastPoster;
    }

    public void setLastPoster(int lastPoster) {
        this.lastPoster = lastPoster;
    }

    public int getLasPost() {
        return lasPost;
    }

    public void setLasPost(int lasPost) {
        this.lasPost = lasPost;
    }

    @Override
    public String toString() {
        return "Sujet{" + "id=" + id + ", forum=" + idForum + ", utilisateur=" + idUtilisateur + ", titre=" + titre + ", sousTitre=" + sousTitre + ", descriptionSujet=" + descriptionSujet + ", nombreMessage=" + nombreMessage + ", etatSujet=" + etatSujet + ", datePublication=" + datePublication + ", dateModification=" + dateModification + ", aimeSujet=" + aimeSujet + ", lastPoster=" + lastPoster + ", lasPost=" + lasPost + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sujet other = (Sujet) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
