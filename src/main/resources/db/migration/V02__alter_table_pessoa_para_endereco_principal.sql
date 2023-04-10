ALTER TABLE pessoa ADD endereco_principal_id bigint;

ALTER TABLE pessoa ADD CONSTRAINT fk_endereco_principal FOREIGN KEY (endereco_principal_id) REFERENCES endereco(id);

ALTER TABLE pessoa ADD CONSTRAINT uk_endereco_principal UNIQUE (endereco_principal_id);


UPDATE pessoa SET endereco_principal_id = 1 WHERE id = 1;

UPDATE pessoa SET endereco_principal_id = 4 WHERE id = 2;

UPDATE pessoa SET endereco_principal_id = 12 WHERE id = 7;
