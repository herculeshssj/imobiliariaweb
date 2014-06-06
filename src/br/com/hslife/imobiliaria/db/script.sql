/*** 

    Copyright (c) 2011 Hércules S. S. José
    

    Este arquivo é parte do programa Imobiliária Web.

    Imobiliária Web é um software livre; você pode redistribui-lo e/ou 

    modificá-lo dentro dos termos da Licença Pública Geral Menor GNU como 

    publicada pela Fundação do Software Livre (FSF); na versão 2.1 da 

    Licença.
    
    
    Este programa é distribuído na esperança que possa ser util, 

    mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer

    MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral Menor GNU em 
    
    português para maiores detalhes.
    
    
    Você deve ter recebido uma cópia da Licença Pública Geral Menor GNU sob o 

    nome de "LICENSE.TXT" junto com este programa, se não, acesse o site HSlife no 

    endereco www.hslife.com.br ou escreva para a Fundação do Software Livre(FSF) Inc., 

    51 Franklin St, Fifth Floor, Boston, MA  02110-1301, USA.
    
    
    Para mais informações sobre o programa Imobiliária Web e seus autores acesso o 

    endereço www.hslife.com.br, pelo e-mail contato@hslife.com.br ou escreva para 

    Hércules S. S. José, Av. Ministro Lafaeyte de Andrade, 1683 - Bl. 3 Apt 404, 

    Marco II - Nova Iguaçu, RJ, Brasil.

 ***/

-- Script de criação das tabelas

-- Apaga as constraints
/*
alter table Agenda drop constraint FK74C6082CC682C5D7;
alter table Agenda drop constraint FK74C6082C2FC02E2F;
alter table Cliente drop constraint FK96841DDA344DB4C5;
alter table ClientePJ drop constraint FK5F419548D279432;
alter table ClientePJ_Socio drop constraint FK3D2AC22231854922;
alter table ClientePJ_Socio drop constraint FK3D2AC2225F08F7A9;
alter table ClientePJ_Telefone drop constraint FK78D9259531854922;
alter table ClientePJ_Telefone drop constraint FK78D92595D5BB2C53;
alter table Cliente_Telefone drop constraint FK8E7ED7CFD5BB2C53;
alter table Cliente_Telefone drop constraint FK8E7ED7CF35541162;
alter table Contrato drop constraint FKE20F773C2AB4E031;
alter table Contrato drop constraint FKE20F773CECE4BD46;
alter table Contrato drop constraint FKE20F773C80E16BD6;
alter table Contrato drop constraint FKE20F773CC682C5D7;
alter table Contrato drop constraint FKE20F773C50FDAFE5;
alter table Contrato drop constraint FKE20F773C1CF283FB;
alter table Contrato drop constraint FKE20F773C2FC02E2F;
alter table Corretor drop constraint FKE8C1089E56235B;
alter table Funcionario drop constraint FKB3A9C5BB56235B;
alter table Grupo_Permissao drop constraint FKB660B8F1B29E2102;
alter table Grupo_Permissao drop constraint FKB660B8F1B691DD85;
alter table Imovel drop constraint FK82C604126BC94EED;
alter table Imovel drop constraint FK82C60412344DB4C5;
alter table Imovel_Foto drop constraint FK406FF13191BA1D92;
alter table Imovel_Foto drop constraint FK406FF1315E151F87;
alter table Socio drop constraint FK4C594CDBCABDE2C;
alter table Usuario drop constraint FK5B4D8B0EED6D787F;
alter table Usuario drop constraint FK5B4D8B0E857E65A3;

-- Apaga as tabelas existentes
drop table Agenda;
drop table Cliente;
drop table ClientePJ;
drop table ClientePJ_Socio;
drop table ClientePJ_Telefone;
drop table Cliente_Telefone;
drop table Contrato;
drop table Corretor;
drop table Endereco;
drop table Foto;
drop table Funcionario;
drop table Grupo;
drop table Grupo_Permissao;
drop table Imovel;
drop table Imovel_Foto;
drop table IndiceReajuste;
drop table ModeloContrato;
drop table Permissao;
drop table Relatorio;
drop table Socio;
drop table Telefone;
drop table Usuario;
*/
-- Criação das tabelas
create table Agenda (id  bigserial not null, data date, emailCliente varchar(100) not null, horaFim int4, horaInicio int4, nomeCliente varchar(100) not null, observacao text, telefoneCliente varchar(50) not null, visitado bool, idCorretor int8 not null, idImovel int8 not null, primary key (id));
create table Cliente (id  bigserial not null, ativo bool, cpf varchar(11) not null, cpfConjuge varchar(11), dataEmissao date, dataEmissaoConjuge date, dataNascimento date not null, dataNascimentoConjuge date, email varchar(100), estadoCivil varchar(30), filiacaoMae varchar(100), filiacaoMaeConjuge varchar(100), filiacaoPai varchar(100), filiacaoPaiConjuge varchar(100), genero varchar(1) not null, generoConjuge varchar(1), nacionalidade varchar(50), nacionalidadeConjuge varchar(50), naturalidade varchar(50), naturalidadeConjuge varchar(50), nome varchar(100) not null, nomeConjuge varchar(100), numPasta varchar(10), orgaoEmissor varchar(30), orgaoEmissorConjuge varchar(30), profissao varchar(50), profissaoConjuge varchar(50), rg varchar(15), rgConjuge varchar(15), tipoCliente varchar(255) not null, idEndereco int8, primary key (id));
create table ClientePJ (id  bigserial not null, ativo bool, cnpj varchar(14) not null, nomeFantasia varchar(200) not null, numPasta varchar(255), ramoAtividade varchar(255), endereco_id int8, primary key (id));
create table ClientePJ_Socio (ClientePJ_id int8 not null, socios_id int8 not null, unique (socios_id));
create table ClientePJ_Telefone (ClientePJ_id int8 not null, telefones_id int8 not null, unique (telefones_id));
create table Cliente_Telefone (Cliente_id int8 not null, telefones_id int8 not null, unique (telefones_id));
create table Contrato (id  bigserial not null, comissaoCorretor float8, comissaoImobiliaria float8, dataCadastro timestamp, dataEncerramento timestamp, dataRenovacao timestamp, dataVigoracao timestamp, deposito float8, desconto float8, diaVencimento int4 not null, foro varchar(255), inicioContrato date not null, juros float8, multa float8, numContrato varchar(10) not null, periodoDesconto int4, prazo int4 not null, seguradora varchar(255), situacao int4 not null, terminoContrato date not null, valor float8 not null, idCorretor int8, idFiador int8, idImovel int8 not null, idIndice int8 not null, idLocatario int8, idLocatarioPJ int8, idModelo int8, primary key (id));
create table Corretor (creci varchar(12), dataRegistro date, regiao varchar(5), idPessoaFisica int8 not null, primary key (idPessoaFisica));
create table Endereco (id  bigserial not null, bairro varchar(100) not null, cep varchar(8) not null, cidade varchar(100) not null, complemento varchar(100), logradouro varchar(200) not null, numero varchar(10) not null, tipoLogradouro varchar(100) not null, uf varchar(2) not null, primary key (id));
create table Foto (id  bigserial not null, altura int4, arquivo varchar(255), caminho varchar(255), dados oid, dataInclusao timestamp, extensao varchar(255), largura int4, mime varchar(255), tamanho int8, primary key (id));
create table Funcionario (cargo varchar(30) not null, matricula varchar(10) not null, idPessoaFisica int8 not null, primary key (idPessoaFisica));
create table Grupo (id  bigserial not null, dataCriacao timestamp, descricao varchar(30) not null, padrao bool, primary key (id));
create table Grupo_Permissao (Grupo_id int8 not null, permissoes_id int8 not null, unique (permissoes_id));
create table Imovel (id  bigserial not null, ativo bool, maisInfo text, numRegistro varchar(20) not null, site bool, situacaoImovel varchar(20), tipoImovel varchar(20), valor float8, idEndereco int8 not null, idLocador int8 not null, primary key (id));
create table Imovel_Foto (Imovel_id int8 not null, fotos_id int8 not null, unique (fotos_id));
create table IndiceReajuste (id  bigserial not null, ativo bool, descricao varchar(100) not null, percentual float8 not null, periodo varchar(15) not null, primary key (id));
create table ModeloContrato (id  bigserial not null, arquivo varchar(255) not null, ativo bool, dados oid, descricao varchar(255) not null, tamanhoArquivo int4, primary key (id));
create table Permissao (id  bigserial not null, add bool, delete bool, edit bool, list bool, modulo varchar(50) not null, view bool, primary key (id));
create table Relatorio (id  bigserial not null, arquivo varchar(255) not null, ativo bool, dados oid, descricao varchar(255) not null, tamanhoArquivo int4, primary key (id));
create table Socio (id  bigserial not null, funcao varchar(255), clientePF_id int8, primary key (id));
create table Telefone (id  bigserial not null, ddd varchar(5), numero varchar(20), tipoTelefone varchar(15), primary key (id));
create table Usuario (id  bigserial not null, admin bool, ativo bool, dataCriacao timestamp, login varchar(50) not null unique, senha varchar(40) not null, ultimoLogin timestamp, idFuncionario int8 not null, idGrupo int8 not null, primary key (id));

-- Definição das constraints
alter table Agenda add constraint FK74C6082CC682C5D7 foreign key (idImovel) references Imovel;
alter table Agenda add constraint FK74C6082C2FC02E2F foreign key (idCorretor) references Corretor;
alter table Cliente add constraint FK96841DDA344DB4C5 foreign key (idEndereco) references Endereco;
alter table ClientePJ add constraint FK5F419548D279432 foreign key (endereco_id) references Endereco;
alter table ClientePJ_Socio add constraint FK3D2AC22231854922 foreign key (ClientePJ_id) references ClientePJ;
alter table ClientePJ_Socio add constraint FK3D2AC2225F08F7A9 foreign key (socios_id) references Socio;
alter table ClientePJ_Telefone add constraint FK78D9259531854922 foreign key (ClientePJ_id) references ClientePJ;
alter table ClientePJ_Telefone add constraint FK78D92595D5BB2C53 foreign key (telefones_id) references Telefone;
alter table Cliente_Telefone add constraint FK8E7ED7CFD5BB2C53 foreign key (telefones_id) references Telefone;
alter table Cliente_Telefone add constraint FK8E7ED7CF35541162 foreign key (Cliente_id) references Cliente;
alter table Contrato add constraint FKE20F773C2AB4E031 foreign key (idLocatario) references Cliente;
alter table Contrato add constraint FKE20F773CECE4BD46 foreign key (idFiador) references Cliente;
alter table Contrato add constraint FKE20F773C80E16BD6 foreign key (idIndice) references IndiceReajuste;
alter table Contrato add constraint FKE20F773CC682C5D7 foreign key (idImovel) references Imovel;
alter table Contrato add constraint FKE20F773C50FDAFE5 foreign key (idLocatarioPJ) references ClientePJ;
alter table Contrato add constraint FKE20F773C1CF283FB foreign key (idModelo) references ModeloContrato;
alter table Contrato add constraint FKE20F773C2FC02E2F foreign key (idCorretor) references Corretor;
alter table Corretor add constraint FKE8C1089E56235B foreign key (idPessoaFisica) references Cliente;
alter table Funcionario add constraint FKB3A9C5BB56235B foreign key (idPessoaFisica) references Cliente;
alter table Grupo_Permissao add constraint FKB660B8F1B29E2102 foreign key (Grupo_id) references Grupo;
alter table Grupo_Permissao add constraint FKB660B8F1B691DD85 foreign key (permissoes_id) references Permissao;
alter table Imovel add constraint FK82C604126BC94EED foreign key (idLocador) references Cliente;
alter table Imovel add constraint FK82C60412344DB4C5 foreign key (idEndereco) references Endereco;
alter table Imovel_Foto add constraint FK406FF13191BA1D92 foreign key (Imovel_id) references Imovel;
alter table Imovel_Foto add constraint FK406FF1315E151F87 foreign key (fotos_id) references Foto;
alter table Socio add constraint FK4C594CDBCABDE2C foreign key (clientePF_id) references Cliente;
alter table Usuario add constraint FK5B4D8B0EED6D787F foreign key (idGrupo) references Grupo;
alter table Usuario add constraint FK5B4D8B0E857E65A3 foreign key (idFuncionario) references Funcionario;

-- Insert do usuário Administrador
insert into endereco (bairro, cep, cidade, logradouro, numero, tipoLogradouro, uf) values ('DEMO','00000000','DEMO','DEMO','1','Rua','RJ');
insert into cliente (cpf, dataNascimento, tipoCliente, genero, nome, idEndereco, ativo) values ('00000000000','2011-10-01','funcionario','M','Administrador do sistema',1, false);
insert into funcionario (cargo, matricula, idPessoaFisica) values ('ADMIN','00000',1);
insert into grupo (dataCriacao, descricao, padrao) values ('2011-10-01 00:00:00', 'DEFAULT', true);
insert into usuario (admin, ativo, dataCriacao, login, senha, ultimoLogin, idFuncionario, idGrupo) values (true, true, '2011-10-01 00:00:00','admin','d033e22ae348aeb5660fc2140aec35850c4da997',null,1,1);
