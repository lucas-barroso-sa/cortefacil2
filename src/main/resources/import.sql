-- 1. LIMPEZA (Garante que o script possa ser rodado várias vezes)
-- As tabelas de relacionamento (junção) devem ser limpas primeiro
DELETE FROM sale_items;
DELETE FROM appointment_services;
DELETE FROM appointments;
DELETE FROM bar_sales;
DELETE FROM services;
DELETE FROM inventory_movements;
DELETE FROM items;
DELETE FROM barbers;
DELETE FROM clients;


-- 2. CADASTRO DE PESSOAS (HERANÇA SIMPLIFICADA PARA TESTE)
--------------------------------------------------------------

-- CLIENTS (Clientes que agendam)
INSERT INTO CLIENTS (id, nome, telefone, login, senha_hash) VALUES
(1, 'Maria Oliveira', '85988887777', 'maria.o', '$2a$10$hashed_client_pw'), -- Senha: 123
(2, 'Pedro Rocha', '85977776666', 'pedro.r', '$2a$10$hashed_client_pw'); -- Senha: 123

-- BARBERS (Barbeiros e Administrador - Admin é um Barbeiro com mais permissões)
INSERT INTO BARBERS (id, nome, telefone, login, senha_hash, percentual_repasse, is_admin) VALUES
(10, 'Don Victor', '85999990000', 'don.victor', '$2a$10$hashed_admin_pw', 40.0, TRUE), -- ADMIN
(11, 'João Barbeiro', '85988880000', 'joao.b', '$2a$10$hashed_barber_pw', 60.0, FALSE), -- BARBER
(12, 'Lucas Barbeiro', '85966661111', 'lucas.b', '$2a$10$hashed_barber_pw', 50.0, FALSE); -- BARBER

-- 3. CATÁLOGO DE PRODUTOS E SERVIÇOS
--------------------------------------------------------------

-- ITEMS (Produtos/Materiais - A classe unificada)
-- Tipos: VENDA, CONSUMO_INTERNO, AMBOS
INSERT INTO ITEMS (id, nome, preco_venda, quantidade_estoque, estoque_minimo, tipo) VALUES
(100, 'Cerveja Devassa', 10.00, 50, 10, 'VENDA'),
(101, 'Lâmina de Barbear', 1.50, 200, 50, 'CONSUMO_INTERNO'),
(102, 'Pomada Modeladora', 35.00, 15, 5, 'AMBOS'),
(103, 'Refrigerante Coca', 6.00, 30, 5, 'VENDA');

-- SERVICES (Serviços de Barbearia)
INSERT INTO SERVICES (id, nome, preco) VALUES
(200, 'Corte Clássico', 45.00),
(201, 'Barba Completa', 35.00),
(202, 'Pigmentação', 80.00);

-- 4. REGISTROS DE EVENTOS (MOVIMENTAÇÕES E AGENDAMENTOS)
--------------------------------------------------------------

-- APPOINTMENTS (Agendamentos - PENDENTE e CONCLUIDO)
-- Maria agendou (PENDENTE) com João
INSERT INTO APPOINTMENTS (id, client_id, barber_id, date_time, status, total_amount) VALUES
(300, 1, 11, NOW(), 'PENDENTE', 0.00),
-- Pedro agendou (CONCLUIDO) com Lucas
(301, 2, 12, DATEADD('HOUR', -2, NOW()), 'CONCLUIDO', 115.00);

-- BAR_SALES (Vendas no Bar)
INSERT INTO BAR_SALES (id, sale_date, total_amount) VALUES
(400, NOW(), 30.00); -- Venda de 3 Cervejas (3 x R$10)
-- Total amount é inserido para fins de teste, mas seu Service deve calculá-lo.

-- INVENTORY_MOVEMENTS (Movimentação de Estoque)
-- Don Victor fez a entrada de 50 Lâminas (item 101)
INSERT INTO INVENTORY_MOVEMENTS (id, item_id, quantity, type, timestamp) VALUES
(500, 101, 50, 'ENTRADA', NOW());


-- 5. TABELAS DE JUNÇÃO (RELACIONAMENTOS M-N)
-----------------------------------------------------------------

-- APPOINTMENT_SERVICES (O que foi agendado em 300 e 301)
-- Agendamento 300 (Maria): Corte + Barba
INSERT INTO APPOINTMENT_SERVICES (appointment_id, service_id, price_at_booking) VALUES
(300, 200, 45.00),
(300, 201, 35.00);
-- Agendamento 301 (Pedro): Corte + Pigmentação
INSERT INTO APPOINTMENT_SERVICES (appointment_id, service_id, price_at_booking) VALUES
(301, 200, 45.00),
(301, 202, 80.00);

-- SALE_ITEMS (Itens da Venda 400)
-- Venda 400: 3 Cervejas
INSERT INTO SALE_ITEMS (sale_id, item_id, quantity, unit_price, subtotal) VALUES
(400, 100, 3, 10.00, 30.00);