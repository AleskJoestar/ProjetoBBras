
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Mercadorias;
import mapeamento.RealizarVendas;
import utilitario.Conectar;

public class MercadoriasDao {
    public void salvar(Mercadorias d) {
    Connection con = Conectar.getconectar();
String sql = "INSERT into Mercadorias (quant_merc,data_merc,hora_merc, cod_prod_fk,cod_fun_fk) values (?,?,?,?,?) ";
    
     try (PreparedStatement stm = con.prepareStatement(sql)) {
         stm.setInt(1, d.getQuant_merc());
         stm.setString(2, d.getData_merc());
         stm.setString(3, d.getHora_merc());
         stm.setInt(4, d.getCod_prod_fk());
         stm.setInt(5, d.getCod_fun_fk());
         stm.execute();
         stm.close();
         con.close();
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
     }
}
    
     public void atualizar(Mercadorias d){
    Connection con = Conectar.getconectar();
    //String sql = "UPDATE Mercadorias SET quant_merc=?, data_merc=?, hora_merc=?, cod_prod_fk=?, cod_fun_fk=? WHERE cod_merc=?";
    String sql = "UPDATE Mercadorias SET quant_merc=?, data_merc=?, cod_prod_fk=?, cod_fun_fk=? WHERE cod_merc=?";
    System.out.println("" + d.getQuant_merc());
    System.out.println("" + d.getCod_fun_fk());
    System.out.println("" + d.getCod_merc());
    System.out.println("" + d.getData_merc());


     try (PreparedStatement stm = con.prepareStatement(sql)) {
       
         stm.setInt(1, d.getQuant_merc());
         stm.setString(2, d.getData_merc());
         stm.setInt(3, d.getCod_prod_fk());
         stm.setInt(4, d.getCod_fun_fk());
         stm.setInt(5, d.getCod_merc());
         
    
         stm.executeUpdate();         
         
         stm.close();
        
         con.close();
         JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "error"+ex);
     }
    
}
     public void deletar(Mercadorias d){
    Connection con = Conectar.getconectar();
    String sql = "delete from Mercadorias where cod_merc=?";
        int op = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR "+ d.getCod_merc()+ " ?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, d.getCod_merc()); 
                stm.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                stm.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error" + ex.getMessage());
            }
        }
            
}
      public List<Mercadorias> listarTodos(String nome){
   Connection con = Conectar.getconectar();
   List <Mercadorias> listaMercadorias  = new ArrayList<>();
   String sql = "Select * from Mercadorias";
   String sql2 = "select nome_prod from mercadorias,produto where cod_prod_fk = cod_prod";
   String sql3 = "select nome_fun from mercadorias,funcionario where cod_fun_fk = cod_fun";

   try(PreparedStatement stm = con.prepareStatement(sql);PreparedStatement stm2 = con.prepareStatement(sql2);PreparedStatement stm3 = con.prepareStatement(sql3)){
       ResultSet resultado = stm.executeQuery();
       ResultSet resultado2 = stm2.executeQuery();
       ResultSet resultado3 = stm3.executeQuery();
      while(resultado.next() &resultado2.next() & resultado3.next()){
         Mercadorias d = new Mercadorias();
           
           d.setCod_merc(resultado.getInt("cod_merc"));
           d.setQuant_merc(resultado.getInt("quant_merc"));
           d.setData_merc(resultado.getString("data_merc"));
           d.setHora_merc(resultado.getString("hora_merc"));
           d.setCod_prod_fk(resultado.getInt("cod_prod_fk"));
           d.setCod_fun_fk(resultado.getInt("cod_fun_fk"));  
           d.setNome_prod(resultado2.getString("nome_prod"));
           d.setNome_fun(resultado3.getString("nome_fun"));

       
     listaMercadorias.add(d);
   }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaMercadorias;
  }
      
      
      
      
      
      
      
      /*
      
       public List<Mercadorias> chamarQuant(int nome){
                    Mercadorias d = new Mercadorias();
                    RealizarVendas v = new RealizarVendas();
   Connection con = Conectar.getconectar();
   List <Mercadorias> listaMercadorias  = new ArrayList<>();
   String sql = "select * from mercadorias where mercadorias.cod_prod_fk ="+nome+"";

   try(PreparedStatement stm = con.prepareStatement(sql)){
     
       ResultSet resultado = stm.executeQuery();
      while(resultado.next() ){
           
           d.setCod_merc(resultado.getInt("cod_merc"));
           d.setQuant_merc(resultado.getInt("quant_merc"));
       
           d.setCod_prod_fk(resultado.getInt("cod_prod_fk"));
         

       
     listaMercadorias.add(d);
   }
          stm.close();
          con.close();
      
   }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex.getMessage());
  }
   return listaMercadorias;
  }
      
      
  
   
      
      */
      
       
      
    
}
      
      
      

