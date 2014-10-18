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
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<f:view>

<f:subview id="cabecalho">
	<jsp:include page="../header.jsp"></jsp:include>
</f:subview>
	<h:form>
		<div id="content">
			
			<h2>Corretores</h2>
			
			<rich:messages style="color: #000000; font-weight: bold;"></rich:messages>
			
			<h:commandButton value="Incluir corretor" style="padding: 3px 10px;" action="#{corretorMB.addView }" rendered="#{corretorMB.canAdd }"/>
			<h:panelGroup rendered="#{corretorMB.canList }">
			&nbsp; CPF do corretor:
			<h:inputText value="#{corretorMB.findValue }"/>	
			&nbsp;
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{corretorMB.simpleSearch }"></h:commandButton>
			&nbsp;&nbsp;&nbsp;
			<h:commandLink value="Mais opções de pesquisa" action="#{corretorMB.searchView }"></h:commandLink>
			</h:panelGroup>
			<br /> <br/>
			<rich:dataTable id="tableCorretor" width="100%" value="#{corretorMB.dadosModelo }" var="item">
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="CPF"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.cpf }"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="CRECI"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.creci }"></h:outputText>
				</rich:column>
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Nome"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.nome }"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Região"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.regiao }"></h:outputText>ª
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Nº de Pasta"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.numPasta }"></h:outputText>
				</rich:column>
				
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ver/Editar"></h:outputText>
					</f:facet>					
					<h:commandLink value="Ver/Editar" action="#{corretorMB.editView }" rendered="#{corretorMB.canView }"/>
				</rich:column>
				
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ativar/Desativar"></h:outputText>
					</f:facet>
					<h:panelGroup rendered="#{corretorMB.canDelete }">
					<h:commandLink value="Ativar" rendered="#{!item.ativo }" action="#{corretorMB.deleteView }"/>				
					<h:commandLink value="Desativar" rendered="#{item.ativo }" action="#{corretorMB.deleteView }"/>
					</h:panelGroup>					
				</rich:column>
				
			</rich:dataTable>
			<rich:datascroller id="dsCorretor" for="tableCorretor"></rich:datascroller>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>