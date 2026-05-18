--Auditoria e soft delete tabela barbeiros
ALTER TABLE barbeiros
    ADD COLUMN ativo        BOOLEAN     NOT NULL DEFAULT TRUE,
    ADD COLUMN criado_em    TIMESTAMP   NOT NULL DEFAULT NOW(),
    ADD COLUMN atualizado_em TIMESTAMP  NOT NULL DEFAULT NOW();

-- Auditoria na tabela clientes
ALTER TABLE clientes
    ADD COLUMN criado_em    TIMESTAMP   NOT NULL DEFAULT NOW(),
    ADD COLUMN atualizado_em TIMESTAMP  NOT NULL DEFAULT NOW();

-- Auditoria na tabela servicos
ALTER TABLE servicos
    ADD COLUMN criado_em    TIMESTAMP   NOT NULL DEFAULT NOW(),
    ADD COLUMN atualizado_em TIMESTAMP  NOT NULL DEFAULT NOW();

-- Auditoria na tabela agendamentos
ALTER TABLE agendamentos
    ADD COLUMN observacao   VARCHAR(500),
    ADD COLUMN criado_em    TIMESTAMP   NOT NULL DEFAULT NOW(),
    ADD COLUMN atualizado_em TIMESTAMP  NOT NULL DEFAULT NOW();