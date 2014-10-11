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

package br.com.hslife.imobiliaria.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.facade.IModeloContratoBusiness;
import br.com.hslife.imobiliaria.model.Contrato;
import br.com.hslife.imobiliaria.model.ModeloContrato;
import br.com.hslife.imobiliaria.repository.ContratoRepository;
import br.com.hslife.imobiliaria.repository.ModeloContratoRepository;

@Component
public class ModeloContratoBusiness extends AbstractCRUDBusiness<ModeloContrato> implements IModeloContratoBusiness {

	@Autowired
	private ModeloContratoRepository repository;
	
	@Autowired
	private ContratoRepository contratoRepository;

	public ModeloContratoRepository getRepository() {
		return repository;
	}

	public void setRepository(ModeloContratoRepository repository) {
		this.repository = repository;
	}

	public void setContratoRepository(ContratoRepository contratoRepository) {
		this.contratoRepository = contratoRepository;
	}

	@Override
	public List<ModeloContrato> buscarPorDescricao(String descricao) throws BusinessException {
		return getRepository().findByDescricao(descricao);
	}
	
	@Override
	public void habilitar(ModeloContrato modeloContrato) throws BusinessException {
		if (modeloContrato.isAtivo()) {
			modeloContrato.setAtivo(false);
		} else {
			modeloContrato.setAtivo(true);
		}
		getRepository().update(modeloContrato);
	}
	
	@Override
	public void excluir(ModeloContrato entity) throws BusinessException {
		List<Contrato> resultado = contratoRepository.findByModeloContrato(entity);
		if (resultado == null || resultado.isEmpty())
			super.excluir(entity);
		else
			throw new BusinessException("Existem contratos vinculados ao modelo!");
	}
}
