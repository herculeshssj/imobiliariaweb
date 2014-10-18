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
	<h:form id="frmCliente">
		<div id="content">
			
			<h2>Cliente - Editar</h2>
			

			<rich:message for="frmCliente" style="color: #ff0000; font-weight: bold;"></rich:message>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do cliente"></h:outputText>
						</f:facet>
						
						* Tipo de Cliente:
						<h:selectManyCheckbox id="txtTipoCliente" value="#{clienteMB.tipoCliente }" required="true" requiredMessage="Selecione pelo menos um tipo de cliente!" disabled="#{!clienteMB.cliente.ativo }">							
							<f:selectItem itemValue="locador" itemLabel="Locador"/>
							<f:selectItem itemValue="locatario" itemLabel="Locatario"/>
							<f:selectItem itemValue="fiador" itemLabel="Fiador"/>
							<f:selectItem itemValue="socio" itemLabel="Sócio"/>													
						</h:selectManyCheckbox>
						<rich:message for="txtTipoCliente" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Nome:
						<h:inputText id="txtNome" value="#{clienteMB.cliente.nome }" size="50" maxlength="100" required="true" requiredMessage="Informe o nome do cliente!" disabled="#{!clienteMB.cliente.ativo }"/>
						<rich:message for="txtNome" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* CPF:
						<h:inputText id="txtCpf" value="#{clienteMB.cliente.cpf }" size="13" disabled="#{!clienteMB.cliente.ativo }"
							maxlength="11" converterMessage="CPF só deve conter números!"
							required="true" requiredMessage="Informe o CPF do cliente!">							
							<f:converter converterId="cpfconverter"/>
							<f:validator validatorId="cpfvalidator"/>
						</h:inputText>
						<rich:message for="txtCpf" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Data de nascimento:
						<h:panelGroup>						
							<h:inputText id="txtDataNascimento" value="#{clienteMB.cliente.dataNascimento }" required="true" requiredMessage="Informe a data de nascimento!" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataNascimento" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataNascimento" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Gênero:
						<h:selectOneRadio id="txtGenero" value="#{clienteMB.cliente.genero }" required="true" requiredMessage="Informe o gênero!" layout="lineDirection" disabled="#{!clienteMB.cliente.ativo }">
							<f:selectItem itemValue="M" itemLabel="Masculino"/>
							<f:selectItem itemValue="F" itemLabel="Feminino"/>
						</h:selectOneRadio>
						<rich:message for="txtGenero" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Nacionalidade:
						<h:inputText id="txtNacionalidade" value="#{clienteMB.cliente.nacionalidade }" maxlength="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Naturalidade:
						<h:inputText id="txtNaturalidade" value="#{clienteMB.cliente.naturalidade }" maxlength="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Estado Civil:
						<h:selectOneMenu id="txtEstadoCivil" value="#{clienteMB.cliente.estadoCivil }" required="true" requiredMessage="Informe o estado civil!" disabled="#{!clienteMB.cliente.ativo }">							
							<f:selectItem itemValue="Solteiro(a)" itemLabel="Solteiro(a)"/>
							<f:selectItem itemValue="Casado(a)" itemLabel="Casado(a)"/>
							<f:selectItem itemValue="Divorciado(a)" itemLabel="Divorciado(a)"/>
							<f:selectItem itemValue="Viúvo(a)" itemLabel="Viúvo(a)"/>
							<f:selectItem itemValue="Sepadaro(a) judicialmente" itemLabel="Sepadaro(a) judicialmente"/>							
						</h:selectOneMenu>
						<rich:message for="txtEstadoCivil" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Filiação - Pai:
						<h:inputText id="txtFiliacaoPai" value="#{clienteMB.cliente.filiacaoPai }" maxlength="100" size="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Filiação - Mãe:
						<h:inputText id="txtFiliacaoMae" value="#{clienteMB.cliente.filiacaoMae }" maxlength="100" size="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						E-Mail:
						<h:inputText id="txtEmail" value="#{clienteMB.cliente.email }" disabled="#{!clienteMB.cliente.ativo }" 
							maxlength="100" size="30">							
							<f:validator validatorId="emailvalidator"/>
						</h:inputText>
						<rich:message for="txtEmail" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Profissão:
						<h:inputText id="txtProfissao" value="#{clienteMB.cliente.profissao }" maxlength="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						
						RG:
						<h:inputText id="txtRg" value="#{clienteMB.cliente.rg }"	maxlength="15" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Orgão Emissor:
						<h:inputText id="txtOrgaoEmissor" value="#{clienteMB.cliente.orgaoEmissor }"	maxlength="30" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Data de emissão:	
						<h:panelGroup>					
							<h:inputText id="txtDataEmissao" value="#{clienteMB.cliente.dataEmissao }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataEmissao" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataEmissao" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Nº da pasta/arquivo:
						<h:inputText value="#{clienteMB.cliente.numPasta }" maxlength="10"  disabled="#{!clienteMB.cliente.ativo }"/>		
						
					</h:panelGrid>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do cônjuge"></h:outputText>
						</f:facet>						
						
						Nome:
						<h:inputText id="txtNomeConjuge" value="#{clienteMB.cliente.nomeConjuge }" size="50" maxlength="100" disabled="#{!clienteMB.cliente.ativo }"/>
						<rich:message for="txtNomeConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						CPF:
						<h:inputText id="txtCpfConjuge" value="#{clienteMB.cliente.cpfConjuge }" size="13" disabled="#{!clienteMB.cliente.ativo }"
							maxlength="11" converterMessage="CPF só deve conter números!">												
							<f:converter converterId="cpfconverter"/>
							<f:validator validatorId="cpfvalidator"/>
						</h:inputText>
						<rich:message for="txtCpf" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Data de nascimento:
						<h:panelGroup>						
							<h:inputText id="txtDataNascimentoConjuge" value="#{clienteMB.cliente.dataNascimentoConjuge }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataNascimentoConjuge" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataNascimentoConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Gênero:
						<h:selectOneRadio id="txtGeneroConjuge" value="#{clienteMB.cliente.generoConjuge }" layout="lineDirection" disabled="#{!clienteMB.cliente.ativo }">
							<f:selectItem itemValue="M" itemLabel="Masculino"/>
							<f:selectItem itemValue="F" itemLabel="Feminino"/>
						</h:selectOneRadio>
						<rich:message for="txtGeneroConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Nacionalidade:
						<h:inputText id="txtNacionalidadeConjuge" value="#{clienteMB.cliente.nacionalidadeConjuge }" maxlength="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Naturalidade:
						<h:inputText id="txtNaturalidadeConjuge" value="#{clienteMB.cliente.naturalidadeConjuge }" maxlength="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Filiação - Pai:
						<h:inputText id="txtFiliacaoPaiConjuge" value="#{clienteMB.cliente.filiacaoPaiConjuge }" maxlength="100" size="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Filiação - Mãe:
						<h:inputText id="txtFiliacaoMaeConjuge" value="#{clienteMB.cliente.filiacaoMaeConjuge }" maxlength="100" size="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Profissão:
						<h:inputText id="txtProfissaoConjuge" value="#{clienteMB.cliente.profissaoConjuge }" maxlength="50" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>						
						
						RG:
						<h:inputText id="txtRgConjuge" value="#{clienteMB.cliente.rgConjuge }"	maxlength="15" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Orgão Emissor:
						<h:inputText id="txtOrgaoEmissorConjuge" value="#{clienteMB.cliente.orgaoEmissorConjuge }"	maxlength="30" disabled="#{!clienteMB.cliente.ativo }"/>
						<h:inputHidden/>
						
						Data de emissão:	
						<h:panelGroup>					
							<h:inputText id="txtDataEmissaoConjuge" value="#{clienteMB.cliente.dataEmissaoConjuge }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataEmissaoConjuge" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataEmissaoConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						
					</h:panelGrid>
					
				
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do Endereço"></h:outputText>
						</f:facet>
						
						* CEP:
						<h:panelGroup>
							<h:inputText id="txtCep" value="#{clienteMB.cliente.endereco.cep }" required="true" requiredMessage="Informe o CEP!" size="9" maxlength="8"  disabled="#{!clienteMB.cliente.ativo }">
								<f:validator validatorId="cepvalidator"/>
							</h:inputText>
							&nbsp;
						<a4j:commandButton value="Pesquisar" action="#{clienteMB.buscarEndereco }" reRender="txtTipoLogradouro,txtLogradouro,txtBairro,txtUF,txtCidade,txtCep" style="padding: 3px 10px;" disabled="#{!clienteMB.cliente.ativo }"/>
						</h:panelGroup>
						<rich:message for="txtCep" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Tipo de logradouro:
						<h:inputText id="txtTipoLogradouro" value="#{clienteMB.cliente.endereco.tipoLogradouro }" maxlength="100"  disabled="#{!clienteMB.cliente.ativo }"/>
						<rich:message for="txtTipoLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Logradouro:
						<h:inputText id="txtLogradouro"  disabled="#{!clienteMB.cliente.ativo }"
							value="#{clienteMB.cliente.endereco.logradouro }" size="50"							
							maxlength="150">							
						</h:inputText>
						<rich:message for="txtLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Número:
						<h:inputText id="txtNumero" value="#{clienteMB.cliente.endereco.numero }" disabled="#{!clienteMB.cliente.ativo }"							
							maxlength="10">							
						</h:inputText>
						<rich:message for="txtNumero" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Complemento:
						<h:inputText value="#{clienteMB.cliente.endereco.complemento }" disabled="#{!clienteMB.cliente.ativo }" maxlength="50"></h:inputText>
						<h:inputHidden/>
						
						* Bairro:
						<h:inputText id="txtBairro" value="#{clienteMB.cliente.endereco.bairro }"  disabled="#{!clienteMB.cliente.ativo }"							
							maxlength="50"/>
						<rich:message for="txtBairro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Cidade:
						<h:inputText id="txtCidade" value="#{clienteMB.cliente.endereco.cidade }"  disabled="#{!clienteMB.cliente.ativo }"/>
						<rich:message for="txtCidade" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* UF:
						<h:inputText id="txtUF" value="#{clienteMB.cliente.endereco.uf }" disabled="#{!clienteMB.cliente.ativo }"/>
						<rich:message for="txtUF" style="color: #ff0000; font-weight: bold;"></rich:message>
						
					</h:panelGrid>
					
					<h:panelGrid id="pnlTelefone" columns="7" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Telefones de Contato"></h:outputText>
						</f:facet>
						
						DDD:	
						<h:inputText value="#{clienteMB.telefone.ddd }" size="5" disabled="#{!clienteMB.cliente.ativo }"
							validatorMessage="DDD deve ter pelo menos 2 dígitos!"
							maxlength="5" converterMessage="DDD só aceita números!">
							<f:converter converterId="telefoneconverter"/>
							<f:validateLength minimum="2"></f:validateLength>
						</h:inputText>
						
						Telefone:
						<h:inputText value="#{clienteMB.telefone.numero }" size="10" disabled="#{!clienteMB.cliente.ativo }"
							maxlength="20" converterMessage="Telefone só aceita números!"
							validatorMessage="Telefone deve ter pelo menos 6 dígitos">
							<f:converter converterId="telefoneconverter"/>
							<f:validateLength minimum="6"></f:validateLength>
						</h:inputText>
						
						Tipo:
						<h:selectOneMenu value="#{clienteMB.telefone.tipoTelefone }" disabled="#{!clienteMB.cliente.ativo }">
							<f:selectItem itemValue="Residencial" itemLabel="Residencial"/>
							<f:selectItem itemValue="Comercial" itemLabel="Comercial"/>
							<f:selectItem itemValue="Celular" itemLabel="Celular"/>
							<f:selectItem itemValue="Fax" itemLabel="Fax"/>
							<f:selectItem itemValue="Recado" itemLabel="Recado"/>
							<f:selectItem itemValue="Outros" itemLabel="Outros"/>
						</h:selectOneMenu>
						
						<a4j:commandButton value="Adicionar" style="padding: 3px 10px;" action="#{clienteMB.adicionarTelefone }" reRender="tableTelefones,pnlTelefone"  disabled="#{!clienteMB.cliente.ativo }"/>
					</h:panelGrid>
					
						<rich:dataTable id="tableTelefones" value="#{clienteMB.listaTelefone }"
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
								<a4j:commandLink value="Remover" reRender="tableTelefones"  disabled="#{!clienteMB.cliente.ativo }"
									action="#{clienteMB.removerTelefone }">
									<f:setPropertyActionListener value="#{item.numero }" target="#{clienteMB.numTelefone }" />
									<f:setPropertyActionListener value="#{item.tipoTelefone }" target="#{clienteMB.tipoTelefone }" />									
									<f:setPropertyActionListener value="#{item.ddd }" target="#{clienteMB.dddTelefone }" />
								</a4j:commandLink>
							</rich:column>
							
						</rich:dataTable>	
						
						<h:panelGrid columns="9" cellspacing="10" style="padding: 10px 10px 10px 10px;" width="100%">
				<f:facet name="header">
					<h:outputText value="Imóveis do cliente"></h:outputText>
				</f:facet>
				<rich:dataTable width="100%" value="#{clienteMB.cliente.imoveis }"
					var="item">
					<rich:column style="text-align: center">
						<f:facet name="header">
							<h:outputText value="Nº de Registro"></h:outputText>
						</f:facet>
						<h:outputText value="#{item.numRegistro }"></h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Tipo de Imóvel"></h:outputText>
						</f:facet>
						<h:outputText value="#{item.tipoImovel }"></h:outputText>
					</rich:column>
					<rich:column style="text-align: center">
						<f:facet name="header">
							<h:outputText value="Valor"></h:outputText>
						</f:facet>
						<h:outputText value="#{item.valor }">
							<f:convertNumber type="currency"/>
						</h:outputText>
					</rich:column>
					<rich:column style="text-align: center">
						<f:facet name="header">
							<h:outputText value="Situação"></h:outputText>
						</f:facet>
						<h:outputText value="#{item.situacaoImovel }"></h:outputText>
					</rich:column>
					<rich:column style="text-align: center">
						<f:facet name="header">
							<h:outputText value="Divulgado no site?"></h:outputText>
						</f:facet>
						<h:outputText value="#{item.site }">
							<f:converter converterId="simnaoconverter" />
						</h:outputText>
					</rich:column>
					<rich:column style="text-align: center">
						<f:facet name="header">
							<h:outputText value="Status"></h:outputText>
						</f:facet>
						<h:outputText value="Ativo" rendered="#{item.ativo }"></h:outputText>
						<h:outputText value="Inativo" rendered="#{!item.ativo }"></h:outputText>
					</rich:column>
				</rich:dataTable>
			</h:panelGrid>
			
			<h:panelGrid columns="9" cellspacing="10" style="padding: 10px 10px 10px 10px;" width="100%">
				<f:facet name="header">
					<h:outputText value="Contratos do cliente"></h:outputText>
				</f:facet>
				<rich:dataTable width="100%" value="#{clienteMB.cliente.contratos }"
					var="item">
					<rich:column style="text-align: center">
						<f:facet name="header">
							<h:outputText value="Nº do Contrato"></h:outputText>
						</f:facet>
						<h:outputText value="#{item.numContrato }"></h:outputText>
					</rich:column>
					<rich:column style="text-align: center">
						<f:facet name="header">
							<h:outputText value="Imóvel"></h:outputText>
						</f:facet>
						<h:outputText value="#{item.imovel.numRegistro }"/> - <h:outputText value="#{item.imovel.tipoImovel }"/> 
					</rich:column>					
					<rich:column style="text-align: center">
						<f:facet name="header">
							<h:outputText value="Período"></h:outputText>
						</f:facet>
						<h:outputText value="#{item.inicioContrato }">
							<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText> à 
						<h:outputText value="#{item.terminoContrato }">
							<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText> 
					</rich:column>
					<rich:column style="text-align: center">
						<f:facet name="header">
							<h:outputText value="Situação"></h:outputText>
						</f:facet>
						<h:outputText value="#{item.situacao }">
							<f:converter converterId="situacaocontratoconverter"/>
						</h:outputText>						
					</rich:column>
				</rich:dataTable>
			</h:panelGrid>
						
			<h:commandButton value="Editar" style="padding: 3px 10px;" action="#{clienteMB.edit }" disabled="#{!clienteMB.cliente.ativo }" rendered="#{clienteMB.canEdit }"/>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;" action="#{clienteMB.cancelAction }" immediate="true"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>