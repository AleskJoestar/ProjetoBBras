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
public class VendasTotais {
  private  int cod_cli_fk,cod_fun_fk,cod_vendas;
  private double valortotal_vendas,valorpago_vendas,valortroco_vendas,valordesconto_vendas;
  private String nome_fun, nome_cli;

    public String getNome_fun() {
        return nome_fun;
    }

    public void setNome_fun(String nome_fun) {
        this.nome_fun = nome_fun;
    }

    public String getNome_cli() {
        return nome_cli;
    }

    public void setNome_cli(String nome_cli) {
        this.nome_cli = nome_cli;
    }
  
    
    public int getCod_cli_fk() {
        return cod_cli_fk;
    }

    public void setCod_cli_fk(int cod_cli_fk) {
        this.cod_cli_fk = cod_cli_fk;
    }

    public int getCod_fun_fk() {
        return cod_fun_fk;
    }

    public void setCod_fun_fk(int cod_fun_fk) {
        this.cod_fun_fk = cod_fun_fk;
    }

    public int getCod_vendas() {
        return cod_vendas;
    }

    public void setCod_vendas(int cod_vendas) {
        this.cod_vendas = cod_vendas;
    }

    public double getValortotal_vendas() {
        return valortotal_vendas;
    }

    public void setValortotal_vendas(double valortotal_vendas) {
        this.valortotal_vendas = valortotal_vendas;
    }

    public double getValorpago_vendas() {
        return valorpago_vendas;
    }

    public void setValorpago_vendas(double valorpago_vendas) {
        this.valorpago_vendas = valorpago_vendas;
    }

    public double getValortroco_vendas() {
        return valortroco_vendas;
    }

    public void setValortroco_vendas(double valortroco_vendas) {
        this.valortroco_vendas = valortroco_vendas;
    }

    public double getValordesconto_vendas() {
        return valordesconto_vendas;
    }

    public void setValordesconto_vendas(double valordesconto_vendas) {
        this.valordesconto_vendas = valordesconto_vendas;
    }
    
    
}
