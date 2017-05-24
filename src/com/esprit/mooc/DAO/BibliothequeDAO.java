/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.DAO.CourDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Bibliotheque;
import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.Interfaces.IBibliotheque;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Firas
 */
public class BibliothequeDAO implements IBibliotheque {

    private static Connection connection;
    private Statement stm = null;
    private PreparedStatement preStm;
    ResultSet rs;

    private final String ajoutreq = "INSERT INTO bibliotheque ( id_cours_bibliotheque, id_apprenant_bibliotheque) VALUES (?, ?)";
    private final String reqSupprimer = "DELETE FROM bibliotheque WHERE id_cours_bibliotheque = ? and id_apprenant_bibliotheque= ? ";

    public BibliothequeDAO() {

        try {
            connection = MyConnection.getInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addCourBib(Cours c) {

        UtilisateurDAO user = new UtilisateurDAO();

        try {
            preStm = connection.prepareStatement(ajoutreq);
            preStm.setInt(2, user.loggedUser.getId());
            preStm.setInt(1, c.getId());

            preStm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BibliothequeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeCourBib(Cours c) {
        UtilisateurDAO user = new UtilisateurDAO();

        try {
            preStm = connection.prepareStatement(reqSupprimer);
            preStm.setInt(1, c.getId());
            preStm.setInt(2, user.loggedUser.getId());
             preStm.executeUpdate();
            System.out.println("removeBib");
            
        } catch (SQLException ex) {
            Logger.getLogger(BibliothequeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Cours> displayAll(Utilisateur u) {
        ObservableList<Cours> lbib = FXCollections.observableArrayList();

        try {

            String sh;
            System.out.println(u.getId());
            sh = String.valueOf(u.getId());

            String req = "select * from Bibliotheque where id_apprenant_bibliotheque=" + sh;

            preStm = connection.prepareStatement(req);
            rs = preStm.executeQuery();
            while (rs.next()) {

                CourDAO cour = new CourDAO();

                int i;
                i = rs.getInt("id_cours_bibliotheque");

                lbib.add(cour.findCourById(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lbib;

    }

}
