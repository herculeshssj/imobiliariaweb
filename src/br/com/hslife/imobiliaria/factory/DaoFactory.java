/*** 

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

 ***/

package br.com.hslife.imobiliaria.factory;

import br.com.hslife.imobiliaria.dao.IAgendaDao;
import br.com.hslife.imobiliaria.dao.IAluguelDao;
import br.com.hslife.imobiliaria.dao.IClienteDao;
import br.com.hslife.imobiliaria.dao.IClientePJDao;
import br.com.hslife.imobiliaria.dao.IContratoDao;
import br.com.hslife.imobiliaria.dao.ICorretorDao;
import br.com.hslife.imobiliaria.dao.IFormaPagamentoDao;
import br.com.hslife.imobiliaria.dao.IFuncionarioDao;
import br.com.hslife.imobiliaria.dao.IGrupoDao;
import br.com.hslife.imobiliaria.dao.IImovelDao;
import br.com.hslife.imobiliaria.dao.IIndiceReajusteDao;
import br.com.hslife.imobiliaria.dao.IModeloContratoDao;
import br.com.hslife.imobiliaria.dao.IRelatorioDao;
import br.com.hslife.imobiliaria.dao.IUsuarioDao;
import br.com.hslife.imobiliaria.dao.impl.AgendaDao;
import br.com.hslife.imobiliaria.dao.impl.AluguelDao;
import br.com.hslife.imobiliaria.dao.impl.ClienteDao;
import br.com.hslife.imobiliaria.dao.impl.ClientePJDao;
import br.com.hslife.imobiliaria.dao.impl.ContratoDao;
import br.com.hslife.imobiliaria.dao.impl.CorretorDao;
import br.com.hslife.imobiliaria.dao.impl.FormaPagamentoDao;
import br.com.hslife.imobiliaria.dao.impl.FuncionarioDao;
import br.com.hslife.imobiliaria.dao.impl.GrupoDao;
import br.com.hslife.imobiliaria.dao.impl.ImovelDao;
import br.com.hslife.imobiliaria.dao.impl.IndiceReajusteDao;
import br.com.hslife.imobiliaria.dao.impl.ModeloContratoDao;
import br.com.hslife.imobiliaria.dao.impl.RelatorioDao;
import br.com.hslife.imobiliaria.dao.impl.UsuarioDao;

public class DaoFactory {

	private DaoFactory() {
		// Construtor privado
	}
	
	public static IClienteDao createClienteDao() {
		return new ClienteDao();
	}
	
	public static IClientePJDao createClientePJDao() {
		return new ClientePJDao();
	}
	
	public static IImovelDao createImovelDao() {
		return new ImovelDao();
	}
	
	public static IIndiceReajusteDao createIndiceReajusteDao() {
		return new IndiceReajusteDao();
	}
	
	public static IModeloContratoDao createModeloContratoDao() {
		return new ModeloContratoDao();
	}
	
	public static IContratoDao createContratoDao() {
		return new ContratoDao();
	}
	
	public static IAgendaDao createAgendaDao() {
		return new AgendaDao();
	}
	
	public static ICorretorDao createCorretorDao() {
		return new CorretorDao();
	}
	
	public static IFuncionarioDao createFuncionarioDao() {
		return new FuncionarioDao();
	}
	
	public static IGrupoDao createGrupoDao() {
		return new GrupoDao();
	}
	
	public static IUsuarioDao createUsuarioDao() {
		return new UsuarioDao();
	}
	
	public static IRelatorioDao createRelatorioDao() {
		return new RelatorioDao();
	}
	
	public static IFormaPagamentoDao createFormaPagamentoDao() {
		return new FormaPagamentoDao();
	}
	
	public static IAluguelDao createAluguelDao() {
		return new AluguelDao();
	}
}