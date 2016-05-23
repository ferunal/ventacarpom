/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.wscompautos.ws.soap;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author fercris
 */
public abstract class BaseSOAPWS {

    @Resource(lookup = "java:jboss/datasources/ventacards")
    protected DataSource dsPgVentacar;
 

   
}
