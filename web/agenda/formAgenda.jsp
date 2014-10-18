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
	<h:form id="frmAgenda">
		
			<h2>Agenda de visitas - Cadastrar</h2>
			
					<rich:message for="frmAgenda" style="color: #ff0000; font-weight: bold;"></rich:message>
			
					<h:panelGrid id="pnlAgenda" columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do agendamento"></h:outputText>
						</f:facet>
						
						* Imóvel:
						<h:selectOneMenu id="txtImovel" required="true" requiredMessage="Informe o imóvel!" value="#{agendaMB.idImovel }">
							<f:selectItem itemLabel="Selecione"/>
							<f:selectItems value="#{agendaMB.imoveis }"/>
						</h:selectOneMenu>
						<rich:message for="txtImovel" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Corretor:
						<h:selectOneMenu id="txtCorretor" required="true" requiredMessage="Informe o corretor!" value="#{agendaMB.idCorretor }">
							<f:selectItem itemLabel="Selecione"/>
							<f:selectItems value="#{agendaMB.corretores }"/>
						</h:selectOneMenu>
						<rich:message for="txtCorretor" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Nome do cliente:
						<h:inputText id="txtNomeCliente" value="#{agendaMB.agenda.nomeCliente }" 
							maxlength="100" required="true"
							requiredMessage="Informe o nome do cliente!"
							validatorMessage="Nome do cliente deve ter no mínimo 3 dígitos!">
							<f:validateLength minimum="3"></f:validateLength>
						</h:inputText>
						<rich:message for="txtNomeCliente" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* E-Mail:
						<h:inputText id="txtEmailCliente" value="#{agendaMB.agenda.emailCliente }" 
							maxlength="100" required="true"
							requiredMessage="Informe o e-mail do cliente!">							
							<f:validator validatorId="emailvalidator"/>
						</h:inputText>
						<rich:message for="txtEmailCliente" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Telefone de contato:
						<h:panelGroup>
							<h:inputText id="txtTelefoneCliente" value="#{agendaMB.agenda.telefoneCliente }" required="true" requiredMessage="Informe o telefone do cliente!"/>
							<rich:jQuery selector="#txtTelefoneCliente" query="mask('(99) 9999-9999')" timing="onload"/>														
						</h:panelGroup>
						<rich:message for="txtTelefoneCliente" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Data da visita:
						<h:panelGroup>
						<h:inputText id="txtDataVisita" value="#{agendaMB.agenda.data }" required="true" requiredMessage="Informe a data da visita!" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
							<f:convertDateTime pattern="dd/MM/yyyy"/>							
						</h:inputText>
						<rich:jQuery selector="#txtDataVisita" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>						
						<rich:message for="txtDataVisita" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Hora de início:
						<h:panelGroup>
						<h:selectOneMenu id="txtHoraInicio" value="#{agendaMB.agenda.horaInicio }" disabled="#{agendaMB.agenda.visitado }">														
							<f:selectItems value="#{agendaMB.horas }"/>
						</h:selectOneMenu>&nbsp;h
						</h:panelGroup>
						<h:inputHidden/>
						
						Hora de término:
						<h:panelGroup>
						<h:selectOneMenu value="#{agendaMB.agenda.horaFim }" disabled="#{agendaMB.agenda.visitado }">							
							<f:selectItems value="#{agendaMB.horas }"/>
						</h:selectOneMenu>&nbsp;h
						</h:panelGroup>
						<h:inputHidden/>	
						
					</h:panelGrid>
			
			<h:commandButton value="Agendar" style="padding: 3px 10px;" rendered="#{agendaMB.canAdd }"
				action="#{agendaMB.add }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;"
				immediate="true" action="#{agendaMB.cancelAction }"></h:commandButton>

			<div style="clear: both;"></div>

	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>