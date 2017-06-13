CREATE TABLE public.listacliente
(
  nome character varying(100),
  idade integer NOT NULL,
  email character varying(100),
  cpf character varying(100),
  CONSTRAINT listacliente_pkey PRIMARY KEY (idade),
  CONSTRAINT listacliente_idade_check CHECK (idade > 0)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.listacliente
  OWNER TO postgres;
