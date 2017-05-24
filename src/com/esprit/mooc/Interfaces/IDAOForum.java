/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.Sujet;
import java.util.List;

/**
 *
 * @author Souhaib
 */
public interface IDAOForum {

    public List<Forum> findAllForums();

    public List<Forum> chercherForum(String nomForum);

    public boolean ajouterForum(Forum forum);
}
