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
