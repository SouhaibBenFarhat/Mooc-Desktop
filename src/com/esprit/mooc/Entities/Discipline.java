/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Entities;

/**
 *
 * @author Souhaib
 */
public class Discipline {

    private int id;
    private String nomDiscipline;
    private String logo;
    private int nombreCours;
    private Integer forumDiscipline;
    private String descriptionDiscipline;
    private String dateCreationDiscipline;

    public Discipline() {
    }

    public Discipline(int id, String nomDiscipline, String logo, int nombreCours) {
        this.id = id;
        this.nomDiscipline = nomDiscipline;
        this.logo = logo;
        this.nombreCours = nombreCours;
    }

    public Discipline(String nomDiscipline, String logo, int nombreCours) {

        this.nomDiscipline = nomDiscipline;
        this.logo = logo;
        this.nombreCours = nombreCours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomDiscipline() {
        return nomDiscipline;
    }

    public void setNomDiscipline(String nomDiscipline) {
        this.nomDiscipline = nomDiscipline;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getNombreCours() {
        return nombreCours;
    }

    public void setNombreCours(int nombreCours) {
        this.nombreCours = nombreCours;
    }

    @Override
    public String toString() {
        return nomDiscipline;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Discipline other = (Discipline) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Integer getForumDiscipline() {
        return forumDiscipline;
    }

    public void setForumDiscipline(int forumDiscipline) {
        this.forumDiscipline = forumDiscipline;
    }

    public String getDescriptionDiscipline() {
        return descriptionDiscipline;
    }

    public void setDescriptionDiscipline(String descriptionDiscipline) {
        this.descriptionDiscipline = descriptionDiscipline;
    }

    public String getDateCreationDiscipline() {
        return dateCreationDiscipline;
    }

    public void setDateCreationDiscipline(String dateCreationDiscipline) {
        this.dateCreationDiscipline = dateCreationDiscipline;
    }

}
