DO
$$
BEGIN
   IF EXISTS (SELECT FROM pg_database WHERE datname = 'encomendaplacadb') THEN
   ELSE
      PERFORM dblink_exec('dbname=' || current_database()  -- current db
                        , 'CREATE DATABASE mydb');
   END IF;
END
$$;;