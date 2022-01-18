/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.factories;

import com.ghilas.controleurs.PointDordreControleur.FormPointDordre;
import com.ghilas.controleurs.ReunionControleur;
import com.ghilas.controleurs.ReunionControleur.FormReunion;
import com.ghilas.entites.CompteRendu;
import com.ghilas.entites.Dossier;
import com.ghilas.entites.Membre;
import com.ghilas.entites.PointDordre;
import com.ghilas.entites.Reunion;
import java.util.UUID;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;  

/**
 *
 * @author booska
 */
public class Factory {
    private static String tempIdSauvegard="0";
    private static int tempIdIncep = 0;
    private  static String var,oldvar;
    private static boolean different = true;

    public static Reunion getNewReunion(FormReunion form) {
        Reunion reunion = new Reunion();
        String var;
        var =form.getDate().replace("-", "");
        System.out.println(" id de la reunion : "+ var);
        reunion.setIdReunion(var );
        return reunion;
    }
    
    public static CompteRendu getNewCompteRendu() {
        CompteRendu compteRendu = new CompteRendu();
        compteRendu.setIdComptreRendu(UUID.randomUUID().toString());
        return compteRendu;
    }
    
    public static Dossier getNewDossier() {
        Dossier dossier = new Dossier();
        dossier.setIdDossier(UUID.randomUUID().toString());
        return dossier;
    }
    public static PointDordre getNewPointDordre(FormPointDordre form) {
        PointDordre pointDordre = new PointDordre();
        
        
        var =form.getDate().replace("-", "");
        var+=Integer.toString(tempIdIncep+1);
        oldvar = var;
        System.out.println(" IdPointDordre factory: "+ var);
        System.out.println(" old IdPointDordre factory: "+ oldvar);
        if (!different){
            var+=0;
            oldvar = var;
            different=true;
        }
     
        pointDordre.setIdPointDordre(var);
        
        return pointDordre;
    }
    public static Membre getNewMembre() {
        Membre membre = new Membre();
        membre.setIdMembre(UUID.randomUUID().toString());
        return membre;
    }
  
    
     public static void creerIdReunion(Reunion reunion){
    // changer le id en String
    String var;
        var =reunion.getDate().replace("-", "");
        System.out.println(" id de la reunion : "+ var);
        reunion.setIdReunion(var );
        }
    public static void creerIdPointD(PointDordre pointDordre){
    // changer le id en String
        pointDordre.setIdPointDordre(UUID.randomUUID().toString());
        }

    public static void creerIdMembre(Membre membre){
        String id = "111000"; 
        
        id += tempIdSauvegard;
        System.out.println(" id ajouter : "+tempIdIncep);
        membre.setIdMembre(id);
    }
    /**
    public static void creerIdDossier(Dossier dossier){
        String id = "222003"; 
        id += tempIdIncep;
        dossier.setIdDossier(id);
    }
  
     * public static String creerIdReunion(){
        int idIncrementer;
        Date dateActuel = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyMMdd");
        String id = formatDate.format(dateActuel);
        if(id.equals(tempIdDate)){
            tempIdIncep++;
        } else {
            tempIdDate = id;
            tempIdIncep=0;
        }
        id+=tempIdIncep;
       
        idIncrementer=Integer.parseInt(id);       
        
        return Integer.toString(idIncrementer);
    }
    
    *     public static int creerIdPointD(){
        int idIncrementer;
        Date dateActuel = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyMMdd");
        String id = formatDate.format(dateActuel);
        id+=0;   
        if(id.equals(tempIdDate)){
            tempIdIncep++;
        } else {
            tempIdDate = id;
            tempIdIncep=0;
        }
        id+=tempIdIncep;
       
        idIncrementer=Integer.parseInt(id);       
        
        return idIncrementer;
    }
     */
}