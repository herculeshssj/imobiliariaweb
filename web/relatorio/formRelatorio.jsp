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

	<style>
		.rich-fileupload-list-overflow {
			overflow: auto; overflow-y: hidden; overflow-x: auto;
		} 
	</style>
	<h:form>
		<div id="content">
			
			<h2>Relatórios</h2>
			
			<rich:messages style="font-weight: bold;"></rich:messages>
			<!-- Mensagens de aviso -->

			<h:commandButton value="Novo relatório" style="padding: 3px 10px;" action="#{relatorioMB.addView }" rendered="#{relatorioMB.canAdd }"/>
			&nbsp;
			<h:panelGroup rendered="#{relatorioMB.canList }">
			Descrição:
			<h:inputText value="#{relatorioMB.findValue }" />
			&nbsp;
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{relatorioMB.simpleSearch }"/>
			</h:panelGroup>
			<br/><br/>
			
					<h:panelGrid columns="2" cellspacing="10"
						style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Cadastrar/Editar"></h:outputText>
						</f:facet>
						
						* Descrição:
						<h:inputText value="#{relatorioMB.relatorio.descricao }" size="50" maxlength="100" disabled="#{!relatorioMB.relatorio.ativo }"/>
						
						* Upload do relatório:
						<rich:fileUpload id="txtUploadModelo" disabled="#{!relatorioMB.relatorio.ativo }" listWidth="500" listHeight="100" fileUploadListener="#{relatorioMB.carregaModelo }" acceptedTypes="jasper" maxFilesQuantity="1"/>													
																		
						<h:commandButton value="Salvar" style="padding: 3px 10px;" action="#{relatorioMB.add }" disabled="#{!relatorioMB.relatorio.ativo }" rendered="#{relatorioMB.canAdd || relatorioMB.canEdit }"/>
			
					</h:panelGrid>
			
			<rich:dataTable id="tableRelatorio" width="100%" var="item" value="#{relatorioMB.dadosModelo }" rows="15">
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Descrição"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.descricao}"></h:outputText>
				</rich:column>
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Arquivo"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.arquivo}"></h:outputText>
				</rich:column>
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Tamanho"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.tamanhoArquivo}"></h:outputText> bytes
				</rich:column>
				
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Relatório"></h:outputText>
					</f:facet>
					<h:commandLink value="Gerar" action="#{relatorioMB.gerarRelatorio }" rendered="#{relatorioMB.canView }">
						<f:setPropertyActionListener value="#{item.id }" target="#{relatorioMB.idRelatorio }"/>
					</h:commandLink>
					
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ver/Editar"></h:outputText>
					</f:facet>
					<h:commandLink value="Editar" action="#{relatorioMB.editView }" rendered="#{relatorioMB.canView }">
						<f:setPropertyActionListener value="#{item.id }" target="#{relatorioMB.idRelatorio }"/>
					</h:commandLink>						
				</rich:column>
				
				<rich:column  style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ativar/Desativar"></h:outputText>
					</f:facet>
					<h:panelGroup rendered="#{relatorioMB.canDelete }">
					<h:commandLink value="Ativar" rendered="#{!item.ativo }" action="#{relatorioMB.delete }" onclick="javascript:return(confirm('Tem certeza que deseja ativar esta relatorio?'))">
						<f:setPropertyActionListener value="#{item.id }" target="#{relatorioMB.idRelatorio }"/>
					</h:commandLink>						
					<h:commandLink value="Desativar" rendered="#{item.ativo }" action="#{relatorioMB.delete }" onclick="javascript:return(confirm('Tem certeza que deseja desativar esta relatorio?'))">
						<f:setPropertyActionListener value="#{item.id }" target="#{relatorioMB.idRelatorio }"/>
					</h:commandLink>					
					</h:panelGroup>
				</rich:column>		
			</rich:dataTable>
			<rich:datascroller id="dsRelatorio" for="tableRelatorio"></rich:datascroller>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>