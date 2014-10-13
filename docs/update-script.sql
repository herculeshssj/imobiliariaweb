/*** 

    Copyright (c) 2011, 2014 Hércules S. S. José
    

    Este arquivo é parte do programa ImobiliáriaWeb.

    ImobiliáriaWeb é um software livre; você pode redistribui-lo e/ou 

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
    
    
    Para mais informações sobre o programa ImobiliáriaWeb e seus autores acesso o 

    endereço hslife.com.br, pelo e-mail contato@hslife.com.br ou escreva para 

    Hércules S. S. José, Av. Ministro Lafaeyte de Andrade, 1683 - Bl. 3 Apt 404, 

    Marco II - Nova Iguaçu, RJ, Brasil.

 ***/

-- Script de atualização das tabelas

/*** Versão 2014.1 ***/

-- Tabela formapagamento
create table formapagamento (
id bigserial not null,
descricao varchar(50) not null,
primary key(id)
);

-- Tabela aluguel
create table aluguel (
id bigserial not null,
periodo integer,
ano integer,
valor double precision,
vencimento date,
pagamento date,
valorPago double precision,
idContrato bigint,
juros double precision,
multa double precision,
idFormaPagamento bigint,
primary key(id)
);

alter table aluguel add constraint fk_formapagamento_aluguel foreign key (idFormaPagamento) references formapagamento(id);
alter table aluguel add constraint fk_contrato_aluguel foreign key (idContrato) references contrato(id);

insert into formapagamento (descricao) values ('Dinheiro');
insert into formapagamento (descricao) values ('Cheque');

-- Alteração do dono das tabelas
alter table Agenda owner to imobiliaria;
alter table Cliente owner to imobiliaria;
alter table ClientePJ owner to imobiliaria;
alter table ClientePJ_Socio owner to imobiliaria;
alter table ClientePJ_Telefone owner to imobiliaria;
alter table Cliente_Telefone owner to imobiliaria;
alter table Contrato owner to imobiliaria;
alter table Corretor owner to imobiliaria;
alter table Endereco owner to imobiliaria;
alter table Foto owner to imobiliaria;
alter table Funcionario owner to imobiliaria;
alter table Grupo owner to imobiliaria;
alter table Grupo_Permissao owner to imobiliaria;
alter table Imovel owner to imobiliaria;
alter table Imovel_Foto owner to imobiliaria;
alter table IndiceReajuste owner to imobiliaria;
alter table ModeloContrato owner to imobiliaria;
alter table Permissao owner to imobiliaria;
alter table Relatorio owner to imobiliaria;
alter table Socio owner to imobiliaria;
alter table Telefone owner to imobiliaria;
alter table Usuario owner to imobiliaria;

-- Mudanças na tabela modelocontrato - Tarefa #1159
alter table modelocontrato drop column dados;
alter table modelocontrato drop column tamanhoarquivo;
alter table modelocontrato drop column arquivo;

alter table modelocontrato add column modelo text null;

update modelocontrato set modelo = '<p>Entre aqui com seu modelo de contrato</p>';

alter table modelocontrato alter column modelo set not null;

-- Tabela servicomanutencao - Tarefa #1165
create table servicomanutencao (
id bigserial not null,
descricao varchar(50) not null,
primary key(id)
);

-- Mudanças na tabela de aluguel para registrar serviços de manutenção - Tarefa #1167
alter table aluguel add column idServico bigint null;
alter table aluguel add column valorServico double precision default 0.0;

alter table aluguel add constraint fk_servicomanutencao_aluguel foreign key (idServico) references servicomanutencao(id);