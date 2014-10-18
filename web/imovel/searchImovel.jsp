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
	<h:form id="frmImovel">
		<div id="content">
			
			<h2>Imóvel - Pesquisar</h2>
			
				<rich:message for="frmImovel" style="color: #ff0000; font-weight: bold;"></rich:message>
						
					<h:panelGrid columns="2" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do imóvel"></h:outputText>
						</f:facet>
						
						Nº de registro:
						<h:inputText value="#{imovelMB.imovel.numRegistro }"/>
						
						Locador:
						<h:selectOneMenu id="txtLocador" value="#{imovelMB.idLocador }">
							<f:selectItem itemLabel="Selecione"/>
							<f:selectItems value="#{imovelMB.locadores }"/>
						</h:selectOneMenu>
						
						Valor (R$):
						<h:inputText id="txtValor" value="#{imovelMB.imovel.valor }" size="15" maxlength="15" converterMessage="Valor deve conter somente números e somente um ponto(.)!">							
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>
						
						Tipo de imóvel:
						<h:selectOneMenu id="txtTipoImovel" value="#{imovelMB.imovel.tipoImovel }">
							<f:selectItem itemLabel="Selecione"/>
							<f:selectItems value="#{imovelMB.tipoImoveis }"/>
						</h:selectOneMenu>
						
						Situação de imóvel:						
						<h:inputText id="txtSituacaoImovel" value="#{imovelMB.imovel.situacaoImovel }" maxlength="20"/>
						
						Divulgado no site?
						<h:selectOneMenu value="#{imovelMB.imovel.site }">
							<f:selectItem itemLabel="Todos"/>
							<f:selectItem itemValue="true" itemLabel="Sim"/>
							<f:selectItem itemValue="false" itemLabel="Não"/>
						</h:selectOneMenu>						
						
						Ativo no sistema?
						<h:selectOneMenu value="#{imovelMB.imovel.ativo }">
							<f:selectItem itemLabel="Todos"/>
							<f:selectItem itemValue="true" itemLabel="Sim"/>
							<f:selectItem itemValue="false" itemLabel="Não"/>
						</h:selectOneMenu>
						
					</h:panelGrid>
		
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" rendered="#{imovelMB.canList }"
				action="#{imovelMB.search }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;"
				immediate="true" action="#{imovelMB.cancelAction }"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>