/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.Interfaces.IDAODiscipline;
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
 * @author kods
 */
public class DisciplineDao implements IDAODiscipline {

    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public DisciplineDao() {

        try {
            conn = MyConnection.getInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean ajouterDiscipline(Discipline d) {
        String req = "INSERT INTO discipline ( nom_discipline,"
                + "nombre_cours, description_discipline, date_creation)"
                + "  VALUES (?,?,?,?) ";
        try {
            ps = conn.prepareStatement(req);
            ps.setString(1, d.getNomDiscipline());
            ps.setInt(2, d.getNombreCours());
            ps.setString(3, d.getDescriptionDiscipline());
            ps.setString(4, d.getDateCreationDiscipline());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifierDiscipline(Discipline d) {
        String req = "UPDATE discipline SET "
                + "id_forum_discipline=?"
                + " WHERE id_discipline=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, d.getForumDiscipline());
            ps.setInt(2, d.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean modifierDonneDiscipline(Discipline d) {
        String req = "UPDATE discipline SET "
                + "nom_discipline=?,"
                + "description_discipline=?"
                + " WHERE id_discipline=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setString(1, d.getNomDiscipline());
            ps.setString(2, d.getDescriptionDiscipline());
            ps.setInt(3, d.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void supprimerDiscipline(Discipline d) {

    }

    @Override
    public List<String> findAllDiscipline() {
        String req = "SELECT * from discipline";
        List<String> listDiscipline = new ArrayList();
        try {
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();

            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id_discipline"));
                discipline.setNomDiscipline(rs.getString("nom_discipline"));
                discipline.setLogo(null);
                discipline.setNombreCours(rs.getInt("nombre_cours"));
                listDiscipline.add(discipline.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDiscipline;

    }

    @Override
    public Discipline findDisciplineById(int id) {

        String req = "select *  from discipline  where id_discipline =" + id;
        Discipline discipline = new Discipline();
        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {

                discipline.setId(rs.getInt("id_discipline"));
                discipline.setNomDiscipline(rs.getString("nom_discipline"));
                discipline.setLogo(rs.getString("logo"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return discipline;

    }

    public List<Discipline> findAllDisciplineObject() {
        String req = "SELECT * from discipline";
        List<Discipline> listDiscipline = new ArrayList();
        try {
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();

            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id_discipline"));
                discipline.setNomDiscipline(rs.getString("nom_discipline"));
                discipline.setLogo(rs.getString("logo"));
                discipline.setNombreCours(rs.getInt("nombre_cours"));
                discipline.setForumDiscipline(rs.getInt("id_forum_discipline"));
                discipline.setDescriptionDiscipline(rs.getString("description_discipline"));
                discipline.setDateCreationDiscipline(rs.getString("date_creation"));
                listDiscipline.add(discipline);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDiscipline;

    }

    public List<Discipline> findDisciplineByNom(String nom) {
        String req = "select *  from discipline  where  nom_discipline like'%" + nom + "%' ";;
        List<Discipline> listDiscipline = new ArrayList();
        try {
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();

            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id_discipline"));
                discipline.setNomDiscipline(rs.getString("nom_discipline"));
                discipline.setLogo(rs.getString("logo"));
                discipline.setNombreCours(rs.getInt("nombre_cours"));
                discipline.setForumDiscipline(rs.getInt("id_forum_discipline"));
                discipline.setDescriptionDiscipline(rs.getString("description_discipline"));
                discipline.setDateCreationDiscipline(rs.getString("date_creation"));
                listDiscipline.add(discipline);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDiscipline;

    }

    public ObservableList<Discipline> displayAll() {

        String req = "SELECT * from discipline";
        ObservableList lsDiscipline = FXCollections.observableArrayList();
//    String nomDescipline="";
        try {
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                Discipline d = new Discipline();
                d = new Discipline(rs.getInt("id_discipline"),
                        rs.getString("nom_discipline"),
                        rs.getString("logo"),
                        rs.getInt("nombre_cours")
                );

                lsDiscipline.add(d);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lsDiscipline;

    }
}
