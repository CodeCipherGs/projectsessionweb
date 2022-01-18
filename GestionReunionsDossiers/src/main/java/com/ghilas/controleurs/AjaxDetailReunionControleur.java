package com.ghilas.controleurs;

import com.ghilas.entites.Membre;
import com.ghilas.entites.Reunion;
import com.ghilas.entites.ReunionMembres;
import com.ghilas.services.MembreServices;
import com.ghilas.services.MembresReunionServices;
import com.ghilas.services.PointDordreServices;
import com.ghilas.services.ReunionServices;
import java.io.IOException;
//import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ajax")
public class AjaxDetailReunionControleur {

    private ReunionServices reunionService;
    private PointDordreServices pointDordreService;
    private MembreServices membreService;
    private MembresReunionServices membresReunionService;
    
    public void setServicePointDordre(PointDordreServices service) { 
        this.pointDordreService = service; 
    }
    public void setServiceReunion(ReunionServices service) {
        this.reunionService = service;
    }
    public void setServiceMembre(MembreServices service) {
        this.membreService = service;
    }
    public void setServiceMembreReunion(MembresReunionServices service) { this.membresReunionService = service; }

    
    @RequestMapping(value = "/confirmerParticipation", method = RequestMethod.GET, params={"idReunion"})
    @ResponseBody
    public String AjaxConfirmerParticipation (@RequestParam String idReunion, HttpSession session) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
        }
        boolean resultatAjout = false;
        List<ReunionMembres> reunionMembres = membresReunionService.trouverMembresParIdReunion(idReunion);
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        ReunionMembres membreReunion = new ReunionMembres();
        
        membreReunion.setIdMembre(membreActuelle.getIdMembre());
        membreReunion.setIdReunion(idReunion);
        membreReunion.setNom(membreActuelle.getNom());
        
        for (ReunionMembres reunionMembre : reunionMembres) {
            if(reunionMembre.getIdMembre().equals(membreReunion.getIdMembre())){
                resultatAjout = false;
            }
        }
        if(membresReunionService.participationMembre(membreReunion)){
            resultatAjout = true;
        }
        return "{\"TACHE\":\"SUPPRESSION\",\"RESULTAT\":"+resultatAjout+"}";
    }
    
    @RequestMapping(value = "/infirmerParticipation", method = RequestMethod.GET, params={"idReunion"})
    @ResponseBody
    public String AjaxInfirmerParticipation (@RequestParam String idReunion, HttpSession session) throws IOException
    {
        if (session.getAttribute("membre")==null) { //non connecté
             return "redirect:login";
        }
        boolean resultatInfermer = false;
        Membre membreActuelle = (Membre) session.getAttribute("membre");
        ReunionMembres membreReunion = new ReunionMembres(); 
        
        membreReunion.setIdMembre(membreActuelle.getIdMembre());
        membreReunion.setIdReunion(idReunion);
        membreReunion.setNom(membreActuelle.getNom());

        membresReunionService.infirmerReunionMembre(membreReunion);
        return "{\"TACHE\":\"SUPPRESSION\",\"RESULTAT\":"+resultatInfermer+"}";
    }
}
