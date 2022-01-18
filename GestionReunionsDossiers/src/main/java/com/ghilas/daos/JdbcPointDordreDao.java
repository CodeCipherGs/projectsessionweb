/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.entites.PointDordre;
import static com.ghilas.factories.Factory.getNewPointDordre;
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
public class JdbcPointDordreDao implements PointDordreDao{
    private Connexion dbConnexion;
    public void setDbConnexion(Connexion cnx) {
        this.dbConnexion = cnx;
    }
    
    public List<PointDordre> trouverTout() {
        List<PointDordre> liste = new LinkedList();
        PointDordre pointdordre;
        String requete = "SELECT * FROM pointsdordre";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            Statement stm = cnx.createStatement();
            ResultSet res = stm.executeQuery(requete);
        ){
            while (res.next()) {
                pointdordre = new PointDordre();
                pointdordre.setIdPointDordre(res.getString("ID_POINT_DORDRE"));
                pointdordre.setNom(res.getString("NOM"));
                pointdordre.setDescription(res.getString("DESCRIPTION"));
                pointdordre.setComptesRendus(res.getString("COMPTE_RENDUS"));
                pointdordre.setDate(res.getDate("UNE_DATE").toString());                
                pointdordre.setIdDossier(res.getString("ID_DOSSIER"));
                pointdordre.setIdReunion(res.getString("ID_REUNION"));
                liste.add(pointdordre);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return liste;
    }

    @Override
    public List<PointDordre> trouverParTitre(String titre) {
        List<PointDordre> liste = new LinkedList();
        PointDordre pointdordre;
        String requete = "SELECT * FROM pointsdordre WHERE TITRE=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, titre);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                pointdordre = new PointDordre();
                pointdordre.setIdPointDordre(res.getString("ID_POINT_DORDRE"));
                pointdordre.setNom(res.getString("NOM"));
                pointdordre.setDescription(res.getString("DESCRIPTION"));
                pointdordre.setComptesRendus(res.getString("COMPTE_RENDUS"));
                pointdordre.setDate(res.getDate("UNE_DATE").toString());                
                pointdordre.setIdDossier(res.getString("ID_DOSSIER"));
                pointdordre.setIdReunion(res.getString("ID_REUNION"));
                liste.add(pointdordre);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return liste;
    }

    @Override
    public boolean ajouter(PointDordre pointdordre) {
        String requete = "INSERT INTO pointsdordre(ID_POINT_DORDRE,NOM, DESCRIPTION, COMPTE_RENDUS, UNE_DATE, ID_DOSSIER, ID_REUNION)"
                       + " VALUES (?,?,?,?,?,?,?)";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, pointdordre.getIdPointDordre());
            stm.setString(2, pointdordre.getNom());
            stm.setString(3, pointdordre.getDescription());
            stm.setString(4, pointdordre.getComptesRendus());
            stm.setString(5, pointdordre.getDate());
            stm.setString(6, pointdordre.getIdDossier());
            stm.setString(7, pointdordre.getIdReunion());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
         
        return false;    
    }

    @Override
    public boolean modifierNom(PointDordre pointdordre) {
        String requete = "UPDATE pointsdordre SET NOM=?"
                       + " WHERE ID_POINT_DORDRE =?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, pointdordre.getNom());
            stm.setString(2, pointdordre.getIdPointDordre());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
         
        return false;
    }
    
    @Override
    public boolean modifierContenu(PointDordre pointdordre) {
        String requete = "UPDATE pointsdordre SET COMPTE_RENDUS=?"
                       + " WHERE ID_POINT_DORDRE =?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, pointdordre.getComptesRendus());
            stm.setString(2, pointdordre.getIdPointDordre());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
         
        return false;
    }
    
    @Override
    public boolean supprimer(String id) {
        String requete = "DELETE FROM pointsdordre WHERE ID_POINT_DORDRE=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, id);
           // ResultSet res = stm.executeQuery();
            int n = stm.executeUpdate();
            return n>0; 
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return true;
    }

    @Override
    public PointDordre trouverParId(String id) {
        PointDordre pointdordre = new PointDordre();
        String requete = "SELECT * FROM pointsdordre WHERE ID_POINT_DORDRE=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, id);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                pointdordre.setIdPointDordre(res.getString("ID_POINT_DORDRE"));
                pointdordre.setNom(res.getString("NOM"));
                pointdordre.setDescription(res.getString("DESCRIPTION"));
                pointdordre.setComptesRendus(res.getString("COMPTE_RENDUS"));
                pointdordre.setDate(res.getDate("UNE_DATE").toString());                
                pointdordre.setIdDossier(res.getString("ID_DOSSIER"));
                pointdordre.setIdReunion(res.getString("ID_REUNION"));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return pointdordre;
    }

    @Override
    public List<PointDordre> trouverParIdReunion(String idReunion) {
        List<PointDordre> liste = new LinkedList();
        PointDordre pointdordre;
        String requete = "SELECT * FROM pointsdordre WHERE ID_REUNION=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, idReunion);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                pointdordre = new PointDordre();
                pointdordre.setIdPointDordre(res.getString("ID_POINT_DORDRE"));
                pointdordre.setNom(res.getString("NOM"));
                pointdordre.setDescription(res.getString("DESCRIPTION"));
                pointdordre.setComptesRendus(res.getString("COMPTE_RENDUS"));
                pointdordre.setDate(res.getDate("UNE_DATE").toString());                
                pointdordre.setIdDossier(res.getString("ID_DOSSIER"));
                pointdordre.setIdReunion(res.getString("ID_REUNION"));
                liste.add(pointdordre);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return liste;
    }
    
}
