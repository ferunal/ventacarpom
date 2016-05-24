/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.wsadmdatosbd.restws;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author fercris
 */
public abstract class BaseRestfulWS {

    @Resource(lookup = "java:jboss/datasources/ventacards")
    protected DataSource dsPgVentacar;
    @Resource(lookup = "java:jboss/datasources/ventacarusr")
    protected DataSource dsMyUsuarios;

   
}
