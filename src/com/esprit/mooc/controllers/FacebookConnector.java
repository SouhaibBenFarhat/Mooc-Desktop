package com.esprit.mooc.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.exception.FacebookException;
import com.restfb.types.User;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Utilisateur;

import java.awt.BorderLayout;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import static java.lang.System.out;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.XMLFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 *
 * @author mokhtarhamdouch
 */
public class FacebookConnector extends javax.swing.JFrame {

    /**
     * Creates new form FacebookConnector
     */
    public static String API_KEY = "220916574941734";
    public static String SECRET = "92d2196116a2d2d50ddddf590b28e44d";

    public static String firstRequest = "https://graph.facebook.com/oauth/authorize?"
            + "client_id="
            + API_KEY
            + "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
            + "scope=publish_actions,email";

    public static String secondRequest = "https://graph.facebook.com/oauth/access_token?"
            + "client_id="
            + API_KEY
            + "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
            + "client_secret=" + SECRET + "&code=";
    
    

    public String access_token = "";
    public boolean firstRequestDone = false;
    public boolean secondRequestDone = false;

    public FacebookConnector(){
         NativeInterface.open();
         NativeInterface.initialize();
         initComponents();
         
    }
    public void ShowConnector() {
        final JFrame authFrame = new JFrame();
        // Create the JWebBrowser and add the WebBrowserAdapter
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
     final JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.setMenuBarVisible(false);
        webBrowser.setButtonBarVisible(false);
        webBrowser.setLocationBarVisible(false);
        webBrowser.navigate(firstRequest);
        webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
            @Override
            public void locationChanged(WebBrowserNavigationEvent e) {
                super.locationChanged(e);
                // Check if first request was not done
                if (!firstRequestDone) {
                    // Check if you left the location and were redirected to the next 
                    // location
                    if (e.getNewResourceLocation().contains("http://www.facebook.com/connect/login_success.html?code=")) {
                        // If it successfully redirects you, get the verification code
                        // and go for a second request
                        String[] splits = e.getNewResourceLocation().split("=");
                        String stage2temp = secondRequest + splits[1];
                        System.out.println("First =" + splits[1]);
                        webBrowser.navigate(stage2temp);
                        firstRequestDone = true;
                    }
                } else {
                    // If secondRequest is not done yet, you perform this and get the 
                    // access_token
                    if (!secondRequestDone) {
                        try {
                            System.out.println(webBrowser.getHTMLContent());
                            // Create reader with the html content
                            StringReader readerSTR = new StringReader(webBrowser.getHTMLContent());
                            // Create a callback for html parser
                            HTMLEditorKit.ParserCallback callback
                                    = new HTMLEditorKit.ParserCallback() {
                                        @Override
                                        public void handleText(char[] data, int pos) {
                                            System.out.println(data);
                                            // because there is only one line with the access_token
                                            // in the html content you can parse it.
                                            String string = new String(data);
                                            String[] temp1 = string.split("&");
                                            String[] temp2 = temp1[0].split("=");
                                            System.out.println("access tocken-------=" + temp2[0]);
                                            access_token = temp2[0];
                                            secondRequestDone = true;
                                        }
                                    };
                            try {
                                // Call the parse method
                                new ParserDelegator().parse(readerSTR, callback, false);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            // After everything is done, you can dispose the jframe
                            authFrame.setVisible(false);
                            webBrowser.navigate("https://www.facebook.com/logout.php?&access_token="+access_token);
                            Utilisateur c = GetFacebookClient();
                            UtilisateurDAO cDao = null;
                            cDao = new UtilisateurDAO();
                            //List<Utilisateur> utils = cDao.afficher();
                            boolean UtilNew=true;
//                            for(Utilisateur ut : utils)
//                            {
//                                if(ut.getMail().equals(c.getMail()))
//                                {
//                                    UtilNew = false;
//                                    
//                                    Utilisateur.user_name= ut.getUser_name();
//                                   //System.out.println(Utilisateur.id);
//                                    //AcueilUtilisateur home =  new  AcueilUtilisateur();
//                                    //home.setVisible(true);
//                                }
//                            }
//                            if(UtilNew)
//                            {
//                                Insccription mod = new Insccription();
//                                try{
//                                    mod.setNom(c.getNom());
//                                    mod.setPrenom(c.getPrenom());
//                                    mod.setEmail(c.getMail());
//                                    mod.setUsername(c.getUser_name());
//                                    mod.setNumTel(c.getNum_telephone());
//                                    mod.setSexe(c.getSexe());
//                                    mod.setImage(c.getPhoto());
//                                    Insccription I = new Insccription();
//                                    I.setVisible(true);
//                                }
//                                catch(Exception ex){
//                                    
//                                }
//                                mod.setVisible(true);
//                            }
                            
                        } catch (FacebookException ex) {
                            Logger.getLogger(FacebookConnector.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (com.restfb.FacebookException ex) {
                            Logger.getLogger(FacebookConnector.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                
                    }
                }
            }
        });
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        
        authFrame.add(webBrowserPanel);
        authFrame.setSize(800, 600);
        
        authFrame.setVisible(true);
        
    }
    private FacebookClient facebookClient;
    public Utilisateur GetFacebookClient() throws FacebookException, com.restfb.FacebookException {
        Utilisateur c=new Utilisateur();
        if (access_token.isEmpty()) return c;
        facebookClient = new DefaultFacebookClient(access_token);

         User user = facebookClient.fetchObject("me", User.class);
        
        /*if(user.getLastName()!=null){
             int finalID = 0;
            for(int i=0;i<user.getId().length();i++){
                finalID += Integer.parseInt(Character.toString( user.getId().charAt(i)));
            }
            c.setID(finalID);
        }*/
       
        
        if(user.getLastName()!=null)
            c.setUsename(user.getLastName());
        if(user.getFirstName()!=null)
            //c.setPrenom(user.getFirstName());
        if(user.getEmail()!=null)
            c.setEmail(user.getEmail());
       
        
//        byte[] imageBytes = GetProfilePicture();
//        if(imageBytes!=null)
//            c.setPhoto(null);
        
     out.println("Name: " + user.getName());
     out.println("Last Name: " + user.getLastName());
       // out.println("Username: " + user.getUsername());
        out.println("Email: " + user.getEmail());
        out.println("ID Facebook: " + user.getId());
        out.println("About: " + user.getAbout());
        out.println("Birthday: " + user.getBirthday());
//        out.println("Gender: " + user.getGender());
        
        
        //addclient.main(null);
       
    // Put data in your baos

        return c;
    }
    public byte[] GetProfilePicture()
    {
        if(access_token.isEmpty()) return null;
        String url = "https://graph.facebook.com/me/picture?";
        url += "access_token=" + access_token;
        url += "&type=large";
        InputStream is = null;
        ByteArrayOutputStream bais = new ByteArrayOutputStream();

        try {
        URL u = new URL(url);
        
        
          is = u.openStream ();
          byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
          int n;

          while ( (n = is.read(byteChunk)) > 0 ) {
            bais.write(byteChunk, 0, n);
          }
        }
        catch (Exception e) {
          System.err.printf ("Failed while reading bytes from %s", url);
          e.printStackTrace ();
          // Perform any other exception handling that's appropriate.
        }
        finally {
          if (is != null) { 
              try { 
                  is.close();
              } catch (IOException ex) {
                  Logger.getLogger(FacebookConnector.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }

        return bais.toByteArray();
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FacebookConnector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacebookConnector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacebookConnector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacebookConnector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FacebookConnector f =  new FacebookConnector();
                f.ShowConnector();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
