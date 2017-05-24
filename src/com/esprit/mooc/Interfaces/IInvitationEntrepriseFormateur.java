/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.InvitationEntrepriseFormateur;
import java.util.List;

/**
 *
 * @author Nesrine Jemai
 */
public interface IInvitationEntrepriseFormateur {
    
    
    public void envoyerInvitationEntrepriseFormateur(InvitationEntrepriseFormateur invitation);
    public void accepterInvitation(int idEntreprise,int idFormateur);
     public void supprimerInvitation(int idEntreprise,int idFormateur);
    public void refuserInvitation(InvitationEntrepriseFormateur invitation);
    public List<InvitationEntrepriseFormateur> findInvitationEntrepriseFormateur(int idFormateur);
    public InvitationEntrepriseFormateur findInvitationByFormateurIdEntrepriseId(int idFormateur, int idEntreprise);
    public List<InvitationEntrepriseFormateur> findListInvitationIdEntrepriseId(int idEntreprise);
    public boolean findInvitByFormateurIdEntrepriseId(int idFormateur, int idEntreprise);
}
