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

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<f:view>

<f:subview id="cabecalho">
	<jsp:include page="../header.jsp"></jsp:include>
</f:subview>

	<style>
		.rich-fileupload-list-overflow {
			overflow: auto; overflow-y: hidden; overflow-x: auto;
		} 
	</style>
	<h:form id="frmModeloContrato">
		<div id="content">
			
			<h2>Modelos de Contrato</h2>
			
			<rich:messages style="font-weight: bold;"></rich:messages>
			<!-- Mensagens de aviso -->

			<h:commandButton value="Novo modelo" style="padding: 3px 10px;" action="#{modeloContratoMB.addView }" rendered="#{modeloContratoMB.canAdd }"/>
			&nbsp;
			<h:panelGroup rendered="#{modeloContratoMB.canList }">
			Descrição:
			<h:inputText value="#{modeloContratoMB.findValue }" />
			&nbsp;
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{modeloContratoMB.simpleSearch }"/>
			</h:panelGroup>
			<br/><br/>
			
					<h:panelGrid id="pnlFormulario" columns="2">
						<f:facet name="header">
							<h:outputText value="Cadastrar/Editar"></h:outputText>
						</f:facet>
						
						* Descrição:
						<h:inputText value="#{modeloContratoMB.modeloContrato.descricao }" size="50" maxlength="100" disabled="#{!modeloContratoMB.modeloContrato.ativo }"/>
									
						<h:outputText />									
						<h:commandButton value="Salvar" style="padding: 3px 10px;" action="#{modeloContratoMB.add }" disabled="#{!modeloContratoMB.modeloContrato.ativo }" rendered="#{modeloContratoMB.canAdd || modeloContratoMB.canEdit }"/>
			
					</h:panelGrid>
			<div id="modelo_contrato">
				<rich:editor id="txtEditor" width="859" height="300" theme="advanced"/>		
			</div>
			
			<br/>
			
			<rich:dataTable id="tableModeloContrato" width="100%" var="item" value="#{modeloContratoMB.dadosModelo }" rows="15">
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Descrição"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.descricao}"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ativo"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.ativo }">
						<f:converter converterId="simnaoconverter"/> 
					</h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Visualizar"></h:outputText>
					</f:facet>
					<h:commandLink value="Visualizar" action="#{modeloContratoMB.editView }" rendered="#{modeloContratoMB.canView }">
						<f:setPropertyActionListener value="#{item.id }" target="#{modeloContratoMB.idModeloContrato }"/>
					</h:commandLink>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ver/Editar"></h:outputText>
					</f:facet>
					<h:commandLink value="Editar" action="#{modeloContratoMB.editView }" rendered="#{modeloContratoMB.canEdit }">
						<f:setPropertyActionListener value="#{item.id }" target="#{modeloContratoMB.idModeloContrato }"/>
					</h:commandLink>						
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ativar/Desativar"></h:outputText>
					</f:facet>
					<h:panelGroup rendered="#{modeloContratoMB.canEdit }">
						<h:commandLink value="Ativar" rendered="#{!item.ativo }" action="#{modeloContratoMB.habilitar }">
							<f:setPropertyActionListener value="#{item.id }" target="#{modeloContratoMB.idModeloContrato }"/>
						</h:commandLink>						
						<h:commandLink value="Desativar" rendered="#{item.ativo }" action="#{modeloContratoMB.habilitar }">
							<f:setPropertyActionListener value="#{item.id }" target="#{modeloContratoMB.idModeloContrato }"/>
						</h:commandLink>					
					</h:panelGroup>						
				</rich:column>
				
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Excluir"></h:outputText>
					</f:facet>
					<h:commandLink rendered="#{modeloContratoMB.canDelete }" value="Excluir" action="#{modeloContratoMB.delete }" onclick="javascript:return(confirm('Tem certeza que deseja excluir esta modelo de contrato?'))">
						<f:setPropertyActionListener value="#{item.id }" target="#{modeloContratoMB.idModeloContrato }"/>
					</h:commandLink>					
				</rich:column>			
			</rich:dataTable>
			
			<rich:datascroller id="dsModeloContrato" for="tableModeloContrato"></rich:datascroller>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>