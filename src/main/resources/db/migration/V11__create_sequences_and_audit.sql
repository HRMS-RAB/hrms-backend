-- V10__create_sequences_and_audit.sql

-- 1) Employee ID sequence
CREATE SEQUENCE IF NOT EXISTS employee_id_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- 2) revinfo (Envers) sequence & table, if not already present
CREATE SEQUENCE IF NOT EXISTS revinfo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS revinfo (
    rev    INTEGER     PRIMARY KEY,
    revtstmp BIGINT    NOT NULL
);

-- 3) Audit table for Employee
CREATE TABLE IF NOT EXISTS employees_aud (
    employee_id      BIGINT   NOT NULL,
    rev              INTEGER  NOT NULL,
    revtype          SMALLINT NOT NULL,
    -- copy all employee columns here, e.g:
    first_name       VARCHAR(100),
    last_name        VARCHAR(100),
    -- … rest of columns …
    CONSTRAINT pk_employees_aud PRIMARY KEY (rev, employee_id),
    CONSTRAINT fk_employees_aud_rev FOREIGN KEY (rev)
        REFERENCES revinfo (rev) ON DELETE CASCADE
);
