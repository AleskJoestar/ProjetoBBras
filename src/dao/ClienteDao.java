
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Cliente;
import utilitario.Conectar;


public class ClienteDao {
    
    public void salvar(Cliente c) {
    Connection con = Conectar.getconectar();
    String sql = "INSERT into Cliente (nome_cli ,cpf_cli, rg_cli, sexo_cli, datanasc_cli, endereco_cli, telefone_cli, email_cli) values (?,?,?,?,?,?,?,?) ";
    
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setString(1, c.getNome_cli());
         stm.setString(2, c.getCpf_cli());
         stm.setString(3, c.getRg_cli());
         stm.setString(4, c.getSexo_cli());
         stm.setString(5, c.getDatanasc_cli());
         stm.setString(6, c.getEndereco_cli());
         stm.setString(7, c.getTelefone_cli());
         stm.setString(8, c.getEmail_cli());
         stm.execute();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Cliente cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
}
    
    public void atualizar(Cliente c){
    Connection con = Conectar.getconectar();
    String sql = "update  cliente set nome_cli=?, cpf_cli=?, rg_cli=?, sexo_cli=?, datanasc_cli=?, endereco_cli=?, telefone_cli=?, email_cli=? where cod_cli=?";
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setString(1, c.getNome_cli());
         stm.setString(2, c.getCpf_cli());
         stm.setString(3, c.getRg_cli());
         stm.setString(4, c.getSexo_cli());
         stm.setString(5, c.getDatanasc_cli());
         stm.setString(6, c.getEndereco_cli());
         stm.setString(7, c.getTelefone_cli());
         stm.setString(8, c.getEmail_cli());
         stm.setInt(9, c.getCod_cli());
         stm.executeUpdate();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
    
}
    
    public void deletar(Cliente c){
    Connection con = Conectar.getconectar();
    String sql = "delete from cliente where cod_cli=?";
        int op = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR "+ c.getCpf_cli()+ " ?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, c.getCod_cli()); 
                stm.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                stm.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error" + ex.getMessage());
            }
        }
            
}
    
    public List<Cliente> listarTodos(String nome){
   Connection con = Conectar.getconectar();
   List <Cliente> listacliente  = new ArrayList<>();
   String sql = "Select * from cliente";
   try(PreparedStatement stm = con.prepareStatement(sql)){
       ResultSet resultado = stm.executeQuery();
       while (resultado.next()) {
           Cliente c = new Cliente();
           c.setCod_cli(resultado.getInt("cod_cli"));
           c.setNome_cli(resultado.getString("nome_cli"));
           c.setCpf_cli(resultado.getString("cpf_cli"));
           c.setRg_cli(resultado.getString("rg_cli"));
           c.setSexo_cli(resultado.getString("sexo_cli"));
           c.setDatanasc_cli(resultado.getString("datanasc_cli"));
           c.setEndereco_cli(resultado.getString("endereco_cli"));
           c.setTelefone_cli(resultado.getString("telefone_cli"));
           c.setEmail_cli(resultado.getString("email_cli"));
           listacliente.add(c);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listacliente;
  }
    
    public List<Cliente> BuscarNome(String nome){
   Connection con = Conectar.getconectar();
   List <Cliente> listacliente  = new ArrayList<>();
   String sql = "Select * from cliente where nome_cli like'"+nome+"%'";
   
   try(PreparedStatement stm = con.prepareStatement(sql)){
     
       ResultSet resultado = stm.executeQuery();
       while (resultado.next()) {
           Cliente c = new Cliente();
           c.setCod_cli(resultado.getInt("cod_cli"));
           c.setNome_cli(resultado.getString("nome_cli"));
           c.setCpf_cli(resultado.getString("cpf_cli"));
           c.setRg_cli(resultado.getString("rg_cli"));
           c.setSexo_cli(resultado.getString("sexo_cli"));
           c.setDatanasc_cli(resultado.getString("datanasc_cli"));
           c.setEndereco_cli(resultado.getString("endereco_cli"));
           c.setTelefone_cli(resultado.getString("telefone_cli"));
           c.setEmail_cli(resultado.getString("email_cli"));
            listacliente.add(c);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listacliente;
  }
  
}


