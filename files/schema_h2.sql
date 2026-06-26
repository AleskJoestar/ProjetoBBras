-- ============================================================
--  SCHEMA H2 — Bbras_willamy_alessandro_willian
--  Banco embarcado, arquivo local, sem servidor MySQL
--  Compatível com H2 Database Engine
-- ============================================================

-- cliente
CREATE TABLE IF NOT EXISTS cliente (
    cod_cli       INT           NOT NULL AUTO_INCREMENT,
    nome_cli      VARCHAR(100)  NOT NULL,
    cpf_cli       VARCHAR(14)   NOT NULL,
    rg_cli        VARCHAR(20),
    sexo_cli      VARCHAR(10),
    datanasc_cli  VARCHAR(20),
    endereco_cli  VARCHAR(200),
    telefone_cli  VARCHAR(20),
    email_cli     VARCHAR(100),
    PRIMARY KEY (cod_cli)
);

-- fornecedor
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
);

-- funcionario (senha em texto puro — modo teste)
CREATE TABLE IF NOT EXISTS funcionario (
    cod_fun          INT          NOT NULL AUTO_INCREMENT,
    nome_fun         VARCHAR(100) NOT NULL,
    cpf_fun          VARCHAR(14)  NOT NULL,
    rg_fun           VARCHAR(20),
    endereco_fun     VARCHAR(200),
    telefone_fun     VARCHAR(20),
    email_fun        VARCHAR(100),
    funcao_fun       VARCHAR(100),
    departamento_fun VARCHAR(100),
    senha_fun        VARCHAR(100),
    PRIMARY KEY (cod_fun)
);

-- produto
CREATE TABLE IF NOT EXISTS produto (
    cod_prod   INT          NOT NULL AUTO_INCREMENT,
    nome_prod  VARCHAR(100) NOT NULL,
    valor_prod FLOAT        NOT NULL DEFAULT 0,
    cod_for_fk INT,
    PRIMARY KEY (cod_prod),
    FOREIGN KEY (cod_for_fk) REFERENCES fornecedor(cod_for)
);

-- mercadorias (entradas de estoque)
CREATE TABLE IF NOT EXISTS mercadorias (
    cod_merc    INT  NOT NULL AUTO_INCREMENT,
    quant_merc  INT  NOT NULL DEFAULT 0,
    data_merc   VARCHAR(20),
    hora_merc   VARCHAR(10),
    cod_prod_fk INT,
    cod_fun_fk  INT,
    PRIMARY KEY (cod_merc),
    FOREIGN KEY (cod_prod_fk) REFERENCES produto(cod_prod),
    FOREIGN KEY (cod_fun_fk)  REFERENCES funcionario(cod_fun)
);

-- vendas (cabeçalho/totais)
CREATE TABLE IF NOT EXISTS vendas (
    cod_vendas            INT    NOT NULL AUTO_INCREMENT,
    valortotal_vendas     DOUBLE NOT NULL DEFAULT 0,
    valorpago_vendas      DOUBLE NOT NULL DEFAULT 0,
    valortroco_vendas     DOUBLE NOT NULL DEFAULT 0,
    valordesconto_vendas  DOUBLE NOT NULL DEFAULT 0,
    cod_cli_fk            INT,
    cod_fun_fk            INT,
    PRIMARY KEY (cod_vendas),
    FOREIGN KEY (cod_cli_fk) REFERENCES cliente(cod_cli),
    FOREIGN KEY (cod_fun_fk) REFERENCES funcionario(cod_fun)
);

-- venda (itens)
CREATE TABLE IF NOT EXISTS venda (
    cod_ven       INT   NOT NULL AUTO_INCREMENT,
    valor_ven     FLOAT NOT NULL DEFAULT 0,
    hora_ven      VARCHAR(10),
    data_ven      VARCHAR(20),
    quant_ven     INT   NOT NULL DEFAULT 1,
    cod_cli_fk    INT,
    cod_fun_fk    INT,
    cod_prod_fk   INT,
    cod_vendas_fk INT,
    PRIMARY KEY (cod_ven),
    FOREIGN KEY (cod_cli_fk)    REFERENCES cliente(cod_cli),
    FOREIGN KEY (cod_fun_fk)    REFERENCES funcionario(cod_fun),
    FOREIGN KEY (cod_prod_fk)   REFERENCES produto(cod_prod),
    FOREIGN KEY (cod_vendas_fk) REFERENCES vendas(cod_vendas)
);

-- receber (contas a receber)
CREATE TABLE IF NOT EXISTS receber (
    cod_rec   INT   NOT NULL AUTO_INCREMENT,
    valor_rec FLOAT NOT NULL DEFAULT 0,
    hora_rec  VARCHAR(10),
    dia_rec   VARCHAR(20),
    cod_ven   INT,
    PRIMARY KEY (cod_rec),
    FOREIGN KEY (cod_ven) REFERENCES vendas(cod_vendas)
);

-- despesas
CREATE TABLE IF NOT EXISTS despesas (
    cod_desp   INT          NOT NULL AUTO_INCREMENT,
    valor_desp DOUBLE       NOT NULL DEFAULT 0,
    desc_desp  VARCHAR(200),
    data_desp  VARCHAR(20),
    hora_desp  VARCHAR(10),
    PRIMARY KEY (cod_desp)
);

-- caixareceitas
CREATE TABLE IF NOT EXISTS caixareceitas (
    cod_cair     INT    NOT NULL AUTO_INCREMENT,
    receitas_cai DOUBLE NOT NULL DEFAULT 0,
    data_cai     VARCHAR(20),
    hora_cai     VARCHAR(10),
    PRIMARY KEY (cod_cair)
);

-- caixadespesas
CREATE TABLE IF NOT EXISTS caixadespesas (
    cod_caid     INT    NOT NULL AUTO_INCREMENT,
    despesas_cai DOUBLE NOT NULL DEFAULT 0,
    data_caid    VARCHAR(20),
    hora_caid    VARCHAR(10),
    PRIMARY KEY (cod_caid)
);
