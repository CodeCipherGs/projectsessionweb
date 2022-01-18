/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.controleurs;

import com.ghilas.entites.CompteRendu;
import com.ghilas.entites.Membre;
import com.ghilas.entites.PointDordre;
import com.ghilas.entites.ReunionMembres;
import static com.ghilas.factories.Factory.getNewCompteRendu;
import com.ghilas.services.CompteRenduServices;
import com.ghilas.services.PointDordreServices;
import com.ghilas.services.MembresReunionServices;
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
 * @author Raritetnik
 */
@Controller
public class CompteRenduControleur {
    
    private CompteRenduServices compteRenduService;
    private MembresReunionServices membreReunionService;
    private PointDordreServices pointDordreService;
    
    public void setServiceCompteRendu(CompteRenduServices service) {
        this.compteRenduService = service;
    }
    public void setServiceMembreReunion(MembresReunionServices service) {
        this.membreReunionService = service;
    }
    public void setServicePointDordre(PointDordreServices service) {
        this.pointDordreService = service;
    }
    
    @RequestMapping(value="compteRendu",method=RequestMethod.GET, params={"idPointDordre"})
    public String compteRendu(@RequestParam("idPointDordre") String idPointDordre, HttpSession session, ModelMap model) {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        
        session.setAttribute("pointDordre", idPointDordre);
        PointDordre pointDordre = pointDordreService.trouverPointDordreParId(idPointDordre);
        List<CompteRendu> lesComptesRendu = compteRenduService.trouverParIdPointDordre(idPointDordre);
        model.addAttribute("pointDordre", pointDordre);
        model.addAttribute("listeCompteRendu", lesComptesRendu);
        model.addAttribute("formCompteRendu", new FormCompteRendu());
        return "compteRendu";
    }
    
    @RequestMapping(value="compteRendu",method=RequestMethod.POST)
    public String ajouterCompteRendu(FormCompteRendu form, HttpSession session, ModelMap model) {
        if (session.getAttribute("membre")==null) { //non connecté
            return "redirect:login";
        }
        String idPointDordre = session.getAttribute("pointDordre").toString();
        Membre membre = (Membre) session.getAttribute("membre");
        System.out.println("TXT: "+form.getTexte());
        CompteRendu compteRendu = getNewCompteRendu();
        compteRendu.setIdPointDordre(idPointDordre);
        compteRendu.setIdMembre(membre.getIdMembre());
        compteRendu.setNom(membre.getNom());
        compteRendu.setTexte(form.getTexte());
        
        
        PointDordre pointDordre = pointDordreService.trouverPointDordreParId(idPointDordre);
        List<CompteRendu> lesComptesRendu = compteRenduService.trouverParIdPointDordre(idPointDordre);
        model.addAttribute("pointDordre", pointDordre);
        model.addAttribute("listeCompteRendu", lesComptesRendu);
        
        if(compteRenduService.ajouterCompteRendu(compteRendu)){
            return "redirect:compteRendu?idPointDordre="+compteRendu.getIdPointDordre();
        }
        return "compteRendu";
    }
    
    public class FormCompteRendu {
    
        private String idCompteRendu, idMembre, idPointDordre;
        private String nom, texte;

        public FormCompteRendu(){}

        public String getIdCompteRendu() {
            return idCompteRendu;
        }

        public void setIdCompteRendu(String idCompteRendu) {
            this.idCompteRendu = idCompteRendu;
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

        public String getTexte() {
            return texte;
        }

        public void setTexte(String texte) {
            this.texte = texte;
        }
        
    } 
}
