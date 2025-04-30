-- ===========================================================
-- V8__create_envers_revinfo.sql
-- Creates the sequence and table Envers needs, once only.
-- ===========================================================

-- 1. Ensure the sequence exists
DO $$
BEGIN
  IF NOT EXISTS (
       SELECT 1 FROM pg_class WHERE relname = 'revinfo_seq'
     ) THEN
     CREATE SEQUENCE revinfo_seq START WITH 1 INCREMENT BY 1;
  END IF;
END $$;

-- 2. Create REVINFO table if missing
CREATE TABLE IF NOT EXISTS revinfo (
    rev      INTEGER NOT NULL DEFAULT nextval('revinfo_seq'),
    revtstmp BIGINT  NOT NULL,
    PRIMARY KEY (rev)
);

-- 3. Tie sequence ownership for safety
ALTER SEQUENCE revinfo_seq OWNED BY revinfo.rev;
