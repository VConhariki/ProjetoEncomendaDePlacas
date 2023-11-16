CREATE TABLE IF NOT EXISTS Encomenda
(
    Id SERIAL PRIMARY KEY,
	AlturaPlaca decimal NOT NULL,
	LarguraPlaca decimal NOT NULL,
	Frase VARCHAR(100) DEFAULT 'Sem Frase',
	CorPlaca integer NOT NULL,
	CorFrase integer not null,
	DataEntrega Date not null,
	ValorServico decimal,
	ValorSinal decimal,
	FormaPagamento integer,
	PagamentoPendente bool
);;