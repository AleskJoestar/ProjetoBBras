/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Caixa;
import mapeamento.Cliente;
import utilitario.Conectar;
/**
 *
 * @author T-Gamer
 */
public class CaixaDao {
    

    public List<Caixa> receitas(String nome){
   Connection con = Conectar.getconectar();
   List <Caixa> listaReceita  = new ArrayList<>();
   String sql = "select sum(receitas_cai) as Receitas from caixareceitas";
   String sql2 = "select hora_cai,data_cai from caixareceitas";
   
   try(PreparedStatement stm = con.prepareStatement(sql);
           PreparedStatement stm2 = con.prepareStatement(sql2)
           ){
       ResultSet resultado = stm.executeQuery();
       ResultSet resultado2 = stm2.executeQuery();

       while (resultado.next()& resultado2.next()) {
           Caixa c = new Caixa();
           c.setReceitas_cai(resultado.getDouble("Receitas"));
           c.setData_cai(resultado2.getString("data_cai"));
           c.setHora_cai(resultado2.getString("hora_cai"));
           listaReceita.add(c);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaReceita;
  }
     public List<Caixa> despesas(String nome){
   Connection con = Conectar.getconectar();
   List <Caixa> listadespesas  = new ArrayList<>();
   String sql = "select sum(despesas_cai) as Despesas from caixadespesas";
   String sql2 = "select hora_caiD,data_caiD from caixadespesas";
   
   try(PreparedStatement stm = con.prepareStatement(sql);
           PreparedStatement stm2 = con.prepareStatement(sql2)
           ){
       ResultSet resultado = stm.executeQuery();
       ResultSet resultado2 = stm2.executeQuery();

       while (resultado.next()& resultado2.next()) {
           Caixa c = new Caixa();
           c.setDespesas_cai(resultado.getDouble("Despesas"));
           c.setData_cai(resultado2.getString("data_caiD"));
           c.setHora_cai(resultado2.getString("hora_caiD"));
           listadespesas.add(c);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listadespesas;
  }
    
    


    /* public List<Caixa> receitasData(String data){
   Connection con = Conectar.getconectar();
   List <Caixa> listaReceita  = new ArrayList<>();
   String sql = "select sum(receitas_cai) as Receitas from caixareceitas where data_cai like'"+data+"%'";
   
   try(PreparedStatement stm = con.prepareStatement(sql)
           ){
       ResultSet resultado = stm.executeQuery();

       while (resultado.next()) {
           Caixa c = new Caixa();
           c.setReceitas_cai(resultado.getDouble("Receitas"));
  
           listaReceita.add(c);
       }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaReceita;
  }

*/
}