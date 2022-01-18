package com.ghilas.services;

import java.util.List;

import com.ghilas.daos.JdbcMembresReunionDao;
import com.ghilas.daos.MembresReunionDao;
import com.ghilas.entites.ReunionMembres;

public class MembresReunionServices {
	
    private MembresReunionDao dao;

    public void setDao(MembresReunionDao dao) {
        this.dao = dao;
    }

    public  boolean ajouterReuninonMembre(ReunionMembres membre) {
        return dao.ajouter(membre);
    } 
    public  boolean participationMembre(ReunionMembres membre) {
        return dao.participer(membre);
    }
    
    public boolean infirmerReunionMembre(ReunionMembres membre){
        return dao.infirmer(membre);
    }

    public  boolean supprimerReunionMembre(String id) {
        return dao.retirer(id);
    }

    public  List<ReunionMembres> trouverReunionsParIdMembre(String idMembre) {
        return dao.trouverReunionsParIdMembre(idMembre);
    }
    
    public  List<ReunionMembres> trouverMembresParIdReunion(String idReunion) {
        return dao.trouverMembresParIdReunion(idReunion);
    }
    
}
