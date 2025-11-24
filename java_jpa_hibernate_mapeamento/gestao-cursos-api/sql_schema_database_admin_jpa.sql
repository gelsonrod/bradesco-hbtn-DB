-- =========================================================
-- Schema do banco: database_admin_jpa.db (SQLite)
-- Tabelas conforme diagrama: Aluno, Endereco, Telefone, Professor, Curso, MaterialCurso
-- Observações:
--  - BOOLEAN -> INTEGER (0/1) em SQLite
--  - Datas -> TEXT (ISO-8601: 'YYYY-MM-DD')
--  - Ative FOREIGN KEYS ao abrir a conexão (PRAGMA foreign_keys=ON)
-- =========================================================

PRAGMA foreign_keys = ON;

-- -----------------------------
-- Tabela: alunos
-- -----------------------------
CREATE TABLE IF NOT EXISTS alunos (
    id             INTEGER PRIMARY KEY AUTOINCREMENT,
    nome_completo  TEXT    NOT NULL,
    matricula      TEXT    NOT NULL UNIQUE,
    nascimento     TEXT    NOT NULL,       -- ISO-8601
    email          TEXT    NOT NULL UNIQUE
);

-- -----------------------------
-- Tabela: professores
-- -----------------------------
CREATE TABLE IF NOT EXISTS professores (
    id             INTEGER PRIMARY KEY AUTOINCREMENT,
    nome_completo  TEXT    NOT NULL,
    matricula      TEXT    NOT NULL UNIQUE,
    email          TEXT    NOT NULL UNIQUE
);

-- -----------------------------
-- Tabela: materiais_curso
-- -----------------------------
CREATE TABLE IF NOT EXISTS materiais_curso (
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    url  TEXT    NOT NULL
);

-- -----------------------------
-- Tabela: cursos
--  - muitos cursos para 1 professor
--  - 0..1 material por curso (material exclusivo)
-- -----------------------------
CREATE TABLE IF NOT EXISTS cursos (
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    nome          TEXT    NOT NULL,
    sigla         TEXT    NOT NULL UNIQUE,
    professor_id  INTEGER NOT NULL,
    material_id   INTEGER UNIQUE,
    FOREIGN KEY (professor_id) REFERENCES professores(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (material_id)  REFERENCES materiais_curso(id)
        ON UPDATE CASCADE ON DELETE SET NULL
);

-- -----------------------------
-- Tabela: enderecos
--  - 1 aluno para 0..* endereços
-- -----------------------------
CREATE TABLE IF NOT EXISTS enderecos (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    logradouro  TEXT    NOT NULL,
    endereco    TEXT    NOT NULL,
    numero      TEXT    NOT NULL,
    bairro      TEXT    NOT NULL,
    cidade      TEXT    NOT NULL,
    estado      TEXT    NOT NULL,
    cep         INTEGER NOT NULL,
    aluno_id    INTEGER NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES alunos(id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- -----------------------------
-- Tabela: telefones
--  - 1 aluno para 0..* telefones
-- -----------------------------
CREATE TABLE IF NOT EXISTS telefones (
    id        INTEGER PRIMARY KEY AUTOINCREMENT,
    DDD       TEXT    NOT NULL,
    numero    TEXT    NOT NULL,
    aluno_id  INTEGER NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES alunos(id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- -----------------------------
-- Tabela de junção N:N: curso_aluno
--  - 0..* cursos <-> 1..* alunos
-- -----------------------------
CREATE TABLE IF NOT EXISTS curso_aluno (
    curso_id  INTEGER NOT NULL,
    aluno_id  INTEGER NOT NULL,
    PRIMARY KEY (curso_id, aluno_id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (aluno_id) REFERENCES alunos(id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- Índices úteis (FKs já indexadas pelo PK, mas estes ajudam nas consultas):
CREATE INDEX IF NOT EXISTS idx_enderecos_aluno_id   ON enderecos(aluno_id);
CREATE INDEX IF NOT EXISTS idx_telefones_aluno_id   ON telefones(aluno_id);
CREATE INDEX IF NOT EXISTS idx_cursos_professor_id  ON cursos(professor_id);

-- Exemplos de inserts (opcional):
-- INSERT INTO professores (nome_completo, matricula, email) VALUES ('Dr. Xavier','P001','xavier@school.edu');
-- INSERT INTO materiais_curso (url) VALUES ('https://cdn.exemplo.com/material/java.pdf');
-- INSERT INTO cursos (nome, sigla, professor_id) VALUES ('Introdução a Java','IJAVA',1);
-- UPDATE cursos SET material_id=1 WHERE id=1;
-- INSERT INTO alunos (nome_completo, matricula, nascimento, email) VALUES ('Jane Doe','A123','1998-07-14','jane@example.com');
-- INSERT INTO enderecos (logradouro, endereco, numero, bairro, cidade, estado, cep, aluno_id)
--   VALUES ('Rua das Palmeiras','Apto 12, Bloco B','123','Centro','São Paulo','SP',1000000,1);
-- INSERT INTO telefones (DDD, numero, aluno_id) VALUES ('11','90000-0000',1);
-- INSERT INTO curso_aluno (curso_id, aluno_id) VALUES (1,1);
