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
			
			<h2>Contrato - Editar</h2>
			
			<rich:message for="frmContrato" style="color: #ff0000; font-weight: bold;"></rich:message>

					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do contrato"></h:outputText>
						</f:facet>
						
						Número do contrato:
						<h:outputText value="#{contratoMB.contrato.numContrato }"/>
						<h:inputHidden/>
						
						Situação do contrato:
						<h:outputText value="#{contratoMB.contrato.situacao }">
							<f:converter converterId="situacaocontratoconverter"/>
						</h:outputText>
						<h:inputHidden/>
						
						* Imóvel:
						<h:panelGroup>
						<h:outputText value="#{contratoMB.contrato.imovel.numRegistro }"></h:outputText> - <h:outputText value="#{contratoMB.contrato.imovel.tipoImovel }"></h:outputText>
						</h:panelGroup> 
						<h:inputHidden/>
						
						<h:outputText value="Locatário:"></h:outputText>
						<h:outputText value="#{contratoMB.contrato.locatario.nome }"></h:outputText>
						<h:inputHidden/>
						
						<h:outputText value="Locatário PJ:"></h:outputText>
						<h:outputText value="#{contratoMB.contrato.locatarioPJ.nomeFantasia }"></h:outputText>
						<h:inputHidden/>
						
						<h:outputText value="Fiador:"></h:outputText>
						<h:outputText value="#{contratoMB.contrato.fiador.nome }"></h:outputText>
						<h:inputHidden/>
						
						<h:outputText value="Corretor:"></h:outputText>
						<h:outputText value="#{contratoMB.contrato.corretor.nome }"></h:outputText>
						<h:inputHidden/>
						
						* Início do contrato:
						<h:panelGroup>
							<h:inputText id="txtInicioContrato" required="true" requiredMessage="Informe a data de início do contrato!" value="#{contratoMB.contrato.inicioContrato }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtInicioContrato" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>		
						<rich:message for="txtInicioContrato" style="color: #ff0000; font-weight: bold;"/>
						
						Término do contrato:
						<h:panelGroup>
						<h:outputText value="#{contratoMB.contrato.terminoContrato }" rendered="#{contratoMB.contrato.prazo > 0}">
							<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText>
						<h:outputText value="Indeterminado" rendered="#{contratoMB.contrato.prazo == 0 }"></h:outputText>
						</h:panelGroup>
						<h:inputHidden/>
						
						* Prazo do contrato (meses):
						<h:inputText id="txtPrazo" required="true" requiredMessage="Informe o prazo do contrato!" value="#{contratoMB.contrato.prazo }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }"/>						
						<rich:message for="txtPrazo" style="color: #ff0000; font-weight: bold;"/>
						
						Depósito (R$):
						<h:inputText id="txtDeposito" value="#{contratoMB.contrato.deposito }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>						
						<h:inputHidden/>
						
						Seguradora:
						<h:inputText id="txtSeguradora" value="#{contratoMB.contrato.seguradora }" size="30" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }"/>						
						<h:inputHidden/>
						
						Foro:
						<h:inputText id="txtForo" value="#{contratoMB.contrato.foro }" size="30" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }"/>						
						<h:inputHidden/>
						
						Comissão imobiliária (%):
						<h:inputText id="txtComissaoImobiliaria" value="#{contratoMB.contrato.comissaoImobiliaria }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">
							<f:convertNumber type="percent" pattern="###.##"/>
						</h:inputText>						
						<h:inputHidden/>
						
						Comissão corretor (%):
						<h:inputText id="txtComissaoCorretor" value="#{contratoMB.contrato.comissaoCorretor }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">
							<f:convertNumber type="percent" pattern="###.##"/>
						</h:inputText>						
						<h:inputHidden/>
						
						* Valor do aluguel (R$):
						<h:inputText id="txtValor" required="true" requiredMessage="Informe o valor do aluguel!" value="#{contratoMB.contrato.valor }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>						
						<rich:message for="txtValor" style="color: #ff0000; font-weight: bold;"/>
						
						* Dia de vencimento do aluguel:
						<h:inputText id="txtDiaVencimento" required="true" requiredMessage="Informe o dia de vencimento do aluguel!" value="#{contratoMB.contrato.diaVencimento }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }"/>						
						<rich:message for="txtDiaVencimento" style="color: #ff0000; font-weight: bold;"/>
						
						Desconto no aluguel (R$):
						<h:inputText id="txtDesconto" value="#{contratoMB.contrato.desconto }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>												
						<h:inputHidden/>
						
						Período do desconto (meses):
						<h:inputText id="txtPeriodoDesconto" value="#{contratoMB.contrato.periodoDesconto }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }"/>						
						<h:inputHidden/>
						
						Multa por atraso no aluguel (%):
						<h:inputText id="txtMulta" value="#{contratoMB.contrato.multa }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">
							<f:convertNumber type="percent" pattern="###.##"/>
						</h:inputText>						
						<h:inputHidden/>
						
						Juros por atraso no aluguel (%):
						<h:inputText id="txtJuros" value="#{contratoMB.contrato.juros }" size="10" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">
							<f:convertNumber type="percent" pattern="###.##"/>
						</h:inputText>						
						<h:inputHidden/>
						
						* Índice de reajuste:						
						<h:selectOneMenu id="txtIndiceReajuste" required="true" requiredMessage="Informe o índice de reajuste!" value="#{contratoMB.idIndiceReajuste }" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">							
							<f:selectItems value="#{contratoMB.indices }"/>
						</h:selectOneMenu>						
						<rich:message for="txtIndiceReajuste" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Modelo de contrato:
						<h:selectOneMenu id="txtModeloContrato" required="true" requiredMessage="Informe o modelo de contrato a ser usado!" value="#{contratoMB.idModeloContrato }" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }">							
							<f:selectItems value="#{contratoMB.modelos }"/>
						</h:selectOneMenu>						
						<rich:message for="txtModeloContrato" style="color: #ff0000; font-weight: bold;"></rich:message>										
						
					</h:panelGrid>
			<br /><br/>
			<h:commandButton value="Editar" style="padding: 3px 10px;" disabled="#{contratoMB.contrato.situacao == 2 || contratoMB.contrato.situacao == 4 }" action="#{contratoMB.edit }" rendered="#{contratoMB.canEdit }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;"
				immediate="true" action="#{contratoMB.cancelAction }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Vigorar contrato" style="padding: 3px 10px;" disabled="#{contratoMB.contrato.situacao == 4 || contratoMB.contrato.situacao == 2}" rendered="#{contratoMB.canDelete }"
				action="#{contratoMB.vigorarContrato }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Renovar contrato" style="padding: 3px 10px;" disabled="#{contratoMB.contrato.situacao == 3 || contratoMB.contrato.situacao == 1 || contratoMB.contrato.situacao == 2}" rendered="#{contratoMB.canDelete }"
				action="#{contratoMB.renovarContrato }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Encerrar contrato" style="padding: 3px 10px;" disabled="#{contratoMB.contrato.situacao == 4}" rendered="#{contratoMB.canDelete }"
				action="#{contratoMB.encerrarContrato }"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>