/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;
/**
 *
 * @author tonio
 */
public class BubbleDataModel {
    private LocalDate fecha;
    private int mp;
    private int hospitalizados;

    public BubbleDataModel(LocalDate fecha, int mp, int hospitalizados) {
        this.fecha = fecha;
        this.mp = mp;
        this.hospitalizados = hospitalizados;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getMp() {
        return mp;
    }

    public int getHospitalizados() {
        return hospitalizados;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setHospitalizados(int hospitalizados) {
        this.hospitalizados = hospitalizados;
    }
    
    @Override
    public String toString(){
        return this.fecha + " " + this.mp+ " " + this.hospitalizados ;
    }
    
}
