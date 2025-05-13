-- V12: add photo_path column and drop old photo binary
ALTER TABLE employees
  ADD COLUMN photo_path VARCHAR(255);

ALTER TABLE employees
  DROP COLUMN IF EXISTS photo;
