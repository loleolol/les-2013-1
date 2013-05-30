/* CRIACAO DO BANCO */
CREATE DATABASE LES20131;

USE LES20131;

/* CRIACAO DAS TABELAS */
CREATE TABLE USUARIO (
        ID_USUARIO INT(5) AUTO_INCREMENT NOT NULL PRIMARY KEY,
        EMAIL VARCHAR(100) NOT NULL UNIQUE,
        NOME VARCHAR(100) NOT NULL,
        SENHA CHAR(40) NOT NULL,
        EXCLUIDO INT(1) NOT NULL,
        BLOQUEADO INT(1) NOT NULL);
        
CREATE TABLE ADMINISTRADOR (
		ID_USUARIO INT(5) NOT NULL PRIMARY KEY,
		FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID_USUARIO));

INSERT INTO USUARIO VALUES(1, 'admin', 'admin', SHA1('admin'), 0, 0);
INSERT INTO ADMINISTRADOR VALUES(1);
	
CREATE TABLE EMPRESA (
		ID_USUARIO INT(5) NOT NULL PRIMARY KEY,
		URL VARCHAR(100) NOT NULL,
		DEFINICAO VARCHAR(100) NOT NULL,
		IMAGEM MEDIUMBLOB,
		FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID_USUARIO));

CREATE TABLE ANUNCIO (
		ID_ANUNCIO INT(5) AUTO_INCREMENT NOT NULL PRIMARY KEY,
		ID_USUARIO INT(5) NOT NULL,
		ANUNCIO VARCHAR(500) NOT NULL,
		DATA_INICIAL DATE NOT NULL,
		DATA_FINAL DATE NOT NULL,
		FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID_USUARIO));
		
CREATE TABLE VIAJANTE (
		ID_USUARIO INT(5) NOT NULL PRIMARY KEY,
		SEXO CHAR(1) NOT NULL,
		DATA_NASCIMENTO DATE NOT NULL,
		LATITUDE DOUBLE,
		LONGITUDE DOUBLE,
		IMAGEM MEDIUMBLOB,
		FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID_USUARIO));

CREATE TABLE AVALIACAO_EMPRESA (
		ID_AVALIACAO_EMPRESA INT(5) NOT NULL PRIMARY KEY,
		ID_EMPRESA INT(5) NOT NULL,
		ID_VIAJANTE INT(5) NOT NULL,
		AVALIACAO DOUBLE NOT NULL,
		DESCRICAO VARCHAR(500) NOT NULL,
		DATA_INCLUSAO TIMESTAMP NOT NULL,
		FOREIGN KEY (ID_EMPRESA) REFERENCES EMPRESA(ID_USUARIO),
		FOREIGN KEY (ID_VIAJANTE) REFERENCES VIAJANTE(ID_USUARIO));
		
CREATE TABLE VIAGEM (
		ID_VIAGEM INT(5) AUTO_INCREMENT NOT NULL PRIMARY KEY,
		ID_USUARIO INT(5) NOT NULL,
		TITULO VARCHAR(50) NOT NULL,
		DESCRICAO VARCHAR(500) NOT NULL,
		DATA_INICIAL DATE,
		DATA_FINAL DATE,
		DATA_INCLUSAO TIMESTAMP NOT NULL,
		FOREIGN KEY (ID_USUARIO) REFERENCES VIAJANTE(ID_USUARIO));
		
CREATE TABLE IMAGEM_VIAGEM (
		ID_IMAGEM_VIAGEM INT(5) AUTO_INCREMENT NOT NULL PRIMARY KEY,
		ID_VIAGEM INT(5) NOT NULL,
		IMAGEM MEDIUMBLOB,
		FOREIGN KEY (ID_VIAGEM) REFERENCES VIAGEM(ID_VIAGEM));
		
CREATE TABLE CONTATO (
		ID_CONTATO INT(5) AUTO_INCREMENT NOT NULL PRIMARY KEY,
		ID_USUARIO1 INT(5) NOT NULL,
		ID_USUARIO2 INT(5) NOT NULL,
		DATA_INCLUSAO TIMESTAMP NOT NULL,
		UNIQUE KEY (ID_USUARIO1, ID_USUARIO2),
		FOREIGN KEY (ID_USUARIO1) REFERENCES VIAJANTE (ID_USUARIO),
		FOREIGN KEY (ID_USUARIO2) REFERENCES VIAJANTE (ID_USUARIO));
		

