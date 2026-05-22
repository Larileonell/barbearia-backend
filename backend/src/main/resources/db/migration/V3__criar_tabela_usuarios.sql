CREATE TABLE usuarios (
                          id      BIGSERIAL       PRIMARY KEY,
                          nome    VARCHAR(100)    NOT NULL,
                          email   VARCHAR(100)    NOT NULL UNIQUE,
                          senha   VARCHAR(255)    NOT NULL,
                          perfil  VARCHAR(20)     NOT NULL
);

-- Insere um admin padrão
-- Senha: admin123 (já em BCrypt)
INSERT INTO usuarios (nome, email, senha, perfil)
VALUES (
           'Administrador',
           'admin@barbearia.com',
           '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.',
           'ADMIN'
       );