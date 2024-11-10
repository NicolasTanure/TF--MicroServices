-- Inserindo Clientes
INSERT INTO Cliente (codigo, nome, email) VALUES (1, 'João Silva', 'joao.silva@email.com');
INSERT INTO Cliente (codigo, nome, email) VALUES (2, 'Maria Oliveira', 'maria.oliveira@email.com');
INSERT INTO Cliente (codigo, nome, email) VALUES (3, 'Carlos Pereira', 'carlos.pereira@email.com');

-- Inserindo Aplicativos
INSERT INTO Aplicativo (codigo, nome, custo_Mensal) VALUES (1, 'Spotify', 29.90);
INSERT INTO Aplicativo (codigo, nome, custo_Mensal) VALUES (2, 'Netflix', 49.90);
INSERT INTO Aplicativo (codigo, nome, custo_Mensal) VALUES (3, 'Amazon Prime', 19.90);
INSERT INTO Aplicativo (codigo, nome, custo_Mensal) VALUES (4, 'Disney Plus', 29.90);

-- Inserindo Assinaturas
INSERT INTO Assinatura (codigo, cliente_codigo, aplicativo_codigo, inicio_Vigencia, fim_Vigencia) 
VALUES (1, 1, 1, '2023-01-01', '2023-12-31');

INSERT INTO Assinatura (codigo, cliente_codigo, aplicativo_codigo, inicio_Vigencia, fim_Vigencia) 
VALUES (2, 2, 2, '2023-05-01', '2024-04-30');

INSERT INTO Assinatura (codigo, cliente_codigo, aplicativo_codigo, inicio_Vigencia, fim_Vigencia) 
VALUES (3, 3, 3, '2023-07-01', '2024-06-30');

INSERT INTO Assinatura (codigo, cliente_codigo, aplicativo_codigo, inicio_Vigencia, fim_Vigencia)
VALUES (4, 3, 4, '2023-07-01', '2024-12-30');
