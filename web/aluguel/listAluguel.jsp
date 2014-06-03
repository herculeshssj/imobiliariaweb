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
	<h:form>
		<div id="content">
			
			<h2>Aluguéis</h2>
			
			<rich:messages style="color: #000000; font-weight: bold;"></rich:messages>
			
			<h:commandButton value="Novo aluguel" style="padding: 3px 10px;" action="#{aluguelMB.addView }" rendered="#{aluguelMB.canAdd }"/>
			<h:panelGroup rendered="#{aluguelMB.canList }">
			&nbsp; 
			Contrato:
			<h:selectOneMenu value="#{aluguelMB.idContrato}">
				<f:selectItems value="#{aluguelMB.listaContrato}" />
			</h:selectOneMenu>	
			&nbsp;
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{aluguelMB.simpleSearch }"></h:commandButton>
			&nbsp;&nbsp;&nbsp;
			<h:commandLink value="Mais opções de pesquisa" action="#{aluguelMB.searchView }"></h:commandLink>
			</h:panelGroup>
			<br /> <br/>
			<rich:dataTable id="tableAluguel" width="100%" value="#{aluguelMB.dadosModelo }" var="item">
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Período"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.periodo}" style="color: #0000FF;" rendered="#{item.diasAtrasados > 10 and item.diasAtrasados <= 30}"/>
					<h:outputText value="#{item.periodo}" style="color: #FF0000;" rendered="#{item.diasAtrasados > 30}"/>
					<h:outputText value="#{item.periodo}" rendered="#{item.diasAtrasados <= 10}"/>
				</rich:column>
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ano"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.ano}" style="color: #0000FF;" rendered="#{item.diasAtrasados > 10 and item.diasAtrasados <= 30}"/>
					<h:outputText value="#{item.ano}" style="color: #FF0000;" rendered="#{item.diasAtrasados > 30}"/>
					<h:outputText value="#{item.ano}" rendered="#{item.diasAtrasados <= 10}"/>
				</rich:column>
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Valor"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.valor}" style="color: #0000FF;" rendered="#{item.diasAtrasados > 10 and item.diasAtrasados <= 30}"/>
					<h:outputText value="#{item.valor}" style="color: #FF0000;" rendered="#{item.diasAtrasados > 30}"/>
					<h:outputText value="#{item.valor}" rendered="#{item.diasAtrasados <= 10}"/>
				</rich:column>
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Vencimento"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.vencimento}" style="color: #0000FF;" rendered="#{item.diasAtrasados > 10 and item.diasAtrasados <= 30}"/>
					<h:outputText value="#{item.vencimento}" style="color: #FF0000;" rendered="#{item.diasAtrasados > 30}"/>
					<h:outputText value="#{item.vencimento}" rendered="#{item.diasAtrasados <= 10}"/>
				</rich:column>
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Pagamento"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.pagamento}" style="color: #0000FF;" rendered="#{item.diasAtrasados > 10 and item.diasAtrasados <= 30}"/>
					<h:outputText value="#{item.pagamento}" style="color: #FF0000;" rendered="#{item.diasAtrasados > 30}"/>
					<h:outputText value="#{item.pagamento}" rendered="#{item.diasAtrasados <= 10}"/>
				</rich:column>
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Valor pago"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.valorPago}" style="color: #0000FF;" rendered="#{item.diasAtrasados > 10 and item.diasAtrasados <= 30}"/>
					<h:outputText value="#{item.valorPago}" style="color: #FF0000;" rendered="#{item.diasAtrasados > 30}"/>
					<h:outputText value="#{item.valorPago}" rendered="#{item.diasAtrasados <= 10}"/>
				</rich:column>
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ver/Editar"></h:outputText>
					</f:facet>					
					<h:commandLink value="Ver/Editar" action="#{aluguelMB.editView }" rendered="#{aluguelMB.canView }"/>
				</rich:column>
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Registrar/Recibo"></h:outputText>
					</f:facet>					
					<h:commandLink value="Registrar" action="#{aluguelMB.registrarView }" rendered="#{aluguelMB.canEdit and item.pagamento == null}"/>
					<h:commandLink value="Recibo" action="#{aluguelMB.gerarRecibo }" rendered="#{aluguelMB.canView and item.pagamento != null}"/>
				</rich:column>				
			</rich:dataTable>
			<rich:datascroller id="dsAluguel" for="tableAluguel"></rich:datascroller>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>