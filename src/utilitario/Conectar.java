package utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conectar {

    // FIXED: adicionado ?useSSL=false&serverTimezone=America/Porto_Velho
    // para compatibilidade com MySQL 8.x (driver moderno)
    private static final String url  = "jdbc:mysql://localhost/Bbras_willamy_alessandro_willian"
                                     + "?useSSL=false&serverTimezone=America/Porto_Velho&allowPublicKeyRetrieval=true";
    private static final String nome = "root";
    private static final String senha = "root";

    public static Connection getconectar() {
        Connection con = null;
        try {
            // FIXED: classe de driver atualizada para MySQL 8.x
            // Original era "com.mysql.jdbc.Driver" (deprecated desde MySQL 6+)
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, nome, senha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "FALHA NA CONEXÃO: " + ex.getMessage());
        }
        return con;
    }
}
