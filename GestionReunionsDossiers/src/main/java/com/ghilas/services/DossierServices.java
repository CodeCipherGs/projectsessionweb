/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.services;

import com.ghilas.daos.DossierDao;
import com.ghilas.daos.JdbcDossierDao;
import com.ghilas.entites.Dossier;
import java.util.List;

/**
 *
 * @author guduy
 */
public class DossierServices {
    
    private DossierDao dao;

    public void setDao(DossierDao dao) {
        this.dao = dao;
    }

    public  List<Dossier> trouverTousDossiers() {
        return dao.trouverTout();    
    }
    
    public  List<Dossier> getDossierParTitre(String title) {
        return dao.trouverParTitre(title);
    }
    
    public  boolean ajouterDossier(Dossier dossier) {
        return dao.créer(dossier);
    }
    
    public  Dossier getDossierParId(String id) {
        return dao.trouverParId(id);
    }

    public  boolean modifierTitre(Dossier dossier) {
        return dao.modifierTitre(dossier);
    }
    
    public  boolean modifierDescription(Dossier dossier) {
        return dao.modifierDescription( dossier);
    }
    public  boolean modifierLeDossier(Dossier dossier) {
        return dao.modifierDossier(dossier);
    }

    public static boolean activer(Dossier dossier) {
        return new JdbcDossierDao().activer(dossier);
    }

    public static boolean désactiver(Dossier dossier) {
        return new JdbcDossierDao().désactiver(dossier);
    }
/**
    public static boolean supprimerDossier(Dossier dossier) {
        return new JdbcDossierDao().d(dossier);
    }
   **/ 
}
