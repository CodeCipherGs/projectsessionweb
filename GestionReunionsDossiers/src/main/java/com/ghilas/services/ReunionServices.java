/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.services;

import com.ghilas.daos.ReunionDao;
import com.ghilas.entites.Reunion;
import java.util.List;

/**
 *
 * @author guduy
 */
public class ReunionServices {

    private ReunionDao dao;

    public void setDao(ReunionDao dao) {
        this.dao = dao;
    }

    public  List<Reunion> trouverToutesReunions() {
        return dao.trouverTout();
    }
    
    public  List<Reunion> trouverReunionParTitre(String titre) {
        return dao.trouverParTitre( titre);
    }
    
    public  Reunion trouverReunionParId(String id) {
        return dao.trouverParId(id);
    }

    public  boolean ajouterReunion(Reunion reunion) {
        return  dao.créer(reunion);
    }
    
    public  boolean modifierTitre(Reunion reunion) {
        return  dao.modifierTitre(reunion);
    }

    public  boolean modifierDate(Reunion reunion) {
        return dao.modifierDate( reunion);
    }
     public  boolean modifierLaReunion(Reunion reunion) {
        return dao.modifierReunion( reunion);
    }
    
    public  boolean supprimerReunion(String idReunion) {
        return dao.annuler(idReunion);
    }
    public  boolean fermerReunion(String idReunion) {
        return dao.fermerReunion(idReunion);
    }
        /**
        public static List<Membre> trouverTousLesReunionDeMembre(int idMembre) {
        return new JdbcMembreReunionDao().trouverTousLesReunionDeMembre(idMembre);
    }

    public static List<Membre> trouverTousLesMembreDeReunion(int idReunion) {
        return new JdbcMembreReunionDao().trouverTousLesMembreDeReunion(idReunion);
    }

    public static boolean retirer(Membre membre, int idReunion) {
        return new JdbcMembreReunionDao().retirer(membre, idReunion);
    }**/
        
}
