/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Entities;

/**
 *
 * @author Firas
 */
public class Bibliotheque {
    
    private Utilisateur utilisateur;
    private Cours cour;

    public Bibliotheque() {
        utilisateur=new Utilisateur();
        cour=new Cours();
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Cours getCour() {
        return cour;
    }

    public void setCour(Cours cour) {
        this.cour = cour;
    }

    @Override
    public String toString() {
        return "Bibliotheque{" + "utilisateur=" + utilisateur + ", cour=" + cour + '}';
    }

    
    
    
    
}
