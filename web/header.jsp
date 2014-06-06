<!-- 

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

 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<!DOCTYPE html>
<html dir="ltr" lang="pt-BR">
<head>
<meta charset="UTF-8" />
<title>HSlife - ImobiliariaWeb</title>
<link href="../style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../script/jquery.maskedinput-1.3.js"></script>

</head>

<body class="single single-post postid-1 single-format-standard">
<div id="wrapper" class="hfeed">
	<div id="header">
		<div id="masthead">
			<div id="branding" role="banner">

								<div id="site-title">
					<span>
						<a href="#" title="HSlife - ImobiliariaWeb" rel="home">HSlife</a>
					</span>
				</div>
				                <div id="site-description">Seja bem vindo <h:outputText value="#{homeMB.usuarioLogado }"/></div>

										<img src="../images/header.png" width="940" height="198" alt="" />

								</div><!-- #branding -->

			<div id="access" role="navigation">
			  				<div class="skip-link screen-reader-text"><a href="#content" title="Pular para o conteúdo">Pular para o conteúdo</a></div>
								<div class="menu">
								<h:form>
                                	<ul>                                    	
                                        <li class="page_item page-item-8"><h:commandLink value="Início" action="#{homeMB.atualizaAgenda }"/></li>
                                        <%-- <li class="page_item page-item-8"><h:commandLink value="Cadastros" action="menuCadastro"/></li>--%>
                                        <li class="page_item page-item-8"><h:commandLink value="Funcionários" action="menuFuncionario"/></li>
                                        <li class="page_item page-item-8"><h:commandLink value="Imóveis" action="menuImovel"/></li>
                                        <li class="page_item page-item-8"><h:commandLink value="Contrato" action="menuContrato"/></li>
                                        <li class="page_item page-item-8"><h:commandLink value="Aluguéis" action="menuAluguel"/></li>                                        
                                        <li class="page_item page-item-8"><h:commandLink value="Relatórios" action="menuRelatorio"/></li>
                                        <li class="page_item page-item-8"><h:commandLink value="Opções" action="menuOpcao"/></li>
                                        <li class="page_item page-item-8"><h:commandLink value="Sair" action="#{homeMB.logout }"/></li>
                                                                             
                                    </ul>
                               </h:form>
                                  </div>
			</div><!-- #access -->
		</div><!-- #masthead -->

	</div><!-- #header -->

	<div id="main">

		<div id="container">
			<div id="content" role="main">