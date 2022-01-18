/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.controleurs;

import com.ghilas.entites.PointDordre;
import com.ghilas.entites.Reunion;
import static com.ghilas.factories.Factory.getNewPointDordre;
import com.ghilas.services.PointDordreServices;
import com.ghilas.services.ReunionServices;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author booska
 */
@Controller
public class PointDordreControleur {
    private PointDordreServices pointDordreService;
    private ReunionServices reunionService;

    public void setServiceReunion(ReunionServices service) {
        this.reunionService = service;
    }
    
    public void setServicePointDordre(PointDordreServices service) {
        this.pointDordreService = service;
    }

    @RequestMapping(value = "ajoutPointDordre", method = RequestMethod.GET, params={"idDossier"})
    public String ajouterReunion(@RequestParam("idDossier") String idDossier,ModelMap model, HttpSession session) 
    {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }        
        List<Reunion> reunions = reunionService.trouverToutesReunions();
        System.out.println("id dossier : "+idDossier);
        session.setAttribute("idDossier", idDossier);
        model.addAttribute("idDossier",idDossier);
        model.addAttribute("listeReunions",reunions);
        model.addAttribute("formPointDordre", new FormPointDordre());    
        return "ajoutPointDordre";
    }
    
    @RequestMapping(value = "ajoutPointDordre", method = RequestMethod.POST)
    public String create(HttpSession session, FormPointDordre form, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }      
        System.out.println("id dossier  session : "+session.getAttribute("idDossier"));
        System.out.println("form.getSelectReunion : "+form.getSelectReunion());
        String idDossier = session.getAttribute("idDossier").toString();

        PointDordre pointDordre = getNewPointDordre(form);
        pointDordre.setComptesRendus(form.getComptesRendus());
        pointDordre.setNom(form.getNom());
        pointDordre.setDescription(form.getDescription());
        pointDordre.setDate(form.getDate());
        pointDordre.setIdDossier(idDossier);
        pointDordre.setIdReunion(form.getSelectReunion());
        
        System.out.println("Insertion d'un point d'ordre "+pointDordre.getIdPointDordre());
        
        
        if (this.pointDordreService.ajouterPointDordre(pointDordre)){
            return "redirect:ajoutPointDordre?idDossier"; //redirection
        }
        model.addAttribute("message", "Le point d'ordre avec le nom "+pointDordre.getNom()+" existe déjà");
        model.addAttribute("modele", pointDordre);
        return "ajoutPointDordre";
    }
    
    public class FormPointDordre {
    
        private String nom, description, comptesRendus, date;
        private String idPointDordre, idDossier, idReunion;
        private String selectReunion;

        public FormPointDordre(){}

        public String getSelectReunion() {
            return selectReunion;
        }

        public void setSelectReunion(String selectReunion) {
            this.selectReunion = selectReunion;
        }
        
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
}
