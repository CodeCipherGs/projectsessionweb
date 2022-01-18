/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.daos.JdbcReunionDao;
import com.ghilas.entites.Dossier;
import static com.ghilas.factories.Factory.getNewDossier;
import com.ghilas.jdbc.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raritetnik
 */
public class JdbcDossierDao implements DossierDao{
    private Connexion dbConnexion;
    public void setDbConnexion(Connexion cnx) {
        this.dbConnexion = cnx;
    }
    
    @Override
    public List<Dossier> trouverTout() {
        List<Dossier> liste = new LinkedList();
        Dossier dossier;
        String requete = "SELECT * FROM dossier";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            Statement stm = cnx.createStatement();
            ResultSet res = stm.executeQuery(requete);
        ){
            while (res.next()) {
                dossier = new Dossier();
                dossier.setIdDossier(res.getString("ID_DOSSIER"));
                dossier.setTitre(res.getString("TITRE"));
                dossier.setDescription(res.getString("DESCRIPTION"));
                //System.out.println(" la date est : "+res.getDate("UNE_DATE").toString());
                dossier.setDate(res.getDate("UNE_DATE").toString());
                dossier.setIsActive(res.getString("IS_ACTIVE"));
                liste.add(dossier);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return liste;
    }

    
    @Override
    public boolean créer(Dossier dossier) {
        String requete = "INSERT INTO dossier(ID_DOSSIER,TITRE, DESCRIPTION, UNE_DATE, IS_ACTIVE)"
                       + " VALUES (?,?,?,?,?)";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, dossier.getIdDossier());
            stm.setString(2, dossier.getTitre());
            stm.setString(3, dossier.getDescription());
            stm.setString(4, dossier.getDate());
            stm.setString(5, dossier.getIsActive());
            
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;    
    }
@Override
    public List<Dossier> trouverParTitre(String titre) {
        List<Dossier> liste = new LinkedList();
        Dossier dossier;
        String requete = "SELECT * FROM dossier WHERE TITRE=?";
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, titre);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                dossier = getNewDossier();
                dossier.setIdDossier(res.getString("ID_DOSSIER"));
                dossier.setTitre(res.getString("TITRE"));
                dossier.setDescription(res.getString("DESCRIPTION"));
                dossier.setDate(res.getDate("UNE_DATE").toString());
                dossier.setIsActive(res.getString("IS_ACTIVE"));
                liste.add(dossier);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return liste;
    }

    @Override
    public boolean modifierTitre(Dossier dossier) {
        String requete = "UPDATE dossier SET TITRE=?"
                       + " WHERE ID_DOSSIER =?";
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, dossier.getTitre());
            stm.setString(2, dossier.getIdDossier());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }
    
    @Override
    public boolean modifierDescription(Dossier dossier) {
        String requete = "UPDATE dossier SET DESCRIPTION=?"
                       + " WHERE ID_DOSSIER =?";
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, dossier.getDescription());
            stm.setString(2, dossier.getIdDossier());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }
   @Override
    public boolean modifierDossier(Dossier dsr) {
       String requete = "UPDATE dossier SET DESCRIPTION=?,TITRE=?,UNE_DATE=?"
                       + " WHERE ID_DOSSIER =?";
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            
            stm.setString(1, dsr.getDescription());
            stm.setString(2, dsr.getTitre());
            stm.setString(3, dsr.getDate());
            stm.setString(4, dsr.getIdDossier ());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }
    @Override
    public boolean activer(Dossier dossier) {
       String requete = "UPDATE dossier SET IS_ACTIVE='1'"
                       + " WHERE ID=?";
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, dossier.getIdDossier());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }
    
    @Override
    public boolean désactiver(Dossier dossier) {
       String requete = "UPDATE dossier SET IS_ACTIVE='0'"
                       + " WHERE ID=?";
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, dossier.getIdDossier());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }

    @Override
    public Dossier trouverParId(String id) {
        Dossier dossier = new Dossier();
        String requete = "SELECT * FROM dossier WHERE ID_DOSSIER=?";
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, id);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                dossier.setIdDossier(res.getString("ID_DOSSIER"));
                dossier.setTitre(res.getString("TITRE"));
                dossier.setDescription(res.getString("DESCRIPTION"));
                dossier.setDate(res.getDate("UNE_DATE").toString());
                dossier.setIsActive(res.getString("IS_ACTIVE"));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return dossier;
    }

 


}
