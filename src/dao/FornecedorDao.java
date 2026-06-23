
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Fornecedor;
import utilitario.Conectar;


public class FornecedorDao {
    
    public void salvar(Fornecedor f) {
    Connection con = Conectar.getconectar();
    String sql = "INSERT into Fornecedor (nomeFant_for , razaoSoci_for, cnpj_for, cep_for, ende_for, tel_for, email_for, resp_for, cidade_for) values (?,?,?,?,?,?,?,?,?) ";
    
    try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setString(1, f.getNomeFanta_for());
         stm.setString(2, f.getRazaoSocial_for());
         stm.setString(3, f.getCnpj_for());
         stm.setString(4, f.getCep_for());
         stm.setString(5, f.getEndereco_for());
         stm.setString(6, f.getTelefone_for());
         stm.setString(7, f.getEmail_for());
         stm.setString(8, f.getResp_for());
         stm.setString(9, f.getCidade_for());
         stm.execute();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
}
    
    public void atualizar(Fornecedor f){
    Connection con = Conectar.getconectar();
    String sql = "update fornecedor set nomeFant_for=?, razaoSoci_for=?, cnpj_for=?, cep_for=?, ende_for=?, tel_for=?, email_for=?, resp_for=?, cidade_for=? where cod_for=?";
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setString(1, f.getNomeFanta_for());
         stm.setString(2, f.getRazaoSocial_for());
         stm.setString(3, f.getCnpj_for());
         stm.setString(4, f.getCep_for());
         stm.setString(5, f.getEndereco_for());
         stm.setString(6, f.getTelefone_for());
         stm.setString(7, f.getEmail_for());
         stm.setString(8, f.getResp_for());
         stm.setString(9, f.getCidade_for());
         stm.setInt(10, f.getCod_for());
         stm.executeUpdate();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
         System.out.println("error"+ex.getMessage());
     }
    
}
    
    public void deletar(Fornecedor f){
    Connection con = Conectar.getconectar();
    String sql = "delete from fornecedor where cod_for =?";
        int op = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR "+ f.getNomeFanta_for()+ " ?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, f.getCod_for()); 
                stm.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                stm.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error" + ex.getMessage());
            }
        }
}
    
    public List<Fornecedor> listarTodos(String nome){
   Connection con = Conectar.getconectar();
   List <Fornecedor> listafornecedor  = new ArrayList<>();
   String sql = "Select * from fornecedor";
   try(PreparedStatement stm = con.prepareStatement(sql)){
       ResultSet resultado = stm.executeQuery();
       while (resultado.next()) {
           Fornecedor f = new Fornecedor();
           f.setCod_for(resultado.getInt("cod_for"));
           f.setNomeFanta_for(resultado.getString("nomeFant_for"));
           f.setRazaoSocial_for(resultado.getString("razaoSoci_for"));
           f.setCnpj_for(resultado.getString("cnpj_for"));
           f.setCep_for(resultado.getString("cep_for"));
           f.setEndereco_for(resultado.getString("ende_for"));
           f.setTelefone_for(resultado.getString("tel_for"));
           f.setEmail_for(resultado.getString("email_for"));
           f.setResp_for(resultado.getString("resp_for"));
           f.setCidade_for(resultado.getString("cidade_for"));
           listafornecedor.add(f);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listafornecedor;
  }
    
    public List<Fornecedor> BuscarNome(String nome){
   Connection con = Conectar.getconectar();
   List <Fornecedor> listafornecedor  = new ArrayList<>();
   String sql = "Select * from fornecedor where nomefant_for like'"+nome+"%'";
   
   try(PreparedStatement stm = con.prepareStatement(sql)){
     
       ResultSet resultado = stm.executeQuery();
       while (resultado.next()) {
           Fornecedor f = new Fornecedor();
           f.setCod_for(resultado.getInt("cod_for"));
           f.setNomeFanta_for(resultado.getString("nomeFant_for"));
           f.setRazaoSocial_for(resultado.getString("razaoSoci_for"));
           f.setCnpj_for(resultado.getString("cnpj_for"));
           f.setCep_for(resultado.getString("cep_for"));
           f.setEndereco_for(resultado.getString("ende_for"));
           f.setTelefone_for(resultado.getString("tel_for"));
           f.setEmail_for(resultado.getString("email_for"));
           f.setResp_for(resultado.getString("resp_for"));
           f.setCidade_for(resultado.getString("cidade_for"));
           listafornecedor.add(f);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listafornecedor;
  }
}
