/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import java.util.List;

/**
 *
 * @author Nesrine Jemai
 */
public interface IInvitationFormateurEntreprise {
    
    public void envoyerInvitationFormateurEntreprise(InvitationFormateurEntreprise ife);
    public void accepterInvitation(int idFormateur,int idEntreprise);
    public void supprimerInvitation(int idFormateur,int idEntreprise);
    public void refuserInvitation(int idFormateur,int idEntreprise);
    public List<InvitationFormateurEntreprise> findInvitationFormateurEntreprise(int idEntreprise);
    public InvitationFormateurEntreprise findInvitationByFormateurIdEntrepriseId(int idFormateur,int idEntreprise);
    public boolean findInvitationByIdFormateur(int idFormateur);

}
