-- =========================================================
-- Schema do banco: database_admin.db  (SQLite)
-- Tabelas: pessoas, produtos
-- Observação: em SQLite, BOOLEAN é representado como INTEGER (0/1)
-- e datas como TEXT no formato ISO-8601 (YYYY-MM-DD).
-- =========================================================

PRAGMA foreign_keys = ON;

-- -----------------------------
-- Tabela: pessoas
-- -----------------------------
CREATE TABLE IF NOT EXISTS pessoas (
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    nome             TEXT    NOT NULL,
    email            TEXT    NOT NULL UNIQUE,
    idade            INTEGER NOT NULL,
    cpf              TEXT    NOT NULL UNIQUE,
    data_nascimento  TEXT    NOT NULL   -- ISO-8601: 'YYYY-MM-DD'
);

-- -----------------------------
-- Tabela: produtos
-- -----------------------------
CREATE TABLE IF NOT EXISTS produtos (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    nome        TEXT    NOT NULL,
    quantidade  INTEGER NOT NULL,
    preco       REAL    NOT NULL,
    status      INTEGER NOT NULL CHECK (status IN (0, 1))  -- 0=false, 1=true
);

-- Índices opcionais (UNIQUE já cria índice implícito; abaixo é meramente ilustrativo)
-- CREATE UNIQUE INDEX IF NOT EXISTS idx_pessoas_email ON pessoas(email);
-- CREATE UNIQUE INDEX IF NOT EXISTS idx_pessoas_cpf   ON pessoas(cpf);

-- Exemplos de inserts (opcional):
-- INSERT INTO produtos (nome, quantidade, preco, status) VALUES ('TV', 100, 300.0, 1);
-- INSERT INTO pessoas (nome, email, idade, cpf, data_nascimento) VALUES
-- ('Jane Doe', 'jane@example.com', 30, '12345678901', '1995-05-20');
