-- ============================================================
--  SEED — dados mínimos para testar o sistema
--  Execute APÓS o schema.sql
--  Bbras_willamy_alessandro_willian
-- ============================================================

USE Bbras_willamy_alessandro_willian;

-- ============================================================
-- FUNCIONARIO (inserir primeiro — é usado no Login)
-- ATENÇÃO: senha armazenada como MD5
-- Usuário de teste: CPF=00000000000 / senha=admin123
-- ============================================================
INSERT INTO funcionario
    (nome_fun, cpf_fun, rg_fun, `endereço_fun`, telefone_fun, email_fun, funcao_fun, departamento_fun, senha_fun)
VALUES
    ('Administrador', '00000000000', '0000000', 'Rua Teste, 1', '(69)99999-0001', 'admin@empresa.com', 'Gerente', 'Diretoria', MD5('admin123')),
    ('João Vendedor',  '11122233344', '1112223', 'Rua das Flores, 10', '(69)99999-0002', 'joao@empresa.com',  'Vendedor',  'Vendas',    MD5('senha123'));

-- ============================================================
-- CLIENTE
-- ============================================================
INSERT INTO cliente
    (nome_cli, cpf_cli, rg_cli, sexo_cli, datanasc_cli, endereco_cli, telefone_cli, email_cli)
VALUES
    ('Maria Silva',   '123.456.789-00', '1234567', 'F', '01/01/1990', 'Rua A, 100, Ji-Paraná/RO', '(69)98888-0001', 'maria@email.com'),
    ('Carlos Santos', '987.654.321-00', '9876543', 'M', '15/06/1985', 'Rua B, 200, Ji-Paraná/RO', '(69)98888-0002', 'carlos@email.com');

-- ============================================================
-- FORNECEDOR
-- ============================================================
INSERT INTO fornecedor
    (nomeFant_for, razaoSoci_for, cnpj_for, cep_for, ende_for, tel_for, email_for, resp_for, cidade_for)
VALUES
    ('Distribuidora Central', 'Distribuidora Central LTDA', '12.345.678/0001-90', '76900-000', 'Av. Principal, 500', '(69)3421-0001', 'contato@distcentral.com', 'Pedro Lima', 'Ji-Paraná'),
    ('Atacado Norte',         'Atacado Norte Comércio ME',  '98.765.432/0001-10', '76900-010', 'Rua Comercial, 30',  '(69)3421-0002', 'contato@atacadonorte.com','Ana Costa',  'Ji-Paraná');

-- ============================================================
-- PRODUTO
-- ============================================================
INSERT INTO produto (nome_prod, valor_prod, cod_for_fk)
VALUES
    ('Cabo UTP Cat5e 305m', 250.00, 1),
    ('Switch 8 portas',     180.00, 1),
    ('Roteador WiFi',       320.00, 2),
    ('Patch Panel 24p',     150.00, 2);

-- ============================================================
-- MERCADORIAS (entrada de estoque)
-- ============================================================
INSERT INTO mercadorias (quant_merc, data_merc, hora_merc, cod_prod_fk, cod_fun_fk)
VALUES
    (10, '2026-06-01', '08:30', 1, 1),
    (5,  '2026-06-01', '08:45', 2, 1),
    (8,  '2026-06-02', '09:00', 3, 2),
    (4,  '2026-06-02', '09:15', 4, 2);

-- ============================================================
-- VENDAS (cabeçalho)
-- ============================================================
INSERT INTO vendas
    (valortotal_vendas, valorpago_vendas, valortroco_vendas, valordesconto_vendas, cod_cli_fk, cod_fun_fk)
VALUES
    (430.00, 500.00, 70.00, 0.00,  1, 2),
    (320.00, 320.00,  0.00, 10.00, 2, 2);

-- ============================================================
-- VENDA (itens)
-- ============================================================
INSERT INTO venda
    (valor_ven, hora_ven, data_ven, quant_ven, cod_cli_fk, cod_fun_fk, cod_prod_fk, cod_vendas_fk)
VALUES
    (250.00, '10:00', '2026-06-10', 1, 1, 2, 1, 1),
    (180.00, '10:05', '2026-06-10', 1, 1, 2, 2, 1),
    (320.00, '14:00', '2026-06-11', 1, 2, 2, 3, 2);

-- ============================================================
-- RECEBER (contas a receber)
-- ============================================================
INSERT INTO receber (valor_rec, hora_rec, dia_rec, cod_ven)
VALUES
    (430.00, '10:10', '2026-06-10', 1),
    (320.00, '14:05', '2026-06-11', 2);

-- ============================================================
-- DESPESAS
-- ============================================================
INSERT INTO despesas (valor_desp, desc_desp, data_desp, hora_desp)
VALUES
    (150.00, 'Conta de energia',    '2026-06-05', '09:00'),
    (80.00,  'Material de limpeza', '2026-06-06', '10:00');

-- ============================================================
-- CAIXA (receitas e despesas resumidas)
-- ============================================================
INSERT INTO caixareceitas (receitas_cai, data_cai, hora_cai)
VALUES
    (430.00, '2026-06-10', '10:10'),
    (320.00, '2026-06-11', '14:05');

INSERT INTO caixadespesas (despesas_cai, data_caiD, hora_caiD)
VALUES
    (150.00, '2026-06-05', '09:00'),
    (80.00,  '2026-06-06', '10:00');

-- ============================================================
-- FIM DO SEED
-- Usuário de login para testar o sistema:
--   CPF   : 00000000000
--   Senha  : admin123
-- ============================================================
