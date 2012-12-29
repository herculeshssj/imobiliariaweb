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

package br.com.hslife.imobiliaria.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.hslife.imobiliaria.model.Contrato;

public class RelParams {
	
	public static Map<String, Object> popular(Contrato c) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		CurrencyWriter extenso = new CurrencyWriter();
		
		parametros.put("LOCADOR_CPF", c.getImovel().getLocador().getCpf());
		parametros.put("LOCADOR_CPFCONJUGE", c.getImovel().getLocador().getCpfConjuge());
		parametros.put("LOCADOR_RG", c.getImovel().getLocador().getRg());
		parametros.put("LOCADOR_RGCONJUGE", c.getImovel().getLocador().getRgConjuge());
		parametros.put("LOCADOR_ORGAOEMISSOR", c.getImovel().getLocador().getOrgaoEmissor());
		parametros.put("LOCADOR_ORGAOEMISSORCONJUGE", c.getImovel().getLocador().getOrgaoEmissorConjuge());
		parametros.put("LOCADOR_DATAEMISSAO", Util.formataDataHora(c.getImovel().getLocador().getDataEmissao(), Util.DATA));
		parametros.put("LOCADOR_DATAEMISSAOCONJUGE", Util.formataDataHora(c.getImovel().getLocador().getDataEmissaoConjuge(), Util.DATA));
		parametros.put("LOCADOR_NOME", c.getImovel().getLocador().getNome());
		parametros.put("LOCADOR_NOMECONJUGE", c.getImovel().getLocador().getNomeConjuge());
		parametros.put("LOCADOR_DATANASCIMENTO", Util.formataDataHora(c.getImovel().getLocador().getDataNascimento(), Util.DATA));
		parametros.put("LOCADOR_DATANASCIMENTOCONJUGE", Util.formataDataHora(c.getImovel().getLocador().getDataNascimentoConjuge(), Util.DATA));
		parametros.put("LOCADOR_GENERO", c.getImovel().getLocador().getGenero());
		parametros.put("LOCADOR_GENEROCONJUGE", c.getImovel().getLocador().getGeneroConjuge());
		parametros.put("LOCADOR_NACIONALIDADE", c.getImovel().getLocador().getNacionalidade());
		parametros.put("LOCADOR_NACIONALIDADECONJUGE", c.getImovel().getLocador().getNacionalidadeConjuge());
		parametros.put("LOCADOR_NATURALIDADE", c.getImovel().getLocador().getNaturalidade());
		parametros.put("LOCADOR_NATURALIDADECONJUGE", c.getImovel().getLocador().getNaturalidadeConjuge());
		parametros.put("LOCADOR_FILIACAOPAI", c.getImovel().getLocador().getFiliacaoPai());
		parametros.put("LOCADOR_FILIACAOPAICONJUGE", c.getImovel().getLocador().getFiliacaoPaiConjuge());
		parametros.put("LOCADOR_FILIACAOMAE", c.getImovel().getLocador().getFiliacaoMae());
		parametros.put("LOCADOR_FILIACAOMAECONJUGE", c.getImovel().getLocador().getFiliacaoMaeConjuge());
		parametros.put("LOCADOR_ESTADOCIVIL", c.getImovel().getLocador().getEstadoCivil());
		parametros.put("LOCADOR_PROFISSAO", c.getImovel().getLocador().getProfissao());
		parametros.put("LOCADOR_PROFISSAOCONJUGE", c.getImovel().getLocador().getProfissaoConjuge());
		parametros.put("LOCADOR_TIPOLOGRADOURO", c.getImovel().getLocador().getEndereco().getTipoLogradouro());
		parametros.put("LOCADOR_LOGRADOURO", c.getImovel().getLocador().getEndereco().getLogradouro());
		parametros.put("LOCADOR_NUMERO", c.getImovel().getLocador().getEndereco().getNumero());
		parametros.put("LOCADOR_COMPLEMENTO", c.getImovel().getLocador().getEndereco().getComplemento());
		parametros.put("LOCADOR_BAIRRO", c.getImovel().getLocador().getEndereco().getBairro());
		parametros.put("LOCADOR_CIDADE", c.getImovel().getLocador().getEndereco().getCidade());
		parametros.put("LOCADOR_UF", c.getImovel().getLocador().getEndereco().getUf());
		parametros.put("LOCADOR_CEP", c.getImovel().getLocador().getEndereco().getCep());
		
		if (c.getLocatario() != null) {
			parametros.put("LOCATARIO_CPF", c.getLocatario().getCpf());
			parametros.put("LOCATARIO_CPFCONJUGE", c.getLocatario().getCpfConjuge());
			parametros.put("LOCATARIO_RG", c.getLocatario().getRg());
			parametros.put("LOCATARIO_RGCONJUGE", c.getLocatario().getRgConjuge());
			parametros.put("LOCATARIO_ORGAOEMISSOR", c.getLocatario().getOrgaoEmissor());
			parametros.put("LOCATARIO_ORGAOEMISSORCONJUGE", c.getLocatario().getOrgaoEmissorConjuge());
			parametros.put("LOCATARIO_DATAEMISSAO", Util.formataDataHora(c.getLocatario().getDataEmissao(), Util.DATA));
			parametros.put("LOCATARIO_DATAEMISSAOCONJUGE", Util.formataDataHora(c.getLocatario().getDataEmissaoConjuge(), Util.DATA));
			parametros.put("LOCATARIO_NOME", c.getLocatario().getNome());
			parametros.put("LOCATARIO_NOMECONJUGE", c.getLocatario().getNomeConjuge());
			parametros.put("LOCATARIO_DATANASCIMENTO", Util.formataDataHora(c.getLocatario().getDataNascimento(), Util.DATA));
			parametros.put("LOCATARIO_DATANASCIMENTOCONJUGE", Util.formataDataHora(c.getLocatario().getDataNascimentoConjuge(), Util.DATA));
			parametros.put("LOCATARIO_GENERO", c.getLocatario().getGenero());
			parametros.put("LOCATARIO_GENEROCONJUGE", c.getLocatario().getGeneroConjuge());
			parametros.put("LOCATARIO_NACIONALIDADE", c.getLocatario().getNacionalidade());
			parametros.put("LOCATARIO_NACIONALIDADECONJUGE", c.getLocatario().getNacionalidadeConjuge());
			parametros.put("LOCATARIO_NATURALIDADE", c.getLocatario().getNaturalidade());
			parametros.put("LOCATARIO_NATURALIDADECONJUGE", c.getLocatario().getNaturalidadeConjuge());
			parametros.put("LOCATARIO_FILIACAOPAI", c.getLocatario().getFiliacaoPai());
			parametros.put("LOCATARIO_FILIACAOPAICONJUGE", c.getLocatario().getFiliacaoPaiConjuge());
			parametros.put("LOCATARIO_FILIACAOMAE", c.getLocatario().getFiliacaoMae());
			parametros.put("LOCATARIO_FILIACAOMAECONJUGE", c.getLocatario().getFiliacaoMaeConjuge());
			parametros.put("LOCATARIO_ESTADOCIVIL", c.getLocatario().getEstadoCivil());
			parametros.put("LOCATARIO_PROFISSAO", c.getLocatario().getProfissao());
			parametros.put("LOCATARIO_PROFISSAOCONJUGE", c.getLocatario().getProfissaoConjuge());
			parametros.put("LOCATARIO_TIPOLOGRADOURO", c.getLocatario().getEndereco().getTipoLogradouro());
			parametros.put("LOCATARIO_LOGRADOURO", c.getLocatario().getEndereco().getLogradouro());
			parametros.put("LOCATARIO_NUMERO", c.getLocatario().getEndereco().getNumero());
			parametros.put("LOCATARIO_COMPLEMENTO", c.getLocatario().getEndereco().getComplemento());
			parametros.put("LOCATARIO_BAIRRO", c.getLocatario().getEndereco().getBairro());
			parametros.put("LOCATARIO_CIDADE", c.getLocatario().getEndereco().getCidade());
			parametros.put("LOCATARIO_UF", c.getLocatario().getEndereco().getUf());
			parametros.put("LOCATARIO_CEP", c.getLocatario().getEndereco().getCep());
		}
		
		if (c.getLocatarioPJ() != null) {
			
			parametros.put("EMPRESA_CNPJ", c.getLocatarioPJ().getCnpj());
			parametros.put("EMPRESA_NOMEFANTASIA", c.getLocatarioPJ().getNomeFantasia());
			parametros.put("EMPRESA_RAMOATIVIDADE", c.getLocatarioPJ().getRamoAtividade());
			parametros.put("EMPRESA_TIPOLOGRADOURO", c.getLocatarioPJ().getEndereco().getTipoLogradouro());
			parametros.put("EMPRESA_LOGRADOURO", c.getLocatarioPJ().getEndereco().getLogradouro());
			parametros.put("EMPRESA_NUMERO", c.getLocatarioPJ().getEndereco().getNumero());
			parametros.put("EMPRESA_COMPLEMENTO", c.getLocatarioPJ().getEndereco().getComplemento());
			parametros.put("EMPRESA_BAIRRO", c.getLocatarioPJ().getEndereco().getBairro());
			parametros.put("EMPRESA_CIDADE", c.getLocatarioPJ().getEndereco().getCidade());
			parametros.put("EMPRESA_UF", c.getLocatarioPJ().getEndereco().getUf());
			parametros.put("EMPRESA_CEP", c.getLocatarioPJ().getEndereco().getCep());
		
			if (c.getLocatarioPJ().getSocios() != null && c.getLocatarioPJ().getSocios().size() >=1 ) {
				parametros.put("SOCIO_CPF", c.getLocatarioPJ().getSocios().get(0).getClientePF().getCpf());
				parametros.put("SOCIO_CPFCONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getCpfConjuge());
				parametros.put("SOCIO_RG", c.getLocatarioPJ().getSocios().get(0).getClientePF().getRg());
				parametros.put("SOCIO_RGCONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getRgConjuge());
				parametros.put("SOCIO_ORGAOEMISSOR", c.getLocatarioPJ().getSocios().get(0).getClientePF().getOrgaoEmissor());
				parametros.put("SOCIO_ORGAOEMISSORCONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getOrgaoEmissorConjuge());
				parametros.put("SOCIO_DATAEMISSAO", Util.formataDataHora(c.getLocatarioPJ().getSocios().get(0).getClientePF().getDataEmissao(), Util.DATA));
				parametros.put("SOCIO_DATAEMISSAOCONJUGE", Util.formataDataHora(c.getLocatarioPJ().getSocios().get(0).getClientePF().getDataEmissaoConjuge(), Util.DATA));
				parametros.put("SOCIO_NOME", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNome());
				parametros.put("SOCIO_NOMECONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNomeConjuge());
				parametros.put("SOCIO_DATANASCIMENTO", Util.formataDataHora(c.getLocatarioPJ().getSocios().get(0).getClientePF().getDataNascimento(), Util.DATA));
				parametros.put("SOCIO_DATANASCIMENTOCONJUGE", Util.formataDataHora(c.getLocatarioPJ().getSocios().get(0).getClientePF().getDataNascimentoConjuge(), Util.DATA));
				parametros.put("SOCIO_GENERO", c.getLocatarioPJ().getSocios().get(0).getClientePF().getGenero());
				parametros.put("SOCIO_GENEROCONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getGeneroConjuge());
				parametros.put("SOCIO_NACIONALIDADE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNacionalidade());
				parametros.put("SOCIO_NACIONALIDADECONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNacionalidadeConjuge());
				parametros.put("SOCIO_NATURALIDADE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNaturalidade());
				parametros.put("SOCIO_NATURALIDADECONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNaturalidadeConjuge());
				parametros.put("SOCIO_FILIACAOPAI", c.getLocatarioPJ().getSocios().get(0).getClientePF().getFiliacaoPai());
				parametros.put("SOCIO_FILIACAOPAICONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getFiliacaoPaiConjuge());
				parametros.put("SOCIO_FILIACAOMAE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getFiliacaoMae());
				parametros.put("SOCIO_FILIACAOMAECONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getFiliacaoMaeConjuge());
				parametros.put("SOCIO_ESTADOCIVIL", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEstadoCivil());
				parametros.put("SOCIO_PROFISSAO", c.getLocatarioPJ().getSocios().get(0).getClientePF().getProfissao());
				parametros.put("SOCIO_PROFISSAOCONJUGE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getProfissaoConjuge());
				parametros.put("SOCIO_TIPOLOGRADOURO", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getTipoLogradouro());
				parametros.put("SOCIO_LOGRADOURO", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getLogradouro());
				parametros.put("SOCIO_NUMERO", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getNumero());
				parametros.put("SOCIO_COMPLEMENTO", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getComplemento());
				parametros.put("SOCIO_BAIRRO", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getBairro());
				parametros.put("SOCIO_CIDADE", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getCidade());
				parametros.put("SOCIO_UF", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getUf());
				parametros.put("SOCIO_CEP", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getCep());
				parametros.put("SOCIO_FUNCAO", c.getLocatarioPJ().getSocios().get(0).getFuncao());
			}
			
		}
		
		if (c.getFiador() != null) {
			parametros.put("FIADOR_CPF", c.getFiador().getCpf());
			parametros.put("FIADOR_CPFCONJUGE", c.getFiador().getCpfConjuge());
			parametros.put("FIADOR_RG", c.getFiador().getRg());
			parametros.put("FIADOR_RGCONJUGE", c.getFiador().getRgConjuge());
			parametros.put("FIADOR_ORGAOEMISSOR", c.getFiador().getOrgaoEmissor());
			parametros.put("FIADOR_ORGAOEMISSORCONJUGE", c.getFiador().getOrgaoEmissorConjuge());
			parametros.put("FIADOR_DATAEMISSAO", Util.formataDataHora(c.getFiador().getDataEmissao(), Util.DATA));
			parametros.put("FIADOR_DATAEMISSAOCONJUGE", Util.formataDataHora(c.getFiador().getDataEmissaoConjuge(), Util.DATA));
			parametros.put("FIADOR_NOME", c.getFiador().getNome());
			parametros.put("FIADOR_NOMECONJUGE", c.getFiador().getNomeConjuge());
			parametros.put("FIADOR_DATANASCIMENTO", Util.formataDataHora(c.getFiador().getDataNascimento(), Util.DATA));
			parametros.put("FIADOR_DATANASCIMENTOCONJUGE", Util.formataDataHora(c.getFiador().getDataNascimentoConjuge(), Util.DATA));
			parametros.put("FIADOR_GENERO", c.getFiador().getGenero());
			parametros.put("FIADOR_GENEROCONJUGE", c.getFiador().getGeneroConjuge());
			parametros.put("FIADOR_NACIONALIDADE", c.getFiador().getNacionalidade());
			parametros.put("FIADOR_NACIONALIDADECONJUGE", c.getFiador().getNacionalidadeConjuge());
			parametros.put("FIADOR_NATURALIDADE", c.getFiador().getNaturalidade());
			parametros.put("FIADOR_NATURALIDADECONJUGE", c.getFiador().getNaturalidadeConjuge());
			parametros.put("FIADOR_FILIACAOPAI", c.getFiador().getFiliacaoPai());
			parametros.put("FIADOR_FILIACAOPAICONJUGE", c.getFiador().getFiliacaoPaiConjuge());
			parametros.put("FIADOR_FILIACAOMAE", c.getFiador().getFiliacaoMae());
			parametros.put("FIADOR_FILIACAOMAECONJUGE", c.getFiador().getFiliacaoMaeConjuge());
			parametros.put("FIADOR_ESTADOCIVIL", c.getFiador().getEstadoCivil());
			parametros.put("FIADOR_PROFISSAO", c.getFiador().getProfissao());
			parametros.put("FIADOR_PROFISSAOCONJUGE", c.getFiador().getProfissaoConjuge());
			parametros.put("FIADOR_TIPOLOGRADOURO", c.getFiador().getEndereco().getTipoLogradouro());
			parametros.put("FIADOR_LOGRADOURO", c.getFiador().getEndereco().getLogradouro());
			parametros.put("FIADOR_NUMERO", c.getFiador().getEndereco().getNumero());
			parametros.put("FIADOR_COMPLEMENTO", c.getFiador().getEndereco().getComplemento());
			parametros.put("FIADOR_BAIRRO", c.getFiador().getEndereco().getBairro());
			parametros.put("FIADOR_CIDADE", c.getFiador().getEndereco().getCidade());
			parametros.put("FIADOR_UF", c.getFiador().getEndereco().getUf());
			parametros.put("FIADOR_CEP", c.getFiador().getEndereco().getCep());
		}
		
		parametros.put("IMOVEL_VALOR", c.getImovel().getValor());
		parametros.put("IMOVEL_VALOR_EXTENSO", extenso.write(BigDecimal.valueOf(c.getImovel().getValor())));
		parametros.put("IMOVEL_NUMREGISTRO", c.getImovel().getNumRegistro());
		parametros.put("IMOVEL_TIPOIMOVEL", c.getImovel().getTipoImovel());
		parametros.put("IMOVEL_SITUACAOIMOVEL", c.getImovel().getSituacaoImovel());
		parametros.put("IMOVEL_TIPOLOGRADOURO", c.getImovel().getEndereco().getTipoLogradouro());
		parametros.put("IMOVEL_LOGRADOURO", c.getImovel().getEndereco().getLogradouro());
		parametros.put("IMOVEL_NUMERO", c.getImovel().getEndereco().getNumero());
		parametros.put("IMOVEL_COMPLEMENTO", c.getImovel().getEndereco().getComplemento());
		parametros.put("IMOVEL_BAIRRO", c.getImovel().getEndereco().getBairro());
		parametros.put("IMOVEL_CIDADE", c.getImovel().getEndereco().getCidade());
		parametros.put("IMOVEL_UF", c.getImovel().getEndereco().getUf());
		parametros.put("IMOVEL_CEP", c.getImovel().getEndereco().getCep());
		
		parametros.put("CONTRATO_NUMCONTRATO", c.getNumContrato());
		parametros.put("CONTRATO_INICIOCONTRATO", Util.formataDataHora(c.getInicioContrato(), Util.DATA));
		parametros.put("CONTRATO_TERMINOCONTRATO", Util.formataDataHora(c.getTerminoContrato(), Util.DATA));
		parametros.put("CONTRATO_VALOR", c.getValor());
		parametros.put("CONTRATO_VALOR_EXTENSO", extenso.write(BigDecimal.valueOf(c.getValor())));
		parametros.put("CONTRATO_DEPOSITO", c.getDeposito());
		parametros.put("CONTRATO_DEPOSITO_EXTENSO", extenso.write(BigDecimal.valueOf(c.getDeposito())));
		parametros.put("CONTRATO_DEPOSITO_VEZES", (c.getDeposito()/c.getValor()));
		parametros.put("CONTRATO_DEPOSITO_VEZES_EXTENSO", extenso.writeNoCurrency(BigDecimal.valueOf((c.getDeposito()/c.getValor()))));
		parametros.put("CONTRATO_INDICE_DESCRICAO", c.getIndiceReajuste().getDescricao());
		parametros.put("CONTRATO_INDICE_PERCENTUAL", c.getIndiceReajuste().getPercentual());
		parametros.put("CONTRATO_INDICE_PERCENTUAL_EXTENSO", extenso.writeNoCurrency(BigDecimal.valueOf(c.getIndiceReajuste().getPercentual())));
		parametros.put("CONTRATO_INDICE_PERIODO", c.getIndiceReajuste().getPeriodo());
		parametros.put("CONTRATO_DIAVENCIMENTO", c.getDiaVencimento());
		parametros.put("CONTRATO_DIAVENCIMENTO_EXTENSO", extenso.writeNoCurrency(BigDecimal.valueOf(c.getDiaVencimento())));
		parametros.put("CONTRATO_PRAZO", c.getPrazo());
		parametros.put("CONTRATO_PRAZO_EXTENSO", extenso.writeNoCurrency(BigDecimal.valueOf(c.getPrazo())));
		parametros.put("CONTRATO_SITUACAOCONTRATO", c.getSituacao());
		parametros.put("CONTRATO_DESCONTO", c.getDesconto());
		parametros.put("CONTRATO_DESCONTO_EXTENSO", extenso.write(BigDecimal.valueOf(c.getDesconto())));
		parametros.put("CONTRATO_PERIODODESCONTO", c.getPeriodoDesconto());
		parametros.put("CONTRATO_PERIODODESCONTO_EXTENSO", extenso.writeNoCurrency(BigDecimal.valueOf(c.getPeriodoDesconto())));
		parametros.put("CONTRATO_JUROS", c.getJuros());
		parametros.put("CONTRATO_JUROS_EXTENSO", extenso.writeNoCurrency(BigDecimal.valueOf(c.getJuros())));
		parametros.put("CONTRATO_MULTA", c.getMulta());
		parametros.put("CONTRATO_MULTA_EXTENSO", extenso.writeNoCurrency(BigDecimal.valueOf(c.getMulta())));
		parametros.put("CONTRATO_SEGURADORA", c.getSeguradora());
		parametros.put("CONTRATO_FORO", c.getForo());
		
		return parametros;
	}

}
