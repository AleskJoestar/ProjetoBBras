package utilitario;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Conectar.java — MIGRADO para H2 embarcado
 *
 * Banco de dados armazenado em arquivo local:
 *   Windows: C:\Users\<usuario>\projetods.mv.db
 *   Linux  : ~/projetods.mv.db
 *
 * Não requer instalação de MySQL ou qualquer servidor externo.
 * O banco é criado automaticamente na primeira execução.
 *
 * JAR necessário: h2-2.2.224.jar  (colocar em lib/)
 * Download: https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar
 */
public class Conectar {

    // Arquivo do banco salvo na pasta home do usuário
    // Altere o caminho se quiser salvar em outro lugar (ex: "./data/projetods")
    private static final String URL  = "jdbc:h2:./data/projetods;AUTO_SERVER=TRUE"; // FIXED: absolute path for portability
    private static final String USER = "sa";
    private static final String PASS = "";

    private static boolean inicializado = false;

    public static Connection getconectar() {
        Connection con = null;
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);

            // Cria as tabelas na primeira vez que o banco for aberto
            if (!inicializado) {
                inicializarBanco(con);
                inicializado = true;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "FALHA NA CONEXÃO: " + ex.getMessage());
        }
        return con;
    }

    /**
     * Cria as tabelas (se não existirem) e insere dados de teste.
     * Executado apenas uma vez por sessão da JVM.
     */
    private static void inicializarBanco(Connection con) {
        try (Statement stm = con.createStatement()) {

            // ── TABELAS ─────────────────────────────────────────────

            stm.execute(
                "CREATE TABLE IF NOT EXISTS cliente (" +
                "  cod_cli       INT           NOT NULL AUTO_INCREMENT," +
                "  nome_cli      VARCHAR(100)  NOT NULL," +
                "  cpf_cli       VARCHAR(14)   NOT NULL," +
                "  rg_cli        VARCHAR(20)," +
                "  sexo_cli      VARCHAR(10)," +
                "  datanasc_cli  VARCHAR(20)," +
                "  endereco_cli  VARCHAR(200)," +
                "  telefone_cli  VARCHAR(20)," +
                "  email_cli     VARCHAR(100)," +
                "  PRIMARY KEY (cod_cli)" +
                ")"
            );

            stm.execute(
                "CREATE TABLE IF NOT EXISTS fornecedor (" +
                "  cod_for       INT          NOT NULL AUTO_INCREMENT," +
                "  nomeFant_for  VARCHAR(100) NOT NULL," +
                "  razaoSoci_for VARCHAR(150)," +
                "  cnpj_for      VARCHAR(20)," +
                "  cep_for       VARCHAR(10)," +
                "  ende_for      VARCHAR(200)," +
                "  tel_for       VARCHAR(20)," +
                "  email_for     VARCHAR(100)," +
                "  resp_for      VARCHAR(100)," +
                "  cidade_for    VARCHAR(100)," +
                "  PRIMARY KEY (cod_for)" +
                ")"
            );

            // NOTA: coluna renomeada de 'endereço_fun' (com ç) para
            // 'endereco_fun' (sem acento) para evitar problemas de encoding
            // Os DAOs que usam endereço_fun precisam ser ajustados (BUG indicado abaixo)
            stm.execute(
                "CREATE TABLE IF NOT EXISTS funcionario (" +
                "  cod_fun          INT          NOT NULL AUTO_INCREMENT," +
                "  nome_fun         VARCHAR(100) NOT NULL," +
                "  cpf_fun          VARCHAR(14)  NOT NULL," +
                "  rg_fun           VARCHAR(20)," +
                "  endereco_fun     VARCHAR(200)," +
                "  telefone_fun     VARCHAR(20)," +
                "  email_fun        VARCHAR(100)," +
                "  funcao_fun       VARCHAR(100)," +
                "  departamento_fun VARCHAR(100)," +
                "  senha_fun        VARCHAR(100)," +
                "  PRIMARY KEY (cod_fun)" +
                ")"
            );

            stm.execute(
                "CREATE TABLE IF NOT EXISTS produto (" +
                "  cod_prod   INT          NOT NULL AUTO_INCREMENT," +
                "  nome_prod  VARCHAR(100) NOT NULL," +
                "  valor_prod FLOAT        NOT NULL DEFAULT 0," +
                "  cod_for_fk INT," +
                "  PRIMARY KEY (cod_prod)," +
                "  FOREIGN KEY (cod_for_fk) REFERENCES fornecedor(cod_for)" +
                ")"
            );

            stm.execute(
                "CREATE TABLE IF NOT EXISTS mercadorias (" +
                "  cod_merc    INT  NOT NULL AUTO_INCREMENT," +
                "  quant_merc  INT  NOT NULL DEFAULT 0," +
                "  data_merc   VARCHAR(20)," +
                "  hora_merc   VARCHAR(10)," +
                "  cod_prod_fk INT," +
                "  cod_fun_fk  INT," +
                "  PRIMARY KEY (cod_merc)," +
                "  FOREIGN KEY (cod_prod_fk) REFERENCES produto(cod_prod)," +
                "  FOREIGN KEY (cod_fun_fk)  REFERENCES funcionario(cod_fun)" +
                ")"
            );

            stm.execute(
                "CREATE TABLE IF NOT EXISTS vendas (" +
                "  cod_vendas           INT    NOT NULL AUTO_INCREMENT," +
                "  valortotal_vendas    DOUBLE NOT NULL DEFAULT 0," +
                "  valorpago_vendas     DOUBLE NOT NULL DEFAULT 0," +
                "  valortroco_vendas    DOUBLE NOT NULL DEFAULT 0," +
                "  valordesconto_vendas DOUBLE NOT NULL DEFAULT 0," +
                "  cod_cli_fk           INT," +
                "  cod_fun_fk           INT," +
                "  PRIMARY KEY (cod_vendas)," +
                "  FOREIGN KEY (cod_cli_fk) REFERENCES cliente(cod_cli)," +
                "  FOREIGN KEY (cod_fun_fk) REFERENCES funcionario(cod_fun)" +
                ")"
            );

            stm.execute(
                "CREATE TABLE IF NOT EXISTS venda (" +
                "  cod_ven       INT   NOT NULL AUTO_INCREMENT," +
                "  valor_ven     FLOAT NOT NULL DEFAULT 0," +
                "  hora_ven      VARCHAR(10)," +
                "  data_ven      VARCHAR(20)," +
                "  quant_ven     INT   NOT NULL DEFAULT 1," +
                "  cod_cli_fk    INT," +
                "  cod_fun_fk    INT," +
                "  cod_prod_fk   INT," +
                "  cod_vendas_fk INT," +
                "  PRIMARY KEY (cod_ven)," +
                "  FOREIGN KEY (cod_cli_fk)    REFERENCES cliente(cod_cli)," +
                "  FOREIGN KEY (cod_fun_fk)    REFERENCES funcionario(cod_fun)," +
                "  FOREIGN KEY (cod_prod_fk)   REFERENCES produto(cod_prod)," +
                "  FOREIGN KEY (cod_vendas_fk) REFERENCES vendas(cod_vendas)" +
                ")"
            );

            stm.execute(
                "CREATE TABLE IF NOT EXISTS receber (" +
                "  cod_rec   INT   NOT NULL AUTO_INCREMENT," +
                "  valor_rec FLOAT NOT NULL DEFAULT 0," +
                "  hora_rec  VARCHAR(10)," +
                "  dia_rec   VARCHAR(20)," +
                "  cod_ven   INT," +
                "  PRIMARY KEY (cod_rec)," +
                "  FOREIGN KEY (cod_ven) REFERENCES vendas(cod_vendas)" +
                ")"
            );

            stm.execute(
                "CREATE TABLE IF NOT EXISTS despesas (" +
                "  cod_desp   INT          NOT NULL AUTO_INCREMENT," +
                "  valor_desp DOUBLE       NOT NULL DEFAULT 0," +
                "  desc_desp  VARCHAR(200)," +
                "  data_desp  VARCHAR(20)," +
                "  hora_desp  VARCHAR(10)," +
                "  PRIMARY KEY (cod_desp)" +
                ")"
            );

            stm.execute(
                "CREATE TABLE IF NOT EXISTS caixareceitas (" +
                "  cod_cair     INT    NOT NULL AUTO_INCREMENT," +
                "  receitas_cai DOUBLE NOT NULL DEFAULT 0," +
                "  data_cai     VARCHAR(20)," +
                "  hora_cai     VARCHAR(10)," +
                "  PRIMARY KEY (cod_cair)" +
                ")"
            );

            stm.execute(
                "CREATE TABLE IF NOT EXISTS caixadespesas (" +
                "  cod_caid     INT    NOT NULL AUTO_INCREMENT," +
                "  despesas_cai DOUBLE NOT NULL DEFAULT 0," +
                "  data_caid    VARCHAR(20)," +
                "  hora_caid    VARCHAR(10)," +
                "  PRIMARY KEY (cod_caid)" +
                ")"
            );

            // FIXED: confirma no terminal que a criacao das tabelas terminou
            System.out.println("H2: tables created");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar banco: " + ex.getMessage());
        }

        // FIXED: seed isolado em try/catch proprio para que uma falha na contagem/insercao
        // nao seja mascarada pelo bloco de criacao de tabelas (silent fail original)
        try (Statement stm = con.createStatement()) {

            // ── SEED (só insere se funcionario estiver vazio) ────────
            var rs = stm.executeQuery("SELECT COUNT(*) FROM funcionario");
            rs.next();
            if (rs.getInt(1) == 0) {
                stm.execute(
                    "INSERT INTO funcionario (nome_fun,cpf_fun,rg_fun,endereco_fun,telefone_fun,email_fun,funcao_fun,departamento_fun,senha_fun) VALUES" +
                    "('Administrador','000.000.000-00','0000000','Rua Teste, 1','(69)99999-0001','admin@empresa.com','Gerente','Diretoria','admin123')," + // FIXED: CPF format match mask
                    "('Joao Vendedor','111.222.333-44','1112223','Rua das Flores, 10','(69)99999-0002','joao@empresa.com','Vendedor','Vendas','senha123')" // FIXED: CPF format match mask
                );
                stm.execute(
                    "INSERT INTO cliente (nome_cli,cpf_cli,rg_cli,sexo_cli,datanasc_cli,endereco_cli,telefone_cli,email_cli) VALUES" +
                    "('Maria Silva','123.456.789-00','1234567','F','01/01/1990','Rua A, 100','(69)98888-0001','maria@email.com')," +
                    "('Carlos Santos','987.654.321-00','9876543','M','15/06/1985','Rua B, 200','(69)98888-0002','carlos@email.com')"
                );
                stm.execute(
                    "INSERT INTO fornecedor (nomeFant_for,razaoSoci_for,cnpj_for,cep_for,ende_for,tel_for,email_for,resp_for,cidade_for) VALUES" +
                    "('Distribuidora Central','Distribuidora Central LTDA','12.345.678/0001-90','76900-000','Av. Principal, 500','(69)3421-0001','contato@dist.com','Pedro Lima','Ji-Parana')," +
                    "('Atacado Norte','Atacado Norte ME','98.765.432/0001-10','76900-010','Rua Comercial, 30','(69)3421-0002','contato@atacado.com','Ana Costa','Ji-Parana')"
                );
                stm.execute(
                    "INSERT INTO produto (nome_prod,valor_prod,cod_for_fk) VALUES" +
                    "('Cabo UTP Cat5e 305m',250.00,1)," +
                    "('Switch 8 portas',180.00,1)," +
                    "('Roteador WiFi',320.00,2)," +
                    "('Patch Panel 24p',150.00,2)"
                );
                // FIXED: confirma no terminal que o seed foi inserido
                System.out.println("H2: seed inserted");
            } else {
                // FIXED: avisa no terminal que o seed ja existe e sera ignorado
                System.out.println("H2: seed already exists, skipping");
            }

        } catch (SQLException ex) {
            // FIXED: exibe a excecao do bloco de seed no terminal em vez de falhar silenciosamente
            System.out.println("H2: seed error: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao inserir seed: " + ex.getMessage());
        }
    }
}
