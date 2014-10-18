/*** 

    Copyright (c) 2014 Hércules S. S. José
    

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

 ***/

package br.com.hslife.imobiliaria.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.facade.IServicoManutencaoBusiness;
import br.com.hslife.imobiliaria.model.ServicoManutencao;
import br.com.hslife.imobiliaria.repository.ServicoManutencaoRepository;

@Component
public class ServicoManutencaoBusiness extends AbstractCRUDBusiness<ServicoManutencao> implements IServicoManutencaoBusiness {

	@Autowired
	private ServicoManutencaoRepository repository;

	public ServicoManutencaoRepository getRepository() {
		return repository;
	}

	public void setRepository(ServicoManutencaoRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ServicoManutencao> buscarTodos() throws BusinessException {
		return getRepository().findAll();
	}

	@Override
	public List<ServicoManutencao> buscarPorDescricao(String descricao) throws BusinessException {
		return getRepository().findByDescricao(descricao);
	}

	
}
