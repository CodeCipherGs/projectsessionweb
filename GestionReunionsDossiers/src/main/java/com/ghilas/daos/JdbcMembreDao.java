/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.daos.MembreDao;
import com.ghilas.entites.Membre;
import static com.ghilas.factories.Factory.getNewMembre;
import com.ghilas.jdbc.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author booska
 */
public class JdbcMembreDao implements MembreDao {
    private Connexion dbConnexion;
    public void setDbConnexion(Connexion cnx) {
        this.dbConnexion = cnx;
    }
    
    @Override
    public Membre findByCourriel(String courriel) {
        Membre membre = null;
        String requete= "SELECT * FROM membre WHERE COURRIEL=?";
        Connection cnx = dbConnexion.getInstance();
        if (cnx==null) {
            return null;
        }
        try(
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, courriel);
            ResultSet res = stm.executeQuery();
            if(res.next()){
                membre = getNewMembre();
                membre.setIdMembre(res.getString("ID_MEMBRE"));
                membre.setNom(res.getString("NOM"));
                membre.setPassword(res.getString("MOT_DE_PASSE"));
                membre.setCourriel(res.getString("COURRIEL"));
                membre.setStatut(res.getString("STATUT"));
                membre.setTelephone(res.getString("TELEPHONE"));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMembreDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        return membre;
    }
    
     @Override
    public Membre findById(String id) {
        Membre membre = null;
        String requete= "SELECT * FROM membre WHERE ID_MEMBRE =?";
        
        try(
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, id);
            ResultSet res = stm.executeQuery();
            if(res.next()){
            membre = getNewMembre();
            membre.setIdMembre(res.getString("ID_MEMBRE"));
            membre.setNom(res.getString("NOM"));
            membre.setPassword(res.getString("MOT_DE_PASSE"));
            membre.setCourriel(res.getString("COURRIEL"));
            membre.setStatut(res.getString("STATUT"));
            membre.setTelephone(res.getString("TELEPHONE"));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMembreDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        return membre;
    }
    

    @Override
    public boolean create(Membre membre) {
       String requete = "INSERT INTO membre(ID_MEMBRE, NOM ,MOT_DE_PASSE ,COURRIEL, STATUT , TELEPHONE) "
               +"VALUES (?,?,?,?,?,?)";
       
       try(
           Connection cnx = dbConnexion.getInstance();
           PreparedStatement stm = cnx.prepareStatement(requete);
       ){
           stm.setString(1, membre.getIdMembre());
           stm.setString(2, membre.getNom());
           stm.setString(3, membre.getPassword ());
           stm.setString(4, membre.getCourriel ());
           stm.setString(5, membre.getStatut());
           stm.setString(6, membre.getTelephone());
           int n = stm.executeUpdate();
           return n>0;
       
       }  catch (SQLException ex) {
               Logger.getLogger(JdbcMembreDao.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return false;
    }
    
    @Override
    public List<Membre> trouverParNom(String nom) {
        List<Membre> liste = new LinkedList();
        Membre membre;
        String requete = "SELECT * FROM membre WHERE NOM=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, nom);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                membre = new Membre();
                membre.setIdMembre(res.getString("ID_MEMBRE"));
                membre.setNom(res.getString("NOM"));
                membre.setPassword(res.getString("MOT_DE_PASSE"));
                membre.setCourriel(res.getString("COURRIEL"));
                membre.setStatut(res.getString("STATUT"));
                membre.setTelephone(res.getString("TELEPHONE"));
                liste.add(membre);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return liste;
    }

    @Override
    public List<Membre> trouverTous() {
        List<Membre> liste = new LinkedList();
        Membre membre;
        String requete = "SELECT * FROM membre";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                membre = new Membre();
                membre.setIdMembre(res.getString("ID_MEMBRE"));
                membre.setNom(res.getString("NOM"));
                membre.setPassword(res.getString("MOT_DE_PASSE"));
                membre.setCourriel(res.getString("COURRIEL"));
                membre.setStatut(res.getString("STATUT"));
                membre.setTelephone(res.getString("TELEPHONE"));
                liste.add(membre);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return liste;
    }
}
