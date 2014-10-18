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

	<style>
		.rich-fileupload-list-overflow {
			overflow: auto; overflow-y: hidden; overflow-x: auto;
		} 
	</style>
	<h:form id="frmImovel">
		<div id="content">
			
			<h2>Imóvel - Cadastrar</h2>
			
				<rich:message for="frmImovel" style="color: #ff0000; font-weight: bold;"></rich:message>
						
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do imóvel"></h:outputText>
						</f:facet>
						
						Nº de Registro:
						<h:inputText value="#{imovelMB.imovel.numRegistro }"/>
						<h:inputHidden/>
						
						* Locador:
						<h:selectOneMenu id="txtLocador" required="true" requiredMessage="Informe o locador!" value="#{imovelMB.idLocador }">
							<f:selectItem itemLabel="Selecione"/>
							<f:selectItems value="#{imovelMB.locadores }"/>
						</h:selectOneMenu>
						<rich:message for="txtLocador" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Valor (R$):
						<h:inputText id="txtValor" value="#{imovelMB.imovel.valor }" size="15"
							maxlength="15" required="true"
							requiredMessage="Informe o valor do imóvel!"
							validatorMessage="Valor deve conter somente números e somente um ponto(.)!">
							<f:validateDoubleRange minimum="1.0"></f:validateDoubleRange>
							<f:convertNumber type="currency" pattern="###.##"/>
						</h:inputText>
						<rich:message for="txtValor" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Tipo de imóvel:
						<h:selectOneMenu id="txtTipoImovel" required="true" requiredMessage="Informe o tipo de imóvel!" value="#{imovelMB.imovel.tipoImovel }">
							<f:selectItem itemLabel="Selecione"/>
							<f:selectItems value="#{imovelMB.tipoImoveis }"/>
						</h:selectOneMenu>
						<rich:message for="txtTipoImovel" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Situação de imóvel:						
						<h:inputText id="txtSituacaoImovel" value="#{imovelMB.imovel.situacaoImovel }" maxlength="20" required="true" requiredMessage="Informe a situação do imóvel!"/>
						<rich:message for="txtSituacaoImovel" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Divulgar no site?
						<h:selectBooleanCheckbox value="#{imovelMB.imovel.site }"></h:selectBooleanCheckbox>
						<h:inputHidden/>
						
						Mais informação:
						<h:inputTextarea value="#{imovelMB.imovel.maisInfo }" cols="40" rows="5"></h:inputTextarea>
						<h:inputHidden/>
						
					</h:panelGrid>
				
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Endereço"></h:outputText>
						</f:facet>
						
						* CEP:
						<h:panelGroup>
							<h:inputText id="txtCep" value="#{imovelMB.imovel.endereco.cep }" required="true" requiredMessage="Informe o CEP!" size="9" maxlength="8"  >
								<f:validator validatorId="cepvalidator"/>
							</h:inputText>
							&nbsp;
						<a4j:commandButton value="Pesquisar" action="#{imovelMB.buscarEndereco }" reRender="txtTipoLogradouro,txtLogradouro,txtBairro,txtUF,txtCidade,txtCep" style="padding: 3px 10px;"/>
						</h:panelGroup>
						<rich:message for="txtCep" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Tipo de logradouro:
						<h:inputText id="txtTipoLogradouro" value="#{imovelMB.imovel.endereco.tipoLogradouro }" maxlength="100"  />
						<rich:message for="txtTipoLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Logradouro:
						<h:inputText id="txtLogradouro"  
							value="#{imovelMB.imovel.endereco.logradouro }" size="50"							
							maxlength="150">							
						</h:inputText>
						<rich:message for="txtLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Número:
						<h:inputText id="txtNumero" value="#{imovelMB.imovel.endereco.numero }"							
							maxlength="10">							
						</h:inputText>
						<rich:message for="txtNumero" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Complemento:
						<h:inputText value="#{imovelMB.imovel.endereco.complemento }"  maxlength="50"></h:inputText>
						<h:inputHidden/>
						
						* Bairro:
						<h:inputText id="txtBairro" value="#{imovelMB.imovel.endereco.bairro }"							
							maxlength="50"/>
						<rich:message for="txtBairro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Cidade:
						<h:inputText id="txtCidade" value="#{imovelMB.imovel.endereco.cidade }"  />
						<rich:message for="txtCidade" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* UF:
						<h:inputText id="txtUF" value="#{imovelMB.imovel.endereco.uf }" />
						<rich:message for="txtUF" style="color: #ff0000; font-weight: bold;"></rich:message>
						
					</h:panelGrid>
								
				
					<h:panelGrid columns="1" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Galeria de fotos"></h:outputText>
						</f:facet>						
						<rich:fileUpload id="txtUploadFoto" disabled="#{!imovelMB.imovel.ativo }" listWidth="780" listHeight="100" fileUploadListener="#{imovelMB.carregaFoto }" acceptedTypes="jpg,jpeg,bmp,gif,png" maxFilesQuantity="#{imovelMB.contaArquivos }" noDuplicate="true">
							<a4j:support reRender="tableFotos" event="onfileuploadcomplete"/>
						</rich:fileUpload>						
					<br/>
						<rich:dataTable value="#{imovelMB.listaFoto }" id="tableFotos" var="item" rowKeyVar="row">							
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Foto"></h:outputText>
								</f:facet>
								<a4j:mediaOutput id="preview" element="img" mimeType="#{item.mime }" createContent="#{imovelMB.paint }" style="width: 64px; height:36px;" cacheable="false" value="#{row }" session="true"></a4j:mediaOutput>																
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Arquivo"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.arquivo }"></h:outputText>
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Tamanho"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.tamanho }"></h:outputText> bytes
							</rich:column>
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Dimensões"></h:outputText>
								</f:facet>
								<h:outputText value="#{item.largura }"></h:outputText> X
								<h:outputText value="#{item.altura }"></h:outputText> 
							</rich:column>							
							<rich:column style="text-align: center">
								<f:facet name="header">
									<h:outputText value="Remover"></h:outputText>
								</f:facet>
								<a4j:commandLink value="Remover"
									action="#{imovelMB.removerFoto }">
									<f:setPropertyActionListener value="#{item.arquivo }" target="#{imovelMB.nomeFoto }" />
								</a4j:commandLink>
							</rich:column>
							
						</rich:dataTable>
					</h:panelGrid>
		
			<h:commandButton value="Cadastrar" style="padding: 3px 10px;" rendered="#{imovelMB.canAdd }"
				action="#{imovelMB.add }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;"
				immediate="true" action="#{imovelMB.cancelAction }"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>