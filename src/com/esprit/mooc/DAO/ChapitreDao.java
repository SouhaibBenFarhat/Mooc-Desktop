/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.Chapitre;
import com.esprit.mooc.Entities.Chapitre;
import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Interfaces.IDaoChapitre;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kods
 */
public class ChapitreDao implements IDaoChapitre {

    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ChapitreDao() {

        try {
            conn = MyConnection.getInstance();
        } catch (Exception e) {
        }
    }

    public List<Chapitre> findAllChapitre() {

        String req = "select * from chapitre";
        List<Chapitre> ChapList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Chapitre chap = new Chapitre();
                chap.setId_chapitre(rs.getInt("id_chapitre"));
                chap.setId_cours_chapitre(rs.getInt("id_cours_chapitre"));
                chap.setTitre_chapitre(rs.getString("titre_chapitre"));
                chap.setDescription_chapitre(rs.getString("description_chapitre"));
                chap.setChemin_chapitre(rs.getString("chemin_chapitre"));
                chap.setChemin_video_chapitre(rs.getString("chemin_video_chapitre"));
                chap.setChemin_presentation_chapitre(rs.getString("chemin_presentation_chapitre"));
                chap.setDate_ajout(rs.getString("date_ajout"));
                chap.setContenu_chapitre(rs.getString("contenu_chapitre"));
                chap.setDuree_chapitre(rs.getInt("duree_chapitre"));
                chap.setNiveau_chapitre(rs.getString("niveau_chapitre"));
                chap.setIntroduction_chapitre(rs.getString("introduction_chapitre"));
                chap.setObjectif_chapitre(rs.getString("objectif_chapitre"));
                ChapList.add(chap);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ChapList;
    }

    public ObservableList<Chapitre> find2Chapitre(int id) {

        String req = "select * from chapitre where id_cours_chapitre=" + id;
        ObservableList<Chapitre> ChapList = FXCollections.observableArrayList();

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Chapitre chap = new Chapitre();
                chap.setId_chapitre(rs.getInt("id_chapitre"));
                chap.setId_cours_chapitre(rs.getInt("id_cours_chapitre"));
                chap.setTitre_chapitre(rs.getString("titre_chapitre"));
                chap.setDescription_chapitre(rs.getString("description_chapitre"));
                chap.setChemin_chapitre(rs.getString("chemin_chapitre"));
                chap.setChemin_video_chapitre(rs.getString("chemin_video_chapitre"));
                chap.setChemin_presentation_chapitre(rs.getString("chemin_presentation_chapitre"));
                chap.setDate_ajout(rs.getString("date_ajout"));
                chap.setContenu_chapitre(rs.getString("contenu_chapitre"));
                chap.setDuree_chapitre(rs.getInt("duree_chapitre"));
                chap.setNiveau_chapitre(rs.getString("niveau_chapitre"));
                chap.setIntroduction_chapitre(rs.getString("introduction_chapitre"));
                chap.setObjectif_chapitre(rs.getString("objectif_chapitre"));
                ChapList.add(chap);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ChapList;
    }
    public ObservableList<Chapitre> findchap(int id,String rech) {
                    ObservableList<Chapitre> ChapList = FXCollections.observableArrayList();
 try {
          PreparedStatement st=conn.prepareStatement("select * from chapitre where (titre_chapitre  like '%' ? '%') OR (description_chapitre like '%' ? '%') OR (chemin_chapitre  like '%' ? '%') OR (chemin_video_chapitre like '%' ? '%') OR (chemin_presentation_chapitre like '%' ? '%') AND (id_cours_chapitre=?)");
            st.setString(1, rech);
            st.setString(2, rech);
            st.setString(3, rech);
            st.setString(4, rech);
            st.setString(5, rech);
            st.setInt(6,id);
            ResultSet rs=st.executeQuery();


            while (rs.next()) {
                Chapitre chap = new Chapitre();
                chap.setId_chapitre(rs.getInt("id_chapitre"));
                chap.setId_cours_chapitre(rs.getInt("id_cours_chapitre"));
                chap.setTitre_chapitre(rs.getString("titre_chapitre"));
                chap.setDescription_chapitre(rs.getString("description_chapitre"));
                chap.setChemin_chapitre(rs.getString("chemin_chapitre"));
                chap.setChemin_video_chapitre(rs.getString("chemin_video_chapitre"));
                chap.setChemin_presentation_chapitre(rs.getString("chemin_presentation_chapitre"));
                chap.setDate_ajout(rs.getString("date_ajout"));
                chap.setContenu_chapitre(rs.getString("contenu_chapitre"));
                chap.setDuree_chapitre(rs.getInt("duree_chapitre"));
                chap.setNiveau_chapitre(rs.getString("niveau_chapitre"));
                chap.setIntroduction_chapitre(rs.getString("introduction_chapitre"));
                chap.setObjectif_chapitre(rs.getString("objectif_chapitre"));
                ChapList.add(chap);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ChapList;
    }
    public ObservableList<Chapitre> findChapitreByCour(Cours c) {

//
//        String sh=String.valueOf(c.getId());
        String req = "select * from chapitre where id_cours_chapitre=" + c.getId();
        ObservableList<Chapitre> ChapList = FXCollections.observableArrayList();

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Chapitre chap = new Chapitre();
                chap.setId_chapitre(rs.getInt("id_chapitre"));
                chap.setId_cours_chapitre(rs.getInt("id_cours_chapitre"));
                chap.setTitre_chapitre(rs.getString("titre_chapitre"));
                chap.setDescription_chapitre(rs.getString("description_chapitre"));
                chap.setChemin_chapitre(rs.getString("chemin_chapitre"));
                chap.setChemin_video_chapitre(rs.getString("chemin_video_chapitre"));
                chap.setChemin_presentation_chapitre(rs.getString("chemin_presentation_chapitre"));
                chap.setDate_ajout(rs.getString("date_ajout"));
                chap.setContenu_chapitre(rs.getString("contenu_chapitre"));
                chap.setDuree_chapitre(rs.getInt("duree_chapitre"));
                chap.setNiveau_chapitre(rs.getString("niveau_chapitre"));
                chap.setIntroduction_chapitre(rs.getString("introduction_chapitre"));
                chap.setObjectif_chapitre(rs.getString("objectif_chapitre"));;

                ChapList.add(chap);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ChapList;
    }
    public void findidchap(int id) {

        String req = "select * from chapitre where id_chapitre=" +id;
        ObservableList<Chapitre> ChapList = FXCollections.observableArrayList();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Chapitre chap = new Chapitre();
                chap.setId_chapitre(rs.getInt("id_chapitre"));
                chap.setId_cours_chapitre(rs.getInt("id_cours_chapitre"));
                chap.setTitre_chapitre(rs.getString("titre_chapitre"));
                chap.setDescription_chapitre(rs.getString("description_chapitre"));
                chap.setChemin_chapitre(rs.getString("chemin_chapitre"));
                chap.setChemin_video_chapitre(rs.getString("chemin_video_chapitre"));
                chap.setChemin_presentation_chapitre(rs.getString("chemin_presentation_chapitre"));
                chap.setDate_ajout(rs.getString("date_ajout"));
                chap.setContenu_chapitre(rs.getString("contenu_chapitre"));
                chap.setDuree_chapitre(rs.getInt("duree_chapitre"));
                chap.setNiveau_chapitre(rs.getString("niveau_chapitre"));
                chap.setIntroduction_chapitre(rs.getString("introduction_chapitre"));
                chap.setObjectif_chapitre(rs.getString("objectif_chapitre"));
                ChapList.add(chap);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public ObservableList<Chapitre> findChapitreById(int id, int id2) {

//
//        String sh=String.valueOf(c.getId());
        String req = "select * from chapitre where (id_cours_chapitre=" + id + ")" + "AND (id_chapitre!=" + id2 + ")";
        ObservableList<Chapitre> ChapList = FXCollections.observableArrayList();

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Chapitre chap = new Chapitre();
                chap.setId_chapitre(rs.getInt("id_chapitre"));
                chap.setId_cours_chapitre(rs.getInt("id_cours_chapitre"));
                chap.setTitre_chapitre(rs.getString("titre_chapitre"));
                chap.setDescription_chapitre(rs.getString("description_chapitre"));
                chap.setChemin_chapitre(rs.getString("chemin_chapitre"));
                chap.setChemin_video_chapitre(rs.getString("chemin_video_chapitre"));
                chap.setChemin_presentation_chapitre(rs.getString("chemin_presentation_chapitre"));
                chap.setDate_ajout(rs.getString("date_ajout"));
                chap.setContenu_chapitre(rs.getString("contenu_chapitre"));
                chap.setDuree_chapitre(rs.getInt("duree_chapitre"));
                chap.setNiveau_chapitre(rs.getString("niveau_chapitre"));
                chap.setIntroduction_chapitre(rs.getString("introduction_chapitre"));
                chap.setObjectif_chapitre(rs.getString("objectif_chapitre"));

                ChapList.add(chap);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ChapList;
    }

    public ObservableList<Chapitre> findChapitreRech(int id, int id2, String nom) throws SQLException {

//
//        String sh=String.valueOf(c.getId());
        PreparedStatement st = conn.prepareStatement("select * from chapitre where (id_cours_chapitre=" + id + ")" + "AND (id_chapitre!=" + id2 + ") AND (titre_chapitre like '%' ? '%')");

        ObservableList<Chapitre> ChapList = FXCollections.observableArrayList();

        try {
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapitre chap = new Chapitre();
                chap.setId_chapitre(rs.getInt("id_chapitre"));
                chap.setId_cours_chapitre(rs.getInt("id_cours_chapitre"));
                chap.setTitre_chapitre(rs.getString("titre_chapitre"));
                chap.setDescription_chapitre(rs.getString("description_chapitre"));
                chap.setChemin_chapitre(rs.getString("chemin_chapitre"));
                chap.setChemin_video_chapitre(rs.getString("chemin_video_chapitre"));
                chap.setChemin_presentation_chapitre(rs.getString("chemin_presentation_chapitre"));
                chap.setDate_ajout(rs.getString("date_ajout"));
                chap.setContenu_chapitre(rs.getString("contenu_chapitre"));
                chap.setDuree_chapitre(rs.getInt("duree_chapitre"));
                chap.setNiveau_chapitre(rs.getString("niveau_chapitre"));
                chap.setIntroduction_chapitre(rs.getString("introduction_chapitre"));
                chap.setObjectif_chapitre(rs.getString("objectif_chapitre"));

                ChapList.add(chap);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ChapList;
    }

    public void modifierChapitre(Chapitre c) {
        String req="UPDATE `chapitre` SET `id_cours_chapitre`="+c.getId_cours_chapitre()+",`titre_chapitre`= '"+c.getTitre_chapitre()+"',`description_chapitre`= '"+c.getDescription_chapitre()+"',`chemin_chapitre`= '"+c.getChemin_chapitre()+"',`chemin_chapitre`= '"+c.getChemin_video_chapitre()+"'WHERE id_chapitre="+c.getId_chapitre();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void ajouterChapitre(Chapitre c) {
        String req = "insert into chapitre values(NULL,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, c.getId_cours_chapitre());
            ps.setString(2, c.getTitre_chapitre());
            ps.setString(3, c.getDescription_chapitre());
            ps.setString(4, c.getChemin_chapitre());
            ps.setString(5, c.getChemin_video_chapitre());
            ps.setString(6, c.getChemin_presentation_chapitre());
            ps.setString(7, c.getContenu_chapitre());
            ps.setInt(8, c.getDuree_chapitre());
            ps.setString(9, c.getNiveau_chapitre());
            ps.setString(10, c.getIntroduction_chapitre());
            ps.setString(11, c.getObjectif_chapitre());
            ps.setString(12, c.getDate_ajout());
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        public void supprimerChapitre(int id){
            String req="DELETE from chapitre WHERE id_chapitre='"+id+"'";
        try {
        ps=conn.prepareStatement(req);        
        ps.executeUpdate();
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
