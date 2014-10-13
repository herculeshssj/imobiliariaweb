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
			
			<h2>Serviços de Manutenção</h2>
			
			<rich:messages style="font-weight: bold;"></rich:messages>
			<!-- Mensagens de aviso -->

			<h:commandButton value="Novo" style="padding: 3px 10px;" action="#{servicoManutencaoMB.addView }" rendered="#{servicoManutencaoMB.canAdd }"/>
			&nbsp;
			<h:panelGroup rendered="#{servicoManutencaoMB.canList }">
			Descrição:
			<h:inputText value="#{servicoManutencaoMB.findValue }" />
			&nbsp;
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{servicoManutencaoMB.simpleSearch }"/>
			</h:panelGroup>
			<br/><br/>
			
					<h:panelGrid columns="2" cellspacing="10"
						style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Cadastrar/Editar"></h:outputText>
						</f:facet>
						
						* Descrição:
						<h:inputText value="#{servicoManutencaoMB.servicoManutencao.descricao }" maxlength="100" />						
																		
						<h:commandButton value="Salvar" style="padding: 3px 10px;" action="#{servicoManutencaoMB.add }" rendered="#{servicoManutencaoMB.canAdd || servicoManutencaoMB.canEdit }"/>
						<h:outputText />
			
					</h:panelGrid>
			
			<rich:dataTable id="tableFormaPagamento" width="100%" var="item" value="#{servicoManutencaoMB.dadosModelo }" rows="15">
				
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Descrição"></h:outputText>
					</f:facet>
					<h:outputText value="#{item.descricao}"></h:outputText>
				</rich:column>
				
				<rich:column style="text-align: center">
					<f:facet name="header">
						<h:outputText value="Ver/Editar"></h:outputText>
					</f:facet>
					<h:commandLink value="Editar" action="#{servicoManutencaoMB.editView }" rendered="#{servicoManutencaoMB.canView }">
						<f:setPropertyActionListener value="#{item.id }" target="#{servicoManutencaoMB.idServicoManutencao }"/>
					</h:commandLink>						
				</rich:column>	
			</rich:dataTable>
			<rich:datascroller id="dsFormaPagamento" for="tableFormaPagamento"></rich:datascroller>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>