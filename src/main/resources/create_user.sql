DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'diplom') THEN
        CREATE USER diplom WITH PASSWORD '1234';
    END IF;
END
$$;
ALTER USER diplom WITH PASSWORD '1234';

SELECT 'CREATE DATABASE diplom_bd WITH OWNER diplom'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'diplom_bd')\gexec

GRANT ALL PRIVILEGES ON DATABASE diplom_bd TO diplom;