/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.controleurs;

import com.ghilas.entites.Dossier;
import com.ghilas.entites.Membre;
import com.ghilas.services.DossierServices;
import com.ghilas.services.MembreServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Raritetnik
 */
@Controller
public class DetailDossierControleur {
    
    private DossierServices dossierService;
    private MembreServices membreService;
    
    public void setServiceDossier(DossierServices service) {
        this.dossierService = service;
    }
    public void setServiceMembre(MembreServices service) {
        this.membreService = service;
    }
   
    @RequestMapping(value="/detailsDossier",method=RequestMethod.GET, params={"idDossier"})
    public String detailsReunion(@RequestParam("idDossier") String idDossier, HttpSession session, ModelMap model) {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        model.addAttribute("statutMembre", membreActuelle.getStatut());
        session.setAttribute("idDossierSession", idDossier);
        Dossier dossier = dossierService.getDossierParId(idDossier);
        List<Membre> membres = membreService.trouverTous();
        

        if(membres.isEmpty()){
            membres = new ArrayList<Membre>();
        }
        model.addAttribute("detailsDossier", dossier);
        
        session.setAttribute("detailsDossier", dossier);
        model.addAttribute("membres", membres);
 
        return "detailsDossier";
    }
   
    @RequestMapping(value = "detailsDossier", method = RequestMethod.POST)
    public String retourListe (HttpSession session, ModelMap model) throws IOException
    {
       if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        return "detailsDossier";
    }
    @RequestMapping(value = "modifierDossier", method = RequestMethod.GET, params={"idDossier"})
    public String modifierDossier (@RequestParam("idDossier") String idDossier,HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
         }
       
        Dossier unDossier =this.dossierService.getDossierParId(idDossier);
        if ( unDossier == null){
            return "redirect:detailsDossier?idDossier="+idDossier;
        }
        model.addAttribute("formDossier", new FormDossier());
        model.addAttribute("message", "Impossible de modifier le dossier");
        return "modifDossier";
    }
    @RequestMapping(value = "modifierDossier", method = RequestMethod.POST)
    public String insererModification (Dossier unDossier,FormDossier form,HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
         }
        unDossier.setTitre(form.getTitre());
        unDossier.setDescription(form.getDescription());
        unDossier.setDate(form.getDate().toString());
        if ( this.dossierService.modifierLeDossier(unDossier)){
            return "redirect:detailsDossier?idDossier="+unDossier.getIdDossier();
        }
        model.addAttribute("message", "Impossible d'inserer la modification de dossier");      
        return "modifier?idDossier="+unDossier.getIdDossier();
    }
    public static class FormDossier {
        private String titre = "";
        private String date = "";
        private String description= "";
        private String isActive = "";

        public int getIsActive() {
            return Integer.parseInt(isActive);
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
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
    public static class FormMembre {
        private String idMembre = ""; 
        private String nom = ""; 
        private String courriel = "";
        private String password = "";
        private String telephone = "";
        private String statut = "";

        public String getStatut() {
            return statut;
        }

        public void setStatut(String statut) {
            this.statut = statut;
        }

        public String getIdMembre() {
            return idMembre;
        }

        public void setIdMembre(String idMembre) {
            this.idMembre = idMembre;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getCourriel() {
            return courriel;
        }

        public void setCourriel(String courriel) {
            this.courriel = courriel;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
         
    }
    public static class FormReunionMembres {
        private String idMembre = "";
        private String idReunion = "";
        private String statut = ""; 
        private String nom = "";

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
    public static class FormPointDordre {
        private String nom = "";
        private String description = "";
        private String comptresRenus = "";
        private String date = "";
        private String idPointDordre = "";
        private String idDossier = "";

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getIdPointDordre() {
            return idPointDordre;
        }

        public void setIdPointDordre(String idPointDordre) {
            this.idPointDordre = idPointDordre;
        }

        public String getIdDossier() {
            return idDossier;
        }
        
        public void setIdDossier(String idDossier) {
            this.idDossier = idDossier;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}