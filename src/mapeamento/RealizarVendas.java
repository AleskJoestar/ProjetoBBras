
package mapeamento;


public class RealizarVendas {
    private int cod_ven, cod_cli, cod_fun,cod_prod,quant_ven,cod_vendas_fk,cod_vendas;
    private String hora_ven, data_ven,nome_prod,nome_cli,nome_fun;
    private float valor_ven;

    public int getCod_vendas() {
        return cod_vendas;
    }

    public void setCod_vendas(int cod_vendas) {
        this.cod_vendas = cod_vendas;
    }
    
    public int getCod_vendas_fk() {
        return cod_vendas_fk;
    }

    public void setCod_vendas_fk(int cod_vendas_fk) {
        this.cod_vendas_fk = cod_vendas_fk;
    }

    public String getNome_cli() {
        return nome_cli;
    }

    public void setNome_cli(String nome_cli) {
        this.nome_cli = nome_cli;
    }

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
    
    public int getQuant_ven() {
        return quant_ven;
    }

    public void setQuant_ven(int quant_ven) {
        this.quant_ven = quant_ven;
    }

    
    public int getCod_ven() {
        return cod_ven;
    }

    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public void setCod_ven(int cod_ven) {
        this.cod_ven = cod_ven;
    }

    public int getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(int cod_cli) {
        this.cod_cli = cod_cli;
    }

    public int getCod_fun() {
        return cod_fun;
    }

    public void setCod_fun(int cod_fun) {
        this.cod_fun = cod_fun;
    }

    public String getHora_ven() {
        return hora_ven;
    }

    public void setHora_ven(String hora_ven) {
        this.hora_ven = hora_ven;
    }

    public String getData_ven() {
        return data_ven;
    }

    public void setData_ven(String data_ven) {
        this.data_ven = data_ven;
    }

    public float getValor_ven() {
        return valor_ven;
    }

    public void setValor_ven(float valor_ven) {
        this.valor_ven = valor_ven;
    }
    
    
    
    
    
    
    
}
