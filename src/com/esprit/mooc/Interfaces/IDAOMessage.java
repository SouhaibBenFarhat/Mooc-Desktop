/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.Message;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import java.util.List;

/**
 *
 * @author souhaib
 */
public interface IDAOMessage {

    public Boolean ajouterMessage(Message message);

    public void modifierMessage(Message message);

    public void supprimerMessage(int id);

    public List<Message> findMessageBySujet(int idSujet);

}
