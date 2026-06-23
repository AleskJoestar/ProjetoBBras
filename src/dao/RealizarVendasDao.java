
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.RealizarVendas;
import utilitario.Conectar;



public class RealizarVendasDao {

public void deletar(RealizarVendas p){
    Connection con = Conectar.getconectar();
    String sql = "delete from venda where cod_ven=?";
        int op = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR "+ p.getCod_ven()+ " ?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, p.getCod_ven()); 
                stm.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                stm.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error" + ex.getMessage());
            }
        }
            
}
    
    
    


    
    public void salvar(RealizarVendas r) {
    Connection con = Conectar.getconectar();
    String sql = "INSERT into venda (valor_ven ,hora_ven, data_ven, cod_cli_fk, cod_fun_fk,cod_prod_fk,quant_ven,cod_vendas_fk) values (?,?,?,?,?,?,?,?) ";
    
    
     try (PreparedStatement stm = con.prepareStatement(sql); ) {
         stm.setFloat(1, r.getValor_ven());
         stm.setString(2, r.getHora_ven());
         stm.setString(3, r.getData_ven());
         stm.setInt(4, r.getCod_cli());
         stm.setInt(5, r.getCod_fun());
         stm.setInt(6, r.getCod_prod());
         stm.setInt(7, r.getQuant_ven());
         stm.setInt(8, r.getCod_vendas_fk());
         stm.execute();
      
         stm.close();
       
         con.close();
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
}
       public void editar(RealizarVendas r) {
    Connection con = Conectar.getconectar();
    String sql = "update  venda set valor_ven=?, hora_ven=?, data_ven=?, quant_ven=?  where cod_ven=? ";
    
    
     try (PreparedStatement stm = con.prepareStatement(sql); ) {
         stm.setFloat(1, r.getValor_ven());
         stm.setString(2, r.getHora_ven());
         stm.setString(3, r.getData_ven());
         stm.setInt(4, r.getQuant_ven());
         stm.setInt(5, r.getCod_ven());
         stm.execute();
      
         stm.close();
       
         con.close();
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
}
    
    
    
    public List<RealizarVendas> listarTodos(String nome){
   Connection con = Conectar.getconectar();
   List <RealizarVendas> listaVendas  = new ArrayList<>();
   String sql = "Select * from venda";
   String sql2 = "select nome_prod from venda,produto where cod_prod_fk = cod_prod order by cod_ven";
   String sql3 = "select nome_fun from venda,funcionario where cod_fun_fk = cod_fun";
   String sql4 = "select nome_cli from venda,cliente where cod_cli_fk = cod_cli";

   try(PreparedStatement stm = con.prepareStatement(sql) ; PreparedStatement stm2 = con.prepareStatement(sql2);
       PreparedStatement stm3 = con.prepareStatement(sql3) ; PreparedStatement stm4 = con.prepareStatement(sql4)  ){
       ResultSet resultado = stm.executeQuery();
       ResultSet resultado2 = stm2.executeQuery();
       ResultSet resultado3 = stm3.executeQuery();
       ResultSet resultado4 = stm4.executeQuery();
       
       
       while (resultado.next() & resultado2.next()& resultado3.next()& resultado4.next()) {
           RealizarVendas r = new RealizarVendas();
           r.setCod_ven(resultado.getInt("cod_ven"));
           r.setCod_prod(resultado.getInt("cod_prod_fk"));
           r.setValor_ven(resultado.getFloat("valor_ven"));
           r.setHora_ven(resultado.getString("hora_ven"));
           r.setData_ven(resultado.getString("data_ven"));
           
           r.setCod_cli(resultado.getInt("cod_cli_fk"));
           r.setCod_fun(resultado.getInt("cod_fun_fk"));
           
           r.setNome_prod(resultado2.getString("nome_prod"));
           r.setNome_cli(resultado4.getString("nome_cli"));
           r.setNome_fun(resultado3.getString("nome_fun"));
           r.setQuant_ven(resultado.getInt("quant_ven"));
           listaVendas.add(r);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaVendas;
  }
    
     public List<RealizarVendas> listarPorVenda(int nome){
   Connection con = Conectar.getconectar();
   List <RealizarVendas> listaVendas  = new ArrayList<>();
   String sql = "Select * from venda where cod_vendas_fk="+nome +" order by cod_ven";
   String sql2 = "select nome_prod from venda,produto where (cod_prod_fk = cod_prod)  ";
   String sql3 = "select nome_fun from venda,funcionario where (cod_fun_fk = cod_fun) ";
   String sql4 = "select nome_cli from venda,cliente where (cod_cli_fk = cod_cli) ";

   try(PreparedStatement stm = con.prepareStatement(sql) ; PreparedStatement stm2 = con.prepareStatement(sql2);
         PreparedStatement stm3 = con.prepareStatement(sql3) ; PreparedStatement stm4 = con.prepareStatement(sql4)  ){
       RealizarVendas r = new RealizarVendas();
  
       ResultSet resultado = stm.executeQuery();
       ResultSet resultado2 = stm2.executeQuery();
       ResultSet resultado3 = stm3.executeQuery();
       ResultSet resultado4 = stm4.executeQuery();

       
       while (resultado.next() & resultado2.next()& resultado3.next()& resultado4.next()) {
           r.setCod_ven(resultado.getInt("cod_ven"));
           r.setCod_prod(resultado.getInt("cod_prod_fk"));
           r.setValor_ven(resultado.getFloat("valor_ven"));
           r.setHora_ven(resultado.getString("hora_ven"));
           r.setData_ven(resultado.getString("data_ven"));
           r.setCod_vendas_fk(resultado.getInt("cod_vendas_fk"));
           r.setCod_cli(resultado.getInt("cod_cli_fk"));
           r.setCod_fun(resultado.getInt("cod_fun_fk"));
           r.setNome_prod(resultado2.getString("nome_prod"));
           r.setNome_cli(resultado4.getString("nome_cli"));
           r.setNome_fun(resultado3.getString("nome_fun"));
           r.setQuant_ven(resultado.getInt("quant_ven"));
           listaVendas.add(r);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaVendas;
  }
    public List<RealizarVendas> listaTodosprod(String nome){
   Connection con = Conectar.getconectar();
   List <RealizarVendas> listarVendas  = new ArrayList<>();
   String sql = "Select * from venda";
   try(PreparedStatement stm = con.prepareStatement(sql)){
       ResultSet resultado = stm.executeQuery();
       while (resultado.next()) {
           RealizarVendas v = new RealizarVendas();
           v.setCod_prod(resultado.getInt("cod_prod"));
           listarVendas.add(v);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listarVendas;
  }
    
}


