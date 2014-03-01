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
<%@ taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j" %>

<f:view>

<f:subview id="cabecalho">
	<jsp:include page="../header.jsp"></jsp:include>
</f:subview>
	<h:form>
			
			<h2>Agenda de visitas</h2>
			
			<rich:messages style="color: #000000; font-weight: bold;"></rich:messages>		

			<h:commandButton value="Novo agendamento" style="padding: 3px 10px;" action="#{agendaMB.addView }" rendered="#{agendaMB.canAdd }"/>
			<h:panelGroup rendered="#{agendaMB.canList }">
			&nbsp; Informe uma data:
			<h:inputText id="txtFindDate" value="#{agendaMB.findDate }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
				<f:convertDateTime pattern="dd/MM/yyyy"/>				
			</h:inputText>
			<rich:jQuery selector="#txtFindDate" query="mask('99/99/9999')" timing="onload"/>
				
			&nbsp;
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{agendaMB.simpleSearch }"></h:commandButton>
			&nbsp;&nbsp;&nbsp;
			<h:commandLink value="Mais opções de pesquisa" action="#{agendaMB.searchView }"></h:commandLink>
			</h:panelGroup>
			<br /> <br/>
			<rich:dataTable id="tableAgenda" width="100%" var="item" value="#{agendaMB.dadosModelo}" rows="15">
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Cliente"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.nomeCliente }"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Telefone"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.telefoneCliente }"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Data"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.data }">
						<f:convertDateTime pattern="dd/MM/yyyy"/>
					</h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Hora"></h:outputText>
					</f:facet>
					de <h:outputText value="#{item.horaInicio }" />h às <h:outputText value="#{item.horaFim }" />h 
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Visita"></h:outputText>
					</f:facet>
					<h:commandLink value="Registrar" rendered="#{!item.visitado }" action="#{agendaMB.registerView}"/>					
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ver/Remarcar"></h:outputText>
					</f:facet>
					<h:panelGroup rendered="#{agendaMB.canView }">
					<h:commandLink value="Remarcar" action="#{agendaMB.editView }" rendered="#{!item.visitado }"/>					
					<h:commandLink value="Ver" action="#{agendaMB.editView }" rendered="#{item.visitado }"/>
					</h:panelGroup>						
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Desmarcar"></h:outputText>
					</f:facet>
					<h:panelGroup rendered="#{agendaMB.canAdd }">
					<h:commandLink value="Desmarcar" action="#{agendaMB.deleteView }" rendered="#{!item.visitado }"/>
					</h:panelGroup>					
				</rich:column>				
			</rich:dataTable>
			<rich:datascroller id="dsAgenda" for="tableAgenda"></rich:datascroller>

			<div style="clear: both;"></div>

	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>