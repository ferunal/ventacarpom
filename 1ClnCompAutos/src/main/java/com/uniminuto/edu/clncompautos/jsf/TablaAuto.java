/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.clncompautos.jsf;

import com.uniminuto.edu.clncompautos.wscln.VntCarro;
import java.util.Objects;

/**
 *
 * @author fercris
 */
public class TablaAuto extends TablaBaseFrm {

    private VntCarro vntCarro = new VntCarro();

    public TablaAuto() {
    }

    public TablaAuto(VntCarro pVntCarro) {
        this.vntCarro = pVntCarro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.vntCarro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TablaAuto other = (TablaAuto) obj;
        return Objects.equals(this.vntCarro, other.vntCarro);
    }

    /**
     * @return the vntCarro
     */
    public VntCarro getVntCarro() {
        return vntCarro;
    }

    /**
     * @param vntCarro the vntCarro to set
     */
    public void setVntCarro(VntCarro vntCarro) {
        this.vntCarro = vntCarro;
    }
}
