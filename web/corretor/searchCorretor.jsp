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
			
			<h2>Corretor - Pesquisar</h2>
			
					<h:panelGrid columns="2" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados pessoais"></h:outputText>
						</f:facet>
						
						Nome:
						<h:inputText id="txtNome" value="#{corretorMB.corretor.nome }" size="50" maxlength="100"/>
							
						CPF:
						<h:inputText id="txtCpf" value="#{corretorMB.corretor.cpf }" size="11"	maxlength="11"/> 
						
						CRECI:
						<h:inputText id="txtCreci" value="#{corretorMB.corretor.creci }" maxlength="100" size="30"/>							
						
						Região de registro/atuação:
						<h:selectOneMenu id="txtRegiao" value="#{corretorMB.corretor.regiao }">
							<f:selectItem itemLabel="Selecione"/>
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
						
						Data de registro do conselho:
						<h:panelGroup>						
							<h:inputText id="txtDataRegistro" value="#{corretorMB.corretor.dataRegistro }" converterMessage="Formato de data inválido! Tente dd/mm/aaaa e somente números!">
								<f:convertDateTime pattern="dd/MM/yyyy"/>							
							</h:inputText>
							<rich:jQuery selector="#txtDataRegistro" query="mask('99/99/9999')" timing="onload"/>
						</h:panelGroup>						
												
						Nº da pasta/arquivo:
						<h:inputText value="#{corretorMB.corretor.numPasta }"></h:inputText>	
						
						Ativo no sistema:
						<h:selectBooleanCheckbox value="#{corretorMB.corretor.ativo }"/>					

					</h:panelGrid>
		
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{corretorMB.search }" rendered="#{corretorMB.canList }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;" action="#{corretorMB.cancelAction }" immediate="true"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>