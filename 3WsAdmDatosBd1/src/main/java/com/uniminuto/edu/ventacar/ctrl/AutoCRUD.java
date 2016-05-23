/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.ventacar.ctrl;

import com.uniminuto.edu.ventacar.base.ConexionBD;
import com.uniminuto.edu.ventacar.modelo.CaractXAuto;
import com.uniminuto.edu.ventacar.modelo.CmtCalificacion;
import com.uniminuto.edu.ventacar.modelo.VntCaracteristicas;
import com.uniminuto.edu.ventacar.modelo.VntCarro;
import com.uniminuto.edu.ventacar.modelo.VntTipocrt;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class AutoCRUD extends ConexionBD implements Serializable {

//    private List<TablaCarro> lstTablaCarro = new ArrayList<>();
//    private TablaCarro tablaCarroSel = new TablaCarro();
    public Long guardarCarro(VntCarro pVntCarro) {
        try {
            dsPgConexion();
            String strInsCaract = "{ ? = call fn_insertar_auto(?,?) }";
            CallableStatement pst = conPg.prepareCall(strInsCaract);
            pst.registerOutParameter(1, Types.BIGINT);
            pst.setString(2, pVntCarro.getCarNombre());
            pst.setString(3, pVntCarro.getCarFoto());
            pst.execute();
            return pst.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return -1L;
        } finally {
            try {
                cerrarPgConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

//    public List<TablaCaracteristica> cargarCaractXCarro() {
//        
//    }
    public List<CmtCalificacion> getCmtCalificacionsXauto(Long pCarId) {
        List<CmtCalificacion> lstCalfXAuto = new ArrayList<>();

        try {
            dsPgConexion();
            String strSql = "SELECT \n"
                    + "  cmt_calificacion.cmtr_id, \n"
                    + "  cmt_calificacion.cmtr_comentario, \n"
                    + "  cmt_calificacion.usr_id, \n"
                    + "  cmt_calificacion.car_id, \n"
                    + "  cmt_calificacion.cmrt_fecha\n"
                    + "FROM \n"
                    + "  cmt_calificacion, \n"
                    + "  vnt_carro\n"
                    + "WHERE \n"
                    + "  vnt_carro.car_id = cmt_calificacion.car_id\n"
                    + "  AND cmt_calificacion.car_id = ? ORDER BY cmt_calificacion.cmrt_fecha DESC";
            PreparedStatement st = conPg.prepareStatement(strSql);
            st.setLong(1, pCarId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CmtCalificacion vc = new CmtCalificacion(rs.getLong("cmtr_id"));
                vc.setCmtrComentario(rs.getString("cmtr_comentario"));
                vc.setCmrtFecha(rs.getDate("cmrt_fecha"));
                vc.setUsrId("usr_id");

                lstCalfXAuto.add(vc);
            }
            return lstCalfXAuto;
        } catch (SQLException ex) {
            Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        } finally {
            try {
                cerrarPgConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<VntCaracteristicas> cargarCaractsXAuto(Long pCarId) {
        List<VntCaracteristicas> lstTablaCaractXAuto = new ArrayList<>();

        try {
            dsPgConexion();
            String strSql = "SELECT c.crtc_id, c.crtc_descripcion, c.crtc_est, t.tpcr_id , t.tpcr_nombre "
                    + " FROM vnt_caracteristicas c INNER JOIN vnt_tipocrt t ON c.tpcr_id = t.tpcr_id "
                    + "INNER JOIN vnt_carcactxauto cxa ON c.crtc_id = cxa.crtc_id "
                    + "WHERE cxa.car_id = ? ORDER BY crtc_descripcion";
            PreparedStatement st = conPg.prepareStatement(strSql);
            st.setLong(1, pCarId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                VntCaracteristicas vc = new VntCaracteristicas(rs.getLong("crtc_id"));
                vc.setCrtcDescripcion(rs.getString("crtc_descripcion"));
                VntTipocrt vt = new VntTipocrt(rs.getInt("tpcr_id"));
                vt.setTpcrNombre(rs.getString("tpcr_nombre"));
                vc.setTpcrId(vt);
                vc.setCrtcEst(rs.getBoolean("crtc_est"));

                lstTablaCaractXAuto.add(vc);
            }
            return lstTablaCaractXAuto;
        } catch (SQLException ex) {
            Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        } finally {
            try {
                cerrarPgConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<VntCarro> getLstCarros() {
        List<VntCarro> lstTablaCarro = new ArrayList<>();

        try {
            dsPgConexion();
            String strSql = "SELECT car_id, car_nombre, car_foto, car_est  FROM public.vnt_carro ORDER BY car_nombre";
            Statement st = conPg.createStatement();
            ResultSet rs = st.executeQuery(strSql);
            while (rs.next()) {
                VntCarro vc = new VntCarro(rs.getLong("car_id"));
                vc.setCarNombre(rs.getString("car_nombre"));
                vc.setCarFoto(rs.getString("car_foto"));
                vc.setCarEst(rs.getBoolean("car_est"));

                lstTablaCarro.add(vc);
            }
            return lstTablaCarro;
        } catch (SQLException ex) {
            Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        } finally {
            try {
                cerrarPgConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Long guardarCaracteristica(String pCaract, Integer tipoCarSel) {
        try {
            dsPgConexion();
            String strInsCaract = "{ ? = call fn_insertar_caracterisitca(?,?) }";
            CallableStatement pst = conPg.prepareCall(strInsCaract);
            pst.registerOutParameter(1, Types.BIGINT);
            pst.setString(2, pCaract);
            pst.setInt(3, tipoCarSel);
            pst.execute();
            return pst.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return -1L;
        } finally {
            try {
                cerrarPgConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public List<VntCaracteristicas> cargarCaracts() {

        List<VntCaracteristicas> lstTablaCaract = new ArrayList<>();
        try {
            dsPgConexion();
            String strSql = "SELECT c.crtc_id, c.crtc_descripcion, c.crtc_est, t.tpcr_id , t.tpcr_nombre  FROM vnt_caracteristicas c INNER JOIN vnt_tipocrt t ON c.tpcr_id = t.tpcr_id ORDER BY crtc_descripcion";
            Statement st = conPg.createStatement();
            ResultSet rs = st.executeQuery(strSql);
            while (rs.next()) {
                VntCaracteristicas vc = new VntCaracteristicas(rs.getLong("crtc_id"));
                vc.setCrtcDescripcion(rs.getString("crtc_descripcion"));
                VntTipocrt vt = new VntTipocrt(rs.getInt("tpcr_id"));
                vt.setTpcrNombre(rs.getString("tpcr_nombre"));
                vc.setTpcrId(vt);
                vc.setCrtcEst(rs.getBoolean("crtc_est"));

                lstTablaCaract.add(vc);
            }
            return lstTablaCaract;
        } catch (SQLException ex) {
            Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        } finally {
            try {
                cerrarPgConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<VntTipocrt> cargarTipoCaract() {
        List<VntTipocrt> lstTipocrts = new ArrayList<>();

        try {
            dsPgConexion();
            String strSql = "SELECT tpcr_id, tpcr_nombre  FROM vnt_tipocrt ORDER BY  tpcr_nombre;";
            Statement st = conPg.createStatement();
            ResultSet rs = st.executeQuery(strSql);
            while (rs.next()) {
                VntTipocrt vt = new VntTipocrt(rs.getInt("tpcr_id"));
                vt.setTpcrNombre(rs.getString("tpcr_nombre"));
                lstTipocrts.add(vt);

            }
            return lstTipocrts;
        } catch (SQLException ex) {
            Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        } finally {
            try {
                cerrarPgConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean guardarCaracXAuto(List<CaractXAuto> pVntCarcactxautos) {

        String strInsCaract = "{ ? = call fn_insertar_caracxauto(?,?) }";

        try {
            dsPgConexion();
            CallableStatement pst = conPg.prepareCall(strInsCaract);

            for (CaractXAuto cxa : pVntCarcactxautos) {

                pst.registerOutParameter(1, Types.BIGINT);
                pst.setLong(2, cxa.getCarId());
                pst.setLong(3, cxa.getCrtcId());
                pst.execute();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                cerrarPgConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
