/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.Message;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Interfaces.IDAOMessage;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Souhaib
 */
public class MessageDAO implements IDAOMessage {

    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    public MessageDAO() {
        try {
            conn = MyConnection.getInstance();
        } catch (Exception e) {
        }

    }

    @Override
    public Boolean ajouterMessage(Message message) {
        String req = "INSERT INTO message (id_utilisateur_message,   id_sujet_message,   contenu_message,"
                + "titre_message, date_publication_message)"
                + " VALUES (?,?,?,?,?) ";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, message.getUtilisateurMessage());
            ps.setInt(2, message.getSujetMessage());
            ps.setString(3, message.getContenuMessage());
            ps.setString(4, message.getTitreMessage());
            ps.setString(5, message.getDatePublicationMessage());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public void modifierMessage(Message message) {
        String req = "UPDATE message SET contenu_message=?, titre_message=? date_modification_message=? WHERE id_message=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setString(1, message.getContenuMessage());
            ps.setString(2, message.getTitreMessage());
            ps.setString(3, date.toString());
            ps.setInt(4, message.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void supprimerMessage(int id) {
        String req = "DELETE from message WHERE id_message=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Message> findMessageBySujet(int idSujet) {
        String req = "Select * from message where id_sujet_message=" + idSujet;
        List<Message> listMessage = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id_message"));
                message.setUtilisateurMessage(rs.getInt("id_utilisateur_message"));
                message.setSujetMessage(rs.getInt("id_sujet_message"));
                message.setContenuMessage(rs.getString("contenu_message"));
                message.setTitreMessage(rs.getString("titre_message"));
                message.setDatePublicationMessage(rs.getString("date_publication_message"));
                message.setDateModificationMessage(rs.getString("date_modification_message"));
                listMessage.add(message);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listMessage;
    }

    public List<Message> findMessageByUsername(String username) {
        String req = "Select message.* from message,utilisateur "
                + "where message.id_utilisateur_message=utilisateur.id "
                + "AND username  like'%" + username + "%' ";
        List<Message> listMessage = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id_message"));
                message.setUtilisateurMessage(rs.getInt("id_utilisateur_message"));
                message.setSujetMessage(rs.getInt("id_sujet_message"));
                message.setContenuMessage(rs.getString("contenu_message"));
                message.setTitreMessage(rs.getString("titre_message"));
                message.setDatePublicationMessage(rs.getString("date_publication_message"));
                message.setDateModificationMessage(rs.getString("date_modification_message"));
                listMessage.add(message);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listMessage;
    }
}
