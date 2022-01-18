/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ghilas.controleurs;

import com.ghilas.entites.Membre;
import static com.ghilas.factories.Factory.getNewMembre;
import com.ghilas.services.MembreServices;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author guduy
 */
@Controller
public class MembreController {
    private MembreServices service;

    public void setService(MembreServices service) {
        this.service = service;
    }    
    
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(HttpSession session, ModelMap model) {
        if (session.getAttribute("membre")!=null) { //déjà connecté
            return "redirect:/";
        }
        model.addAttribute("modele", new Membre());
        return "login";
    }  
    
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String doLogin(HttpSession session, Membre membre, ModelMap model) {
        if ("".equals(membre.getCourriel().trim()) || "".equals(membre.getPassword().trim())) {
            model.addAttribute("message", "Remplir les 2 champs");
            membre.setPassword("");
            model.addAttribute("modele", membre);
            return "login";
        }
        Membre m = this.service.trouverParCourriel(membre.getCourriel());
        if (m==null || !m.getPassword().equals(membre.getPassword())) {
            model.addAttribute("message", "Infos de connexion incorrectes");
            membre.setPassword("");
            model.addAttribute("modele", membre);
            return "login";  
        }
        membre.setPassword("");
        session.setAttribute("membre", m);
        return "redirect:/";
    }
    @RequestMapping(value="/logout")
    public String deconnexion(HttpSession session, ModelMap model) {
        session.removeAttribute("membre");//ou: session.invalidate();
        model.addAttribute("modele", new Membre());
        return "redirect:login";
    }
    
    @RequestMapping(value="/inscription",method=RequestMethod.GET)
    public String inscription(HttpSession session, ModelMap model) {
        if (session.getAttribute("membre")!=null) { //déjà connecté
            return "redirect:/";
        }
        
        model.addAttribute("form", new FormMembre());
        return "inscription";
    } 
    @RequestMapping(value="/inscription", method = RequestMethod.POST)
    public View create(HttpSession session, FormMembre form, ModelMap model) {
        
        Membre membre = getNewMembre();
        membre.setCourriel(form.getCourriel());
        membre.setNom(form.getNom());
        membre.setTelephone(form.getTelephone());
        membre.setStatut(form.getStatut());
        String tempPass1 = form.getPassword().toString();
        String tempPass2 = form.getPassword2().toString();
        if (tempPass1.equals(tempPass2)){
            membre.setPassword(form.getPassword());
        }else{
            model.addAttribute("motDePasse2"," Les deux mots de passe ne sont pas similaire");
        }
        this.service.inscrire(membre);
        return new RedirectView("/",true,false);
    }

    public static class FormMembre {
        private String nom, courriel, password, password2, telephone, statut;

        public String getStatut() {
            return statut;
        }

        public void setStatut(String statut) {
            this.statut = statut;
        }

        public String getNom(){
            return nom;
        }

        public void setNom(String nom){
            this.nom = nom;
        }

        public String getCourriel(){
            return courriel;
        }

        public void setCourriel(String courriel){
            this.courriel = courriel;
        }

        public String getPassword(){
            return password;
        }

        public void setPassword(String password){
            this.password = password;
        }
        public String getPassword2(){
            return password2;
        }

        public void setPassword2(String password2){
            this.password2 = password2;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}
