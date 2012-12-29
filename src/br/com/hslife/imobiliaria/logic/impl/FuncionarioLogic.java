/*** 

    Copyright (c) 2011 Hércules S. S. José
    

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

package br.com.hslife.imobiliaria.logic.impl;

import java.util.List;

import br.com.hslife.imobiliaria.dao.IFuncionarioDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.logic.IFuncionario;
import br.com.hslife.imobiliaria.model.Funcionario;

public class FuncionarioLogic implements IFuncionario {

	IFuncionarioDao dao;

	public FuncionarioLogic(IFuncionarioDao dao) {
		this.dao = dao;
	}

	@Override
	public void cadastrar(Funcionario funcionario) throws BusinessException {
		// Verifica se o CPF já está cadastrado
		if (dao.buscarPorCPF(funcionario.getCpf()).size() > 0) {
			throw new BusinessException("CPF já cadastrado!");
		}

		try {
			HibernateUtility.beginTransaction();
			dao.save(funcionario);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}

	}

	@Override
	public void editar(Funcionario funcionario) throws BusinessException {
		// Verifica se o CPF já está cadastrado
		if (dao.buscarPorCPF(funcionario.getCpf()).size() > 1) {
			throw new BusinessException("CPF já cadastrado!");
		}

		try {
			HibernateUtility.beginTransaction();
			dao.update(funcionario);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}

	}

	@Override
	public void habilitar(Long id) throws BusinessException {
		Funcionario f = (Funcionario) dao.getById(id, Funcionario.class);
		try {
			HibernateUtility.beginTransaction();
			if (f.isAtivo()) {
				f.setAtivo(false);
				dao.desativarLogins(id);
			} else {
				f.setAtivo(true);
			}
			dao.update(f);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}

	}

	@Override
	public Funcionario buscar(Long id) throws BusinessException {
		return (Funcionario) dao.getById(id, Funcionario.class);
	}

	@Override
	public List<Funcionario> buscar(Funcionario funcionario)
			throws BusinessException {
		return dao.listByExample(funcionario);
	}

	@Override
	public List<Funcionario> buscarTodos() throws BusinessException {
		return dao.list(Funcionario.class);
	}

	@Override
	public List<Funcionario> buscarTodosAtivos() throws BusinessException {
		return dao.buscarTodosAtivos();
	}

	@Override
	public List<Funcionario> buscarPorCPF(String cpf) throws BusinessException {
		return dao.buscarPorCPF(cpf);
	}

	/*
	 * // Esta classe é responsável por gerenciar os funcionarioes da
	 * imobiliária
	 * 
	 * public void cadastrarFuncionario(Funcionario funcionario) throws
	 * GlobalException {
	 * 
	 * // Verifica se o CPF informado já está cadastrado. Caso esteja lança uma
	 * exceção de aviso if (dao.findAllByField("cpf",
	 * funcionario.getCpf()).size() > 0) { throw new
	 * LogicException("O CPF informado já está cadastrado!"); }
	 * 
	 * // Recebe o funcionario populado para cadastrar dao.save(funcionario); }
	 * 
	 * public void editarFuncionario(Funcionario funcionario) throws
	 * GlobalException { // Recebe o funcionario com os dados alterados e
	 * prontos para salvar dao.update(funcionario); }
	 * 
	 * public void habilitarFuncionario(long id) throws GlobalException { //
	 * Recebe o id do funcionario para alterar o estado dele de acordo com a
	 * informação salva no banco Funcionario funcionario = (Funcionario)
	 * dao.findById(id); if (funcionario.isAtivo()) { new UsuarioLogic(new
	 * UsuarioDao()).temLoginAtivoFuncionario(id); funcionario.setAtivo(false);
	 * } else { funcionario.setAtivo(true); } dao.update(funcionario); }
	 * 
	 * public Funcionario buscarFuncionario(long id) throws GlobalException {
	 * return (Funcionario)dao.findById(id); }
	 * 
	 * public List<Funcionario> buscarFuncionarios(Funcionario funcionario)
	 * throws GlobalException { // Retorna a lista de funcionarioes encontrados
	 * a partir dos dados informados
	 * 
	 * // Declara o Map que será passado para o Dao Map<String, Object> criterio
	 * = new HashMap<String, Object>();
	 * 
	 * // Verifica cada campo em busca de valores válidos para a busca if
	 * (funcionario.getNome()!= null && !funcionario.getNome().trim().isEmpty())
	 * { criterio.put(SEARCH_NOME, funcionario.getNome()); } if
	 * (funcionario.getCpf()!= null && !funcionario.getCpf().trim().isEmpty()) {
	 * criterio.put(SEARCH_CPF, funcionario.getCpf()); } if
	 * (funcionario.getMatricula()!= null &&
	 * !funcionario.getMatricula().trim().isEmpty()) {
	 * criterio.put(SEARCH_MATRICULA, funcionario.getMatricula()); } if
	 * (funcionario.getCargo()!= null &&
	 * !funcionario.getCargo().trim().isEmpty()) { criterio.put(SEARCH_CARGO,
	 * funcionario.getCargo()); } if (funcionario.getNumPasta()!= null) {
	 * criterio.put(SEARCH_PASTA, funcionario.getNumPasta()); }
	 * criterio.put(SEARCH_ATIVO, funcionario.isAtivo()); return
	 * dao.findByCriteria(criterio); }
	 * 
	 * public IDao getDao() { return dao; }
	 * 
	 * public void setDao(IDao dao) { this.dao = dao; }
	 */
}