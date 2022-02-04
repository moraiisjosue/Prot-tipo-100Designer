/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  fabricio
 * Created: Oct 21, 2021
 */

drop table if exists usuarios;
create table usuarios (
    id integer not null auto_increment,
    nome varchar(255) not null unique,
    email varchar(255) not null unique,
    senha char(32) not null,
    primary key(id)
);

drop table if exists designer;
    create table designers(
        id integer not null auto_increment,
        designer boolean true,
        nome varchar(255) not null unique,
        email varchar(255) not null unique,
        senha char(32) not null,
        avaliacao integer,
        desExper varchar(255),
        dataNasc date,
        primary key(id)
);


select * from usuarios;

//Banco de dados 2

create table usuarios (
    id integer not null auto_increment,
    nome varchar(255) not null,
    email varchar(255) not null unique,
    senha char(32) not null,
    bio varchar(255),
    dtnascimento DATE,
    designer boolean default false,
    primary key(id)
);