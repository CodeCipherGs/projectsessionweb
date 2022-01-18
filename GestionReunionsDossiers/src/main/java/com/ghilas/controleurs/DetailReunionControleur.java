/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.controleurs;

import com.ghilas.entites.Membre;
import com.ghilas.entites.PointDordre;
import com.ghilas.entites.Reunion;
import com.ghilas.entites.ReunionMembres;
import com.ghilas.services.MembreServices;
import com.ghilas.services.MembresReunionServices;
import com.ghilas.services.PointDordreServices;
import com.ghilas.services.ReunionServices;
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
public class DetailReunionControleur {
    
    private ReunionServices reunionService;
    private PointDordreServices pointDordreService;
    private MembreServices membreService;
    private MembresReunionServices membresReunionService;
    
    public void setServiceReunion(ReunionServices service) {
        this.reunionService = service;
    }
    
    public void setServicePointDordre(PointDordreServices service) {
        this.pointDordreService = service;
    }
    
    public void setServiceMembre(MembreServices service) {
        this.membreService = service;
    }
    
    public void setServiceMembreReunion(MembresReunionServices service) {
        this.membresReunionService = service;
    }
    
    @RequestMapping(value="/detailsReunion",method=RequestMethod.GET, params={"idReunion"})
    public String detailsReunion(@RequestParam("idReunion") String idReunion, HttpSession session, ModelMap model) {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        session.setAttribute("idRenion",idReunion);
        Reunion reunion = reunionService.trouverReunionParId(idReunion);
        model.addAttribute("isActive", reunion.getIsActive());
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        model.addAttribute("statutMembre", membreActuelle.getStatut());
        
        List<Membre> membres = membreService.trouverTous();
        List<ReunionMembres> reunionMembres = membresReunionService.trouverMembresParIdReunion(idReunion);
        List<PointDordre> pointDordres =  pointDordreService.trouverTout();
        List<PointDordre> pointDordresReunion =  pointDordreService.trouverParIdReunion(idReunion);
        session.setAttribute("idRenionSession", idReunion);
        
        if(membres.isEmpty()){
            membres = new ArrayList<Membre>();
        }
        if (reunionMembres.isEmpty()){
            reunionMembres = new ArrayList<ReunionMembres>();
        }
        if (pointDordres.isEmpty()) {
            pointDordres = new ArrayList<PointDordre>();
        }
        
        List<Membre> membresSupprimer = new ArrayList<Membre>();
        for (Membre mem : membres) {
            for (ReunionMembres reunionMembre : reunionMembres) {
                if(mem.getIdMembre().equals(reunionMembre.getIdMembre())){
                    membresSupprimer.add(mem);
                }
            }
        }
        
        membres.removeAll(membresSupprimer);
        model.addAttribute("membres", membres);
        model.addAttribute("detailsReunion", reunion);
        session.setAttribute("detailsReunion", reunion);
        model.addAttribute("reunionMembres", reunionMembres);
        model.addAttribute("pointDordres", pointDordres);
        model.addAttribute("pointDordresReunion", pointDordresReunion); 
        model.addAttribute("formDetailReunion", new FormDetailReunion());
        return "detailsReunion";
    }
    
    @RequestMapping(value = "detailsReunion", method = RequestMethod.POST)
    public String addMembre(HttpSession session, ModelMap model, FormDetailReunion form) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        String idReunion = session.getAttribute("idRenion").toString();
        Reunion reunion = reunionService.trouverReunionParId(idReunion);
        if(reunion.getIsActive().equals(0) || !(reunion.getIsActive().equals("1"))){
            return "redirect:Accueil";
        }
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        model.addAttribute("statutMembre", membreActuelle.getStatut());
        model.addAttribute("isActive", reunion.getIsActive());

        ReunionMembres membre = new ReunionMembres();
        Membre membreRecherche = this.membreService.trouverParIdMembre(form.getSelectMembre());
        
        if(membreRecherche == null){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        List<Membre> membres = membreService.trouverTous();
        List<ReunionMembres> reunionMembres = membresReunionService.trouverMembresParIdReunion(idReunion);
        List<Membre> membresSupprimer = new ArrayList<Membre>();
        for (Membre mem : membres) {
            for (ReunionMembres reunionMembre : reunionMembres) {
                if(mem.getIdMembre().equals(reunionMembre.getIdMembre())){
                    membresSupprimer.add(mem);
                }
            }
        }
        membres.removeAll(membresSupprimer);
        model.addAttribute("membres", membres);
        membre.setIdMembre(form.getSelectMembre());
        membre.setIdReunion(idReunion);
        membre.setNom(membreRecherche.getNom());
        model.addAttribute("reunionMembres", reunionMembres);
        
        if(membresReunionService.participationMembre(membre)){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        model.addAttribute("message", "Le membre avec ce nom "+reunion.getTitre()+" existe déjà");
        return "detailsReunion?idReunion="+idReunion;
    }
    
    @RequestMapping(value = "fermerReunion", method = RequestMethod.GET, params={"idReunion"})
    public String fermerReunion (@RequestParam("idReunion") String idReunion, HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
        }
        if(reunionService.fermerReunion(idReunion)){
            return "redirect:listeReunions";
        }
        model.addAttribute("message", "La reuion n'a pas été fermée");
        return "detailsReunion?idReunion="+idReunion;
    }
    
    @RequestMapping(value = "annulerReunion", method = RequestMethod.GET, params={"idReunion"})
    public String annulerReunion (@RequestParam("idReunion") String idReunion, HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
        }
        if(reunionService.supprimerReunion(idReunion)){
            return "redirect:listeReunions";
        }
        model.addAttribute("message", "La reuion n'a pas été supprimée");
        return "detailsReunion?idReunion="+idReunion;
    }
    
    @RequestMapping(value = "confirmerParticipation", method = RequestMethod.GET, params={"idReunion"})
    public String confirmerParticipation (@RequestParam("idReunion") String idReunion, HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        
        List<ReunionMembres> reunionMembres = membresReunionService.trouverMembresParIdReunion(idReunion);
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        ReunionMembres membreReunion = new ReunionMembres();
        
        membreReunion.setIdMembre(membreActuelle.getIdMembre());
        membreReunion.setIdReunion(idReunion);
        membreReunion.setNom(membreActuelle.getNom());
        
        for (ReunionMembres reunionMembre : reunionMembres) {
            if(reunionMembre.getIdMembre().equals(membreReunion.getIdMembre())){
                return "redirect:detailsReunion?idReunion="+idReunion;
            }
        }
        if(membresReunionService.participationMembre(membreReunion)){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        model.addAttribute("message", "Vous etes déjè inscrit à cette reunion");
        return "detailsReunion?idReunion="+idReunion;
    }
    
    @RequestMapping(value = "infirmerParticipation", method = RequestMethod.GET, params={"idReunion"})
    public String infirmerParticipation (@RequestParam("idReunion") String idReunion, HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        ReunionMembres membreReunion = new ReunionMembres(); 
        
        membreReunion.setIdMembre(membreActuelle.getIdMembre());
        membreReunion.setIdReunion(idReunion);
        membreReunion.setNom(membreActuelle.getNom());
        List<ReunionMembres> reunionMembres = membresReunionService.trouverMembresParIdReunion(idReunion);
        boolean dansListe = false;
        for (ReunionMembres membre : reunionMembres) {
            if(membre.getIdMembre().equals(membreReunion.getIdMembre())){
                dansListe = true;
            }
        }
        if(!dansListe){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        if(membresReunionService.infirmerReunionMembre(membreReunion)){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        model.addAttribute("message", "Vous n'etes pas inscrit à cette reunion");
        return "detailsReunion?idReunion="+idReunion;
    }
    
    @RequestMapping(value = "removeMembre", method = RequestMethod.GET, params={"idMembre"})
    public String removeMembre (@RequestParam("idMembre") String idMembre,HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        String idReunion = session.getAttribute("idRenion").toString();
        List<ReunionMembres> reunionMembres = membresReunionService.trouverMembresParIdReunion(idReunion);
        model.addAttribute("reunionMembres", reunionMembres);
        if(membresReunionService.supprimerReunionMembre(idMembre)){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        model.addAttribute("message", "Le membre n'est pas dans la reunion");
        return "detailsReunion?idReunion="+idReunion; 
    }

    @RequestMapping(value = "removePointDordre", method = RequestMethod.GET, params={"idPointDordre"})
    public String removePointDordre (@RequestParam("idPointDordre") String idPointDordre,HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
         }
        String idReunion = session.getAttribute("idRenion").toString();
        System.out.println("le id de point d'ordre : "+idPointDordre);
        if (this.pointDordreService.supprimerPointDordre(idPointDordre)){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        model.addAttribute("message", "Le point d'ordre n'est pas attribué à cette reunion");
        return "detailsReunion?idReunion="+idReunion;
    }
    @RequestMapping(value = "modifier", method = RequestMethod.GET, params={"idPointDordre"})
    public String modifierCompteRendus (@RequestParam("idPointDordre") String idPointDordre,HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
         }
        String idReunion = session.getAttribute("idRenionSession").toString();
        PointDordre unPointDordre =this.pointDordreService.trouverPointDordreParId(idReunion);
        if ( unPointDordre == null){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        model.addAttribute("message", "Impossible de modifier le compte rendu");
        model.addAttribute("formPointDordre", new FormPointDordre());
        return "/compteRendu?idPointDordre="+idPointDordre;
    }
    
    @RequestMapping(value = "modifier", method = RequestMethod.POST)
    public String modifier (PointDordre unPointDordre,FormPointDordre form,HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
        }
        String idReunion = session.getAttribute("idRenionSession").toString();
        unPointDordre.setComptesRendus(form.getComptesRendus());
        if ( this.pointDordreService.modifierContenuPointDordre(unPointDordre)){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        model.addAttribute("message", "Impossible de modifier le compte rendu");
        return "modifier?idPointDordre="+idReunion;
    }
    
     @RequestMapping(value = "modifierReunion", method = RequestMethod.GET, params={"idReunion"})
    public String modifierReunion (@RequestParam("idReunion") String idReunion,HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
         }
       
        Reunion unDossier =this.reunionService.trouverReunionParId(idReunion);
        if ( unDossier == null){
            return "redirect:detailsReunion?idReunion="+idReunion;
        }
        model.addAttribute("message", "Impossible de modifier la reunion");
        model.addAttribute("formReunion", new FormReunion());
        return "modifReunion";
    }
    @RequestMapping(value = "modifierReunion", method = RequestMethod.POST)
    public String insererModification (Reunion uneReunion,FormReunion form,HttpSession session, ModelMap model) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
         }
        uneReunion.setTitre(form.getTitre());
        uneReunion.setDescription(form.getDescription());
        uneReunion.setDate(form.getDate());
        uneReunion.setResponsable(form.getResponsable());
        if ( this.reunionService.modifierLaReunion(uneReunion)){
            return "redirect:detailsReunion?idReunion="+uneReunion.getIdReunion();
        }
        model.addAttribute("message", "Impossible d'inserer la modification de reunion");
              
        return "modifier?idReunion="+uneReunion.getIdReunion();
    }
    public class FormPointDordre {
    
        private String nom, description, comptesRendus, date;
        private String idPointDordre, idDossier, idReunion;
        private String selectReunion, isActive;

        public FormPointDordre(){}

        public String getIsActive() {
            return isActive;
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
        }

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
    public static class FormReunion {
        private String titre = "";
        private String idReunion = "";
        private String date = "";
        private String responsable = "";
        private String description = "";
        private String membre = "";

        public String getMembre() {
            return membre;
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
    
    public static class FormReunionMembres {
        private String idMembre = "";
        private String idReunion = "";
        private String nom = "";
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
    
    public static class FormDetailReunion {
        // Variables de Point D'ordre
        private String nomPointDordre = "";
        private String description = "";
        private String date = "";
        private String idPointDordre = "";
        private String idDossier = "";
        private String selectPointDordre = "";
        
        // Variables de Membre
        
        private String idMembre = ""; 
        private String nom = ""; 
        private String courriel = "";
        private String selectMembre = "";

        // Les méthodes de Membre
        
        public String getSelectMembre() {
            return selectMembre;
        }

        public void setSelectMembre(String selectMembre) {
            this.selectMembre = selectMembre;
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
        
        // Les méthodes de point d'ordre

        public String getSelectPointDordre() {
            return selectPointDordre;
        }

        public void setSelectPointDordre(String selectPointDordre) {
            this.selectPointDordre = selectPointDordre;
        }

        
        public String getNomPointDordre() {
            return nomPointDordre;
        }

        public void setNomPointDordre(String nom) {
            this.nomPointDordre = nom;
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