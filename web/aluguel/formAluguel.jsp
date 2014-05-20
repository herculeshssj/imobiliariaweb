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

<f:view>

<f:subview id="cabecalho">
	<jsp:include page="../header.jsp"></jsp:include>
</f:subview>
	<h:form id="frmAluguel">
		<div id="content">
			
			<h2>Aluguel - Cadastrar</h2>
			
					<rich:message for="frmAluguel" style="color: #ff0000; font-weight: bold;"></rich:message>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do Aluguel"></h:outputText>
						</f:facet>
						
						* Contrato:
						<h:selectOneMenu id="txtContrato" value="#{aluguelMB.idContrato}" required="true" requiredMessage="Informe o contrato!">
							<f:selectItems value="#{aluguelMB.listaContrato}" />
						</h:selectOneMenu>
						<rich:message for="txtContrato" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Período:
						<h:inputText id="txtPeriodo" value="#{aluguelMB.aluguel.periodo }" size="5" required="true" requiredMessage="Informe o período!"/>
						<rich:message for="txtPeriodo" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Ano:
						<h:inputText id="txtAno" value="#{aluguelMB.aluguel.ano }" size="5" required="true" requiredMessage="Informe o ano!"/>
						<rich:message for="txtAno" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Vencimento:
						<h:panelGroup>
							<h:inputText id="txtVencimento" value="#{aluguelMB.aluguel.vencimento }" size="12" required="true" requiredMessage="Informe a data de vencimento!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>
							</h:inputText>
							<rich:jQuery selector="#txtVencimento" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtVencimento" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Valor:
						<h:inputText id="txtValor" value="#{aluguelMB.aluguel.valor }" size="10" required="true" requiredMessage="Informe o valor do aluguel!">
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>
						<rich:message for="txtValor" style="color: #ff0000; font-weight: bold;"></rich:message>
						
				</h:panelGrid>
						
			<h:commandButton value="Cadastrar" style="padding: 3px 10px;"
				action="#{aluguelMB.add }" rendered="#{aluguelMB.canAdd }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;"
				immediate="true" action="#{aluguelMB.cancelAction }"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>