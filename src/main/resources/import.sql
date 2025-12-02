-- 1. LIMPEZA (Garante que o script possa ser rodado várias vezes)
-- As tabelas de relacionamento (junção) devem ser limpas primeiro
DELETE FROM tb_item

INSERT INTO tb_item (id, name, description, price, quantity, minimal_quantity, type) VALUES (100, 'Cerveja Devassa', 'Lata 350ml', 10.00, 50, 10, 'VENDA');

INSERT INTO tb_item (id, name, description, price, quantity, minimal_quantity, type) VALUES (101, 'Lâmina de Barbear', 'Caixa com 100 unidades', 2.50, 200, 50, 'CONSUMO_INTERNO');

INSERT INTO tb_item (id, name, description, price, quantity, minimal_quantity, type) VALUES (102, 'Pomada Modeladora', 'Pomada matte 150g', 35.00, 15, 5, 'AMBOS');