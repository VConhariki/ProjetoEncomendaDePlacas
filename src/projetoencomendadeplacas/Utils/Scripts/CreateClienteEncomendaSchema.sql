CREATE TABLE IF NOT EXISTS ClienteEncomenda (
  id serial PRIMARY KEY,
  IdCliente integer NOT NULL,
  IdEncomenda integer NOT NULL,
  FOREIGN KEY (IdCliente) REFERENCES cliente(id),
  FOREIGN KEY (IdEncomenda) REFERENCES encomenda(id)
);