/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.springframework.web.context.ServletContextAware;
/**
 *
 * @author Raritetnik
 */
public class Connexion implements ServletContextAware{
    private Connection connection;
    private ServletContext servletContext;
    @Override
    public void setServletContext(ServletContext sc) {
        this.servletContext = sc;
    }
    public Connection getInstance(){
        try {
            if (connection==null|| connection.isClosed()) {
                String pilote = servletContext.getInitParameter("pilote");
                String url = servletContext.getInitParameter("bdUrl");
                String user = servletContext.getInitParameter("user");
                String password = servletContext.getInitParameter("password");
                try {
                    Class.forName(pilote);
                    connection = DriverManager.getConnection(url,user,password);
                    if (connection == null)
                        System.out.println("cnx est NULL");
                 
                }
                catch (ClassNotFoundException | SQLException exp) {
                    exp.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    

}
