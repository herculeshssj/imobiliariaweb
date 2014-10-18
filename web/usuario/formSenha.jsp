<!-- 

    Copyright (c) 2011, 2014 Hércules S. S. José
    

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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<f:view>

	<f:subview id="cabecalho">
		<jsp:include page="../header.jsp"></jsp:include>
	</f:subview>
	<h:form id="frmUsuario">
		<div id="content">

			<h2>Alterar sua senha</h2>

			<rich:messages style="font-weight: bold;"></rich:messages>
			<!-- Mensagens de aviso -->

			<h:panelGrid columns="3" cellspacing="10"
				style="padding: 10px 10px 10px 10px;">
				
				<f:facet name="header">
					<h:outputText value="Alterar senha"></h:outputText>
				</f:facet>
						
						Login:
						<h:outputText id="txtLogin" value="#{homeMB.usuarioLogado }" />
						<h:inputHidden></h:inputHidden>				
						
						* Senha atual:						
						<h:inputSecret id="txtSenhaAtual" value="#{usuarioMB.senhaAtual }"
					required="true" requiredMessage="Informe sua senha atual!" />
					<rich:message for="txtSenhaAtual" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Nova Senha:						
						<h:inputSecret id="txtNovaSenha" value="#{usuarioMB.novaSenha }"
					required="true" requiredMessage="Informe sua nova senha!" />
					<rich:message for="txtNovaSenha" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Confirma a senha:
						<h:inputSecret id="txtConfirmaSenha"
					value="#{usuarioMB.confirmaSenha }" required="true"
					requiredMessage="Confirme sua nova senha!" />
					<rich:message for="txtConfirmaSenha" style="color: #ff0000; font-weight: bold;"></rich:message>
				
			</h:panelGrid>
			
			<h:commandButton value="Alterar senha" style="padding: 3px 10px;"
				action="#{usuarioMB.alterarSenha }"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>