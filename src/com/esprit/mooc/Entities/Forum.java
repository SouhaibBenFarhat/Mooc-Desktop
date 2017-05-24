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
public class Forum {

    private int id;
    private int idDiscipline;
    private String nomForum;
    private int nombreSujet;
    private String dateCreation;
    private int lastSujet;
    private int nombreVue;

    public Forum() {
    }

    public Forum(int id, int idDiscipline, String nomForum, int nombreSujet, String dateCreation, int lastSujet, int nombreVue) {
        this.id = id;
        this.idDiscipline = idDiscipline;
        this.nomForum = nomForum;
        this.nombreSujet = nombreSujet;
        this.dateCreation = dateCreation;
        this.lastSujet = lastSujet;
        this.nombreVue = nombreVue;
    }

    public Forum(int idDiscipline, String nomForum, int nombreSujet, String dateCreation, int lastSujet, int nombreVue) {

        this.idDiscipline = idDiscipline;
        this.nomForum = nomForum;
        this.nombreSujet = nombreSujet;
        this.dateCreation = dateCreation;
        this.lastSujet = lastSujet;
        this.nombreVue = nombreVue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscipline() {
        return idDiscipline;
    }

    public void setDiscipline(int discipline) {
        this.idDiscipline = discipline;
    }

    public String getNomForum() {
        return nomForum;
    }

    public void setNomForum(String nomForum) {
        this.nomForum = nomForum;
    }

    public int getNombreSujet() {
        return nombreSujet;
    }

    public void setNombreSujet(int nombreSujet) {
        this.nombreSujet = nombreSujet;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getLastSujet() {
        return lastSujet;
    }

    public void setLastSujet(int lastSujet) {
        this.lastSujet = lastSujet;
    }

    public int getNombreVue() {
        return nombreVue;
    }

    public void setNombreVue(int nombreVue) {
        this.nombreVue = nombreVue;
    }

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", idDiscipline=" + idDiscipline + ", nomForum=" + nomForum + ", nombreSujet=" + nombreSujet + ", dateCreation=" + dateCreation + ", lastSujet=" + lastSujet + ", nombreVue=" + nombreVue + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Forum other = (Forum) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
