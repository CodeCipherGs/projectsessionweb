/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.entites;

/**
 *
 * @author Raritetnik
 */
public class Dossier {
    
        private String titre = "";
        private String date = "";
        private String description = "";
        private String idDossier = "";
        private String isActive = "";

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Dossier(String titre, String date){
        this.date = date;
        this.titre = titre;
    }

    public Dossier() {
    }

    public String getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(String id) {
        this.idDossier = id;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String contenu) {
        this.description = contenu;
    }

    public String getDescription() {
        return description;
    }    
}


