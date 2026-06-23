
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.ReceberContas;
import utilitario.Conectar;

public class ReceberContasDao {
    
    public void salvar(ReceberContas r) {
    Connection con = Conectar.getconectar();
    String sql = "INSERT into receber (valor_rec ,hora_rec, dia_rec, cod_ven) values (?,?,?,?) ";
    
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setFloat(1, r.getValor_rec());
         stm.setString(2, r.getHora_rec());
         stm.setString(3, r.getDia_rec());
         stm.setInt(4, r.getCod_ven());
         stm.execute();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
     
    }
    
     public void atualizar(ReceberContas r){
    Connection con = Conectar.getconectar();
    String sql = "update  receber set valor_rec=?, hora_rec=?, dia_rec=?, cod_ven=? where cod_rec=?";
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setFloat(1, r.getValor_rec());
         stm.setString(2, r.getHora_rec());
         stm.setString(3, r.getDia_rec());
         stm.setInt(4, r.getCod_ven());
         stm.setInt(5, r.getCod_rec());
         stm.executeUpdate();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
    
    
    
    
    
    
    }

     
      public void deletar(ReceberContas r){
    Connection con = Conectar.getconectar();
    String sql = "delete from receber where cod_rec=?";
        int op = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR "+ r.getCod_rec()+ " ?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, r.getCod_rec()); 
                stm.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                stm.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error" + ex.getMessage());
            }
        }
            
}
      public List<ReceberContas> listarTodos(String nome){
   Connection con = Conectar.getconectar();
   List <ReceberContas> listaContas  = new ArrayList<>();
   String sql = "Select * from receber";
   try(PreparedStatement stm = con.prepareStatement(sql)){
       ResultSet resultado = stm.executeQuery();
       while (resultado.next()) {
           ReceberContas r = new ReceberContas();
           r.setCod_rec(resultado.getInt("cod_rec"));
           r.setHora_rec(resultado.getString("hora_rec"));
           r.setDia_rec(resultado.getString("dia_rec"));
           r.setCod_ven(resultado.getInt("cod_ven"));
           listaContas.add(r);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaContas;
  }
    
}
