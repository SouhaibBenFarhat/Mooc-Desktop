/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.Interfaces.IDAOForum;
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
public class ForumDAO implements IDAOForum {

    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    public ForumDAO() {
        try {
            conn = MyConnection.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Forum> findAllForums() {
        String req = "SELECT * FROM FORUM";
        List<Forum> listForum = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Forum f = new Forum();
                f.setId(rs.getInt("id_forum"));
                f.setDiscipline(rs.getInt("id_discipline_forum"));
                f.setNomForum(rs.getString("nom_forum"));
                f.setNombreSujet(rs.getInt("nombre_sujet"));
                f.setDateCreation(rs.getString("date_creation"));
                f.setLastSujet(rs.getInt("last_sujet"));
                f.setNombreVue(rs.getInt("nombre_vu"));
                listForum.add(f);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listForum;
    }

    public int getTheLastId() {
        int lastId = 0;
        String req = "SELECT id_forum FROM forum ORDER BY id_forum DESC LIMIT 1";

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                lastId = rs.getInt("id_forum");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(lastId);
        return lastId;
    }

    public Forum findForumById(int id) {
        String req = "select * from forum WHERE id_forum=" + id;
        try {
            Forum forum = new Forum();
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();
            if (rs.next()) {
                forum.setId(rs.getInt("id_forum"));
                forum.setDiscipline(rs.getInt("id_discipline_forum"));
                forum.setNomForum(rs.getString("nom_forum"));
                forum.setNombreSujet(rs.getInt("nombre_sujet"));
                forum.setDateCreation(rs.getString("date_creation"));
                forum.setLastSujet(rs.getInt("last_sujet"));
                forum.setNombreVue(rs.getInt("nombre_vu"));
                return forum;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean modifierForum(Forum forum) {
        forum.toString();
        String req = "UPDATE forum SET "
                + " nom_forum=? "
                + " WHERE id_forum=?";
        try {

            ps = conn.prepareStatement(req);
            ps.setString(1, forum.getNomForum());
            ps.setInt(2, forum.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean ajouterForum(Forum forum) {
        String req = "INSERT INTO forum ( id_discipline_forum,   nom_forum,"
                + "nombre_sujet, date_creation, nombre_vu)"
                + "  VALUES (?,?,?,?,?) ";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, forum.getDiscipline());
            ps.setString(2, forum.getNomForum());
            ps.setInt(3, forum.getNombreSujet());
            ps.setString(4, forum.getDateCreation());
            ps.setInt(5, forum.getNombreVue());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Forum> chercherForum(String nomForum) {
        String req = "select *  from forum  where  nom_forum like'%" + nomForum + "%' ";
        List<Forum> forums = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Forum forum = new Forum();
                forum.setDiscipline(rs.getInt("id_discipline_forum"));
                forum.setNomForum(rs.getString("nom_forum"));
                forum.setNombreSujet(rs.getInt("nombre_sujet"));
                forum.setDateCreation(rs.getString("date_creation"));
                forum.setNombreVue(rs.getInt("nombre_vu"));

                forums.add(forum);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return forums;

    }

    public Boolean supprimerForum(int id) {
        String req = "DELETE from forum WHERE id_forum=?";
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

}
