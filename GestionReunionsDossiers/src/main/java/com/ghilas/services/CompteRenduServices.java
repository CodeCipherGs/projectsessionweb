/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ghilas.services;

import com.ghilas.daos.CompteRenduDao;
import com.ghilas.entites.CompteRendu;
import java.util.List;

/**
 *
 * @author guduy
 */
public class CompteRenduServices {

    private CompteRenduDao dao;

    public void setDao(CompteRenduDao dao) {
        this.dao = dao;
    }
    public  boolean ajouterCompteRendu(CompteRendu compteRendu) {
        return  dao.ajouter(compteRendu);
    }

    public  boolean supprimerChefDepartement(String idCompteRendu) {
        return  dao.supprimer(idCompteRendu);
    }
    public  boolean modifierCompteRendu(CompteRendu compteRendu) {
        return  dao.modifier(compteRendu);
    }
    
    public  List<CompteRendu> trouverTout() {
        return  dao.findAll();
    }
    
    public  List<CompteRendu> trouverParIdPointDordre(String idPointDordre) {
        return  dao.findByIdPointDordre(idPointDordre);
    }

}
