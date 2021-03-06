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

<div id="content">
	
		<h:form>
		<rich:messages></rich:messages>
		
		<h3>				
					<img src="../images/icon_aluguel.png" height="32" width="32"/>
					<h:commandLink value="Aluguéis" action="listAluguel"></h:commandLink>
					&nbsp;	
										
					<img src="../images/icon_historicoaluguel.png" height="32" width="32"/>
					<h:commandLink value="Histórico" action="listHistoricoAluguel"></h:commandLink>
					&nbsp;
					
					<img src="../images/icon_formapagamento.png" height="32" width="32"/>
					<h:commandLink value="Formas de Pagamento" action="formFormaPagamento"></h:commandLink>
					&nbsp;
					
					<img src="../images/icon_servicomanutencao.png" height="32" width="32"/>
					<h:commandLink value="Serviços de Manutenção" action="formServicoManutencao"></h:commandLink>
					&nbsp;							
											
			</h3>	
		
		</h:form>
	</f:view>

	<div style="clear: both;"></div>

</div>

<jsp:include page="../footer.jsp"></jsp:include>