/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.entites.CompteRendu;
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
public class JdbcCompteRenduDao implements CompteRenduDao{
   private Connexion dbConnexion;
    public void setDbConnexion(Connexion cnx) {
        this.dbConnexion = cnx;
    }

    @Override
    public boolean ajouter(CompteRendu compteRendu) {
        String requete = "INSERT INTO compterendu(`ID_COMPTE_RENDU`, `ID_MEMBRE`, `ID_POINT_DORDRE`, `NOM`, `COMPTE_RENDU`) "
                + "VALUES (?,?,?,?,?)";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            
            stm.setString(1, compteRendu.getIdComptreRendu());
            stm.setString(2, compteRendu.getIdMembre());
            stm.setString(3, compteRendu.getIdPointDordre());
            stm.setString(4, compteRendu.getNom());
            stm.setString(5, compteRendu.getTexte());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCompteRenduDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;    
    }

    @Override
    public boolean supprimer(String idCompteRendu) {
        String requete = "DELETE FROM compterendu WHERE ID_COMPTE_RENDU=?";
        try(
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, idCompteRendu);
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCompteRenduDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;   
    } 
    
    @Override
    public boolean modifier(CompteRendu compteRendu) {
        String requete = "UPDATE compterendu SET `COMPTE_RENDU`=? WHERE `ID_COMPTE_RENDU`=?";
        try(
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, compteRendu.getTexte());
            stm.setString(2, compteRendu.getIdComptreRendu());
            int n = stm.executeUpdate();
            return n>0;            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCompteRenduDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return false;   
    }

    @Override
    public List<CompteRendu> findAll() {
        List<CompteRendu> liste = new LinkedList();
        CompteRendu compteRendu;
        String requete = "SELECT * FROM compterendu";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            Statement stm = cnx.createStatement();
            ResultSet res = stm.executeQuery(requete);
        ){
            while (res.next()) {
                compteRendu = new CompteRendu();
                compteRendu.setIdComptreRendu(res.getString("ID_COMPTE_RENDU"));
                compteRendu.setIdMembre(res.getString("ID_MEMBRE"));
                compteRendu.setIdPointDordre(res.getString("ID_POINT_DORDRE"));
                compteRendu.setNom(res.getString("NOM"));
                compteRendu.setTexte(res.getString("COMPTE_RENDU"));
                liste.add(compteRendu);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return liste;
    }

    @Override
    public List<CompteRendu> findByIdPointDordre(String idPointDordre) {
        List<CompteRendu> liste = new LinkedList();
        CompteRendu compteRendu;
        String requete = "SELECT * FROM compterendu WHERE ID_POINT_DORDRE=?";
        
        try (
            Connection cnx = dbConnexion.getInstance();
            PreparedStatement stm = cnx.prepareStatement(requete);
        ){
            stm.setString(1, idPointDordre);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                compteRendu = new CompteRendu();
                compteRendu.setIdComptreRendu(res.getString("ID_COMPTE_RENDU"));
                compteRendu.setIdMembre(res.getString("ID_MEMBRE"));
                compteRendu.setIdPointDordre(res.getString("ID_POINT_DORDRE"));
                compteRendu.setNom(res.getString("NOM"));
                compteRendu.setTexte(res.getString("COMPTE_RENDU"));
                liste.add(compteRendu);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcReunionDao.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return liste;
    }
}
