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
public class CompteRendu {
    
    private String idComptreRendu = "";
    private String idMembre = "";
    private String idPointDordre = "";
    private String nom = "";
    private String texte = "";

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getIdPointDordre() {
        return idPointDordre;
    }

    public void setIdPointDordre(String idPointDordre) {
        this.idPointDordre = idPointDordre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getIdComptreRendu() {
        return idComptreRendu;
    }

    public void setIdComptreRendu(String idComptreRendu) {
        this.idComptreRendu = idComptreRendu;
    }
    
    
}
