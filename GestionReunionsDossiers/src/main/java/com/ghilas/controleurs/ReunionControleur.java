/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ghilas.controleurs;


import com.ghilas.entites.Membre;
import com.ghilas.entites.Reunion;
import static com.ghilas.factories.Factory.getNewReunion;
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
 * @author guduy
 */
@Controller
//@RequestMapping("/")
public class ReunionControleur {

    private ReunionServices reunionService;

    public void setService(ReunionServices service) {
        this.reunionService = service;
    }

    //@ResponseBody
    @RequestMapping("/listeReunions")
    public String listeReunions(ModelMap model, HttpSession session) {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        
        model.addAttribute("statutMembre", membreActuelle.getStatut());
        List<Reunion> liste = this.reunionService.trouverToutesReunions();
        model.addAttribute("data", liste);
        return "listeReunions";
    }

    //@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value="/listeReunions", params={"reunion"})
    public String reunion(@RequestParam("reunion") Reunion reunion, ModelMap model, HttpSession session) {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        
        Reunion uneReunion = new Reunion();
        uneReunion.setTitre(reunion.getTitre());
        reunion.setDate(reunion.getDate());
        reunion.setResponsable(membreActuelle.getNom());
        reunion.setDescription(reunion.getDescription());
        reunion.setIsActive(reunion.getIsActive());
        
        model.addAttribute("uneReunion", uneReunion);
        return "listeReunions";
    }

    @RequestMapping(value = "ajoutReunion", method = RequestMethod.GET)
    public String ajouterReunion(ModelMap model, HttpSession session) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        if(!membreActuelle.getStatut().contains("Chef")){
            return "redirect:listeReunions";
        }
        
        model.addAttribute("formReunion", new FormReunion());    
        return "ajoutReunion";
    }
    
    @RequestMapping(value = "ajoutReunion", method = RequestMethod.POST)
    public String create(HttpSession session, FormReunion form, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }  
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        if(!membreActuelle.getStatut().contains("Chef")){
            return "redirect:listeReunions";
        }
        
        Reunion reunion = getNewReunion(form);
        reunion.setTitre(form.getTitre());
        reunion.setDate(form.getDate());
        reunion.setResponsable(membreActuelle.getNom());
        reunion.setDescription(form.getDescription());
        reunion.setIsActive(String.valueOf(form.getIsActive()));
        
        if (this.reunionService.ajouterReunion(reunion)){
            return "redirect:ajoutReunion"; //redirection
        }
        model.addAttribute("message", "Le dossier avec le titre "+reunion.getTitre()+" existe déjà");
        model.addAttribute("modele", reunion);
        return "ajoutReunion";
    }

    public static class FormReunion {
        private String titre = "";
        private String idReunion = "";
        private String date = "";
        private String responsable = "";
        private String description = "";
        private String isActive = "";
        private String membre = "";

        public String getMembre() {
            return membre;
        }

        public int getIsActive() {
            return Integer.parseInt(isActive);
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
        }

        public void setMembre(String membre) {
            this.membre = membre;
        }
        
        public String getIdReunion(){
            return idReunion;
        }

        public void setIdReunion(String idReunion){
            this.idReunion = idReunion;
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
        public String getResponsable() {
            return responsable;
        }
        public void setResponsable(String responsable) {
            this.responsable = responsable;
        }
        public void setDescription(String contenu) {
            this.description = contenu;
        }

        public String getDescription() {
            return description;
        }   
    }
}
