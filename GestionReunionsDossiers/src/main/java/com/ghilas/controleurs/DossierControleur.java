/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.controleurs;

import com.ghilas.entites.Dossier;
import com.ghilas.entites.Membre;
import static com.ghilas.factories.Factory.getNewDossier;
import com.ghilas.services.DossierServices;
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
 * @author guduy
 */
@Controller
//@RequestMapping("/")
public class DossierControleur {

    private DossierServices dossierService;

    public void setService(DossierServices service) {
        this.dossierService = service;
    }

    //@ResponseBody
    @RequestMapping("/listeDossiers")
    public String listeDossier(ModelMap model, HttpSession session) {
        if (session.getAttribute("membre") == null) { //non connecté
            return "redirect:login";
        }
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        List<Dossier> liste = this.dossierService.trouverTousDossiers();

        model.addAttribute("statutMembre", membreActuelle.getStatut());
        model.addAttribute("data", liste);
        return "listeDossiers";
    }

    //@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/dossier", params = {"dossier"})
    public String dossier(@RequestParam("dossier") Dossier dossier, ModelMap model, HttpSession session) {
        if (session.getAttribute("membre") == null) { //non connecté
            return "redirect:login";
        }
        
        model.addAttribute("isActive", dossier.getIsActive());
        model.addAttribute("titre", dossier.getTitre());
        model.addAttribute("date", dossier.getDate());
        model.addAttribute("description", dossier.getDescription());
        model.addAttribute("id", dossier.getIdDossier());
        return "listeDossiers";
    }

    @RequestMapping(value = "ajoutDossier", method = RequestMethod.GET)
    public String ajouterDossier(ModelMap model, HttpSession session) throws IOException {
        if (session.getAttribute("membre") == null) { //non connecté
            return "redirect:login";
        }
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        if(!membreActuelle.getStatut().contains("Chef")){
            return "redirect:listeDossiers";
        }

        model.addAttribute("formDossier", new FormDossier());
        return "ajoutDossier";
    }

    @RequestMapping(value = "ajoutDossier", method = RequestMethod.POST)
    public String create(HttpSession session, FormDossier form, ModelMap model) throws IOException {
        if (session.getAttribute("membre") == null) { //non connecté
            return "redirect:login";
        }
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        if(!membreActuelle.getStatut().contains("Chef")){
            return "redirect:listeDossiers";
        }

        Dossier dossier = getNewDossier();
        dossier.setTitre(form.getTitre());
        dossier.setDate(form.getDate());
        dossier.setDescription(form.getDescription());
        dossier.setIsActive("0"); // est desactiver par default
        System.out.println("Insertion du dossier " + dossier.getIdDossier());
        if (this.dossierService.ajouterDossier(dossier)) {
            return "redirect:ajoutDossier"; //redirection
        }
        model.addAttribute("message", "Le dossier avec le titre " + dossier.getTitre() + " existe déjà");
        model.addAttribute("modele", dossier);
        return "ajoutDossier";
    }

    public static class FormDossier {

        private String titre = "";
        private String date = "";
        private String description = "";
        private String idDossier = "";
        private String statutMembre = "";
        private String isActive = "";

        public int getIsActive() {
            return Integer.parseInt(isActive);
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
        }
        
        

        public String getStatutMembre() {
            return statutMembre;
        }

        public void setStatutMembre(String statutMembre) {
            this.statutMembre = statutMembre;
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
}
