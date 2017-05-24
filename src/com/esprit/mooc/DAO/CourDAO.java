/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.Interfaces.ICourDAO;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Firas
 */
public class CourDAO implements ICourDAO {

    private static Connection connection;
    private Statement stm = null;
    private PreparedStatement preStm;
    ResultSet rs;
    private final String reqAfficher = "SELECT `id_cours`,`id_discipline_cours`, `id_formateur_cours`, `titre_cours`, `description_cours`, `objectif_cours`, `prerequis_cours`, `duree_cours`, `etat_cours`, `video_cours`, `like_cours`, `niveau_cours`, `introduction_cours`  FROM COURS ";
    private final String reqAjout = "INSERT INTO cours (id_discipline_cours, id_formateur_cours, titre_cours, description_cours, objectif_cours, prerequis_cours, duree_cours, etat_cours, video_cours, like_cours, niveau_cours, introduction_cours, date_ajout, nombre_visite) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
    private final String reqModifier = "UPDATE `cours` SET `id_discipline_cours` = ?, `id_formateur_cours` = ?, `titre_cours` = ?, `description_cours` = ?, `objectif_cours` = ?, `prerequis_cours` = ?, `duree_cours` = ?, `etat_cours` = ?, `video_cours` = ?, `like_cours` = ?, `niveau_cours` = ?, `introduction_cours` = ? WHERE `cours`.`id_cours` = ?";
    private final String reqSupprimer = "DELETE FROM cours WHERE id_cours = ?";

    public CourDAO() {
        try {
            connection = MyConnection.getInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean AjoutCour(Cours c) {
        try {
            preStm = connection.prepareStatement(reqAjout);
            preStm.setInt(1, c.getDiscipline().getId());
            preStm.setInt(2, c.getUtilisateur().getId());
            preStm.setString(3, c.getTitre_cours());
            preStm.setString(4, c.getDescription_cours());
            preStm.setString(5, c.getObjectif_cours());
            preStm.setString(6, c.getPrerequis_cours());
            preStm.setInt(7, c.getDuree_cours());
            preStm.setString(8, c.getEtat_cours());
            preStm.setString(9, c.getVideo_cours());
            preStm.setInt(10, c.getLike_cours());
            preStm.setString(11, c.getNiveau_cours());
            preStm.setString(12, c.getIntroduction_cours());
            preStm.setDate(13, c.getDate_ajout());
            preStm.setInt(14, c.getNmbr_vu());

            preStm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
return true;
    }

    @Override
    public void SupprimerCour(int id_Cour) {
        try {
            preStm = connection.prepareStatement(reqSupprimer);
            preStm.setInt(1, id_Cour);

            preStm.executeUpdate();

            System.out.println(preStm);

        } catch (SQLException ex) {
            Logger.getLogger(CourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public ObservableList<Cours> displayAll() {
        String req = "select * from Cours";

        ObservableList<Cours> lCour = FXCollections.observableArrayList();
        try {
            preStm = connection.prepareStatement(req);
            rs = preStm.executeQuery();
            while (rs.next()) {

                DisciplineDao disp = new DisciplineDao();
                UtilisateurDAO usr = new UtilisateurDAO();
                Cours s = new Cours(usr.findUtilisateurById(rs.getInt("id_formateur_cours")),
                        disp.findDisciplineById(rs.getInt("id_discipline_cours")),
                        rs.getString("titre_cours"),
                        rs.getString("description_cours"),
                        rs.getString("objectif_cours"),
                        rs.getString("prerequis_cours"),
                        rs.getInt("duree_cours"),
                        rs.getString("etat_cours"),
                        rs.getString("video_cours"),
                        rs.getInt("like_cours"),
                        rs.getString("niveau_cours"),
                        rs.getString("introduction_cours"),
                        rs.getDate("date_ajout"),
                        rs.getInt("nombre_visite"));
                s.setId(rs.getInt("id_cours"));

                System.out.println(disp.findDisciplineById(rs.getInt("id_discipline_cours")));
                lCour.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lCour;
    }

    public ObservableList<Cours> displayAllAccepted() {
        String req = "select * from Cours where etat_cours='accepter'";

        ObservableList<Cours> lCour = FXCollections.observableArrayList();
        try {
            preStm = connection.prepareStatement(req);
            rs = preStm.executeQuery();
            while (rs.next()) {

                DisciplineDao disp = new DisciplineDao();
                UtilisateurDAO usr = new UtilisateurDAO();
                Cours s = new Cours(usr.findUtilisateurById(rs.getInt("id_formateur_cours")),
                        disp.findDisciplineById(rs.getInt("id_discipline_cours")),
                        rs.getString("titre_cours"),
                        rs.getString("description_cours"),
                        rs.getString("objectif_cours"),
                        rs.getString("prerequis_cours"),
                        rs.getInt("duree_cours"),
                        rs.getString("etat_cours"),
                        rs.getString("video_cours"),
                        rs.getInt("like_cours"),
                        rs.getString("niveau_cours"),
                        rs.getString("introduction_cours"),
                        rs.getDate("date_ajout"),
                        rs.getInt("nombre_visite"));
                s.setId(rs.getInt("id_cours"));

                System.out.println(disp.findDisciplineById(rs.getInt("id_discipline_cours")));
                lCour.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lCour;
    }

    public ObservableList<Cours> displayAllEnAttente() {
        String req = "select * from Cours where etat_cours='en attente'";

        ObservableList<Cours> lCour = FXCollections.observableArrayList();
        try {
            preStm = connection.prepareStatement(req);
            rs = preStm.executeQuery();
            while (rs.next()) {

                DisciplineDao disp = new DisciplineDao();
                UtilisateurDAO usr = new UtilisateurDAO();
                Cours s = new Cours(usr.findUtilisateurById(rs.getInt("id_formateur_cours")),
                        disp.findDisciplineById(rs.getInt("id_discipline_cours")),
                        rs.getString("titre_cours"),
                        rs.getString("description_cours"),
                        rs.getString("objectif_cours"),
                        rs.getString("prerequis_cours"),
                        rs.getInt("duree_cours"),
                        rs.getString("etat_cours"),
                        rs.getString("video_cours"),
                        rs.getInt("like_cours"),
                        rs.getString("niveau_cours"),
                        rs.getString("introduction_cours"),
                        rs.getDate("date_ajout"),
                        rs.getInt("nombre_visite"));
                s.setId(rs.getInt("id_cours"));

                System.out.println(disp.findDisciplineById(rs.getInt("id_discipline_cours")));
                lCour.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lCour;
    }

    public void AccepterCour(Cours cour) {
        String req = "UPDATE `Cours` SET `etat_cours` = 'accepter' WHERE `cours`.`id_cours` =" + cour.getId();
        try {
            preStm = connection.prepareStatement(req);

            preStm.executeUpdate();

            System.out.println(preStm);

        } catch (SQLException ex) {
            Logger.getLogger(CourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void RefuserCour(Cours cour) {
        String req = "UPDATE `Cours` SET `etat_cours` = 'refuser' WHERE `cours`.`id_cours` =" + cour.getId();
        try {
            preStm = connection.prepareStatement(req);

            preStm.executeUpdate();

            System.out.println(preStm);

        } catch (SQLException ex) {
            Logger.getLogger(CourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Cours> RechercheCour(String seachBy, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cours RechercheOneCour(String seachBy, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ModifierCour(Cours cour) {
        String req = "UPDATE `cours` SET  `titre_cours` = '" + cour.getTitre_cours() + "' WHERE id_cours =" + cour.getId();
        try {
            preStm = connection.prepareStatement(req);

            preStm.executeUpdate();

            System.out.println(preStm);

        } catch (SQLException ex) {
            Logger.getLogger(CourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public Cours findCourById(int id) {
        String req = "select * from Cours WHERE id_cours=" + id;
        try {

            preStm = connection.prepareStatement(req);
            rs = preStm.executeQuery();
            if (rs.next()) {
                DisciplineDao disp = new DisciplineDao();
                UtilisateurDAO usr = new UtilisateurDAO();
                Cours s = new Cours(usr.findUtilisateurById(rs.getInt("id_formateur_cours")),
                        disp.findDisciplineById(rs.getInt("id_discipline_cours")),
                        rs.getString("titre_cours"),
                        rs.getString("description_cours"),
                        rs.getString("objectif_cours"),
                        rs.getString("prerequis_cours"),
                        rs.getInt("duree_cours"),
                        rs.getString("etat_cours"),
                        rs.getString("video_cours"),
                        rs.getInt("like_cours"),
                        rs.getString("niveau_cours"),
                        rs.getString("introduction_cours"),
                        rs.getDate("date_ajout"),
                        rs.getInt("nombre_visite"));
                s.setId(rs.getInt("id_cours"));
                return s;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ObservableList<Cours> displayMyCours(Utilisateur u) {

        String req = "select * from Cours where id_formateur_cours=" + u.getId();

        ObservableList<Cours> lCour = FXCollections.observableArrayList();
        try {
            preStm = connection.prepareStatement(req);
            rs = preStm.executeQuery();
            while (rs.next()) {

                DisciplineDao disp = new DisciplineDao();
                UtilisateurDAO usr = new UtilisateurDAO();
                Cours s = new Cours(usr.findUtilisateurById(rs.getInt("id_formateur_cours")),
                        disp.findDisciplineById(rs.getInt("id_discipline_cours")),
                        rs.getString("titre_cours"),
                        rs.getString("description_cours"),
                        rs.getString("objectif_cours"),
                        rs.getString("prerequis_cours"),
                        rs.getInt("duree_cours"),
                        rs.getString("etat_cours"),
                        rs.getString("video_cours"),
                        rs.getInt("like_cours"),
                        rs.getString("niveau_cours"),
                        rs.getString("introduction_cours"),
                        rs.getDate("date_ajout"),
                        rs.getInt("nombre_visite"));
                s.setId(rs.getInt("id_cours"));
                System.out.println(disp.findDisciplineById(rs.getInt("id_discipline_cours")));
                lCour.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lCour;
    }

    public ObservableList<Cours> findCours(String Nom) {

        String req = "Select * from Cours where etat_cours= 'accepter' AND titre_cours LIKE '%" + Nom + "%'";

        ObservableList<Cours> lCour = FXCollections.observableArrayList();
        try {
            preStm = connection.prepareStatement(req);
            rs = preStm.executeQuery();
            while (rs.next()) {

                DisciplineDao disp = new DisciplineDao();
                UtilisateurDAO usr = new UtilisateurDAO();
                Cours s = new Cours(usr.findUtilisateurById(rs.getInt("id_formateur_cours")),
                        disp.findDisciplineById(rs.getInt("id_discipline_cours")),
                        rs.getString("titre_cours"),
                        rs.getString("description_cours"),
                        rs.getString("objectif_cours"),
                        rs.getString("prerequis_cours"),
                        rs.getInt("duree_cours"),
                        rs.getString("etat_cours"),
                        rs.getString("video_cours"),
                        rs.getInt("like_cours"),
                        rs.getString("niveau_cours"),
                        rs.getString("introduction_cours"),
                        rs.getDate("date_ajout"),
                        rs.getInt("nombre_visite"));
                s.setId(rs.getInt("id_cours"));

                System.out.println(disp.findDisciplineById(rs.getInt("id_discipline_cours")));
                lCour.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lCour;
    }
}
