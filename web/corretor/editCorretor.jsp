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
	<h:form id="frmCorretor">
		<div id="content">
			
			<h2>Corretor - Editar</h2>
			

			<rich:message for="frmCorretor" style="color: #ff0000; font-weight: bold;"></rich:message>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do corretor"></h:outputText>
						</f:facet>
												
						* Nome:
						<h:inputText id="txtNome" value="#{corretorMB.corretor.nome }" size="50" maxlength="100" required="true" requiredMessage="Informe o nome do corretor!" disabled="#{!corretorMB.corretor.ativo }"/>
						<rich:message for="txtNome" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* CPF:
						<h:inputText id="txtCpf" value="#{corretorMB.corretor.cpf }" size="13" disabled="#{!corretorMB.corretor.ativo }"
							maxlength="11" converterMessage="CPF só deve conter números!"
							required="true" requiredMessage="Informe o CPF do corretor!">							
							<f:converter converterId="cpfconverter"/>
							<f:validator validatorId="cpfvalidator"/>
						</h:inputText>
						<rich:message for="txtCpf" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Data de nascimento:
						<h:panelGroup>						
							<h:inputText id="txtDataNascimento" value="#{corretorMB.corretor.dataNascimento }" required="true" requiredMessage="Informe a data de nascimento!" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataNascimento" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataNascimento" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Gênero:
						<h:selectOneRadio id="txtGenero" value="#{corretorMB.corretor.genero }" required="true" requiredMessage="Informe o gênero!" layout="lineDirection" disabled="#{!corretorMB.corretor.ativo }">
							<f:selectItem itemValue="M" itemLabel="Masculino"/>
							<f:selectItem itemValue="F" itemLabel="Feminino"/>
						</h:selectOneRadio>
						<rich:message for="txtGenero" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Nacionalidade:
						<h:inputText id="txtNacionalidade" value="#{corretorMB.corretor.nacionalidade }" maxlength="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Naturalidade:
						<h:inputText id="txtNaturalidade" value="#{corretorMB.corretor.naturalidade }" maxlength="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Estado Civil:
						<h:selectOneMenu id="txtEstadoCivil" value="#{corretorMB.corretor.estadoCivil }" required="true" requiredMessage="Informe o estado civil!" disabled="#{!corretorMB.corretor.ativo }">							
							<f:selectItem itemValue="Solteiro(a)" itemLabel="Solteiro(a)"/>
							<f:selectItem itemValue="Casado(a)" itemLabel="Casado(a)"/>
							<f:selectItem itemValue="Divorciado(a)" itemLabel="Divorciado(a)"/>
							<f:selectItem itemValue="Viúvo(a)" itemLabel="Viúvo(a)"/>
							<f:selectItem itemValue="Sepadaro(a) judicialmente" itemLabel="Sepadaro(a) judicialmente"/>							
						</h:selectOneMenu>
						<rich:message for="txtEstadoCivil" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Filiação - Pai:
						<h:inputText id="txtFiliacaoPai" value="#{corretorMB.corretor.filiacaoPai }" maxlength="100" size="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Filiação - Mãe:
						<h:inputText id="txtFiliacaoMae" value="#{corretorMB.corretor.filiacaoMae }" maxlength="100" size="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						E-Mail:
						<h:inputText id="txtEmail" value="#{corretorMB.corretor.email }" disabled="#{!corretorMB.corretor.ativo }" 
							maxlength="100" size="30">							
							<f:validator validatorId="emailvalidator"/>
						</h:inputText>
						<rich:message for="txtEmail" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Profissão:
						<h:inputText id="txtProfissao" value="#{corretorMB.corretor.profissao }" maxlength="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						
						RG:
						<h:inputText id="txtRg" value="#{corretorMB.corretor.rg }"	maxlength="15" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Orgão Emissor:
						<h:inputText id="txtOrgaoEmissor" value="#{corretorMB.corretor.orgaoEmissor }"	maxlength="30" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Data de emissão:	
						<h:panelGroup>					
							<h:inputText id="txtDataEmissao" value="#{corretorMB.corretor.dataEmissao }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataEmissao" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataEmissao" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						CRECI:
						<h:inputText id="txtCreci" value="#{corretorMB.corretor.creci }" disabled="#{!corretorMB.corretor.ativo }"
							maxlength="12">							
						</h:inputText>
						<rich:message for="txtCreci" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Região de registro/atuação:
						<h:selectOneMenu id="txtRegiao" value="#{corretorMB.corretor.regiao }" disabled="#{!corretorMB.corretor.ativo }">	
							<f:selectItem itemLabel="Selecione uma região"/>						
							<f:selectItem itemValue="1" itemLabel="1ª Região - Rio de Janeiro"/>
							<f:selectItem itemValue="2" itemLabel="2ª Região - São Paulo"/>
							<f:selectItem itemValue="3" itemLabel="3ª Região - Rio Grande do Sul"/>
							<f:selectItem itemValue="4" itemLabel="4ª Região - Minas Gerais"/>
							<f:selectItem itemValue="5" itemLabel="5ª Região - Goiás"/>
							<f:selectItem itemValue="6" itemLabel="6ª Região - Paraná"/>
							<f:selectItem itemValue="7" itemLabel="7ª Região - Pernambuco"/>
							<f:selectItem itemValue="8" itemLabel="8ª Região - Distrito Federal"/>
							<f:selectItem itemValue="9" itemLabel="9ª Região - Bahia"/>
							<f:selectItem itemValue="11" itemLabel="11ª Região - Santa Catarina"/>
							<f:selectItem itemValue="12" itemLabel="12ª Região - Pará"/>
							<f:selectItem itemValue="13" itemLabel="13ª Região - Espírito Santo"/>
							<f:selectItem itemValue="14" itemLabel="14ª Região - Mato Grosso do Sul"/>
							<f:selectItem itemValue="15" itemLabel="15ª Região - Ceará"/>
							<f:selectItem itemValue="16" itemLabel="16ª Região - Sergipe"/>
							<f:selectItem itemValue="17" itemLabel="17ª Região - Rio Grande do Norte"/>
							<f:selectItem itemValue="18" itemLabel="18ª Região - Amazonas e Roraima"/>
							<f:selectItem itemValue="19" itemLabel="19ª Região - Mato Grosso"/>
							<f:selectItem itemValue="20" itemLabel="20ª Região - Maranhão"/>
							<f:selectItem itemValue="21" itemLabel="21ª Região - Paraíba"/>
							<f:selectItem itemValue="22" itemLabel="22ª Região - Alagoas"/>
							<f:selectItem itemValue="23" itemLabel="23ª Região - Piauí"/>
							<f:selectItem itemValue="24" itemLabel="24ª Região - Rondônia"/>
							<f:selectItem itemValue="25" itemLabel="25ª Região - Tocantins"/>
							<f:selectItem itemValue="26" itemLabel="26ª Região - Acre"/>
						</h:selectOneMenu>
						<rich:message for="txtRegiao" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Data de registro do conselho:		
						<h:panelGroup>				
							<h:inputText id="txtDataRegistro" value="#{corretorMB.corretor.dataRegistro }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataRegistro" query="mask('99/99/9999')" timing="onload"/>
							</h:panelGroup>
						<rich:message for="txtDataRegistro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Nº da pasta/arquivo:
						<h:inputText value="#{corretorMB.corretor.numPasta }" maxlength="10"  disabled="#{!corretorMB.corretor.ativo }"/>		
						
					</h:panelGrid>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do cônjuge"></h:outputText>
						</f:facet>						
						
						Nome:
						<h:inputText id="txtNomeConjuge" value="#{corretorMB.corretor.nomeConjuge }" size="50" maxlength="100" disabled="#{!corretorMB.corretor.ativo }"/>
						<rich:message for="txtNomeConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						CPF:
						<h:inputText id="txtCpfConjuge" value="#{corretorMB.corretor.cpfConjuge }" size="13" disabled="#{!corretorMB.corretor.ativo }"
							maxlength="11" converterMessage="CPF só deve conter números!">												
							<f:converter converterId="cpfconverter"/>
							<f:validator validatorId="cpfvalidator"/>
						</h:inputText>
						<rich:message for="txtCpf" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Data de nascimento:
						<h:panelGroup>						
							<h:inputText id="txtDataNascimentoConjuge" value="#{corretorMB.corretor.dataNascimentoConjuge }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataNascimentoConjuge" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataNascimentoConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Gênero:
						<h:selectOneRadio id="txtGeneroConjuge" value="#{corretorMB.corretor.generoConjuge }" layout="lineDirection" disabled="#{!corretorMB.corretor.ativo }">
							<f:selectItem itemValue="M" itemLabel="Masculino"/>
							<f:selectItem itemValue="F" itemLabel="Feminino"/>
						</h:selectOneRadio>
						<rich:message for="txtGeneroConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Nacionalidade:
						<h:inputText id="txtNacionalidadeConjuge" value="#{corretorMB.corretor.nacionalidadeConjuge }" maxlength="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Naturalidade:
						<h:inputText id="txtNaturalidadeConjuge" value="#{corretorMB.corretor.naturalidadeConjuge }" maxlength="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Filiação - Pai:
						<h:inputText id="txtFiliacaoPaiConjuge" value="#{corretorMB.corretor.filiacaoPaiConjuge }" maxlength="100" size="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Filiação - Mãe:
						<h:inputText id="txtFiliacaoMaeConjuge" value="#{corretorMB.corretor.filiacaoMaeConjuge }" maxlength="100" size="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Profissão:
						<h:inputText id="txtProfissaoConjuge" value="#{corretorMB.corretor.profissaoConjuge }" maxlength="50" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>						
						
						RG:
						<h:inputText id="txtRgConjuge" value="#{corretorMB.corretor.rgConjuge }"	maxlength="15" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Orgão Emissor:
						<h:inputText id="txtOrgaoEmissorConjuge" value="#{corretorMB.corretor.orgaoEmissorConjuge }"	maxlength="30" disabled="#{!corretorMB.corretor.ativo }"/>
						<h:inputHidden/>
						
						Data de emissão:	
						<h:panelGroup>					
							<h:inputText id="txtDataEmissaoConjuge" value="#{corretorMB.corretor.dataEmissaoConjuge }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
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
							<h:inputText id="txtCep" value="#{corretorMB.corretor.endereco.cep }" required="true" requiredMessage="Informe o CEP!" size="9" maxlength="8"  disabled="#{!corretorMB.corretor.ativo }">
								<f:validator validatorId="cepvalidator"/>
							</h:inputText>
							&nbsp;
						<a4j:commandButton value="Pesquisar" action="#{corretorMB.buscarEndereco }" reRender="txtTipoLogradouro,txtLogradouro,txtBairro,txtUF,txtCidade,txtCep" style="padding: 3px 10px;" disabled="#{!corretorMB.corretor.ativo }"/>
						</h:panelGroup>
						<rich:message for="txtCep" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Tipo de logradouro:
						<h:inputText id="txtTipoLogradouro" value="#{corretorMB.corretor.endereco.tipoLogradouro }" maxlength="100"  disabled="#{!corretorMB.corretor.ativo }"/>
						<rich:message for="txtTipoLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Logradouro:
						<h:inputText id="txtLogradouro"  disabled="#{!corretorMB.corretor.ativo }"
							value="#{corretorMB.corretor.endereco.logradouro }" size="50"							
							maxlength="150">							
						</h:inputText>
						<rich:message for="txtLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Número:
						<h:inputText id="txtNumero" value="#{corretorMB.corretor.endereco.numero }" disabled="#{!corretorMB.corretor.ativo }"							
							maxlength="10">							
						</h:inputText>
						<rich:message for="txtNumero" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Complemento:
						<h:inputText value="#{corretorMB.corretor.endereco.complemento }" disabled="#{!corretorMB.corretor.ativo }" maxlength="50"></h:inputText>
						<h:inputHidden/>
						
						* Bairro:
						<h:inputText id="txtBairro" value="#{corretorMB.corretor.endereco.bairro }"  disabled="#{!corretorMB.corretor.ativo }"							
							maxlength="50"/>
						<rich:message for="txtBairro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Cidade:
						<h:inputText id="txtCidade" value="#{corretorMB.corretor.endereco.cidade }"  disabled="#{!corretorMB.corretor.ativo }"/>
						<rich:message for="txtCidade" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* UF:
						<h:inputText id="txtUF" value="#{corretorMB.corretor.endereco.uf }" disabled="#{!corretorMB.corretor.ativo }"/>
						<rich:message for="txtUF" style="color: #ff0000; font-weight: bold;"></rich:message>
						
					</h:panelGrid>
					
					<h:panelGrid id="pnlTelefone" columns="7" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Telefones de Contato"></h:outputText>
						</f:facet>
						
						DDD:	
						<h:inputText value="#{corretorMB.telefone.ddd }" size="5" disabled="#{!corretorMB.corretor.ativo }"
							validatorMessage="DDD deve ter pelo menos 2 dígitos!"
							maxlength="5" converterMessage="DDD só aceita números!">
							<f:converter converterId="telefoneconverter"/>
							<f:validateLength minimum="2"></f:validateLength>
						</h:inputText>
						
						Telefone:
						<h:inputText value="#{corretorMB.telefone.numero }" size="10" disabled="#{!corretorMB.corretor.ativo }"
							maxlength="20" converterMessage="Telefone só aceita números!"
							validatorMessage="Telefone deve ter pelo menos 6 dígitos">
							<f:converter converterId="telefoneconverter"/>
							<f:validateLength minimum="6"></f:validateLength>
						</h:inputText>
						
						Tipo:
						<h:selectOneMenu value="#{corretorMB.telefone.tipoTelefone }" disabled="#{!corretorMB.corretor.ativo }">
							<f:selectItem itemValue="Residencial" itemLabel="Residencial"/>
							<f:selectItem itemValue="Comercial" itemLabel="Comercial"/>
							<f:selectItem itemValue="Celular" itemLabel="Celular"/>
							<f:selectItem itemValue="Fax" itemLabel="Fax"/>
							<f:selectItem itemValue="Recado" itemLabel="Recado"/>
							<f:selectItem itemValue="Outros" itemLabel="Outros"/>
						</h:selectOneMenu>
						
						<a4j:commandButton value="Adicionar" style="padding: 3px 10px;" action="#{corretorMB.adicionarTelefone }" reRender="tableTelefones,pnlTelefone"  disabled="#{!corretorMB.corretor.ativo }"/>
					</h:panelGrid>
					
						<rich:dataTable id="tableTelefones" value="#{corretorMB.listaTelefone }"
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
								<a4j:commandLink value="Remover" reRender="tableTelefones"  disabled="#{!corretorMB.corretor.ativo }"
									action="#{corretorMB.removerTelefone }">
									<f:setPropertyActionListener value="#{item.numero }" target="#{corretorMB.numTelefone }" />
									<f:setPropertyActionListener value="#{item.tipoTelefone }" target="#{corretorMB.tipoTelefone }" />									
									<f:setPropertyActionListener value="#{item.ddd }" target="#{corretorMB.dddTelefone }" />
								</a4j:commandLink>
							</rich:column>
							
						</rich:dataTable>	
						
			
			<h:panelGrid columns="9" cellspacing="10" style="padding: 10px 10px 10px 10px;" width="100%">
				<f:facet name="header">
					<h:outputText value="Contratos do corretor"></h:outputText>
				</f:facet>
				<rich:dataTable width="100%" value="#{corretorMB.corretor.contratos }"
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
						
			<h:commandButton value="Editar" style="padding: 3px 10px;" action="#{corretorMB.edit }" disabled="#{!corretorMB.corretor.ativo }" rendered="#{corretorMB.canEdit }"/>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;" action="#{corretorMB.cancelAction }" immediate="true"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>