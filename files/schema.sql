-- ============================================================
--  SCHEMA RECONSTRUÍDO — Bbras_willamy_alessandro_willian
--  Fonte: análise dos DAOs do projeto PROJETODS-MASTER
--  Gerado em: 2026-06
-- ============================================================

CREATE DATABASE IF NOT EXISTS Bbras_willamy_alessandro_willian
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE Bbras_willamy_alessandro_willian;

-- ============================================================
-- TABELA: cliente
-- Fonte: ClienteDao.java
-- ============================================================
CREATE TABLE IF NOT EXISTS cliente (
    cod_cli       INT          NOT NULL AUTO_INCREMENT,
    nome_cli      VARCHAR(100) NOT NULL,
    cpf_cli       VARCHAR(14)  NOT NULL,
    rg_cli        VARCHAR(20),
    sexo_cli      VARCHAR(10),
    datanasc_cli  VARCHAR(20),          -- armazenado como String no sistema
    endereco_cli  VARCHAR(200),
    telefone_cli  VARCHAR(20),
    email_cli     VARCHAR(100),
    PRIMARY KEY (cod_cli)
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: fornecedor
-- Fonte: FornecedorDao.java
-- ============================================================
CREATE TABLE IF NOT EXISTS fornecedor (
    cod_for       INT          NOT NULL AUTO_INCREMENT,
    nomeFant_for  VARCHAR(100) NOT NULL,
    razaoSoci_for VARCHAR(150),
    cnpj_for      VARCHAR(20),
    cep_for       VARCHAR(10),
    ende_for      VARCHAR(200),
    tel_for       VARCHAR(20),
    email_for     VARCHAR(100),
    resp_for      VARCHAR(100),
    cidade_for    VARCHAR(100),
    PRIMARY KEY (cod_for)
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: funcionario
-- Fonte: FuncionarioDao.java
-- Observação: campo endereço usa ç (acento) — mantido igual ao código
-- Observação: senha armazenada com MD5() no MySQL
-- ============================================================
CREATE TABLE IF NOT EXISTS funcionario (
    cod_fun          INT          NOT NULL AUTO_INCREMENT,
    nome_fun         VARCHAR(100) NOT NULL,
    cpf_fun          VARCHAR(14)  NOT NULL,
    rg_fun           VARCHAR(20),
    `endereço_fun`   VARCHAR(200),       -- backticks por causa do ç
    telefone_fun     VARCHAR(20),
    email_fun        VARCHAR(100),
    funcao_fun       VARCHAR(100),
    departamento_fun VARCHAR(100),
    senha_fun        VARCHAR(32),        -- MD5 = 32 chars hex
    PRIMARY KEY (cod_fun)
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: produto
-- Fonte: ProdutoDao.java
-- ============================================================
CREATE TABLE IF NOT EXISTS produto (
    cod_prod   INT           NOT NULL AUTO_INCREMENT,
    nome_prod  VARCHAR(100)  NOT NULL,
    valor_prod FLOAT         NOT NULL DEFAULT 0,
    cod_for_fk INT,
    PRIMARY KEY (cod_prod),
    CONSTRAINT fk_produto_fornecedor
        FOREIGN KEY (cod_for_fk) REFERENCES fornecedor(cod_for)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: mercadorias  (entradas de estoque)
-- Fonte: MercadoriasDao.java
-- ============================================================
CREATE TABLE IF NOT EXISTS mercadorias (
    cod_merc    INT         NOT NULL AUTO_INCREMENT,
    quant_merc  INT         NOT NULL DEFAULT 0,
    data_merc   VARCHAR(20),
    hora_merc   VARCHAR(10),
    cod_prod_fk INT,
    cod_fun_fk  INT,
    PRIMARY KEY (cod_merc),
    CONSTRAINT fk_merc_produto
        FOREIGN KEY (cod_prod_fk) REFERENCES produto(cod_prod)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_merc_funcionario
        FOREIGN KEY (cod_fun_fk) REFERENCES funcionario(cod_fun)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: vendas  (cabeçalho/totais da venda)
-- Fonte: VendasDao.java
-- ============================================================
CREATE TABLE IF NOT EXISTS vendas (
    cod_vendas          INT    NOT NULL AUTO_INCREMENT,
    valortotal_vendas   DOUBLE NOT NULL DEFAULT 0,
    valorpago_vendas    DOUBLE NOT NULL DEFAULT 0,
    valortroco_vendas   DOUBLE NOT NULL DEFAULT 0,
    valordesconto_vendas DOUBLE NOT NULL DEFAULT 0,
    cod_cli_fk          INT,
    cod_fun_fk          INT,
    PRIMARY KEY (cod_vendas),
    CONSTRAINT fk_vendas_cliente
        FOREIGN KEY (cod_cli_fk) REFERENCES cliente(cod_cli)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_vendas_funcionario
        FOREIGN KEY (cod_fun_fk) REFERENCES funcionario(cod_fun)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: venda  (itens da venda / realizar vendas)
-- Fonte: RealizarVendasDao.java
-- Observação: tabela chama-se "venda" (singular) nos SQLs do DAO
-- ============================================================
CREATE TABLE IF NOT EXISTS venda (
    cod_ven      INT   NOT NULL AUTO_INCREMENT,
    valor_ven    FLOAT NOT NULL DEFAULT 0,
    hora_ven     VARCHAR(10),
    data_ven     VARCHAR(20),
    quant_ven    INT   NOT NULL DEFAULT 1,
    cod_cli_fk   INT,
    cod_fun_fk   INT,
    cod_prod_fk  INT,
    cod_vendas_fk INT,
    PRIMARY KEY (cod_ven),
    CONSTRAINT fk_venda_cliente
        FOREIGN KEY (cod_cli_fk) REFERENCES cliente(cod_cli)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_venda_funcionario
        FOREIGN KEY (cod_fun_fk) REFERENCES funcionario(cod_fun)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_venda_produto
        FOREIGN KEY (cod_prod_fk) REFERENCES produto(cod_prod)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_venda_vendas
        FOREIGN KEY (cod_vendas_fk) REFERENCES vendas(cod_vendas)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: receber  (contas a receber)
-- Fonte: ReceberContasDao.java
-- ============================================================
CREATE TABLE IF NOT EXISTS receber (
    cod_rec   INT   NOT NULL AUTO_INCREMENT,
    valor_rec FLOAT NOT NULL DEFAULT 0,
    hora_rec  VARCHAR(10),
    dia_rec   VARCHAR(20),
    cod_ven   INT,
    PRIMARY KEY (cod_rec),
    CONSTRAINT fk_receber_venda
        FOREIGN KEY (cod_ven) REFERENCES vendas(cod_vendas)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: despesas
-- Fonte: DespesasDao.java
-- ============================================================
CREATE TABLE IF NOT EXISTS despesas (
    cod_desp   INT          NOT NULL AUTO_INCREMENT,
    valor_desp DOUBLE       NOT NULL DEFAULT 0,
    desc_desp  VARCHAR(200),
    data_desp  VARCHAR(20),
    hora_desp  VARCHAR(10),
    PRIMARY KEY (cod_desp)
) ENGINE=InnoDB;

-- ============================================================
-- TABELAS: caixareceitas e caixadespesas
-- Fonte: CaixaDao.java
-- Observação: usadas apenas para leitura (sum), provavelmente
--             alimentadas por triggers ou pelo próprio sistema
-- ============================================================
CREATE TABLE IF NOT EXISTS caixareceitas (
    cod_cair      INT    NOT NULL AUTO_INCREMENT,
    receitas_cai  DOUBLE NOT NULL DEFAULT 0,
    data_cai      VARCHAR(20),
    hora_cai      VARCHAR(10),
    PRIMARY KEY (cod_cair)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS caixadespesas (
    cod_caid      INT    NOT NULL AUTO_INCREMENT,
    despesas_cai  DOUBLE NOT NULL DEFAULT 0,
    data_caiD     VARCHAR(20),
    hora_caiD     VARCHAR(10),
    PRIMARY KEY (cod_caid)
) ENGINE=InnoDB;

-- ============================================================
-- FIM DO SCHEMA
-- ============================================================
