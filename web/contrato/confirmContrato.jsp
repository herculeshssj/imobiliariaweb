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
	<h:form id="frmContrato">
		<div id="content">
			
			<h2>Contrato - Confirmar</h2>
			<br />
			<h4>Tem certeza que deseja continuar ? Esta operação não poderá ser desfeita!</h4>
			
			<rich:message for="frmContrato" style="color: #ff0000; font-weight: bold;"></rich:message>

					<h:panelGrid columns="4" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do contrato"></h:outputText>
						</f:facet>
						
						Número do contrato:
						<h:outputText value="#{contratoMB.contrato.numContrato }"/>						
						
						Situação do contrato:
						<h:outputText value="#{contratoMB.contrato.situacao }">
							<f:converter converterId="situacaocontratoconverter"/>
						</h:outputText>						
						
						Imóvel:
						<h:panelGroup>
						<h:outputText value="#{contratoMB.contrato.imovel.numRegistro }"></h:outputText> - <h:outputText value="#{contratoMB.contrato.imovel.tipoImovel }"></h:outputText>
						</h:panelGroup>					
						
						<h:outputText value="Locatário:"></h:outputText>
						<h:outputText value="#{contratoMB.contrato.locatario.nome }"></h:outputText>
												
						<h:outputText value="Locatário PJ:"></h:outputText>
						<h:outputText value="#{contratoMB.contrato.locatarioPJ.nomeFantasia }"></h:outputText>
												
						<h:outputText value="Fiador:"></h:outputText>
						<h:outputText value="#{contratoMB.contrato.fiador.nome }"></h:outputText>
												
						<h:outputText value="Corretor:"></h:outputText>
						<h:outputText value="#{contratoMB.contrato.corretor.nome }"></h:outputText>
												
						Início do contrato:
						<h:outputText value="#{contratoMB.contrato.inicioContrato }">
							<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText>
						
						Término do contrato:
						<h:outputText value="#{contratoMB.contrato.terminoContrato }">
							<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText>
						
						Prazo do contrato (meses):
						<h:outputText id="txtPrazo" value="#{contratoMB.contrato.prazo }"/>						
						
						Depósito (R$):
						<h:outputText id="txtDeposito" value="#{contratoMB.contrato.deposito }"/>						
												
						Seguradora:
						<h:outputText id="txtSeguradora" value="#{contratoMB.contrato.seguradora }"/>						
												
						Foro:
						<h:outputText id="txtForo" value="#{contratoMB.contrato.foro }"/>						
												
						Comissão imobiliária (%):
						<h:outputText id="txtComissaoImobiliaria" value="#{contratoMB.contrato.comissaoImobiliaria }"/>						
												
						Comissão corretor (%):
						<h:outputText id="txtComissaoCorretor" value="#{contratoMB.contrato.comissaoCorretor }"/>						
						
						Valor do aluguel (R$):
						<h:outputText id="txtValor" value="#{contratoMB.contrato.valor }"/>						
												
						Dia de vencimento do aluguel:
						<h:outputText id="txtDiaVencimento" value="#{contratoMB.contrato.diaVencimento }"/>						
												
						Desconto no aluguel (R$):
						<h:outputText id="txtDesconto" value="#{contratoMB.contrato.desconto }"/>						
												
						Período do desconto (meses):
						<h:outputText id="txtPeriodoDesconto" value="#{contratoMB.contrato.periodoDesconto }"/>						
												
						Multa por atraso no aluguel (%):
						<h:outputText id="txtMulta" value="#{contratoMB.contrato.multa }"/>						
												
						Juros por atraso no aluguel (%):
						<h:outputText id="txtJuros" value="#{contratoMB.contrato.juros }"/>						
												
						Índice de reajuste (%):
						<h:outputText value="#{contratoMB.contrato.indiceReajuste.descricao }"></h:outputText>						
												
						Modelo de contrato:
						<h:outputText value="#{contratoMB.contrato.modeloContrato.descricao }"></h:outputText>								
						
					</h:panelGrid>
			<br />
			<h:commandButton value="Confirmar" style="padding: 3px 10px;" rendered="#{contratoMB.canDelete }"
				action="#{contratoMB.delete }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;"
				immediate="true" action="#{contratoMB.cancelConfirm }"></h:commandButton>
				
			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>