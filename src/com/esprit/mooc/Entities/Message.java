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
public class Message {

    private int id;
    private int idUtilisateurMessage; // proprietaire du message
    private int idSujetMessage;
    private String contenuMessage;
    private String titreMessage;
    private String datePublicationMessage;
    private String dateModificationMessage;

    public Message() {
    }

    public Message(int utilisateurMessage, int sujetMessage, String contenuMessage, String titreMessage, String datePublicationMessage, String dateModificationMessage) {

        this.idUtilisateurMessage = utilisateurMessage;
        this.idSujetMessage = sujetMessage;
        this.contenuMessage = contenuMessage;
        this.titreMessage = titreMessage;
        this.datePublicationMessage = datePublicationMessage;
        this.dateModificationMessage = dateModificationMessage;
    }

    public Message(int id, int utilisateurMessage, int sujetMessage, String contenuMessage, String titreMessage, String datePublicationMessage, String dateModificationMessage) {
        this.id = id;
        this.idUtilisateurMessage = utilisateurMessage;
        this.idSujetMessage = sujetMessage;
        this.contenuMessage = contenuMessage;
        this.titreMessage = titreMessage;
        this.datePublicationMessage = datePublicationMessage;
        this.dateModificationMessage = dateModificationMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurMessage() {
        return idUtilisateurMessage;
    }

    public void setUtilisateurMessage(int utilisateurMessage) {
        this.idUtilisateurMessage = utilisateurMessage;
    }

    public int getSujetMessage() {
        return idSujetMessage;
    }

    public void setSujetMessage(int sujetMessage) {
        this.idSujetMessage = sujetMessage;
    }

    public String getContenuMessage() {
        return contenuMessage;
    }

    public void setContenuMessage(String contenuMessage) {
        this.contenuMessage = contenuMessage;
    }

    public String getTitreMessage() {
        return titreMessage;
    }

    public void setTitreMessage(String titreMessage) {
        this.titreMessage = titreMessage;
    }

    public String getDatePublicationMessage() {
        return datePublicationMessage;
    }

    public void setDatePublicationMessage(String datePublicationMessage) {
        this.datePublicationMessage = datePublicationMessage;
    }

    public String getDateModificationMessage() {
        return dateModificationMessage;
    }

    public void setDateModificationMessage(String dateModificationMessage) {
        this.dateModificationMessage = dateModificationMessage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
