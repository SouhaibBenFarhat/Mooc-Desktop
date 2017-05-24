/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.Discipline;
import java.util.List;

/**
 *
 * @author kods
 */
public interface IDAODiscipline {

    public boolean ajouterDiscipline(Discipline d);

    public boolean modifierDiscipline(Discipline d);

    public void supprimerDiscipline(Discipline d);
      public Discipline findDisciplineById(int d);

    public List<String> findAllDiscipline();
   

}
