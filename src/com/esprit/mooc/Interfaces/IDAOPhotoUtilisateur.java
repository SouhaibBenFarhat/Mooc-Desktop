/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.PhotoUtilisateur;

/**
 *
 * @author kods
 */
public interface IDAOPhotoUtilisateur {
    public void ajouterPhoto(PhotoUtilisateur photoUtilisateur);
    public void updatePhoto(PhotoUtilisateur photoUtilisateur);
    public void supprimerPhoto(PhotoUtilisateur photoUtilisateur);
    public PhotoUtilisateur findPhotoById(int idPhoto);
    public PhotoUtilisateur findPhotoByPath(String path);
    
}
