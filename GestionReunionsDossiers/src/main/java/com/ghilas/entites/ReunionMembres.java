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
public class ReunionMembres {

   private String idMembre, idReunion;
   private String nom;
   
   
   public ReunionMembres(){
       
   }

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(String idReunion) {
        this.idReunion = idReunion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
}
