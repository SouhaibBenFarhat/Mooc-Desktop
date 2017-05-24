/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Entities;

import java.sql.Date;

/**
 *
 * @author Firas
 */
public class Cours {

    int id;
    Utilisateur utilisateur ;   
    Discipline discipline ;
    
    String titre_cours;
    String description_cours;
    String objectif_cours;
    String prerequis_cours;
    int duree_cours;
    String etat_cours;
    String video_cours;
    int like_cours;
    String niveau_cours;
    String introduction_cours;
    Date date_ajout;
    int nmbr_vu;

    public Cours() {
        utilisateur=new Utilisateur();
        discipline= new Discipline();
    }

    public Cours(Utilisateur utilisateur, Discipline discipline, String titre_cours, String description_cours, String objectif_cours, String prerequis_cours, int duree_cours, String etat_cours, String video_cours, int like_cours, String niveau_cours, String introduction_cours,Date date_ajout, int nmbr_vu) {
        this();
        this.utilisateur=utilisateur;
        this.discipline=discipline;
        this.titre_cours = titre_cours;
        this.description_cours = description_cours;
        this.objectif_cours = objectif_cours;
        this.prerequis_cours = prerequis_cours;
        this.duree_cours = duree_cours;
        this.etat_cours = etat_cours;
        this.video_cours = video_cours;
        this.like_cours = like_cours;
        this.niveau_cours = niveau_cours;
        this.introduction_cours = introduction_cours;
        this.date_ajout = date_ajout;
        this.nmbr_vu = nmbr_vu;
    }


 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

   

    public String getTitre_cours() {
        return titre_cours;
    }

    public void setTitre_cours(String titre_cours) {
        this.titre_cours = titre_cours;
    }

    public String getDescription_cours() {
        return description_cours;
    }

    public void setDescription_cours(String description_cours) {
        this.description_cours = description_cours;
    }

    public String getObjectif_cours() {
        return objectif_cours;
    }

    public void setObjectif_cours(String objectif_cours) {
        this.objectif_cours = objectif_cours;
    }

    public String getPrerequis_cours() {
        return prerequis_cours;
    }

    public void setPrerequis_cours(String prerequis_cours) {
        this.prerequis_cours = prerequis_cours;
    }

    public int getDuree_cours() {
        return duree_cours;
    }

    public void setDuree_cours(int duree_cours) {
        this.duree_cours = duree_cours;
    }

    public String getEtat_cours() {
        return etat_cours;
    }

    public void setEtat_cours(String etat_cours) {
        this.etat_cours = etat_cours;
    }

    public String getVideo_cours() {
        return video_cours;
    }

    public void setVideo_cours(String video_cours) {
        this.video_cours = video_cours;
    }

    public int getLike_cours() {
        return like_cours;
    }

    public void setLike_cours(int like_cours) {
        this.like_cours = like_cours;
    }

    public String getNiveau_cours() {
        return niveau_cours;
    }

    public void setNiveau_cours(String niveau_cours) {
        this.niveau_cours = niveau_cours;
    }

    public String getIntroduction_cours() {
        return introduction_cours;
    }

    public void setIntroduction_cours(String introduction_cours) {
        this.introduction_cours = introduction_cours;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public int getNmbr_vu() {
        return nmbr_vu;
    }

    public void setNmbr_vu(int nmbr_vu) {
        this.nmbr_vu = nmbr_vu;
    }

    @Override
    public String toString() {
        return "Cour{" + "id=" + id + ", utilisateur=" + utilisateur + ", discipline=" + discipline + ", titre_cours=" + titre_cours + ", description_cours=" + description_cours + ", objectif_cours=" + objectif_cours + ", prerequis_cours=" + prerequis_cours + ", duree_cours=" + duree_cours + ", etat_cours=" + etat_cours + ", video_cours=" + video_cours + ", like_cours=" + like_cours + ", niveau_cours=" + niveau_cours + ", introduction_cours=" + introduction_cours + ", date_ajout=" + date_ajout + ", nmbr_vu=" + nmbr_vu + '}';
    }
    


}
