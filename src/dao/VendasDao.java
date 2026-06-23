
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Cliente;
import mapeamento.VendasTotais;
import utilitario.Conectar;



public class VendasDao {
    
    public void salvar(VendasTotais r) {
    Connection con = Conectar.getconectar();
    String sql = "INSERT into vendas (valortotal_vendas, valorpago_vendas, valortroco_vendas,valordesconto_vendas,cod_cli_fk ,cod_fun_fk) values (?,?,?,?,?,?) ";
    
    
     try (PreparedStatement stm = con.prepareStatement(sql); ) {
         stm.setDouble(1, r.getValortotal_vendas());
         stm.setDouble(2, r.getValorpago_vendas());
         stm.setDouble(3, r.getValortroco_vendas());
         stm.setDouble(4, r.getValordesconto_vendas());
         stm.setInt(5, r.getCod_cli_fk());
         stm.setInt(6, r.getCod_fun_fk());
         stm.execute();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
}
    
    public void deletar(VendasTotais p){
    Connection con = Conectar.getconectar();
    String sql = "delete from vendas where cod_vendas=?";
        int op = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR "+ p.getCod_vendas()+ " ?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, p.getCod_vendas()); 
                stm.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                stm.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error" + ex.getMessage());
            }
        }
            
}
    
    
    public void editar(VendasTotais v){
    Connection con = Conectar.getconectar();
String sql = "update  vendas set valortotal_vendas=?, valorpago_vendas=?, valortroco_vendas=?, valordesconto_vendas=? where cod_vendas=?";
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setDouble(1, v.getValortotal_vendas());
         stm.setDouble(2, v.getValorpago_vendas());
         stm.setDouble(3, v.getValortroco_vendas());
         stm.setDouble(4, v.getValordesconto_vendas());
         stm.setInt(5, v.getCod_vendas());
         
         stm.executeUpdate();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
         System.out.println("error"+ex.getMessage());
     }
    
}
    public List<VendasTotais> listarTodos(String nome){
   Connection con = Conectar.getconectar();
   List <VendasTotais> listaVendas  = new ArrayList<>();
   String sql = "Select * from vendas ";
   String sql3 = "select nome_fun from venda,funcionario where cod_fun_fk = cod_fun";
   String sql4 = "select nome_cli from venda,cliente where cod_cli_fk = cod_cli";

   try(PreparedStatement stm = con.prepareStatement(sql) ;
         PreparedStatement stm3 = con.prepareStatement(sql3) ; PreparedStatement stm4 = con.prepareStatement(sql4)  ){
       ResultSet resultado = stm.executeQuery();
       ResultSet resultado3 = stm3.executeQuery();
       ResultSet resultado4 = stm4.executeQuery();

       
       while (resultado.next() & resultado3.next()& resultado4.next()) {
           VendasTotais r = new VendasTotais();
           r.setCod_vendas(resultado.getInt("cod_vendas"));
           r.setValortotal_vendas(resultado.getDouble("valortotal_vendas"));
           r.setValorpago_vendas(resultado.getDouble("valorpago_vendas"));
           r.setValortroco_vendas(resultado.getDouble("valortroco_vendas"));
           r.setValordesconto_vendas(resultado.getDouble("valordesconto_vendas"));
           
           r.setCod_cli_fk(resultado.getInt("cod_cli_fk"));
           r.setCod_fun_fk(resultado.getInt("cod_fun_fk"));
           
           
           
           r.setNome_cli(resultado4.getString("nome_cli"));
           r.setNome_fun(resultado3.getString("nome_fun"));
           listaVendas.add(r);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaVendas;
  }
       
    
    
    
    
    
     
}


