/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.InformationEntreprise;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.Utilisateur;

/**
 *
 * @author kods
 */
public interface IDAOInformationEntreprise {
    public void ajouterInforamtionEntreprise(InformationEntreprise infoEntreprise);
    public void modifierInformationEntreprise(InformationEntreprise infoEntreprise);
    public void supprimerInformationEntreprise(InformationEntreprise infEntreprise);
     public InformationEntreprise findInfoEntrepriseByIdEntreprise(int idEntreprise);
    
}
