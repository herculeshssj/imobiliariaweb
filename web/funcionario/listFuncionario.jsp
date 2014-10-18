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
			
			<h2>Funcionários</h2>
			
			<rich:messages style="color: #000000; font-weight: bold;"></rich:messages>
			
			<h:commandButton value="Incluir funcionario" style="padding: 3px 10px;" action="#{funcionarioMB.addView }" rendered="#{funcionarioMB.canAdd }"/>
			<h:panelGroup rendered="#{funcionarioMB.canList }">
			&nbsp; CPF do funcionario:
			<h:inputText value="#{funcionarioMB.findValue }"/>	
			&nbsp;
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{funcionarioMB.simpleSearch }"></h:commandButton>
			&nbsp;&nbsp;&nbsp;
			<h:commandLink value="Mais opções de pesquisa" action="#{funcionarioMB.searchView }"></h:commandLink>
			</h:panelGroup>
			<br /> <br/>
			<rich:dataTable id="tableFuncionario" width="100%" value="#{funcionarioMB.dadosModelo }" var="item" rows="15">
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="CPF"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.cpf }"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Matrícula"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.matricula }"></h:outputText>
				</rich:column>
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Nome"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.nome }"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Cargo / Função"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.cargo }"></h:outputText>
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
					<h:commandLink value="Ver/Editar" action="#{funcionarioMB.editView }" rendered="#{funcionarioMB.canView }"/>
				</rich:column>
				
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ativar/Desativar"></h:outputText>
					</f:facet>
					<h:panelGroup rendered="#{funcionarioMB.canDelete }">
					<h:commandLink value="Ativar" rendered="#{!item.ativo }" action="#{funcionarioMB.deleteView }"/>				
					<h:commandLink value="Desativar" rendered="#{item.ativo }" action="#{funcionarioMB.deleteView }"/>
					</h:panelGroup>					
				</rich:column>
				
			</rich:dataTable>
			<rich:datascroller id="dsFuncionario" for="tableFuncionario"></rich:datascroller>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>