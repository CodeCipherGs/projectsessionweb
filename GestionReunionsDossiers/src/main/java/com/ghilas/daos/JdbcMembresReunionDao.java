/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.entites.ReunionMembres;
import com.ghilas.jdbc.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ghilas.daos.MembresReunionDao;

/**
 *
 * @author Raritetnik
 */
public class JdbcMembresReunionDao implements MembresReunionDao{
    private Connexion dbConnexion;
    public void setDbConnexion(Connexion cnx) {
        this.dbConnexion = cnx;
    }
    
    @Override
    public boolean retirer(String id) {
        String requete = "DELETE FROM reunionmembre WHERE ID_MEMBRE=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, id);
            int n = stm.executeUpdate();
            return n>0;           
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }

    @Override
    public boolean ajouter(ReunionMembres membre) {
        String requete = "INSERT INTO reunionmembre (ID_MEMBRE, ID_REUNION, NOM)"
                        +" VALUES (?,?,?)";

        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, membre.getIdMembre());
            stm.setString(2, membre.getIdReunion());
            stm.setString(3, membre.getNom());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }
     @Override
    public boolean participer(ReunionMembres membre) {
        String requete = "INSERT INTO reunionmembre (ID_MEMBRE, ID_REUNION, NOM)"
                        +" VALUES (?,?,?)";

        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, membre.getIdMembre());
            stm.setString(2, membre.getIdReunion());
            stm.setString(3, membre.getNom());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }
    
    @Override
    public List<ReunionMembres> trouverReunionsParIdMembre(String idMembre) {
        List<ReunionMembres> listeReunions = new LinkedList();
        String requete = "SELECT * FROM reunionmembre WHERE ID_MEMBRE=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, idMembre);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                ReunionMembres reunion = new ReunionMembres();
                reunion.setIdReunion(res.getString("ID_MEMBRE"));
                reunion.setIdMembre(res.getString("ID_REUNION"));
                reunion.setNom(res.getString("NOM"));
                listeReunions.add(reunion);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return listeReunions;
    }
    
    @Override
    public List<ReunionMembres> trouverMembresParIdReunion(String idReunion) {
        List<ReunionMembres> listeMembres = new LinkedList();
        String requete = "SELECT * FROM reunionmembre WHERE ID_REUNION=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, idReunion);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                ReunionMembres membre = new ReunionMembres();
                membre.setIdReunion(res.getString("ID_REUNION"));
                membre.setIdMembre(res.getString("ID_MEMBRE"));
                membre.setNom(res.getString("NOM"));
                listeMembres.add(membre);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return listeMembres;
    }

    @Override
    public boolean infirmer(ReunionMembres membre) {
        String requete = "DELETE FROM reunionmembre WHERE ID_MEMBRE=?";

        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, membre.getIdMembre());
            
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;
    }
}
