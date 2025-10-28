-- Criação da tabela EMPRESA
CREATE TABLE empresa (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    codigo_da_empresa VARCHAR(50)
);

-- Criação da tabela FUNCIONARIO
CREATE TABLE funcionario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cargo VARCHAR(255),
    codigo_de_identificacao VARCHAR(50) NOT NULL UNIQUE,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    empresa_id BIGINT,
    CONSTRAINT fk_funcionario_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

-- Criação da tabela BANCO_HORAS
CREATE TABLE banco_horas (
    id BIGSERIAL PRIMARY KEY,
    funcionario_id BIGINT UNIQUE,
    mes_referencia VARCHAR(7), -- formato: '2025-10' (já que YearMonth não existe no SQL)
    horas_trabalhadas DOUBLE PRECISION DEFAULT 0.0,
    horas_previstas DOUBLE PRECISION DEFAULT 0.0,
    saldo DOUBLE PRECISION DEFAULT 0.0,
    CONSTRAINT fk_banco_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionario(id)
);

-- Criação da tabela REGISTRO_PONTO
CREATE TABLE registro_ponto (
    id BIGSERIAL PRIMARY KEY,
    funcionario_id BIGINT,
    data_hora TIMESTAMP NOT NULL,
    tipo_registro VARCHAR(20) NOT NULL,
    CONSTRAINT fk_registro_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionario(id)
);
