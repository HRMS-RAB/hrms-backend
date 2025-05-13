-- V13: add photo_path to the audit table so Envers can log it
ALTER TABLE employees_aud
  ADD COLUMN IF NOT EXISTS photo_path VARCHAR(255);
