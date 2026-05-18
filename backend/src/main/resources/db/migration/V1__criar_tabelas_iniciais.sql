-- Tabela de Barbeiros
CREATE TABLE barbeiros (
                           id          BIGSERIAL PRIMARY KEY,
                           nome        VARCHAR(100) NOT NULL,
                           email       VARCHAR(100) NOT NULL UNIQUE,
                           telefone    VARCHAR(20)  NOT NULL,
                           especialidade VARCHAR(100) NOT NULL
);

-- Tabela de Clientes
CREATE TABLE clientes (
                          id          BIGSERIAL PRIMARY KEY,
                          nome        VARCHAR(100) NOT NULL,
                          email       VARCHAR(100) NOT NULL UNIQUE,
                          cpf         VARCHAR(14)  NOT NULL UNIQUE,
                          endereco    VARCHAR(255) NOT NULL
);

-- Tabela de Serviços
CREATE TABLE servicos (
                          id                BIGSERIAL PRIMARY KEY,
                          tipo              VARCHAR(50)    NOT NULL,
                          descricao         VARCHAR(255)   NOT NULL,
                          preco             DECIMAL(10, 2) NOT NULL,
                          duracao_minutos   INTEGER        NOT NULL
);

-- Tabela de Agendamentos
CREATE TABLE agendamentos (
                              id          BIGSERIAL PRIMARY KEY,
                              data        DATE        NOT NULL,
                              horario     TIME        NOT NULL,
                              status      VARCHAR(20) NOT NULL,
                              barbeiro_id BIGINT      NOT NULL REFERENCES barbeiros(id),
                              cliente_id  BIGINT      NOT NULL REFERENCES clientes(id),
                              servico_id  BIGINT      NOT NULL REFERENCES servicos(id)
);