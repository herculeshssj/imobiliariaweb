<!-- 

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

 -->

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<html dir="ltr" lang="pt-BR">
<head>
<meta charset="UTF-8" />
<title>Real Administradora de Imóveis</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>

<body class="single single-post postid-1 single-format-standard">
<div id="wrapper" class="hfeed">
	<div id="header">
		<div id="masthead">
			<div id="branding" role="banner">

								<div id="site-title">
					<span>
						<a href="http://hslife.com.br/imobiliaria/" title="Real Administradora de imóveis" rel="home">Real Administradora de Imóveis</a>
					</span>
				</div>
				                <div id="site-description">Seja bem vindo</div>

										<img src="images/header.png" width="940" height="198" alt="" />

								</div><!-- #branding -->

			<div id="access" role="navigation">
			  				<div class="skip-link screen-reader-text"><a href="#content" title="Pular para o conteúdo">Pular para o conteúdo</a></div>
								
			</div><!-- #access -->
		</div><!-- #masthead -->

	</div><!-- #header -->

	<div id="main">

		<div id="container">
			<div id="content" role="main">

	<f:view>
	<center>
	<h3>Para acessar informe seu login e senha</h3>
		<h:form>
		<rich:messages style="color: #ff0000; font-weight: bold;"></rich:messages>
			<h:panelGrid columns="2" cellspacing="10">

                                        Login:
                                        <h:inputText value="#{homeMB.usuario.login}" required="true" requiredMessage="Informe o seu login."/>

                                        Senha:
                                        <h:inputSecret value="#{homeMB.usuario.senha}" required="true" requiredMessage="Informe a sua senha."/>

                                        <br/>
                                        <h:commandButton value="Entrar no Sistema" action="#{homeMB.login}" style="padding: 3px 10px;"/>

                                    </h:panelGrid>
                                   
                                    
		</h:form>
		</center>
	</f:view>
			</div><!-- #content -->

		</div><!-- #container -->


		<!-- #primary .widget-area -->

	</div><!-- #main -->

	<div id="footer" role="contentinfo">
		<div id="colophon">



			<div id="site-info">
					Copyright &copy; 2011 - Todos os direitos reservados a <a href="http://hslife.com.br/">HSlife Serviços de TI</a>.
			</div><!-- #site-info -->

			<div id="site-generator">
								<a href="http://wordpress.org/" title="Plataforma de Publicação Pessoal Semântica" rel="generator">Orgulhosamente criado a partir de um tema WordPress.</a>

			</div>
			<!-- #site-generator -->

		</div><!-- #colophon -->
	</div><!-- #footer -->

</div><!-- #wrapper -->

</body>
</html>