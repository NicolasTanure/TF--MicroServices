-- Inserindo Clientes
INSERT INTO Cliente (codigo, nome, email) VALUES (10, 'João Silva', 'joao.silva@email.com');
INSERT INTO Cliente (codigo, nome, email) VALUES (20, 'Maria Oliveira', 'maria.oliveira@email.com');
INSERT INTO Cliente (codigo, nome, email) VALUES (30, 'Carlos Pereira', 'carlos.pereira@email.com');

-- Inserindo Aplicativos
INSERT INTO Aplicativo (codigo, nome, custo_Mensal) VALUES (10, 'Spotify', 29.90);
INSERT INTO Aplicativo (codigo, nome, custo_Mensal) VALUES (20, 'Netflix', 49.90);
INSERT INTO Aplicativo (codigo, nome, custo_Mensal) VALUES (30, 'Amazon Prime', 19.90);
INSERT INTO Aplicativo (codigo, nome, custo_Mensal) VALUES (40, 'Disney Plus', 29.90);

-- Inserindo Assinaturas
INSERT INTO Assinatura (codigo, cliente_codigo, aplicativo_codigo, inicio_Vigencia, fim_Vigencia) 
VALUES (10, 10, 10, '2023-01-01', '2023-12-31');

INSERT INTO Assinatura (codigo, cliente_codigo, aplicativo_codigo, inicio_Vigencia, fim_Vigencia) 
VALUES (11, 20, 20, '2023-05-01', '2024-04-30');

INSERT INTO Assinatura (codigo, cliente_codigo, aplicativo_codigo, inicio_Vigencia, fim_Vigencia) 
VALUES (12, 30, 30, '2023-07-01', '2024-06-30');

INSERT INTO Assinatura (codigo, cliente_codigo, aplicativo_codigo, inicio_Vigencia, fim_Vigencia)
VALUES (13, 30, 40, '2023-07-01', '2024-12-30');
