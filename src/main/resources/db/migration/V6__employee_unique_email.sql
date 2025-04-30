-- Prevent duplicate employee emails
ALTER TABLE employees
    ADD CONSTRAINT uk_employees_work_email UNIQUE (work_email);
