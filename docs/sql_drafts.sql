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

/*
select * from imovel;
select * from contrato;

select * from cliente;
select * from corretor;

select * from modelocontrato;

alter table modelocontrato drop column dados;
alter table modelocontrato drop column tamanhoarquivo;
alter table modelocontrato drop column arquivo;

alter table modelocontrato add column modelo text null;

update modelocontrato set modelo = '<p>Entre aqui com seu modelo de contrato</p>';

alter table modelocontrato alter column modelo set not null;



*
select * from modelocontrato;
insert into modelocontrato (ativo, descricao, modelo) values (true, 'Modelo 1', '<p>Entre aqui com seu modelo de contrato</p>');
insert into modelocontrato (ativo, descricao, modelo) values (true, 'Modelo 2', '<p>Entre aqui com seu modelo de contrato</p>');
insert into modelocontrato (ativo, descricao, modelo) values (true, 'Modelo 3', '<p>Entre aqui com seu modelo de contrato</p>');

*/