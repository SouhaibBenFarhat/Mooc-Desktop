/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.*;
import javafx.collections.ObservableList;

/**
 *
 * @author Firas
 */
public interface ICourDAO {
    boolean AjoutCour(Cours c);
    void SupprimerCour(int id_Cour);
   
    ObservableList<Cours> RechercheCour(String seachBy,String value);
    Cours RechercheOneCour(String seachBy,String value);
    boolean ModifierCour(Cours c);
    
    
    
    
}
