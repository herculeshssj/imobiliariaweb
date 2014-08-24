package br.com.hslife.imobiliaria.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.facade.IFormaPagamentoBusiness;
import br.com.hslife.imobiliaria.model.FormaPagamento;
import br.com.hslife.imobiliaria.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoBusiness extends AbstractCRUDBusiness<FormaPagamento> implements IFormaPagamentoBusiness {

	@Autowired
	private FormaPagamentoRepository repository;

	public FormaPagamentoRepository getRepository() {
		return repository;
	}

	public void setRepository(FormaPagamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<FormaPagamento> buscarTodos() throws BusinessException {
		return getRepository().findAll();
	}

	@Override
	public List<FormaPagamento> buscarPorDescricao(String descricao) throws BusinessException {
		return getRepository().findByDescricao(descricao);
	}

	
}
