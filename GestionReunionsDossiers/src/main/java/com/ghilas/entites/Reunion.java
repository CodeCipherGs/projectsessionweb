/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.entites;

import java.util.List;

/**
 *
 * @author Raritetnik
 */
public class Reunion {
    
    private String titre, responsable, date,idReunion, description, isActive;
    private List<Membre> membres;
    private List<PointDordre> listeDesPoints;

    public Reunion() {
    }
    
    protected enum EtatReunion{
        PLAGNIFIÉ,
        ENCOURS,
        FERMÉ
    }
    private EtatReunion etatReunion;
    
    
    public Reunion(String titre, String responsable, String date){
        this.titre = titre;
        this.date = date;
        this.responsable = responsable;
        
        this.etatReunion = EtatReunion.PLAGNIFIÉ;
    }
    
    /**
     * Les getter et setters
     */
    
    
    public void setIsActive(String isActive){
        this.isActive = isActive;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getIdReunion() {
        return idReunion;
    }
    
    public void setIdReunion(String idReunion){
        this.idReunion = idReunion;
    }
    
    public String getTitre(){
        return titre;
    }
    
    public void setTitre(String nom){
        this.titre = nom;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        this.date = date;
    }
    
    public String getResponsable(){
        return responsable;
    }
    
    public void setResponsable(String nom){
        this.responsable = nom;
    }
    public void setDescription(String contenu) {
        this.description = contenu;
    }

    public String getDescription() {
        return description;
    }    
    
    /**
     * Méthodes additionnels
     */
    
    public void ajouterPointDOrdre(PointDordre _pointDOrdre){
        listeDesPoints.add(_pointDOrdre);
    }
    
    public List<PointDordre> retournerPointsDOrdre(PointDordre _pointDOrdre){
        return listeDesPoints;
    }
    
    public void ajouterMembre(Membre membre){
        membres.add(membre);
    }
    
    public List<Membre> getMembres(){
        return membres;
    }
    
    
}
