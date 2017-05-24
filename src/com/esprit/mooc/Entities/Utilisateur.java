/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Entities;

import java.sql.Date;

/**
 *
 * @author kods
 */
public class Utilisateur {

    private int id;
    private String usename;
    private String usenameCanonical;
    private String email;
    private String emailCanonical;
    private int enbaled;
    private String salt;
    private String password;
    private int locked;
    private int expired;
    private String role;
    private int credentialsExpired;
    private int idEntrepriseUtilisateur;
    private int photo;
    private String sexe;
    public static String loggedUser;
    public Date lastlogin;

    public Utilisateur(int id, String usename, String email, String role, Date lastlogin) {
        this.id = id;
        this.usename = usename;
        this.email = email;
        this.role = role;
        this.lastlogin = lastlogin;
    }

    public Utilisateur() {
        loggedUser = usename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getUsenameCanonical() {
        return usenameCanonical;
    }

    public void setUsenameCanonical(String usenameCanonical) {
        this.usenameCanonical = usenameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public int getEnbaled() {
        return enbaled;
    }

    public void setEnbaled(int enbaled) {
        this.enbaled = enbaled;
    }

    public String getSalt() {
        return salt;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(int credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public int getIdEntrepriseUtilisateur() {
        return idEntrepriseUtilisateur;
    }

    public void setIdEntrepriseUtilisateur(int idEntrepriseUtilisateur) {
        this.idEntrepriseUtilisateur = idEntrepriseUtilisateur;
    }

    public static String getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(String loggedUser) {
        Utilisateur.loggedUser = loggedUser;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", usename=" + usename + ", usenameCanonical=" + usenameCanonical + ", email=" + email + ", emailCanonical=" + emailCanonical + ", enbaled=" + enbaled + ", salt=" + salt + ", password=" + password + ", locked=" + locked + ", expired=" + expired + ", role=" + role + ", credentialsExpired=" + credentialsExpired + ", idEntrepriseUtilisateur=" + idEntrepriseUtilisateur + ", photo=" + photo + ", sexe=" + sexe + '}';
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

}
