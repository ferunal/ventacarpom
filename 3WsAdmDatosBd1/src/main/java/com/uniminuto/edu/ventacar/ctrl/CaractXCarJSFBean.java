/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.ventacar.ctrl;

import com.uniminuto.edu.ventacar.base.ConexionBD;
import com.uniminuto.edu.ventacar.modelo.VntCaracteristicas;
import com.uniminuto.edu.ventacar.modelo.VntCarcactxauto;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author fercris
 */
@SessionScoped
@Named
public class CaractXCarJSFBean extends ConexionBD {

   

    List<TablaCaracteristica> lstCaracteristicas = new ArrayList<>();
    List<TablaCarro> lstTablaCarros = new ArrayList<>();

  
    
    
  


}
