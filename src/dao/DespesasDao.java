
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Despesas;
import utilitario.Conectar;

public class DespesasDao {
    public void salvar(Despesas d) {
    Connection con = Conectar.getconectar();
String sql = "INSERT into Despesas (valor_desp, desc_desp,data_Desp,hora_desp) values (?,?,?,?) ";
    
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setDouble(1, d.getValor_desp());
         stm.setString(2, d.getDesc_desp());
         stm.setString(3, d.getData_Desp());
         stm.setString(4, d.getHora_desp());
         stm.execute();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
}
    
     public void atualizar(Despesas d){
    Connection con = Conectar.getconectar();
    String sql = "update Despesas set valor_desp, desc_desp,data_Desp,hora_desp where cod_desp=?";
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setDouble(1, d.getValor_desp());
         stm.setString(2, d.getDesc_desp());
         stm.setString(3, d.getData_Desp());
         stm.setString(4, d.getHora_desp());
         stm.setInt(5, d.getCod_desp());
      
         stm.executeUpdate();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
    
}
     public void deletar(Despesas d){
    Connection con = Conectar.getconectar();
    String sql = "delete from Despesas where cod_desp=?";
        int op = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR "+ d.getCod_desp()+ " ?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, d.getCod_desp()); 
                stm.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                stm.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error" + ex.getMessage());
            }
        }
            

}
      public List<Despesas> listarTodos(String nome){
   Connection con = Conectar.getconectar();
   List <Despesas> listaDespesas  = new ArrayList<>();
   String sql = "Select * from Despesas";
   try(PreparedStatement stm = con.prepareStatement(sql)){
       ResultSet resultado = stm.executeQuery();
       while (resultado.next()) {
           Despesas d = new Despesas();
           d.setCod_desp(resultado.getInt("cod_desp"));
           d.setValor_desp(resultado.getDouble("valor_desp"));
           d.setDesc_desp(resultado.getString("desc_desp"));
           d.setData_Desp(resultado.getString("data_desp"));
           d.setHora_desp(resultado.getString("hora_desp"));
           
           listaDespesas.add(d);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaDespesas;
  }
}
