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

import br.com.hslife.imobiliaria.logic.impl.AgendaLogic;
import br.com.hslife.imobiliaria.logic.impl.AluguelLogic;
import br.com.hslife.imobiliaria.logic.impl.ClienteLogic;
import br.com.hslife.imobiliaria.logic.impl.ClientePJLogic;
import br.com.hslife.imobiliaria.logic.impl.ContratoLogic;
import br.com.hslife.imobiliaria.logic.impl.CorretorLogic;
import br.com.hslife.imobiliaria.logic.impl.FormaPagamentoLogic;
import br.com.hslife.imobiliaria.logic.impl.FuncionarioLogic;
import br.com.hslife.imobiliaria.logic.impl.GrupoLogic;
import br.com.hslife.imobiliaria.logic.impl.ImovelLogic;
import br.com.hslife.imobiliaria.logic.impl.IndiceReajusteLogic;
import br.com.hslife.imobiliaria.logic.impl.ModeloContratoLogic;
import br.com.hslife.imobiliaria.logic.impl.RelatorioLogic;
import br.com.hslife.imobiliaria.logic.impl.UsuarioLogic;

public class LogicFactory {
	
	private LogicFactory () {
		// Construtor privado
	}
	
	public static ClienteLogic createClienteLogic() {
		return new ClienteLogic(DaoFactory.createClienteDao());
	}
	
	public static ImovelLogic createImovelLogic() {
		return new ImovelLogic(DaoFactory.createImovelDao());
	}
	
	public static ClientePJLogic createClientePJLogic() {
		return new ClientePJLogic(DaoFactory.createClientePJDao());
	}
	
	public static IndiceReajusteLogic createIndiceReajusteLogic() {
		return new IndiceReajusteLogic(DaoFactory.createIndiceReajusteDao());
	}
	
	public static ModeloContratoLogic createModeloContratoLogic() {
		return new ModeloContratoLogic(DaoFactory.createModeloContratoDao());
	}
	
	public static ContratoLogic createContratoLogic() {
		return new ContratoLogic(DaoFactory.createContratoDao());
	}
	
	public static CorretorLogic createCorretorLogic() {
		return new CorretorLogic(DaoFactory.createCorretorDao());
	}
	
	public static AgendaLogic createAgendaLogic() {
		return new AgendaLogic(DaoFactory.createAgendaDao());
	}
	
	public static FuncionarioLogic createFuncionarioLogic() {
		return new FuncionarioLogic(DaoFactory.createFuncionarioDao());
	}
	
	public static GrupoLogic createGrupoLogic() {
		return new GrupoLogic(DaoFactory.createGrupoDao());
	}
	
	public static UsuarioLogic createUsuarioLogic() {
		return new UsuarioLogic(DaoFactory.createUsuarioDao());
	}
	
	public static RelatorioLogic createRelatorioLogic() {
		return new RelatorioLogic(DaoFactory.createRelatorioDao());
	}
	
	public static FormaPagamentoLogic createFormaPagamentoLogic() {
		return new FormaPagamentoLogic(DaoFactory.createFormaPagamentoDao());
	}
	
	public static AluguelLogic createAluguelLogic() {
		return new AluguelLogic(DaoFactory.createAluguelDao());
	}
}