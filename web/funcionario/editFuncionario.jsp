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
	<h:form id="frmFuncionario">
		<div id="content">
			
			<h2>Funcionario - Editar</h2>
			

			<rich:message for="frmFuncionario" style="color: #ff0000; font-weight: bold;"></rich:message>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do funcionario"></h:outputText>
						</f:facet>
												
						* Nome:
						<h:inputText id="txtNome" value="#{funcionarioMB.funcionario.nome }" size="50" maxlength="100" required="true" requiredMessage="Informe o nome do funcionario!" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<rich:message for="txtNome" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* CPF:
						<h:inputText id="txtCpf" value="#{funcionarioMB.funcionario.cpf }" size="13" disabled="#{!funcionarioMB.funcionario.ativo }"
							maxlength="11" converterMessage="CPF só deve conter números!"
							required="true" requiredMessage="Informe o CPF do funcionario!">							
							<f:converter converterId="cpfconverter"/>
							<f:validator validatorId="cpfvalidator"/>
						</h:inputText>
						<rich:message for="txtCpf" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Data de nascimento:
						<h:panelGroup>						
							<h:inputText id="txtDataNascimento" value="#{funcionarioMB.funcionario.dataNascimento }" required="true" requiredMessage="Informe a data de nascimento!" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataNascimento" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataNascimento" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Gênero:
						<h:selectOneRadio id="txtGenero" value="#{funcionarioMB.funcionario.genero }" required="true" requiredMessage="Informe o gênero!" layout="lineDirection" disabled="#{!funcionarioMB.funcionario.ativo }">
							<f:selectItem itemValue="M" itemLabel="Masculino"/>
							<f:selectItem itemValue="F" itemLabel="Feminino"/>
						</h:selectOneRadio>
						<rich:message for="txtGenero" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Nacionalidade:
						<h:inputText id="txtNacionalidade" value="#{funcionarioMB.funcionario.nacionalidade }" maxlength="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Naturalidade:
						<h:inputText id="txtNaturalidade" value="#{funcionarioMB.funcionario.naturalidade }" maxlength="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Estado Civil:
						<h:selectOneMenu id="txtEstadoCivil" value="#{funcionarioMB.funcionario.estadoCivil }" required="true" requiredMessage="Informe o estado civil!" disabled="#{!funcionarioMB.funcionario.ativo }">							
							<f:selectItem itemValue="Solteiro(a)" itemLabel="Solteiro(a)"/>
							<f:selectItem itemValue="Casado(a)" itemLabel="Casado(a)"/>
							<f:selectItem itemValue="Divorciado(a)" itemLabel="Divorciado(a)"/>
							<f:selectItem itemValue="Viúvo(a)" itemLabel="Viúvo(a)"/>
							<f:selectItem itemValue="Sepadaro(a) judicialmente" itemLabel="Sepadaro(a) judicialmente"/>							
						</h:selectOneMenu>
						<rich:message for="txtEstadoCivil" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Filiação - Pai:
						<h:inputText id="txtFiliacaoPai" value="#{funcionarioMB.funcionario.filiacaoPai }" maxlength="100" size="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Filiação - Mãe:
						<h:inputText id="txtFiliacaoMae" value="#{funcionarioMB.funcionario.filiacaoMae }" maxlength="100" size="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						E-Mail:
						<h:inputText id="txtEmail" value="#{funcionarioMB.funcionario.email }" disabled="#{!funcionarioMB.funcionario.ativo }" 
							maxlength="100" size="30">							
							<f:validator validatorId="emailvalidator"/>
						</h:inputText>
						<rich:message for="txtEmail" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Profissão:
						<h:inputText id="txtProfissao" value="#{funcionarioMB.funcionario.profissao }" maxlength="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						
						RG:
						<h:inputText id="txtRg" value="#{funcionarioMB.funcionario.rg }"	maxlength="15" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Orgão Emissor:
						<h:inputText id="txtOrgaoEmissor" value="#{funcionarioMB.funcionario.orgaoEmissor }"	maxlength="30" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Data de emissão:	
						<h:panelGroup>					
							<h:inputText id="txtDataEmissao" value="#{funcionarioMB.funcionario.dataEmissao }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataEmissao" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataEmissao" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Matrícula:
						<h:inputText id="txtMatricula" value="#{funcionarioMB.funcionario.matricula }" disabled="#{!funcionarioMB.funcionario.ativo }"
							maxlength="10" required="true" requiredMessage="Informe a matrícula do funcionario!">							
						</h:inputText>
						<rich:message for="txtMatricula" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Cargo/Função:
						<h:inputText id="txtCargo" value="#{funcionarioMB.funcionario.cargo }" disabled="#{!funcionarioMB.funcionario.ativo }"
							maxlength="30" required="true" requiredMessage="Informe o cargo/função do funcionario!">							
						</h:inputText>
						<rich:message for="txtCargo" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Nº da pasta/arquivo:
						<h:inputText value="#{funcionarioMB.funcionario.numPasta }" maxlength="10"  disabled="#{!funcionarioMB.funcionario.ativo }"/>		
						
					</h:panelGrid>
					
					<h:panelGrid columns="3" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados do cônjuge"></h:outputText>
						</f:facet>						
						
						Nome:
						<h:inputText id="txtNomeConjuge" value="#{funcionarioMB.funcionario.nomeConjuge }" size="50" maxlength="100" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<rich:message for="txtNomeConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						CPF:
						<h:inputText id="txtCpfConjuge" value="#{funcionarioMB.funcionario.cpfConjuge }" size="13" disabled="#{!funcionarioMB.funcionario.ativo }"
							maxlength="11" converterMessage="CPF só deve conter números!">												
							<f:converter converterId="cpfconverter"/>
							<f:validator validatorId="cpfvalidator"/>
						</h:inputText>
						<rich:message for="txtCpf" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Data de nascimento:
						<h:panelGroup>						
							<h:inputText id="txtDataNascimentoConjuge" value="#{funcionarioMB.funcionario.dataNascimentoConjuge }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataNascimentoConjuge" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>
						<rich:message for="txtDataNascimentoConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Gênero:
						<h:selectOneRadio id="txtGeneroConjuge" value="#{funcionarioMB.funcionario.generoConjuge }" layout="lineDirection" disabled="#{!funcionarioMB.funcionario.ativo }">
							<f:selectItem itemValue="M" itemLabel="Masculino"/>
							<f:selectItem itemValue="F" itemLabel="Feminino"/>
						</h:selectOneRadio>
						<rich:message for="txtGeneroConjuge" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Nacionalidade:
						<h:inputText id="txtNacionalidadeConjuge" value="#{funcionarioMB.funcionario.nacionalidadeConjuge }" maxlength="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Naturalidade:
						<h:inputText id="txtNaturalidadeConjuge" value="#{funcionarioMB.funcionario.naturalidadeConjuge }" maxlength="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Filiação - Pai:
						<h:inputText id="txtFiliacaoPaiConjuge" value="#{funcionarioMB.funcionario.filiacaoPaiConjuge }" maxlength="100" size="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Filiação - Mãe:
						<h:inputText id="txtFiliacaoMaeConjuge" value="#{funcionarioMB.funcionario.filiacaoMaeConjuge }" maxlength="100" size="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Profissão:
						<h:inputText id="txtProfissaoConjuge" value="#{funcionarioMB.funcionario.profissaoConjuge }" maxlength="50" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>						
						
						RG:
						<h:inputText id="txtRgConjuge" value="#{funcionarioMB.funcionario.rgConjuge }"	maxlength="15" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Orgão Emissor:
						<h:inputText id="txtOrgaoEmissorConjuge" value="#{funcionarioMB.funcionario.orgaoEmissorConjuge }"	maxlength="30" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<h:inputHidden/>
						
						Data de emissão:	
						<h:panelGroup>					
							<h:inputText id="txtDataEmissaoConjuge" value="#{funcionarioMB.funcionario.dataEmissaoConjuge }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
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
							<h:inputText id="txtCep" value="#{funcionarioMB.funcionario.endereco.cep }" required="true" requiredMessage="Informe o CEP!" size="9" maxlength="8"  disabled="#{!funcionarioMB.funcionario.ativo }">
								<f:validator validatorId="cepvalidator"/>
							</h:inputText>
							&nbsp;
						<a4j:commandButton value="Pesquisar" action="#{funcionarioMB.buscarEndereco }" reRender="txtTipoLogradouro,txtLogradouro,txtBairro,txtUF,txtCidade,txtCep" style="padding: 3px 10px;" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						</h:panelGroup>
						<rich:message for="txtCep" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Tipo de logradouro:
						<h:inputText id="txtTipoLogradouro" value="#{funcionarioMB.funcionario.endereco.tipoLogradouro }" maxlength="100"  disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<rich:message for="txtTipoLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Logradouro:
						<h:inputText id="txtLogradouro"  disabled="#{!funcionarioMB.funcionario.ativo }"
							value="#{funcionarioMB.funcionario.endereco.logradouro }" size="50"							
							maxlength="150">							
						</h:inputText>
						<rich:message for="txtLogradouro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Número:
						<h:inputText id="txtNumero" value="#{funcionarioMB.funcionario.endereco.numero }" disabled="#{!funcionarioMB.funcionario.ativo }"							
							maxlength="10">							
						</h:inputText>
						<rich:message for="txtNumero" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						Complemento:
						<h:inputText value="#{funcionarioMB.funcionario.endereco.complemento }" disabled="#{!funcionarioMB.funcionario.ativo }" maxlength="50"></h:inputText>
						<h:inputHidden/>
						
						* Bairro:
						<h:inputText id="txtBairro" value="#{funcionarioMB.funcionario.endereco.bairro }"  disabled="#{!funcionarioMB.funcionario.ativo }"							
							maxlength="50"/>
						<rich:message for="txtBairro" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* Cidade:
						<h:inputText id="txtCidade" value="#{funcionarioMB.funcionario.endereco.cidade }"  disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<rich:message for="txtCidade" style="color: #ff0000; font-weight: bold;"></rich:message>
						
						* UF:
						<h:inputText id="txtUF" value="#{funcionarioMB.funcionario.endereco.uf }" disabled="#{!funcionarioMB.funcionario.ativo }"/>
						<rich:message for="txtUF" style="color: #ff0000; font-weight: bold;"></rich:message>
						
					</h:panelGrid>
					
					<h:panelGrid id="pnlTelefone" columns="7" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Telefones de Contato"></h:outputText>
						</f:facet>
						
						DDD:	
						<h:inputText value="#{funcionarioMB.telefone.ddd }" size="5" disabled="#{!funcionarioMB.funcionario.ativo }"
							validatorMessage="DDD deve ter pelo menos 2 dígitos!"
							maxlength="5" converterMessage="DDD só aceita números!">
							<f:converter converterId="telefoneconverter"/>
							<f:validateLength minimum="2"></f:validateLength>
						</h:inputText>
						
						Telefone:
						<h:inputText value="#{funcionarioMB.telefone.numero }" size="10" disabled="#{!funcionarioMB.funcionario.ativo }"
							maxlength="20" converterMessage="Telefone só aceita números!"
							validatorMessage="Telefone deve ter pelo menos 6 dígitos">
							<f:converter converterId="telefoneconverter"/>
							<f:validateLength minimum="6"></f:validateLength>
						</h:inputText>
						
						Tipo:
						<h:selectOneMenu value="#{funcionarioMB.telefone.tipoTelefone }" disabled="#{!funcionarioMB.funcionario.ativo }">
							<f:selectItem itemValue="Residencial" itemLabel="Residencial"/>
							<f:selectItem itemValue="Comercial" itemLabel="Comercial"/>
							<f:selectItem itemValue="Celular" itemLabel="Celular"/>
							<f:selectItem itemValue="Fax" itemLabel="Fax"/>
							<f:selectItem itemValue="Recado" itemLabel="Recado"/>
							<f:selectItem itemValue="Outros" itemLabel="Outros"/>
						</h:selectOneMenu>
						
						<a4j:commandButton value="Adicionar" style="padding: 3px 10px;" action="#{funcionarioMB.adicionarTelefone }" reRender="tableTelefones,pnlTelefone"  disabled="#{!funcionarioMB.funcionario.ativo }"/>
					</h:panelGrid>
					
						<rich:dataTable id="tableTelefones" value="#{funcionarioMB.listaTelefone }"
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
								<a4j:commandLink value="Remover" reRender="tableTelefones"  disabled="#{!funcionarioMB.funcionario.ativo }"
									action="#{funcionarioMB.removerTelefone }">
									<f:setPropertyActionListener value="#{item.numero }" target="#{funcionarioMB.numTelefone }" />
									<f:setPropertyActionListener value="#{item.tipoTelefone }" target="#{funcionarioMB.tipoTelefone }" />									
									<f:setPropertyActionListener value="#{item.ddd }" target="#{funcionarioMB.dddTelefone }" />
								</a4j:commandLink>
							</rich:column>
							
						</rich:dataTable>	
						
			<h:commandButton value="Editar" style="padding: 3px 10px;" action="#{funcionarioMB.edit }" disabled="#{!funcionarioMB.funcionario.ativo }" rendered="#{funcionarioMB.canEdit }"/>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;" action="#{funcionarioMB.cancelAction }" immediate="true"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>