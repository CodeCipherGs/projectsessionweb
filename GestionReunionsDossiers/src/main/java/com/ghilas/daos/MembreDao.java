/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.entites.Membre;
import java.util.List;

/**
 *
 * @author booska
 */
public interface MembreDao {
    
    public Membre findById(String id);
    public Membre findByCourriel(String courriel);
    public boolean create(Membre membre);
    public List<Membre> trouverParNom(String nom);
    public List<Membre> trouverTous();
}
