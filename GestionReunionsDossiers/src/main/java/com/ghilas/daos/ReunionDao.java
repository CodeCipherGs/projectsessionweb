/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.entites.Reunion;
import java.util.List;

/**
 *
 * @author Raritetnik
 */
public interface ReunionDao {
    public List<Reunion> trouverTout();
    public boolean créer(Reunion reunion);
    public boolean modifierTitre(Reunion reunion);
    public boolean modifierDate(Reunion reunion);    
    public boolean modifierReunion(Reunion reunion);
    public boolean annuler(String reunion);
    public Reunion trouverParId(String id);
    public List<Reunion> trouverParTitre(String titre);
    public boolean fermerReunion(String idReunion);
    
    
}
