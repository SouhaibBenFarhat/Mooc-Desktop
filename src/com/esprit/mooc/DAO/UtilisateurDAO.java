/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.InformationEntreprise;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.Interfaces.IDAOUtilisateur;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kods
 */
public class UtilisateurDAO implements IDAOUtilisateur {

    private static Connection conn;
    private PreparedStatement ps, ps1;
    private ResultSet rs;
    public static Utilisateur loggedUser;

    public UtilisateurDAO() {

        try {
            conn = MyConnection.getInstance();
        } catch (Exception e) {
        }
    }

    @Override
    public void ajouterUtilisateur(Utilisateur user) {
        String req = "INSERT "
                + "INTO "
                + "utilisateur("
                + "username,username_canonical,"
                + "email,email_canonical,enabled,"
                + "password,salt,locked,"
                + "expired,roles,credentials_expired,user_photo) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            ps = conn.prepareStatement(req);
            ps.setString(1, user.getUsename());
            ps.setString(2, user.getUsename());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getEmail());
            ps.setInt(5, 1);
            ps.setString(6, user.getPassword());
            ps.setString(7, "");
            ps.setInt(8, user.getLocked());
            ps.setInt(9, 0);
            ps.setString(10, user.getRole());
            ps.setInt(11, 0);
            ps.setInt(12, user.getPhoto());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String findlastlogin(String nom) throws SQLException {
        String req = "select * from utilisateur WHERE username=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            if (rs.next()) {
                loggedUser = new Utilisateur();
            }
            loggedUser.setLastlogin(rs.getDate("last_login"));
            System.out.println(loggedUser.getLastlogin());
            Date t = loggedUser.getLastlogin();
            String DATE_FORMAT = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            String s = sdf.format(t);
            return s;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "0";
    }

    @Override
    public void modifierUtilisateur(Utilisateur user) {

    }

    @Override
    public void supprimerUtilisateur(int id) {
        String req = "DELETE from utilisateur WHERE id=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("id " + id);
            System.out.println("deleted");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean existeUtilisateur(String username, String mdp) {
        String req = "select * from utilisateur WHERE username=? and password=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setString(1, username);
            ps.setString(2, mdp);
            rs = ps.executeQuery();
            if (rs.next()) {
                loggedUser = new Utilisateur();
             loggedUser.setId(rs.getInt("id"));
            loggedUser.setUsename(rs.getString("username"));
            loggedUser.setRole(rs.getString("roles"));
            return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Utilisateur> findAllUtilisateur() {

        String req = "select * from utilisateur";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                 user.setPhoto(rs.getInt("user_photo"));
                  user.setRole(rs.getString("roles"));
                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;
    }

    @Override
    public List<Utilisateur> findUtilisateurByRole(String role) {

        String req = "select *  from utilisateur  where locked = 0 and enabled = 1 and roles like'%" + role + "%'";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("roles"));
                user.setLocked(rs.getInt("locked"));
                user.setEnbaled(rs.getInt("enabled"));
                user.setPhoto(rs.getInt("user_photo"));
                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;
    }

    @Override
    public List<Utilisateur> findDisabledUtilisateurByRole(String role) {

        String req = "select *  from utilisateur  where locked = 0 and enabled = 0 and roles like'%" + role + "%'";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("roles"));
                user.setLocked(rs.getInt("locked"));
                user.setEnbaled(rs.getInt("enabled"));
                  user.setPhoto(rs.getInt("user_photo"));
                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;
    }

    @Override
    public List<Utilisateur> findApprenantByName(String nom) {

        String req = "select *  from utilisateur  where enabled = 1 and username like'%" + nom + "%' and roles like '%ROLE_APPRENANT%'";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("roles"));
                user.setPhoto(rs.getInt("user_photo"));
                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;
    }

    @Override
    public List<Utilisateur> findFormateurByName(String nom) {

        String req = "select *  from utilisateur  where locked = 0 and enabled = 1 and username like'%" + nom + "%' and roles like '%ROLE_FORMATEUR%'";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("roles"));
                 user.setPhoto(rs.getInt("user_photo"));
                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;
    }

    @Override
    public List<Utilisateur> findEntrepriseByName(String nom) {

        String req = "select *  from utilisateur  where locked = 0 and enabled = 1 and username like'%" + nom + "%' and roles like '%ROLE_ENTREPRISE%'";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("roles"));
                  user.setPhoto(rs.getInt("user_photo"));
                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;
    }

    @Override
    public List<Utilisateur> findMembreComiteByName(String nom) {

        String req = "select *  from utilisateur  where enabled = 1 and username like'%" + nom + "%' and roles like '%ROLE_MEMBRE_COMITE%'";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("roles"));
                user.setPhoto(rs.getInt("user_photo"));
                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;
    }

    @Override
    public Utilisateur findUtilisateurByLoginMdp(String username, String mdp) {

        String req = "select * from utilisateur WHERE username=? and password=?";
        try {
            Utilisateur user = new Utilisateur();
            ps = conn.prepareStatement(req);
            ps.setString(1, username);
            ps.setString(2, mdp);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setRole(rs.getString("roles"));
                user.setLocked(rs.getInt("locked"));
                user.setEnbaled(rs.getInt("enabled"));
                user.setEmail(rs.getString("email"));
                  user.setPhoto(rs.getInt("user_photo"));
                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void accepterUtilisateur(Utilisateur user) {
        String req = "UPDATE utilisateur SET locked=? WHERE id=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, 0);
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void disableUtilisateur(int id) {
        String req = "UPDATE utilisateur SET enabled = ? WHERE id=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, 0);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void enableUtilisateur(int id) {
        String req = "UPDATE utilisateur SET enabled = ? WHERE id=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public Utilisateur findFormateurById(int id) {
        String req = "select * from utilisateur WHERE id=?";
        try {
            Utilisateur user = new Utilisateur();
            ps = conn.prepareStatement(req);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setRole(rs.getString("roles"));
                user.setLocked(rs.getInt("locked"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getInt("user_photo"));
                user.setIdEntrepriseUtilisateur(rs.getInt("id_entreprise_utilisateur"));
                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Utilisateur findEntrepriseById(int id) {
        String req = "select * from utilisateur WHERE id=?";
        try {
            Utilisateur user = new Utilisateur();
            ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setRole(rs.getString("roles"));
                user.setLocked(rs.getInt("locked"));
                user.setEmail(rs.getString("email"));
                  user.setPhoto(rs.getInt("user_photo"));
                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Utilisateur> findlockedEntreprises() {

        String req = "select *  from utilisateur  where  locked = 1 and enabled = 1 and roles like '%ROLE_ENTREPRISE%'";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("roles"));
                user.setLocked(rs.getInt("locked"));
                user.setPhoto(rs.getInt("user_photo"));
                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;

    }

    @Override
    public List<Utilisateur> findDisabledFormateurs() {
        String req = "select *  from utilisateur  where locked = 0 and enabled = 0 and roles like '%ROLE_FORMATEUR%'";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getInt("user_photo"));
                 user.setRole(rs.getString("roles"));

                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;
    }

        public List<Utilisateur> findNewFormateurs() {
        String req = "select *  from utilisateur  where locked = 1 and enabled = 1 and roles like '%ROLE_FORMATEUR%'";
        List<Utilisateur> UsersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getInt("user_photo"));
                 user.setRole(rs.getString("roles"));

                UsersList.add(user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UsersList;
    }

    
    @Override
    public List<Utilisateur> findFormateursByEntreprise(int idEntreprise) {
        String req = "select *  from utilisateur  where locked = 0 and enabled = 1 and id_entreprise_utilisateur = " + idEntreprise + " and roles like '%ROLE_FORMATEUR%'";
        List<Utilisateur> usersList = new ArrayList<>();

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                  user.setPhoto(rs.getInt("user_photo"));
                   user.setRole(rs.getString("roles"));
                usersList.add(user);
            }
            return usersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
     public List<Utilisateur> findAutreFormateurs(int idEntreprise){
          String req="select *  from utilisateur where locked = 0 and enabled = 1 and ( id_entreprise_utilisateur <> "+idEntreprise+" or id_entreprise_utilisateur is null ) "
                  
                  + "and roles like '%ROLE_FORMATEUR%'";
   
          List<Utilisateur> usersList=new ArrayList<>();
   
        try {
              Statement st=conn.createStatement();
        rs=st.executeQuery(req);
        while (rs.next()) {
             Utilisateur user=new Utilisateur();
             user.setId(rs.getInt("id"));
             System.out.println("user id "+user.getId());
             user.setUsename(rs.getString("username"));
             user.setEmail(rs.getString("email"));  
             user.setPhoto(rs.getInt("user_photo"));
             user.setRole(rs.getString("roles"));
            usersList.add(user);
        }
        return usersList;
        }catch(SQLException e){
            e.printStackTrace();
            }
      return null;
    }
    public boolean MailExistant(String mail) {
        String req = "select email from utilisateur  where email = '" +mail+"'";

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Utilisateur findUtilisateurById(int id) {
        String req = "select * from utilisateur WHERE id=" + id;
        try {
            Utilisateur user = new Utilisateur();
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsename(rs.getString("username"));
                user.setRole(rs.getString("roles"));
                user.setLocked(rs.getInt("locked"));
                user.setPhoto(rs.getInt("user_photo"));
                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
     public boolean UserNameExistant(String username){
         String req="select username from utilisateur  where username= '"+username+"'";
   
        try {
              Statement st=conn.createStatement();
        rs=st.executeQuery(req);
        if (rs.next()) {
             return true;
        }
        else 
            return false;
        }catch(SQLException e){
            e.printStackTrace();
            }
      return false;
     }
     
     

    public int getNombreFormateur() {
        int nombreFormateur = 0;
        String req = "select count(*) nombre_formateur  from utilisateur  where locked = 0 and enabled = 1 and roles like'%FORMATEUR%'";

        try {
            Statement st = conn.createStatement();

            rs = st.executeQuery(req);
            while (rs.next()) {

                nombreFormateur = rs.getInt("nombre_formateur");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(nombreFormateur);
        return nombreFormateur;
    }

  
    public int getNombreApprenant() {
        int nombreApprenant = 0;
        String req = "select count(*) nombre_apprenant  from utilisateur  where locked = 0 and enabled = 1 and roles like'%APPRENANT%'";
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                nombreApprenant = rs.getInt("nombre_apprenant");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(nombreApprenant);
        return nombreApprenant;
    }

    public int getNombreEntreprise() {
        int nombreEntreprise = 0;
        String req = "select count(*) nombre_entreprise  from utilisateur  where locked = 0 and enabled = 1 and roles like'%ENTREPRISE%'";
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                nombreEntreprise = rs.getInt("nombre_entreprise");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(nombreEntreprise);
        return nombreEntreprise;
    }

    
       @Override
    public void supprimerFormateur(int idFormateur) {
      InformationFormateurDAO informationFormateurDAO=new InformationFormateurDAO();
      InformationFormateur informationFormateur=new InformationFormateur();
      informationFormateur.setIdFormateur(idFormateur);
      informationFormateurDAO.supprimerInformationFormateur(informationFormateur);
      supprimerUtilisateur(idFormateur);
    }

    @Override
    public void supprimerEntreprise(int idEntreprise) {
      InformationEntrepriseDAO informationEntrepriseDAO=new InformationEntrepriseDAO();
      InformationEntreprise informationEntreprise=new InformationEntreprise();
      informationEntreprise.setEntrepriseId(idEntreprise);
      informationEntrepriseDAO.supprimerInformationEntreprise(informationEntreprise);
      //supprimerUtilisateur(idEntreprise); 
    
    }

}
