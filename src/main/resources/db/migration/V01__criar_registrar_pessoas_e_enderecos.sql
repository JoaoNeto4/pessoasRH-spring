CREATE TABLE pessoa(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	data_nascimento DATE NOT NULL
);

CREATE TABLE endereco(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	cidade VARCHAR(100) NOT NULL,
	cep VARCHAR(20),
	numero INTEGER,
	logradouro VARCHAR(100),
	codigo_pessoa BIGINT,
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(id)
);


INSERT INTO pessoa (nome, data_nascimento) values ('João Neto', '2023-04-03');
INSERT INTO pessoa (nome, data_nascimento) values ('Bhelquior', '1994-10-03');
INSERT INTO pessoa (nome, data_nascimento) values ('Baltazar', '1980-09-20');
INSERT INTO pessoa (nome, data_nascimento) values ('Gaspar', '1850-03-04');
INSERT INTO pessoa (nome, data_nascimento) values ('Francisco', '1941-02-03');
INSERT INTO pessoa (nome, data_nascimento) values ('Maria Lucia', '1989-01-03');
INSERT INTO pessoa (nome, data_nascimento) values ('Jacinta Marto', '2000-05-03');


INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Palmas', '85555000', 507, 'casa', 1);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Francisco Beltrão', '85555444', 221, 'ap', 1);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Tubarão', '88541321', 21, 'Candominio', 1);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Mariopolis', '65666222', 10, 'chacara', 2);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Coronel Domingos', '12456789', 1021, 'comercio', 3);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Florianopolis', '1234456789', 1080, 'casa', 4);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Nonoai', '78999555', 35, 'comercio', 5);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Palhoça', '45123123', 987, 'casa', 6);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('São Paulo', '78456456', 2081, 'casa', 7);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Foz do Iguaçu', '12458458', 100, 'casa', 7);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Curitiba', '12456456', 21, 'casa', 7);
INSERT INTO endereco (cidade, cep, numero, logradouro, codigo_pessoa) values ('Pinhão', '12452452', 17, 'casa', 7);
