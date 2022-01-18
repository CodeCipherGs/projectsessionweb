/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.services;

import java.util.List;

import com.ghilas.entites.PointDordre;
import com.ghilas.daos.PointDordreDao;

/**
 *
 * @author guduy
 */
public class PointDordreServices {
	
    private PointDordreDao dao;

    public void setDao(PointDordreDao dao) {
        this.dao = dao;
    }

    public  List<PointDordre> trouverTout() {
        return dao.trouverTout();
     }
   
    
    public  List<PointDordre> trouverParTitre(String titre) {
        return dao.trouverParTitre(titre);
    }
    
    public  boolean ajouterPointDordre(PointDordre pointdordre) {
        return dao.ajouter(pointdordre);
    }
    
    public  boolean modifierNomPointDordre(PointDordre pointdordre) {
        return dao.modifierNom(pointdordre);
    }
    
    public  boolean modifierContenuPointDordre(PointDordre pointdordre) {
        return dao.modifierContenu(pointdordre);
    }
    
    public  boolean supprimerPointDordre(String id) {
        return dao.supprimer(id);
    }
    
    public  PointDordre trouverPointDordreParId(String id) {
        return dao.trouverParId(id);
    }

    public List<PointDordre> trouverParIdReunion(String idReunion) {
        return dao.trouverParIdReunion(idReunion);
    }
}
