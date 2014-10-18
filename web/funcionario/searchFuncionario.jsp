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
			
			<h2>Funcionario - Pesquisar</h2>
			
					<h:panelGrid columns="2" cellspacing="10" style="padding: 10px 10px 10px 10px;">
						<f:facet name="header">
							<h:outputText value="Dados pessoais"></h:outputText>
						</f:facet>
						
						Nome:
						<h:inputText id="txtNome" value="#{funcionarioMB.funcionario.nome }" size="50" maxlength="100"/>
							
						CPF:
						<h:inputText id="txtCpf" value="#{funcionarioMB.funcionario.cpf }" size="11"	maxlength="11"/> 	
						
						Matrícula:
						<h:inputText id="txtMatricula" value="#{funcionarioMB.funcionario.matricula }" maxlength="10"/>							
						
						Cargo:
						<h:inputText id="txtCargo" value="#{funcionarioMB.funcionario.cargo }" maxlength="30"/>					
												
						Nº da pasta/arquivo:
						<h:inputText value="#{funcionarioMB.funcionario.numPasta }"></h:inputText>	
						
						Ativo no sistema:
						<h:selectBooleanCheckbox value="#{funcionarioMB.funcionario.ativo }"/>					

					</h:panelGrid>
		
			<h:commandButton value="Pesquisar" style="padding: 3px 10px;" action="#{funcionarioMB.search }" rendered="#{funcionarioMB.canList }"></h:commandButton>
			&nbsp;&nbsp;
			<h:commandButton value="Cancelar" style="padding: 3px 10px;" action="#{funcionarioMB.cancelAction }" immediate="true"></h:commandButton>

			<div style="clear: both;"></div>

		</div>
	</h:form>
</f:view>
<jsp:include page="../footer.jsp"></jsp:include>