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
public class PointDordre {
    
    private String nom, description, comptesRendus, date;
    private String idPointDordre, idDossier, idReunion;
    
    public PointDordre(){}

    public String getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(String idDossier) {
        this.idDossier = idDossier;
    }

    public String getIdPointDordre() {
        return idPointDordre;
    }

    public void setIdPointDordre(String idPointDordre) {
        this.idPointDordre = idPointDordre;
    }

    public void setComptesRendus(String comptresRenus) {
        this.comptesRendus = comptresRenus;
    }

    public String getComptesRendus() {
        return comptesRendus;
    }
    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        this.date = date;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(String idReunion) {
        this.idReunion = idReunion;
    }
    
}
