/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.ventacar.ctrl;

import com.uniminuto.edu.ventacar.base.ConexionBD;
import com.uniminuto.edu.ventacar.modelo.Uusario;
import java.sql.PreparedStatement;
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
        } finally {
            try {
                conMy.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Uusario validarUsuarioCRUD(Uusario usuario) {
        Uusario usuarioloc = new Uusario();
        try {
            String strSql = "SELECT usr_id, usr_nombre, usr_correo, usr_usuario, usr_clave FROM usuario WHERE usr_usuario = ? and usr_clave = ?";
            PreparedStatement st = conMy.prepareStatement(strSql);
            st.setString(1, usuarioloc.getUsrUsuario());
            st.setString(2, usuarioloc.getUsrClave());
            try (ResultSet rs = st.executeQuery(strSql)) {
                while (rs.next()) {

                    usuarioloc.setUsrId(rs.getString("usr_id"));
                    usuarioloc.setUsrNombre(rs.getString("usr_nombre"));

                    usuarioloc.setUsrCorreo(rs.getString("usr_correo"));

                    usuarioloc.setUsrUsuario(rs.getString("usr_usuario"));
                    usuarioloc.setUsrClave(rs.getString("usr_clave"));
                }
            }
            return usuarioloc;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return new Uusario();
        } finally {
            try {
                conMy.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String guardarUsr(Uusario uusario) {
        try {
            StringBuilder strBSql = new StringBuilder();
            strBSql.append(" INSERT INTO usuario\n"
                    + "(usr_id,\n"
                    + "usr_nombre,\n"
                    + "usr_correo,\n"
                    + "usr_usuario,\n"
                    + "usr_clave)\n"
                    + "VALUES ('");
            strBSql.append(uusario.getUsrId());
            strBSql.append("','");
            strBSql.append(uusario.getUsrNombre());
            strBSql.append("','");
            strBSql.append(uusario.getUsrCorreo());
            strBSql.append("','");
            strBSql.append(uusario.getUsrUsuario());
            strBSql.append("','");
            strBSql.append(uusario.getUsrClave());
            strBSql.append("')");
            Statement smt = conMy.createStatement();
            smt.executeUpdate(strBSql.toString());
            return uusario.getUsrId();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return "-1";
        } finally {
            try {
                conMy.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String actualizarUsr(Uusario uusario) {
        try {
            StringBuilder strBSql = new StringBuilder();
            strBSql.append(" UPDATE usuario SET \n"
                    + "usr_nombre = ?,\n"
                    + "usr_correo = ?, usr_usuario  = ?, usr_clave = ? WHERE usr_id = ? ");

            PreparedStatement smt = conMy.prepareStatement(strBSql.toString());
            smt.setString(1, uusario.getUsrNombre());
            smt.setString(2, uusario.getUsrCorreo());
            smt.setString(3, uusario.getUsrUsuario());
            smt.setString(4, uusario.getUsrClave());
            smt.setString(5, uusario.getUsrId());

            smt.executeUpdate(strBSql.toString());
            return uusario.getUsrId();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return "-1";
        } finally {
            try {
                conMy.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
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
