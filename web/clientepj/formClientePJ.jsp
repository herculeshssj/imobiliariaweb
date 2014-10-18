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
	<h:form id="frmClientePJ">
		<div id="content">
			
			<h2>Cliente PJ - Cadastrar</h2>
			
			<rich:message for="frmClientePJ" style="color: #ff0000; font-weight: bold;"></rich:message>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados da Empresa"></h:outputText>
						</f:facet>
						
						* Nome Fantasia:
						<h:inputText id="txtNomeFantasia" value="#{clientePJMB.clientePJ.nomeFantasia }" size="50" maxlength="100" required="true" requiredMessage="Informe o nome da empresa!"/>
						<rich:message for="txtNomeFantasia" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* CNPJ:
						<h:inputText id="txtCnpj" value="#{clientePJMB.clientePJ.cnpj }" size="16"
							maxlength="14" converterMessage="CNPJ só deve conter números!"
							required="true" requiredMessage="Informe o CNPJ da empresa!">							
							<f:converter converterId="cnpjconverter"/>
							<f:validator validatorId="cnpjvalidator"/>
						</h:inputText>
						<rich:message for="txtCnpj" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Ramo de atividade:
						<h:inputText id="txtRamoAtividade" value="#{clientePJMB.clientePJ.ramoAtividade }" size="70" maxlength="100"/>
						<rich:message for="txtRamoAtividade" style="color: #ff0000; font-weight: bold;"></rich:message>
							
						Nº da pasta/arquivo:
						<h:inputText value="#{clientePJMB.clientePJ.numPasta }" maxlength="10"/>		
						
					</h:panelGrid>
					
					<h:panelGrid id="pnlSocios" columns="5" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Sócios da Empresa"></h:outputText>
						</f:facet>
												
						Sócio:
						<h:selectOneMenu id="txtSocio" value="#{clientePJMB.idSocio }">
							<f:selectItem itemLabel="Selecione um sócio"/>							
							<f:selectItems value="#{clientePJMB.socios }"/>
						</h:selectOneMenu>
						
						Função:
						<h:inputText value="#{clientePJMB.socio.funcao }"></h:inputText>
						
						<a4j:commandButton value="Adicionar" style="padding: 3px 10px;" action="#{clientePJMB.adicionarSocio }" reRender="tableSocios,pnlSocios"/>
						
					</h:panelGrid>
					
						<rich:dataTable id="tableSocios" value="#{clientePJMB.listaSocio }"
							var="item">	
							
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Sócio"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.clientePF.nome }"></h:outputText>
							</rich:column>
							
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Função"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.funcao }"/>								
							</rich:column>							
							
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Remover"></h:outputText>
								</f:facet>
								<a4j:commandLink value="Remover" reRender="tableSocios" action="#{clientePJMB.removerSocio }">
									<f:setPropertyActionListener value="#{item.clientePF.id }" target="#{clientePJMB.idSocio }" />									
								</a4j:commandLink>
							</rich:column>
							
						</rich:dataTable>
				
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do Endereço"></h:outputText>
						</f:facet>
						
						* CEP:
						<h:panelGroup>
							<h:inputText id="txtCep" value="#{clientePJMB.clientePJ.endereco.cep }" required="true" requiredMessage="Informe o CEP!" size="9" maxlength="8">
								<f:validator validatorId="cepvalidator"/>
							</h:inputText>
							&nbsp;
						<a4j:commandButton value="Pesquisar" action="#{clientePJMB.buscarEndereco }" reRender="txtTipoLogradouro,txtLogradouro,txtBairro,txtUF,txtCidade,txtCep" style="padding: 3px 10px;"/>
						</h:panelGroup>
						<rich:message for="txtCep" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Tipo de logradouro:
						<h:inputText id="txtTipoLogradouro" value="#{clientePJMB.clientePJ.endereco.tipoLogradouro }" maxlength="100"/>
						<rich:message for="txtTipoLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Logradouro:
						<h:inputText id="txtLogradouro" value="#{clientePJMB.clientePJ.endereco.logradouro }" size="50"							
							maxlength="150">							
						</h:inputText>
						<rich:message for="txtLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Número:
						<h:inputText id="txtNumero" value="#{clientePJMB.clientePJ.endereco.numero }" maxlength="10">							
						</h:inputText>
						<rich:message for="txtNumero" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Complemento:
						<h:inputText value="#{clientePJMB.clientePJ.endereco.complemento }" maxlength="50"></h:inputText>
						<h:inputHidden/>
						
						* Bairro:
						<h:inputText id="txtBairro" value="#{clientePJMB.clientePJ.endereco.bairro }" 							
							maxlength="50"/>
						<rich:message for="txtBairro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Cidade:
						<h:inputText id="txtCidade" value="#{clientePJMB.clientePJ.endereco.cidade }" />
						<rich:message for="txtCidade" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* UF:
						<h:inputText id="txtUF" value="#{clientePJMB.clientePJ.endereco.uf }" />
						<rich:message for="txtUF" style="color: #ff0000; font-weight: bold;"></rich:message>
						
					</h:panelGrid>
					
					<h:panelGrid id="pnlTelefone" columns="7" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Telefones de Contato"></h:outputText>
						</f:facet>
						
						DDD:	
						<h:inputText value="#{clientePJMB.telefone.ddd }" size="5" 
							validatorMessage="DDD deve ter pelo menos 2 dígitos!"
							maxlength="5" converterMessage="DDD só aceita números!">
							<f:converter converterId="telefoneconverter"/>
							<f:validateLength minimum="2"></f:validateLength>
						</h:inputText>
						
						Telefone:
						<h:inputText value="#{clientePJMB.telefone.numero }" size="10" 
							maxlength="20" converterMessage="Telefone só aceita números!"
							validatorMessage="Telefone deve ter pelo menos 6 dígitos">
							<f:converter converterId="telefoneconverter"/>
							<f:validateLength minimum="6"></f:validateLength>
						</h:inputText>
						
						Tipo:
						<h:selectOneMenu value="#{clientePJMB.telefone.tipoTelefone }" >
							<f:selectItem itemValue="Residencial" itemLabel="Residencial"/>
							<f:selectItem itemValue="Comercial" itemLabel="Comercial"/>
							<f:selectItem itemValue="Celular" itemLabel="Celular"/>
							<f:selectItem itemValue="Fax" itemLabel="Fax"/>
							<f:selectItem itemValue="Recado" itemLabel="Recado"/>
							<f:selectItem itemValue="Outros" itemLabel="Outros"/>
						</h:selectOneMenu>
						
						<a4j:commandButton value="Adicionar" style="padding: 3px 10px;" action="#{clientePJMB.adicionarTelefone }" reRender="tableTelefones,pnlTelefone" />
					</h:panelGrid>
					
						<rich:dataTable id="tableTelefones" value="#{clientePJMB.listaTelefone }"
							var="item">						
							
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="DDD"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.ddd }"></h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Número"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.numero }"></h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Tipo"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.tipoTelefone }"></h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Remover"></h:outputText>
								</f:facet>
								<a4j:commandLink value="Remover" reRender="tableTelefones" action="#{clientePJMB.removerTelefone }">
									<f:setPropertyActionListener value="#{item.numero }" target="#{clientePJMB.numTelefone }" />
									<f:setPropertyActionListener value="#{item.tipoTelefone }" target="#{clientePJMB.tipoTelefone }" />									
									<f:setPropertyActionListener value="#{item.ddd }" target="#{clientePJMB.dddTelefone }" />
								</a4j:commandLink>
							</rich:column>
							
						</rich:dataTable>	
				
			<h:commandButton value="Cadastrar" style="padding: 3px 10px;"
				action="#{clientePJMB.add }" rendered="#{clientePJMB.canAdd }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;"
				immediate="true" action="#{clientePJMB.cancelAction }"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>