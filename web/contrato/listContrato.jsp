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
<%@ taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j" %>

<f:view>

<f:subview id="cabecalho">
	<jsp:include page="../header.jsp"></jsp:include>
</f:subview>
	<h:form>
		<div id="content">
			
			<h2>Contratos</h2>
			
			<rich:messages style="color: #000000; font-weight: bold;"></rich:messages>			
			
			<h:commandButton value="Novo contrato" style="padding: 3px 10px;" action="#{contratoMB.addView }" rendered="#{contratoMB.canAdd }"/>
			&nbsp;
			<h:panelGroup rendered="#{contratoMB.canList }">
			Nº do contrato:
			<h:inputText value="#{contratoMB.findValue }"/>
			&nbsp;
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{contratoMB.simpleSearch }"/>
			&nbsp;
			<h:commandLink value="Mais opções de pesquisa" action="#{contratoMB.searchView }"/>
			</h:panelGroup>
			<br/><br/>
			<rich:dataTable id="tableContrato" width="100%" value="#{contratoMB.dadosModelo }" var="item" rows="15">
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Nº Contrato"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.numContrato }"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Imóvel"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.imovel.numRegistro }"/> - <h:outputText value="#{item.imovel.tipoImovel }"/>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Prazo"></h:outputText>
					</f:facet>
					De 
					<h:outputText value="#{item.inicioContrato }">
						<f:convertDateTime pattern="dd/MM/yyyy"/>
					</h:outputText><br/>
					a
					<h:outputText value="#{item.terminoContrato }">
						<f:convertDateTime pattern="dd/MM/yyyy"/>
					</h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Situacao"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.situacao }">
						<f:converter converterId="situacaocontratoconverter"/>
					</h:outputText>
				</rich:column>
				
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ver/Editar"></h:outputText>
					</f:facet>					
					<h:commandLink value="Editar" action="#{contratoMB.editView }" rendered="#{contratoMB.canView }">
						<f:setPropertyActionListener value="#{item.id }" target="#{contratoMB.idContrato }"/>
					</h:commandLink>
				</rich:column>
				
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Contrato"></h:outputText>
					</f:facet>
					<h:commandLink value="Gerar" action="#{contratoMB.gerarContrato }" rendered="#{contratoMB.canView }" target="_blank">
						<f:setPropertyActionListener value="#{item.id }" target="#{contratoMB.idContrato }"/>
					</h:commandLink>
					
				</rich:column>
				
			</rich:dataTable>
			<rich:datascroller id="dsContrato" for="tableContrato"></rich:datascroller>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>