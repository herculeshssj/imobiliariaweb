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
			
			<h2>Usuário - Editar</h2>
			
					<rich:message for="frmUsuario" style="color: #ff0000; font-weight: bold;"></rich:message>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do usuário"></h:outputText>
						</f:facet>
						
						Funcionário:
						<h:outputText value="#{usuarioMB.usuario.funcionario.nome }"></h:outputText>						
						<h:inputHidden></h:inputHidden>
						
						* Grupo:
						<h:selectOneMenu id="txtGrupo" required="true" requiredMessage="Informe o grupo!" value="#{usuarioMB.idGrupo }" disabled="#{!usuarioMB.usuario.ativo }">
							<f:selectItem itemLabel="Selecione"/>
							<f:selectItems value="#{usuarioMB.grupos }"/>
						</h:selectOneMenu>
						<rich:message for="txtGrupo" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Login:
						<h:outputText id="txtLogin" value="#{usuarioMB.usuario.login }" />
						<h:inputHidden></h:inputHidden>
						
						Data de criação:
						<h:outputText value="#{usuarioMB.usuario.dataCriacao }">
							<f:convertDateTime pattern="dd/MM/yyyy HH:mm:ss"/>
						</h:outputText>
						<h:inputHidden></h:inputHidden>
						
						Nova Senha:						
						<h:inputSecret id="txtNovaSenha" value="#{usuarioMB.novaSenha }" disabled="#{!usuarioMB.usuario.ativo }" />
						<rich:message for="txtNovaSenha" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Confirma a senha:
						<h:inputSecret id="txtConfirmaSenha" value="#{usuarioMB.confirmaSenha }" disabled="#{!usuarioMB.usuario.ativo }" />
						<rich:message for="txtConfirmaSenha" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Administrador
						<h:selectBooleanCheckbox value="#{usuarioMB.usuario.admin }" disabled="#{!usuarioMB.usuario.ativo }"/>						
						<h:inputHidden></h:inputHidden>
												
					</h:panelGrid>
		
			<h:commandButton value="Editar" style="padding: 3px 10px;" disabled="#{!usuarioMB.usuario.ativo || usuarioMB.usuario.login=='admin'}"
				action="#{usuarioMB.edit }" rendered="#{usuarioMB.canEdit }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;"
				immediate="true" action="#{usuarioMB.cancelAction }"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>