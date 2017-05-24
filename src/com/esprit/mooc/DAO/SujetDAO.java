/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Interfaces.IDAOSujet;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Souhaib
 */
public class SujetDAO implements IDAOSujet {

    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    public SujetDAO() {
        try {
            conn = MyConnection.getInstance();
        } catch (Exception e) {
        }
    }

    @Override
    public boolean ajouterSujet(Sujet sujet) {
        String req = "INSERT INTO sujet (id_forum_sujet,   id_apprenant_sujet,   titre_sujet,"
                + "soustitre_sujet, description_sujet, date_publication_sujet)"
                + "  VALUES (?,?,?,?,?,?) ";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, sujet.getForum());
            ps.setInt(2, sujet.getUtilisateur());
            ps.setString(3, sujet.getTitre());
            ps.setString(4, sujet.getSousTitre());
            ps.setString(5, sujet.getDescriptionSujet());
            ps.setString(6, sqlDate.toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean modifierSujet(Sujet sujet) {
        String req = "UPDATE sujet SET "
                + "titre_sujet=?,"
                + " soustitre_sujet=?, "
                + "description_sujet=?,"
                + "etat_sujet=?,"
                + " date_modification_sujet=?"
                + " WHERE id_sujet=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setString(1, sujet.getTitre());
            ps.setString(2, sujet.getSousTitre());
            ps.setString(3, sujet.getDescriptionSujet());
            ps.setInt(4, 1);
            ps.setString(5, sujet.getDateModification());
            ps.setInt(6, sujet.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public Boolean supprimerSujet(int id) {
        String req = "DELETE from sujet WHERE id_sujet=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Sujet> findAllSujets(int idForum) {
        String req = "Select * from sujet where id_forum_sujet=" + idForum;
        List<Sujet> listSujets = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Sujet sujet = new Sujet();
                sujet.setId(rs.getInt("id_sujet"));
                sujet.setUtilisateur(rs.getInt("id_apprenant_sujet"));
                sujet.setTitre(rs.getString("titre_sujet"));
                sujet.setSousTitre(rs.getString("soustitre_sujet"));
                sujet.setDescriptionSujet(rs.getString("description_sujet"));
                sujet.setNombreMessage(rs.getInt("nombre_message"));
                sujet.setEtatSujet(rs.getInt("etat_sujet"));
                sujet.setDatePublication(rs.getString("date_publication_sujet"));
                sujet.setDateModification(rs.getString("date_modification_sujet"));
                sujet.setAimeSujet(rs.getInt("aime_sujet"));
                sujet.setLastPoster(rs.getInt("last_poster"));
                sujet.setLasPost(rs.getInt("last_poste"));
                sujet.setForum(rs.getInt("id_forum_sujet"));
                listSujets.add(sujet);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listSujets;
    }

    @Override
    public List<Sujet> findSujetByUtilisateur(int idUtilisateur) {
        String req = "Select * from sujet where id_apprenant_sujet=" + idUtilisateur;
        List<Sujet> listSujets = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Sujet sujet = new Sujet();
                sujet.setId(rs.getInt("id_sujet"));
                sujet.setUtilisateur(rs.getInt("id_apprenant_sujet"));
                sujet.setTitre(rs.getString("titre_sujet"));
                sujet.setSousTitre(rs.getString("soustitre_sujet"));
                sujet.setDescriptionSujet(rs.getString("description_sujet"));
                sujet.setNombreMessage(rs.getInt("nombre_message"));
                sujet.setEtatSujet(rs.getInt("etat_sujet"));
                sujet.setDatePublication(rs.getString("date_publication_sujet"));
                sujet.setDateModification(rs.getString("date_modification_sujet"));
                sujet.setAimeSujet(rs.getInt("aime_sujet"));
                sujet.setLastPoster(rs.getInt("last_poster"));
                sujet.setLasPost(rs.getInt("last_poste"));
                listSujets.add(sujet);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listSujets;
    }

    @Override
    public List<Sujet> chercherSujet(String titreSujet, int idForum) {
        String req = "Select * from sujet where titre_sujet LIKE'%" + titreSujet + "%' AND id_forum_sujet=" + idForum;
        List<Sujet> listSujets = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Sujet sujet = new Sujet();
                sujet.setId(rs.getInt("id_sujet"));
                sujet.setUtilisateur(rs.getInt("id_apprenant_sujet"));
                sujet.setTitre(rs.getString("titre_sujet"));
                sujet.setSousTitre(rs.getString("soustitre_sujet"));
                sujet.setDescriptionSujet(rs.getString("description_sujet"));
                sujet.setNombreMessage(rs.getInt("nombre_message"));
                sujet.setEtatSujet(rs.getInt("etat_sujet"));
                sujet.setDatePublication(rs.getString("date_publication_sujet"));
                sujet.setDateModification(rs.getString("date_modification_sujet"));
                sujet.setAimeSujet(rs.getInt("aime_sujet"));
                sujet.setLastPoster(rs.getInt("last_poster"));
                sujet.setLasPost(rs.getInt("last_poste"));
                listSujets.add(sujet);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listSujets;

    }

    public List<Sujet> chercherSujetByNom(String titreSujet, int idUser) {
        String req = "Select * from sujet where titre_sujet LIKE'%" + titreSujet + "%'  AND id_apprenant_sujet=" + idUser;
        List<Sujet> listSujets = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Sujet sujet = new Sujet();
                sujet.setId(rs.getInt("id_sujet"));
                sujet.setUtilisateur(rs.getInt("id_apprenant_sujet"));
                sujet.setTitre(rs.getString("titre_sujet"));
                sujet.setSousTitre(rs.getString("soustitre_sujet"));
                sujet.setDescriptionSujet(rs.getString("description_sujet"));
                sujet.setNombreMessage(rs.getInt("nombre_message"));
                sujet.setEtatSujet(rs.getInt("etat_sujet"));
                sujet.setDatePublication(rs.getString("date_publication_sujet"));
                sujet.setDateModification(rs.getString("date_modification_sujet"));
                sujet.setAimeSujet(rs.getInt("aime_sujet"));
                sujet.setLastPoster(rs.getInt("last_poster"));
                sujet.setLasPost(rs.getInt("last_poste"));
                listSujets.add(sujet);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listSujets;

    }
}
