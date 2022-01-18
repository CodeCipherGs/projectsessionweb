/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.controleurs;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Raritetnik
 */
@Controller
public class AccueilControleur {
   
    @RequestMapping("/")
    public String accueil(ModelMap model, HttpSession session) {
        
        return "Accueil";
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/")
    public String reunion( ModelMap model, HttpSession session) {
     
        return "Accueil";
    }
}
