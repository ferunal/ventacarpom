/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.ventacar.ctrl;

import com.uniminuto.edu.ventacar.base.ConexionBD;
import com.uniminuto.edu.ventacar.modelo.Uusario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fercris
 */
public class UsuarioCRUD extends ConexionBD {

    public List<Uusario> cargarUsuarios() {
        List<Uusario> lstUusario = new ArrayList<>();
        try {
            String strSql = "SELECT usr_id, usr_nombre, usr_correo FROM usuario ORDER BY usr_nombre;";
            Statement st = conMy.createStatement();
            try (ResultSet rs = st.executeQuery(strSql)) {
                while (rs.next()) {                   

                    lstUusario.add(new Uusario(rs.getString("usr_id"), rs.getString("usr_nombre"), rs.getString("usr_correo")));

                }
            }
            return lstUusario;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

//    public void grabarComentarioXAuto() {
//        try {
//            //fn_insertar_calificacion(p_cmtr_comentario character varying, p_cmrt_fecha timestamp with time zone, p_usr_id character varying, p_car_id bigint)
//            String strInsCaract = "{ ? = call fn_insertar_calificacion(?,?,?,?) }";
//            CallableStatement pst = conPg.prepareCall(strInsCaract);
//            for (TablaCarro tc : autoJSFBean.getLstTablaCarro()) {
//                if (tc.isSeleccionado()) {
//                    pst.registerOutParameter(1, Types.BOOLEAN);
//                    pst.setString(2, comentario);
//
//                    pst.setDate(3, new java.sql.Date(new Date().getTime()));
//                    pst.setString(4, idUsrSel);
//                    pst.setLong(5, tc.getCarro().getCarId());
//                    pst.execute();
//                }
//            }
//            comentario = "";
//            selTodoLst(autoJSFBean.getLstTablaCarro(), false);
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioCRUD.class.getName()).log(Level.SEVERE, null, ex);
//            comentario = "";
//        }
//    }
    /**
     * @return the lstTablaUsuarios
     */
   
}
