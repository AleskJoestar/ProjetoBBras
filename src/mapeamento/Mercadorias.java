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
public class Mercadorias {
    
    private float valor_merc;
    
    
    private int cod_merc,cod_fun_fk,cod_prod_fk,quant_merc;
  private  String data_merc,hora_merc,nome_prod,nome_fun;

    public String getNome_fun() {
        return nome_fun;
    }

    public void setNome_fun(String nome_fun) {
        this.nome_fun = nome_fun;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public void setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
    }

    
  
    public int getQuant_merc() {
        return quant_merc;
    }

    public void setQuant_merc(int quant_merc) {
        this.quant_merc = quant_merc;
    }
  
  
  
    public float getValor_merc() {
        return valor_merc;
    }

    public void setValor_merc(float valor_merc) {
        this.valor_merc = valor_merc;
    }

    
    public int getCod_merc() {
        return cod_merc;
    }

    public void setCod_merc(int cod_merc) {
        this.cod_merc = cod_merc;
    }

    public int getCod_fun_fk() {
        return cod_fun_fk;
    }

    public void setCod_fun_fk(int cod_fun_fk) {
        this.cod_fun_fk = cod_fun_fk;
    }

    public String getData_merc() {
        return data_merc;
    }

    public void setData_merc(String data_merc) {
        this.data_merc = data_merc;
    }

    public String getHora_merc() {
        return hora_merc;
    }

    public void setHora_merc(String hora_merc) {
        this.hora_merc = hora_merc;
    }

    public int getCod_prod_fk() {
        return cod_prod_fk;
    }

    public void setCod_prod_fk(int cod_prod_fk) {
        this.cod_prod_fk = cod_prod_fk;
    }
    
    
}
