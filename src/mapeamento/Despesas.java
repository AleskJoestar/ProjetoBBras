/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeamento;

/**
 *
 * @author T-Gamer
 */
public class Despesas {
    private int cod_desp;
    private double valor_desp;
    String desc_desp,data_Desp,hora_desp;

    public int getCod_desp() {
        return cod_desp;
    }

    public void setCod_desp(int cod_desp) {
        this.cod_desp = cod_desp;
    }

    public double getValor_desp() {
        return valor_desp;
    }

    public void setValor_desp(double valor_desp) {
        this.valor_desp = valor_desp;
    }

    public String getDesc_desp() {
        return desc_desp;
    }

    public void setDesc_desp(String desc_desp) {
        this.desc_desp = desc_desp;
    }

    public String getData_Desp() {
        return data_Desp;
    }

    public void setData_Desp(String data_Desp) {
        this.data_Desp = data_Desp;
    }

    public String getHora_desp() {
        return hora_desp;
    }

    public void setHora_desp(String hora_desp) {
        this.hora_desp = hora_desp;
    }
    
    

    
}