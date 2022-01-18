/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ghilas.services;

import com.ghilas.daos.JdbcMembreDao;
import com.ghilas.daos.MembreDao;
import com.ghilas.entites.Membre;
import java.util.List;

/**
 *
 * @author guduy
 */
public class MembreServices {
    
    private MembreDao dao;

    public void setDao(MembreDao dao) {
        this.dao = dao;
    }
    
    public boolean inscrire(Membre membre) {
       
        return dao.create(membre);
        
    }

    public  Membre trouverParIdMembre(String id) {
        return dao.findById(id);
    }
    
    public  List<Membre> trouverTous() {
        return dao.trouverTous();
    }

    public List<Membre> trouverParNom(String nom) {
        return dao.trouverParNom(nom);
    }
    public Membre trouverParCourriel(String courriel) {
        return dao.findByCourriel(courriel);
    }
}
 