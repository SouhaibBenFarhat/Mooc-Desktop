/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.Bibliotheque;
import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Entities.Utilisateur;
import javafx.collections.ObservableList;

/**
 *
 * @author Firas
 */
public interface IBibliotheque {

    void addCourBib(Cours c);

    void removeCourBib(Cours c);

    ObservableList<Cours> displayAll(Utilisateur u);

}
