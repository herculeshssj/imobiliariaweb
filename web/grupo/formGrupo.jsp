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
	<h:form id="frmGrupo">
		<div id="content">
			
			<h2>Grupo - Cadastrar</h2>
			
					<rich:message for="frmGrupo" style="color: #ff0000; font-weight: bold;"></rich:message>
					<rich:message for="txtModulo" style="color: #ff0000; font-weight: bold;"></rich:message>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Grupo"></h:outputText>
						</f:facet>
						
						* Descrição:
						<h:inputText id="txtDescricao" value="#{grupoMB.grupo.descricao }" maxlength="30" required="true" requiredMessage="Informe o nome do grupo!"/>
						<rich:message for="txtDescricao" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Grupo padrão
						<h:selectBooleanCheckbox value="#{grupoMB.grupo.padrao }"></h:selectBooleanCheckbox>						
						<h:inputHidden></h:inputHidden>
												
					</h:panelGrid>
					
					<h:panelGrid id="pnlPermissao" columns="1" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Permissões"></h:outputText>
						</f:facet>
						
						<h:panelGroup>
						Módulo
						<h:selectOneMenu id="txtModulo" value="#{grupoMB.permissao.modulo }">							
							<f:selectItems value="#{grupoMB.modulos }"/>
						</h:selectOneMenu>
						&nbsp;
						Adicionar
						<h:selectBooleanCheckbox value="#{grupoMB.permissao.add }"/>
						&nbsp;
						Editar
						<h:selectBooleanCheckbox value="#{grupoMB.permissao.edit }"/>
						&nbsp;
						Excluir
						<h:selectBooleanCheckbox value="#{grupoMB.permissao.delete }"/>
						&nbsp;
						Listar
						<h:selectBooleanCheckbox value="#{grupoMB.permissao.list }"/>
						&nbsp;
						Visualizar
						<h:selectBooleanCheckbox value="#{grupoMB.permissao.view }"/>
						&nbsp;
						<a4j:commandButton value="Adicionar" style="padding: 3px 10px;" action="#{grupoMB.adicionarPermissao }" reRender="tablePermissao,pnlPermissao"/>
						</h:panelGroup>
					</h:panelGrid>
					
						<rich:dataTable id="tablePermissao" value="#{grupoMB.listaPermissao }"
							var="item">	
													
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Módulo"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.modulo }"></h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Adicionar"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.add }">
									<f:converter converterId="simnaoconverter"/>
								</h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Editar"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.edit }">
									<f:converter converterId="simnaoconverter"/>
								</h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Excluir"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.delete }">
									<f:converter converterId="simnaoconverter"/>
								</h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Listar"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.list }">
									<f:converter converterId="simnaoconverter"/>
								</h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Visualizar"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.view }">
									<f:converter converterId="simnaoconverter"/>
								</h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Remover"></h:outputText>
								</f:facet>
								<a4j:commandLink value="Remover" reRender="tablePermissao" action="#{grupoMB.removerPermissao }">
									<f:setPropertyActionListener value="#{item.modulo }" target="#{grupoMB.modulo }" />									
								</a4j:commandLink>
							</rich:column>
							
						</rich:dataTable>	
				
		
			<h:commandButton value="Cadastrar" style="padding: 3px 10px;" rendered="#{grupoMB.canAdd }"
				action="#{grupoMB.add }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;"
				immediate="true" action="#{grupoMB.cancelAction }"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>