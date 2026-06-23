
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Funcionario;
import utilitario.Conectar;


public class FuncionarioDao {
    
public void salvar(Funcionario f) {
    Connection con = Conectar.getconectar();
String sql = "INSERT into Funcionario (nome_fun ,cpf_fun, rg_fun, endereço_fun, telefone_fun, email_fun, funcao_fun, departamento_fun, senha_fun) values (?,?,?,?,?,?,?,?,md5(?)) ";
    
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setString(1, f.getNome_fun());
         stm.setString(2, f.getCpf_fun());
         stm.setString(3, f.getRg_fun());
         stm.setString(4, f.getEndereço_fun());
         stm.setString(5, f.getTelefone_fun());
         stm.setString(6,f.getEmail_fun());
         stm.setString(7, f.getFuncao_fun());
         stm.setString(8, f.getDepartamento_fun());
         stm.setString(9, f.getSenha_fun());
         stm.execute();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
}

public void atualizar(Funcionario f){
    Connection con = Conectar.getconectar();
String sql = "update  funcionario set nome_fun=?, cpf_fun=?, rg_fun=?, endereço_fun=?, telefone_fun=?, email_fun=?, funcao_fun=?, departamento_fun=?, senha_fun=md5(?) where cod_fun=?";
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setString(1, f.getNome_fun());
         stm.setString(2, f.getCpf_fun());
         stm.setString(3, f.getRg_fun());
         stm.setString(4, f.getEndereço_fun());
         stm.setString(5, f.getTelefone_fun());
         stm.setString(6,f.getEmail_fun());
         stm.setString(7, f.getFuncao_fun());
         stm.setString(8, f.getDepartamento_fun());
         stm.setString(9, f.getSenha_fun());
         stm.setInt(10,f.getCod_fun());
         stm.executeUpdate();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
         System.out.println("error"+ex.getMessage());
     }
    
}

public void deletar(Funcionario f){
    Connection con = Conectar.getconectar();
    String sql = "delete from funcionario where cod_fun =?";
        int op = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR "+ f.getNome_fun()+ " ?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, f.getCod_fun()); 
                stm.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                stm.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error" + ex.getMessage());
            }
        }
            
}

  public List<Funcionario> listarTodos(String nome){
   Connection con = Conectar.getconectar();
   List <Funcionario> listafuncionario  = new ArrayList<>();
   String sql = "Select * from funcionario";
   try(PreparedStatement stm = con.prepareStatement(sql)){
       ResultSet resultado = stm.executeQuery();
       while (resultado.next()) {
           Funcionario f = new Funcionario();
           f.setCod_fun(resultado.getInt("cod_fun"));
           f.setNome_fun(resultado.getString("nome_fun"));
           f.setCpf_fun(resultado.getString("cpf_fun"));
           f.setRg_fun(resultado.getString("rg_fun"));
           f.setEndereço_fun(resultado.getString("endereço_fun"));
           f.setTelefone_fun(resultado.getString("telefone_fun"));
           f.setEmail_fun(resultado.getString("email_fun"));
           f.setFuncao_fun(resultado.getString("funcao_fun"));
           f.setDepartamento_fun(resultado.getString("departamento_fun"));
           listafuncionario.add(f);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listafuncionario;
  }
 
  public Funcionario Login(String cpf ,String senha) {
      
      Connection con = Conectar.getconectar();
      Funcionario f = new Funcionario();
String sql = "select * from funcionario where cpf_fun=? and senha_fun = MD5(?)";
      try(PreparedStatement smt = con.prepareStatement(sql)){
          smt.setString(1, cpf);
          smt.setString(2, senha);
          ResultSet resultado = smt.executeQuery();
          resultado.next();
          
         if (resultado.getString("Cpf_fun")!=null ) {
           f.setCod_fun(resultado.getInt("cod_fun"));
           f.setNome_fun(resultado.getString("nome_fun"));
             f.setCpf_fun(resultado.getString("cpf_fun"));
            f.setRg_fun(resultado.getString("rg_fun"));
             f.setEndereço_fun(resultado.getString("endereço_fun"));
               f.setFuncao_fun(resultado.getString("funcao_fun"));
           f.setDepartamento_fun(resultado.getString("departamento_fun"));
      f.setTelefone_fun(resultado.getString("telefone_fun"));
      f.setEmail_fun(resultado.getString("email_fun"));
          }else{
              JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto!");
          }
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, "Erro ao buscar o funcionário "+"(" + e.getMessage()+")");
          
  }   
return f;
}
  

      
public List<Funcionario> BuscarNome(String nome){
   Connection con = Conectar.getconectar();
   List <Funcionario> listafuncionario  = new ArrayList<>();
   String sql = "Select * from funcionario where nome_fun like'"+nome+"%'";
   
   try(PreparedStatement stm = con.prepareStatement(sql)){
     
       ResultSet resultado = stm.executeQuery();
       while (resultado.next()) {
           Funcionario f = new Funcionario();
           f.setCod_fun(resultado.getInt("cod_fun"));
           f.setNome_fun(resultado.getString("nome_fun"));
           f.setCpf_fun(resultado.getString("cpf_fun"));
           f.setRg_fun(resultado.getString("rg_fun"));
           f.setEndereço_fun(resultado.getString("endereço_fun"));
           f.setTelefone_fun(resultado.getString("telefone_fun"));
           f.setEmail_fun(resultado.getString("email_fun"));
           f.setFuncao_fun(resultado.getString("funcao_fun"));
           f.setDepartamento_fun(resultado.getString("departamento_fun"));
           listafuncionario.add(f);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listafuncionario;
  }
      
      
}
  

