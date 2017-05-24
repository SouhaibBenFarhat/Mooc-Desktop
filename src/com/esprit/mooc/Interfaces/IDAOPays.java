/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.Pays;
import java.util.List;

/**
 *
 * @author kods
 */
public interface IDAOPays {
    public List<String> findAll();
    
}
