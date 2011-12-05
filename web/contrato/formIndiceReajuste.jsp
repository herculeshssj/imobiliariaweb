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

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
			
			<h2>Índices de Reajuste</h2>
			
			<rich:messages style="font-weight: bold;"></rich:messages>
			<!-- Mensagens de aviso -->

			<h:commandButton value="Novo Índice" style="padding: 3px 10px;" action="#{indiceReajusteMB.addView }" rendered="#{indiceReajusteMB.canAdd }"/>
			&nbsp;
			<h:panelGroup rendered="#{indiceReajusteMB.canList }">
			Descrição:
			<h:inputText value="#{indiceReajusteMB.findValue }" />
			&nbsp;
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{indiceReajusteMB.simpleSearch }"/>
			</h:panelGroup>
			<br/><br/>
			
					<h:panelGrid columns="2" cellspacing="10"
						style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Cadastrar/Editar"></h:outputText>
						</f:facet>
						
						* Descrição:
						<h:inputText value="#{indiceReajusteMB.indiceReajuste.descricao }" maxlength="100" disabled="#{!indiceReajusteMB.indiceReajuste.ativo }"/>						
									
						* Percentual (%):
						<h:inputText value="#{indiceReajusteMB.indiceReajuste.percentual }" maxlength="100" disabled="#{!indiceReajusteMB.indiceReajuste.ativo }"/>
						
						Período de reajuste:
						<h:selectOneMenu value="#{indiceReajusteMB.indiceReajuste.periodo }" disabled="#{!indiceReajusteMB.indiceReajuste.ativo }">
							<f:selectItem itemLabel="Mensal" itemValue="Mensal"/>
							<f:selectItem itemLabel="Bimestral" itemValue="Bimestral"/>
							<f:selectItem itemLabel="Trimestral" itemValue="Trimestral"/>
							<f:selectItem itemLabel="Quadrimestral" itemValue="Quadrimestral"/>
							<f:selectItem itemLabel="Semestral" itemValue="Semestral"/>
							<f:selectItem itemLabel="Anual" itemValue="Anual"/>
							<f:selectItem itemLabel="Bianual" itemValue="Bianual"/>
						</h:selectOneMenu>							
																		
						<h:commandButton value="Salvar" style="padding: 3px 10px;" action="#{indiceReajusteMB.add }" disabled="#{!indiceReajusteMB.indiceReajuste.ativo }" rendered="#{indiceReajusteMB.canAdd || indiceReajusteMB.canEdit }"/>
			
					</h:panelGrid>
			
			<rich:dataTable id="tableIndiceReajuste" width="100%" var="item" value="#{indiceReajusteMB.dadosModelo }" rows="15">
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Descrição"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.descricao}"></h:outputText>
				</rich:column>
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Percentual"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.percentual}"></h:outputText>%
				</rich:column>
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Período"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.periodo}"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ativo no sistema"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.ativo }">
						<f:converter converterId="simnaoconverter"/> 
					</h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ver/Editar"></h:outputText>
					</f:facet>
					<h:commandLink value="Editar" action="#{indiceReajusteMB.editView }" rendered="#{indiceReajusteMB.canView }">
						<f:setPropertyActionListener value="#{item.id }" target="#{indiceReajusteMB.idIndiceReajuste }"/>
					</h:commandLink>						
				</rich:column>
				
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ativar/Desativar"></h:outputText>
					</f:facet>
					<h:panelGroup rendered="#{indiceReajusteMB.canDelete }">
					<h:commandLink value="Ativar" rendered="#{!item.ativo }" action="#{indiceReajusteMB.delete }" onclick="javascript:return(confirm('Tem certeza que deseja ativar esta indiceReajuste?'))">
						<f:setPropertyActionListener value="#{item.id }" target="#{indiceReajusteMB.idIndiceReajuste }"/>
					</h:commandLink>						
					<h:commandLink value="Desativar" rendered="#{item.ativo }" action="#{indiceReajusteMB.delete }" onclick="javascript:return(confirm('Tem certeza que deseja desativar esta indiceReajuste?'))">
						<f:setPropertyActionListener value="#{item.id }" target="#{indiceReajusteMB.idIndiceReajuste }"/>
					</h:commandLink>					
					</h:panelGroup>
				</rich:column>		
			</rich:dataTable>
			<rich:datascroller id="dsIndiceReajusta" for="tableIndiceReajuste"></rich:datascroller>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>