/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.entites.PointDordre;
import com.ghilas.entites.Reunion;
import java.util.List;

/**
 *
 * @author Raritetnik
 */
public interface PointDordreDao {    
    public List<PointDordre> trouverTout();
    public boolean ajouter(PointDordre pointdordre);
    public boolean supprimer(String id);
    public boolean modifierNom(PointDordre pointdordre);
    public boolean modifierContenu(PointDordre pointdordre);
    public PointDordre trouverParId(String id);
    public List<PointDordre> trouverParTitre(String titre);
    public List<PointDordre> trouverParIdReunion(String idReunion);
}
