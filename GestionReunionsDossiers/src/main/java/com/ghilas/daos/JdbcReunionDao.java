/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.daos.ReunionDao;
import com.ghilas.entites.Reunion;
import static com.ghilas.factories.Factory.getNewReunion;
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
public class JdbcReunionDao implements ReunionDao {
    
    private Connexion dbConnexion;
    public void setDbConnexion(Connexion cnx) {
        this.dbConnexion = cnx;
    }
    
    public List<Reunion> trouverTout() {
        List<Reunion> liste = new LinkedList();
        Reunion reunion;
        String requete = "SELECT * FROM reunion";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            Statement stm = cnx.createStatement();
            ResultSet res = stm.executeQuery(requete);
        ){
            while (res.next()) {
                reunion = new Reunion();
                reunion.setIdReunion(res.getString("ID_REUNION"));
                reunion.setTitre(res.getString("TITRE"));
                reunion.setDate(res.getDate("UNE_DATE").toString());                
                reunion.setResponsable(res.getString("NOM_CHEF"));                
                reunion.setDescription(res.getString("DESCRIPTION"));
                reunion.setIsActive(res.getString("IS_ACTIVE"));
                liste.add(reunion);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return liste;
    }

    @Override
    public List<Reunion> trouverParTitre(String titre) {
        List<Reunion> liste = new LinkedList();
        String requete = "SELECT * FROM reunion WHERE TITRE=?";
        Connection cnx = dbConnexion.getInstance();

        if (cnx==null) {
            return liste;
        }
        try (
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, titre);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                Reunion reunion = new Reunion();
                reunion.setIdReunion(res.getString("ID_REUNION"));
                reunion.setTitre(res.getString("TITRE"));
                reunion.setDate(res.getDate("UNE_DATE").toString());                
                reunion.setResponsable(res.getString("NOM_CHEF"));
                reunion.setDescription(res.getString("DESCRIPTION"));
                reunion.setIsActive(res.getString("IS_ACTIVE"));
                liste.add(reunion);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return liste;
    }

    @Override
    public boolean créer(Reunion reunion) {
        String requete = "INSERT INTO reunion(ID_REUNION,TITRE,UNE_DATE,NOM_CHEF,DESCRIPTION,IS_ACTIVE)"
                       + " VALUES (?,?,?,?,?,?)";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, reunion.getIdReunion());
            stm.setString(2, reunion.getTitre());
            stm.setString(3, reunion.getDate());
            stm.setString(4, reunion.getResponsable());
            stm.setString(5, reunion.getDescription());
            stm.setString(6, reunion.getIsActive());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;    
    }

    @Override
    public boolean modifierTitre(Reunion reunion) {
        String requete = "UPDATE reunion SET TITRE=?"
                       + " WHERE ID_REUNION=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, reunion.getTitre());
            stm.setString(2, reunion.getIdReunion());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }
    
    @Override
    public boolean fermerReunion(String idReunion) {
        String requete = "UPDATE reunion SET IS_ACTIVE='0'"
                       + " WHERE ID_REUNION=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, idReunion);
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }
    
    @Override
    public boolean modifierDate(Reunion reunion) {
        String requete = "UPDATE reunion SET UNE_DATE=?"
                       + " WHERE ID_REUNION=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, reunion.getDate());
            stm.setString(2, reunion.getIdReunion());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }

    @Override
    public boolean annuler(String idReunion) {
       String requete = "DELETE FROM reunion "
                       + " WHERE ID_REUNION=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, idReunion);
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }

    @Override
    public Reunion trouverParId(String id) {
        Reunion reunion = new Reunion();
        String requete = "SELECT * FROM reunion WHERE ID_REUNION=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, id);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                reunion.setIdReunion(res.getString("ID_REUNION"));
                reunion.setTitre(res.getString("TITRE"));
                reunion.setDate(res.getDate("UNE_DATE").toString());                
                reunion.setResponsable(res.getString("NOM_CHEF"));
                reunion.setDescription(res.getString("DESCRIPTION"));
                reunion.setIsActive(res.getString("IS_ACTIVE"));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return reunion;
    }

    @Override
    public boolean modifierReunion(Reunion rn) {
        String requete = "UPDATE reunion SET TITRE=?,UNE_DATE=?,DESCRIPTION=?,NOM_CHEF=?,IS_ACTIVE=?"
                       + " WHERE ID_REUNION=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, rn.getTitre());
            stm.setString(2, rn.getDate());
            stm.setString(3, rn.getDescription());
            stm.setString(4, rn.getResponsable());
            stm.setString(5, rn.getIdReunion());
            stm.setString(6, rn.getIsActive());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }

}
