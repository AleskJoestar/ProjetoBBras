
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Produto;
import utilitario.Conectar;

public class ProdutoDao {
    
    public void salvar(Produto p) {
    Connection con = Conectar.getconectar();
    String sql = "INSERT into Produto (nome_prod ,valor_prod, cod_for_fk) values (?,?,?)";
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setString(1, p.getNome_prod());
         stm.setFloat(2, p.getValor_prod());
         stm.setInt(3, p.getCod_for_fk());
         stm.execute();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
}
    
     public void atualizar(Produto p){
    Connection con = Conectar.getconectar();
    String sql = "update Produto set nome_prod=?, valor_prod=?, cod_for_fk=? where cod_prod=?";
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setString(1, p.getNome_prod());
         stm.setFloat(2, p.getValor_prod());
         stm.setInt(3, p.getCod_for_fk());
         stm.setInt(4, p.getCod_prod());
         stm.executeUpdate();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
    
}
     public void deletar(Produto p){
    Connection con = Conectar.getconectar();
    String sql = "delete from Produto where cod_prod=?";
        int op = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR "+ p.getCod_prod()+ " ?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, p.getCod_prod()); 
                stm.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                stm.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error" + ex.getMessage());
            }
        }
            
}
      public List<Produto> listarTodos(String nome){
   Connection con = Conectar.getconectar();
   List <Produto> listaProduto  = new ArrayList<>();
   String sql = "Select * from Produto";
   String sql2 = "select nomefant_for from produto,fornecedor where cod_for = cod_for_fk";
   try(PreparedStatement stm = con.prepareStatement(sql);PreparedStatement stm2 = con.prepareStatement(sql2)){
       ResultSet resultado = stm.executeQuery();
       ResultSet resultado2 = stm2.executeQuery();
       while (resultado.next() & resultado2.next()) {
           Produto p = new Produto();
           p.setCod_prod(resultado.getInt("cod_prod"));
           p.setNome_prod(resultado.getString("nome_prod"));
           p.setValor_prod(resultado.getFloat("valor_prod"));
           p.setCod_for_fk(resultado.getInt("cod_for_fk"));
           p.setNome_for(resultado2.getString("nomefant_for"));
           listaProduto.add(p);
       }
          stm.close();
          con.close();
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaProduto;
  }
      
      
      public List<Produto> BuscarNome(String nome){
   Connection con = Conectar.getconectar();
   List <Produto> listaproduto  = new ArrayList<>();
   String sql = "Select * from produto where nome_prod like'"+nome+"%'";
   String sql2 = "select nomefant_for from produto,fornecedor where cod_for = cod_for_fk";
   
   try(PreparedStatement stm = con.prepareStatement(sql);PreparedStatement stm2 = con.prepareStatement(sql2)){
     
       ResultSet resultado = stm.executeQuery();
       ResultSet resultado2 = stm2.executeQuery();
       while (resultado.next() & resultado2.next()) {
           Produto p = new Produto();
           p.setCod_prod(resultado.getInt("cod_prod"));
           p.setNome_prod(resultado.getString("nome_prod"));
           p.setValor_prod(resultado.getFloat("valor_prod"));
           p.setCod_for_fk(resultado.getInt("cod_for_fk"));
           p.setNome_for(resultado2.getString("nomefant_for"));
           listaproduto.add(p);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaproduto;
  }
}
