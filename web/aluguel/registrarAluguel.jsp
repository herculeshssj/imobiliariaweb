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
			
			<h2>Aluguel - Registrar</h2>
			

			<rich:message for="frmAluguel" style="color: #ff0000; font-weight: bold;"></rich:message>
					
			<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do Aluguel"></h:outputText>
						</f:facet>
						
						Contrato:
						<h:outputText value="#{aluguelMB.aluguel.contrato.id }" />
						<h:outputText />
						
						Período:
						<h:outputText value="#{aluguelMB.aluguel.periodo }" />
						<h:outputText />
						
						Ano:
						<h:outputText value="#{aluguelMB.aluguel.ano }" />
						<h:outputText />
						
						Vencimento:
						<h:outputText value="#{aluguelMB.aluguel.vencimento }" >
							<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText>
						<h:outputText />
						
						* Valor:
						<h:inputText id="txtValor" value="#{aluguelMB.aluguel.valor }" size="10" required="true" requiredMessage="Informe o valor do aluguel!">
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>
						<rich:message for="txtValor" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Juros por atraso (R$):
						<h:inputText id="txtJuros" value="#{aluguelMB.aluguel.juros }" size="10">
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>
						<rich:message for="txtJuros" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Multa por atraso (R$):
						<h:inputText id="txtMulta" value="#{aluguelMB.aluguel.multa }" size="10">
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>
						<rich:message for="txtMulta" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Desconto (R$):
						<h:inputText id="txtDesconto" value="#{aluguelMB.aluguel.desconto }" size="10">
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>
						<rich:message for="txtDesconto" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Serviço de manutenção:
						<h:selectOneMenu id="txtServicoManutencao" value="#{aluguelMB.idServicoManutencao}">
							<f:selectItem itemLabel="Selecione um serviço de manutenção" />
							<f:selectItems value="#{aluguelMB.listaServicoManutencao}" />
						</h:selectOneMenu>
						<h:outputText />
						
						Valor do serviço (R$):
						<h:inputText id="txtValorServico" value="#{aluguelMB.aluguel.valorServico }" size="10">
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>
						<h:outputText />
						
						* Pagamento:
						<h:panelGroup>
							<h:inputText id="txtPagamento" value="#{aluguelMB.aluguel.pagamento }" size="12" required="true" requiredMessage="Informe a data de pagamento!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>
							</h:inputText>
							<rich:jQuery selector="#txtPagamento" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtPagamento" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Valor pago (R$):
						<h:panelGroup>
							<h:inputText id="txtValorPago" value="#{aluguelMB.aluguel.valorPago }" size="10" required="true" requiredMessage="Informe o valor pago!">
								<f:convertNumber type="currency" pattern="###.##"/>
							</h:inputText>
							&nbsp;&nbsp;
							<a4j:commandLink value="Atualizar valor" action="#{aluguelMB.atualizaValorAluguel }" reRender="txtValorPago"/>
						</h:panelGroup>
						<rich:message for="txtValorPago" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Forma de pagamento:
						<h:selectOneMenu id="txtFormaPagamento" value="#{aluguelMB.idFormaPagamento}">
							<f:selectItems value="#{aluguelMB.listaFormaPagamento}" />
						</h:selectOneMenu>
						<h:outputText />
						
				</h:panelGrid>		
						
			<h:commandButton value="Registrar" style="padding: 3px 10px;" action="#{aluguelMB.registrar }" rendered="#{aluguelMB.canEdit }"/>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;" action="#{aluguelMB.cancelAction }" immediate="true"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>