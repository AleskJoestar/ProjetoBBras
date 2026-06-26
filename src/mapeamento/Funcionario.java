
package mapeamento;


public class Funcionario {
   private int cod_fun;
   private String nome_fun;
   private String cpf_fun;
   private String rg_fun;
   private String endereco_fun; // FIXED: campo renomeado sem acento para evitar problemas de encoding com H2
   private String telefone_fun;
   private String email_fun;
   private String funcao_fun;
   private String departamento_fun;
   private String senha_fun;

    public int getCod_fun() {
        return cod_fun;
    }

    public void setCod_fun(int cod_fun) {
        this.cod_fun = cod_fun;
    }
    
    public String getNome_fun() {
        return nome_fun;
    }

    public void setNome_fun(String nome_fun) {
        this.nome_fun = nome_fun;
    }

    public String getCpf_fun() {
        return cpf_fun;
    }

    public void setCpf_fun(String cpf_fun) {
        this.cpf_fun = cpf_fun;
    }

    public String getRg_fun() {
        return rg_fun;
    }

    public void setRg_fun(String rg_fun) {
        this.rg_fun = rg_fun;
    }

    public String getEndereco_fun() { // FIXED: getter renomeado sem acento
        return endereco_fun;
    }

    public void setEndereco_fun(String endereco_fun) { // FIXED: setter renomeado sem acento
        this.endereco_fun = endereco_fun;
    }

    public String getTelefone_fun() {
        return telefone_fun;
    }

    public void setTelefone_fun(String telefone_fun) {
        this.telefone_fun = telefone_fun;
    }


    public String getEmail_fun() {
        return email_fun;
    }

    public void setEmail_fun(String email_fun) {
        this.email_fun = email_fun;
    }

    public String getFuncao_fun() {
        return funcao_fun;
    }

    public void setFuncao_fun(String funcao_fun) {
        this.funcao_fun = funcao_fun;
    }

    public String getDepartamento_fun() {
        return departamento_fun;
    }

    public void setDepartamento_fun(String departamento_fun) {
        this.departamento_fun = departamento_fun;
    }

    public String getSenha_fun() {
        return senha_fun;
    }

    public void setSenha_fun(String senha_fun) {
        this.senha_fun = senha_fun;
    }
   
    
}
