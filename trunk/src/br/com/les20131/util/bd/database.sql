/* CRIACAO DO BANCO */
CREATE DATABASE LES20131;

USE LES20131;

/* CRIACAO DAS TABELAS */
CREATE TABLE USUARIO (
        ID_USUARIO INT(5) AUTO_INCREMENT NOT NULL PRIMARY KEY,
        EMAIL VARCHAR(100) NOT NULL,
        NOME VARCHAR(100) NOT NULL
        SENHA CHAR(32) NOT NULL);