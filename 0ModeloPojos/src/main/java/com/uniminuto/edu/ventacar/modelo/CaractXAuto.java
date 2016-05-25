/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.ventacar.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author fercris
 */
public class CaractXAuto {

    private Long carId;
    private Long crtcId;

    public static CaractXAuto fromString(String jsonRepresentation) {
        ObjectMapper mapper = new ObjectMapper(); //Jackson's JSON marshaller
        CaractXAuto o = null;
        try {
            o = mapper.readValue(jsonRepresentation, CaractXAuto.class);
        } catch (IOException e) {
            throw new WebApplicationException();
        }
        return o;
    }

    /**
     * @return the carId
     */
    public Long getCarId() {
        return carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(Long carId) {
        this.carId = carId;
    }

    /**
     * @return the crtcId
     */
    public Long getCrtcId() {
        return crtcId;
    }

    /**
     * @param crtcId the crtcId to set
     */
    public void setCrtcId(Long crtcId) {
        this.crtcId = crtcId;
    }

}
