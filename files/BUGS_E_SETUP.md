# RELATÓRIO DE BUGS + GUIA DE SETUP
# Projeto: PROJETODS-MASTER
# Análise feita via DAOs — sem executar o código

==============================================================
## 1. SETUP — PASSO A PASSO
==============================================================

### 1.1 — Instalar MySQL (se não tiver)
- Download: https://dev.mysql.com/downloads/installer/
- Durante instalação: usuário root / senha root (igual ao Conectar.java)
- Ou use XAMPP (já inclui MySQL)

### 1.2 — Criar o banco
No MySQL Workbench, HeidiSQL, DBeaver ou terminal:

    mysql -u root -p < schema.sql
    mysql -u root -p < seed.sql

Ou cole o conteúdo dos arquivos direto no Workbench e execute.

### 1.3 — Baixar o JAR do MySQL Connector
URL direta (MySQL Connector/J 8.0.33):
https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar

Salve em:  PROJETODS-MASTER/lib/mysql-connector-java-8.0.33.jar

### 1.4 — Baixar JARs do JasperReports
URL base: https://sourceforge.net/projects/jasperreports/files/jasperreports/
Versão recomendada: 6.20.0

JARs mínimos necessários (colocar em lib/):
  - jasperreports-6.20.0.jar
  - commons-beanutils-1.9.4.jar
  - commons-collections-3.2.2.jar  (ou commons-collections4-4.4.jar)
  - commons-digester-2.1.jar
  - commons-logging-1.2.jar
  - itext-2.1.7.js6.jar
  - poi-5.2.3.jar  (só se exportar Excel)
  - slf4j-api-1.7.36.jar
  - slf4j-simple-1.7.36.jar

Alternativa mais fácil: baixar o ZIP completo do JasperReports e
copiar toda a pasta lib/ dele para lib/ do seu projeto.

### 1.5 — Configurar o VSCode
Criar/atualizar  .vscode/settings.json:

{
    "java.project.referencedLibraries": [
        "lib/**/*.jar"
    ]
}

### 1.6 — Executar o projeto
Entry point: teste.TesteConectar  (método main)
Ele abre: formulario.Login

No VSCode: botão "Run" no TesteConectar.java
Ou: botão direito → Run Java

Login padrão (seed.sql):
  CPF   : 00000000000
  Senha : admin123

==============================================================
## 2. BUGS ENCONTRADOS NOS DAOs
==============================================================

### BUG 1 — Conectar.java [CRÍTICO]
Arquivo: utilitario/Conectar.java
Linha: Class.forName("com.mysql.jdbc.Driver");
Problema: driver deprecated, não existe mais no connector 8.x
Fix: trocar para "com.mysql.cj.jdbc.Driver"
      + adicionar ?useSSL=false&serverTimezone=America/Porto_Velho na URL
Arquivo corrigido: Conectar.java (fornecido)

---

### BUG 2 — DespesasDao.java [MÉDIO — SQL malformado]
Arquivo: dao/DespesasDao.java — método atualizar()
Linha: "update Despesas set valor_desp, desc_desp,data_Desp,hora_desp where cod_desp=?"
Problema: SQL inválido, faltam os "=?" para cada campo
Fix:
  "update Despesas set valor_desp=?, desc_desp=?, data_Desp=?, hora_desp=? where cod_desp=?"

---

### BUG 3 — FuncionarioDao.java [MÉDIO — nome de coluna com acento]
Arquivo: dao/FuncionarioDao.java
Coluna: endereço_fun  (tem ç)
Problema: pode causar erro de encoding dependendo da configuração do MySQL/JVM
Fix: criar tabela com backticks: `endereço_fun`  (já feito no schema.sql)
     E garantir que o MySQL rode com CHARACTER SET utf8mb4

---

### BUG 4 — VendasDao.java / RealizarVendasDao.java [LÓGICA — múltiplos ResultSet]
Arquivo: dao/VendasDao.java — método listarTodos()
         dao/RealizarVendasDao.java — método listarTodos()
Problema: usa múltiplos ResultSets iterados em paralelo com &
          Se as tabelas tiverem quantidades diferentes de linhas, o loop
          para na menor — dados ficam incompletos silenciosamente
Impacto: listagens podem mostrar menos registros do que existem
Fix ideal: usar JOINs no SQL.
Fix rápido (sem refatorar): não é necessário para rodar, só afeta dados exibidos

---

### BUG 5 — RealizarVendasDao.java [LÓGICO — coluna errada]
Arquivo: dao/RealizarVendasDao.java — método listaTodosprod()
Linha: v.setCod_prod(resultado.getInt("cod_prod"));
Problema: coluna na tabela "venda" chama "cod_prod_fk", não "cod_prod"
Fix: resultado.getInt("cod_prod_fk")

---

### BUG 6 — CaixaDao.java [LÓGICO — SUM com cursor separado]
Arquivo: dao/CaixaDao.java
Problema: faz SUM numa query e busca hora/data em outra query separada
          iterando em paralelo — SUM retorna 1 linha, a outra retorna N
          Resultado: dados de hora/data do primeiro registro apenas
Impacto: cosmético, o total financeiro está correto
Fix: não é bloqueante para rodar o sistema

---

### BUG 7 — Imports JasperReports [CRÍTICO — impede compilação]
Arquivos: formulario/FormRVendas.java e possivelmente outros
Problema: JARs do jasperreports ausentes no classpath
Fix: baixar JARs conforme item 1.4 acima e adicionar em lib/

==============================================================
## 3. DIAGRAMA DE TABELAS (resumo)
==============================================================

fornecedor ──< produto ──< mercadorias (entrada estoque)
                   │
                   └──< venda (item) >── vendas (cabeçalho) >── receber
                              │                    │
                           cliente              cliente
                           funcionario          funcionario

despesas       (independente)
caixareceitas  (independente)
caixadespesas  (independente)

==============================================================
## 4. NOTAS FINAIS
==============================================================

- O projeto usa ANT (build.xml) — não converta para Maven sem necessidade
- Arquivos .form são do NetBeans GUI Builder (Matisse)
  O VSCode não edita .form visualmente — só via código Java
  Para editar forms visualmente, abra no NetBeans
- Para compilar pelo terminal:  ant -f build.xml
- Para compilar pelo VSCode: instale "Extension Pack for Java" (já tinha)
  e configure o settings.json conforme item 1.5

==============================================================
