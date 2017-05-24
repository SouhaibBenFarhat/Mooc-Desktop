/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Entities;

import java.sql.Date;

/**
 *
 * @author Anas
 */
public class Chapitre {

    private int id_chapitre;
    private int id_cours_chapitre;
    private String titre_chapitre;
    private String description_chapitre;
    private String chemin_chapitre;
    private String chemin_video_chapitre;
    private String chemin_presentation_chapitre;
    private String contenu_chapitre;
    private int duree_chapitre;
    private String niveau_chapitre;
    private String introduction_chapitre;
    private String objectif_chapitre;
    Utilisateur utilisateur ; 
        String date_ajout;
    public Chapitre() {
        utilisateur=new Utilisateur();

    }
         public Chapitre(int id_cours_chapitre,String titre_chapitre,String description_chapitre,String chemin_chapitre,String chemin_video_chapitre,String chemin_presentation_chapitre,String contenu_chapitre,int duree_chapitre,String niveau_chapitre,String introduction_chapitre,String objectif_chapitre,String date_ajout){
        this.id_cours_chapitre = id_cours_chapitre;
        this.titre_chapitre = titre_chapitre;
        this.description_chapitre = description_chapitre;
        this.chemin_chapitre = chemin_chapitre;
        this.chemin_video_chapitre = chemin_video_chapitre;
        this.chemin_presentation_chapitre = chemin_presentation_chapitre;
        this.contenu_chapitre = contenu_chapitre;
        this.duree_chapitre = duree_chapitre;
        this.niveau_chapitre = niveau_chapitre;
        this.introduction_chapitre = introduction_chapitre;
        this.objectif_chapitre = objectif_chapitre;
        this.date_ajout = date_ajout;
         }

    
    public Chapitre(int id_chapitre, int id_cours_chapitre, String titre_chapitre, String description_chapitre, String chemin_chapitre, String chemin_video_chapitre, String chemin_presentation_chapitre, String contenu_chapitre, int duree_chapitre, String niveau_chapitre, String introduction_chapitre, String objectif_chapitre, String date_ajout) {
        this.id_chapitre = id_chapitre;
        this.id_cours_chapitre = id_cours_chapitre;
        this.titre_chapitre = titre_chapitre;
        this.description_chapitre = description_chapitre;
        this.chemin_chapitre = chemin_chapitre;
        this.chemin_video_chapitre = chemin_video_chapitre;
        this.chemin_presentation_chapitre = chemin_presentation_chapitre;
        this.contenu_chapitre = contenu_chapitre;
        this.duree_chapitre = duree_chapitre;
        this.niveau_chapitre = niveau_chapitre;
        this.introduction_chapitre = introduction_chapitre;
        this.objectif_chapitre = objectif_chapitre;
        this.date_ajout = date_ajout;
    }

    public Chapitre(int id_cours_chapitre, String titre_chapitre, String description_chapitre, String chemin_chapitre, String chemin_video_chapitre, String chemin_presentation_chapitre) {
        this.id_cours_chapitre = id_cours_chapitre;
        this.titre_chapitre = titre_chapitre;
        this.description_chapitre = description_chapitre;
        this.chemin_chapitre = chemin_chapitre;
        this.chemin_video_chapitre = chemin_video_chapitre;
        this.chemin_presentation_chapitre = chemin_presentation_chapitre;
    }

    public Chapitre(int id_chapitre, int id_cours_chapitre, String titre_chapitre, String description_chapitre, String chemin_chapitre, String chemin_video_chapitre, String chemin_presentation_chapitre, Utilisateur utilisateur, String date_ajout,String contenu_chapitre) {
        this.id_chapitre = id_chapitre;
        this.id_cours_chapitre = id_cours_chapitre;
        this.titre_chapitre = titre_chapitre;
        this.description_chapitre = description_chapitre;
        this.chemin_chapitre = chemin_chapitre;
        this.chemin_video_chapitre = chemin_video_chapitre;
        this.chemin_presentation_chapitre = chemin_presentation_chapitre;
        this.utilisateur = utilisateur;
        this.date_ajout = date_ajout;
        this.contenu_chapitre=contenu_chapitre;
    }


    public Chapitre(int id_chapitre) {
        this.id_chapitre = id_chapitre;
    }

    public void setId_chapitre(int id_chapitre) {
        this.id_chapitre = id_chapitre;
    }

    public int getId_chapitre() {
        return id_chapitre;
    }

    public int getId_cours_chapitre() {
        return id_cours_chapitre;
    }

    public void setId_cours_chapitre(int id_cours_chapitre) {
        this.id_cours_chapitre = id_cours_chapitre;
    }

    public String getTitre_chapitre() {
        return titre_chapitre;
    }

    public void setTitre_chapitre(String titre_chapitre) {
        this.titre_chapitre = titre_chapitre;
    }

    public String getDescription_chapitre() {
        return description_chapitre;
    }

    public void setDescription_chapitre(String description_chapitre) {
        this.description_chapitre = description_chapitre;
    }

    public String getChemin_chapitre() {
        return chemin_chapitre;
    }

    public void setChemin_chapitre(String chemin_chapitre) {
        this.chemin_chapitre = chemin_chapitre;
    }

    public String getChemin_video_chapitre() {
        return chemin_video_chapitre;
    }

    public void setChemin_video_chapitre(String chemin_video_chapitre) {
        this.chemin_video_chapitre = chemin_video_chapitre;
    }

    public String getChemin_presentation_chapitre() {
        return chemin_presentation_chapitre;
    }

    public void setChemin_presentation_chapitre(String chemin_presentation_chapitre) {
        this.chemin_presentation_chapitre = chemin_presentation_chapitre;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
        public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getContenu_chapitre() {
        return contenu_chapitre;
    }

    public void setContenu_chapitre(String contenu_chapitre) {
        this.contenu_chapitre = contenu_chapitre;
    }

    public int getDuree_chapitre() {
        return duree_chapitre;
    }

    public void setDuree_chapitre(int duree_chapitre) {
        this.duree_chapitre = duree_chapitre;
    }

    public String getNiveau_chapitre() {
        return niveau_chapitre;
    }

    public void setNiveau_chapitre(String niveau_chapitre) {
        this.niveau_chapitre = niveau_chapitre;
    }

    public String getIntroduction_chapitre() {
        return introduction_chapitre;
    }

    public void setIntroduction_chapitre(String introduction_chapitre) {
        this.introduction_chapitre = introduction_chapitre;
    }

    public String getObjectif_chapitre() {
        return objectif_chapitre;
    }

    public void setObjectif_chapitre(String objectif_chapitre) {
        this.objectif_chapitre = objectif_chapitre;
    }
        
}
