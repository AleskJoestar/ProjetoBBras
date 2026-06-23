
package mapeamento;


public class Produto {
    private String nome_prod, nome_for;
    private float valor_prod;
    private int cod_prod, cod_for_fk;

    public String getNome_prod() {
        return nome_prod;
    }

    public String getNome_for() {
        return nome_for;
    }

    public void setNome_for(String nome_for) {
        this.nome_for = nome_for;
    }

    public void setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
    }

    public float getValor_prod() {
        return valor_prod;
    }

    public void setValor_prod(float valor_prod) {
        this.valor_prod = valor_prod;
    }


    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public int getCod_for_fk() {
        return cod_for_fk;
    }

    public void setCod_for_fk(int cod_for_fk) {
        this.cod_for_fk = cod_for_fk;
    }
    
}
