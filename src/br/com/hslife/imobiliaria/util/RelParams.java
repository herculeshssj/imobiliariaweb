/*** 

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

 ***/

package br.com.hslife.imobiliaria.util;

import java.math.BigDecimal;

import br.com.hslife.imobiliaria.model.Contrato;

public class RelParams {
	
	public static String popular(Contrato c) {
		// Verifica a situação do contrato para poder colocar os textos de alerta de encerramento e edição
		StringBuilder sb = new StringBuilder();
		
		switch (c.getSituacao()) {
			case 1:
			case 3: sb.append("<p style=\"text-align: center; color: red; font-size: 15pt;\">CONTRATO EM PROCESSO DE EDIÇÃO</p><br/>"); break;
			case 4: sb.append("<p style=\"text-align: center; color: red; font-size: 15pt;\">CONTRATO ENCERRADO</p><br/>"); break;
			default:
		}
		
		sb.append(c.getModeloContrato().getModelo());
		
		String contratoGerado = sb.toString();
		
		CurrencyWriter extenso = new CurrencyWriter();
		
		contratoGerado = contratoGerado.replace("{LOCADOR_CPF}", c.getImovel().getLocador().getCpf());
		contratoGerado = contratoGerado.replace("{LOCADOR_CPFCONJUGE}", c.getImovel().getLocador().getCpfConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_RG}", c.getImovel().getLocador().getRg());
		contratoGerado = contratoGerado.replace("{LOCADOR_RGCONJUGE}", c.getImovel().getLocador().getRgConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_ORGAOEMISSOR}", c.getImovel().getLocador().getOrgaoEmissor());
		contratoGerado = contratoGerado.replace("{LOCADOR_ORGAOEMISSORCONJUGE}", c.getImovel().getLocador().getOrgaoEmissorConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_DATAEMISSAO}", Util.formataDataHora(c.getImovel().getLocador().getDataEmissao(), Util.DATA));
		contratoGerado = contratoGerado.replace("{LOCADOR_DATAEMISSAOCONJUGE}", Util.formataDataHora(c.getImovel().getLocador().getDataEmissaoConjuge(), Util.DATA));
		contratoGerado = contratoGerado.replace("{LOCADOR_NOME}", c.getImovel().getLocador().getNome());
		contratoGerado = contratoGerado.replace("{LOCADOR_NOMECONJUGE}", c.getImovel().getLocador().getNomeConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_DATANASCIMENTO}", Util.formataDataHora(c.getImovel().getLocador().getDataNascimento(), Util.DATA));
		contratoGerado = contratoGerado.replace("{LOCADOR_DATANASCIMENTOCONJUGE}", Util.formataDataHora(c.getImovel().getLocador().getDataNascimentoConjuge(), Util.DATA));
		contratoGerado = contratoGerado.replace("{LOCADOR_GENERO}", c.getImovel().getLocador().getGenero());
		contratoGerado = contratoGerado.replace("{LOCADOR_GENEROCONJUGE}", c.getImovel().getLocador().getGeneroConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_NACIONALIDADE}", c.getImovel().getLocador().getNacionalidade());
		contratoGerado = contratoGerado.replace("{LOCADOR_NACIONALIDADECONJUGE}", c.getImovel().getLocador().getNacionalidadeConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_NATURALIDADE}", c.getImovel().getLocador().getNaturalidade());
		contratoGerado = contratoGerado.replace("{LOCADOR_NATURALIDADECONJUGE}", c.getImovel().getLocador().getNaturalidadeConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_FILIACAOPAI}", c.getImovel().getLocador().getFiliacaoPai());
		contratoGerado = contratoGerado.replace("{LOCADOR_FILIACAOPAICONJUGE}", c.getImovel().getLocador().getFiliacaoPaiConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_FILIACAOMAE}", c.getImovel().getLocador().getFiliacaoMae());
		contratoGerado = contratoGerado.replace("{LOCADOR_FILIACAOMAECONJUGE}", c.getImovel().getLocador().getFiliacaoMaeConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_ESTADOCIVIL}", c.getImovel().getLocador().getEstadoCivil());
		contratoGerado = contratoGerado.replace("{LOCADOR_PROFISSAO}", c.getImovel().getLocador().getProfissao());
		contratoGerado = contratoGerado.replace("{LOCADOR_PROFISSAOCONJUGE}", c.getImovel().getLocador().getProfissaoConjuge());
		contratoGerado = contratoGerado.replace("{LOCADOR_TIPOLOGRADOURO}", c.getImovel().getLocador().getEndereco().getTipoLogradouro());
		contratoGerado = contratoGerado.replace("{LOCADOR_LOGRADOURO}", c.getImovel().getLocador().getEndereco().getLogradouro());
		contratoGerado = contratoGerado.replace("{LOCADOR_NUMERO}", c.getImovel().getLocador().getEndereco().getNumero());
		contratoGerado = contratoGerado.replace("{LOCADOR_COMPLEMENTO}", c.getImovel().getLocador().getEndereco().getComplemento());
		contratoGerado = contratoGerado.replace("{LOCADOR_BAIRRO}", c.getImovel().getLocador().getEndereco().getBairro());
		contratoGerado = contratoGerado.replace("{LOCADOR_CIDADE}", c.getImovel().getLocador().getEndereco().getCidade());
		contratoGerado = contratoGerado.replace("{LOCADOR_UF}", c.getImovel().getLocador().getEndereco().getUf());
		contratoGerado = contratoGerado.replace("{LOCADOR_CEP}", c.getImovel().getLocador().getEndereco().getCep());
		
		if (c.getLocatario() != null) {
			contratoGerado = contratoGerado.replace("{LOCATARIO_CPF}", c.getLocatario().getCpf());
			contratoGerado = contratoGerado.replace("{LOCATARIO_CPFCONJUGE}", c.getLocatario().getCpfConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_RG}", c.getLocatario().getRg());
			contratoGerado = contratoGerado.replace("{LOCATARIO_RGCONJUGE}", c.getLocatario().getRgConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_ORGAOEMISSOR}", c.getLocatario().getOrgaoEmissor());
			contratoGerado = contratoGerado.replace("{LOCATARIO_ORGAOEMISSORCONJUGE}", c.getLocatario().getOrgaoEmissorConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_DATAEMISSAO}", Util.formataDataHora(c.getLocatario().getDataEmissao(), Util.DATA));
			contratoGerado = contratoGerado.replace("{LOCATARIO_DATAEMISSAOCONJUGE}", Util.formataDataHora(c.getLocatario().getDataEmissaoConjuge(), Util.DATA));
			contratoGerado = contratoGerado.replace("{LOCATARIO_NOME}", c.getLocatario().getNome());
			contratoGerado = contratoGerado.replace("{LOCATARIO_NOMECONJUGE}", c.getLocatario().getNomeConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_DATANASCIMENTO}", Util.formataDataHora(c.getLocatario().getDataNascimento(), Util.DATA));
			contratoGerado = contratoGerado.replace("{LOCATARIO_DATANASCIMENTOCONJUGE}", Util.formataDataHora(c.getLocatario().getDataNascimentoConjuge(), Util.DATA));
			contratoGerado = contratoGerado.replace("{LOCATARIO_GENERO}", c.getLocatario().getGenero());
			contratoGerado = contratoGerado.replace("{LOCATARIO_GENEROCONJUGE}", c.getLocatario().getGeneroConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_NACIONALIDADE}", c.getLocatario().getNacionalidade());
			contratoGerado = contratoGerado.replace("{LOCATARIO_NACIONALIDADECONJUGE}", c.getLocatario().getNacionalidadeConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_NATURALIDADE}", c.getLocatario().getNaturalidade());
			contratoGerado = contratoGerado.replace("{LOCATARIO_NATURALIDADECONJUGE}", c.getLocatario().getNaturalidadeConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_FILIACAOPAI}", c.getLocatario().getFiliacaoPai());
			contratoGerado = contratoGerado.replace("{LOCATARIO_FILIACAOPAICONJUGE}", c.getLocatario().getFiliacaoPaiConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_FILIACAOMAE}", c.getLocatario().getFiliacaoMae());
			contratoGerado = contratoGerado.replace("{LOCATARIO_FILIACAOMAECONJUGE}", c.getLocatario().getFiliacaoMaeConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_ESTADOCIVIL}", c.getLocatario().getEstadoCivil());
			contratoGerado = contratoGerado.replace("{LOCATARIO_PROFISSAO}", c.getLocatario().getProfissao());
			contratoGerado = contratoGerado.replace("{LOCATARIO_PROFISSAOCONJUGE}", c.getLocatario().getProfissaoConjuge());
			contratoGerado = contratoGerado.replace("{LOCATARIO_TIPOLOGRADOURO}", c.getLocatario().getEndereco().getTipoLogradouro());
			contratoGerado = contratoGerado.replace("{LOCATARIO_LOGRADOURO}", c.getLocatario().getEndereco().getLogradouro());
			contratoGerado = contratoGerado.replace("{LOCATARIO_NUMERO}", c.getLocatario().getEndereco().getNumero());
			contratoGerado = contratoGerado.replace("{LOCATARIO_COMPLEMENTO}", c.getLocatario().getEndereco().getComplemento());
			contratoGerado = contratoGerado.replace("{LOCATARIO_BAIRRO}", c.getLocatario().getEndereco().getBairro());
			contratoGerado = contratoGerado.replace("{LOCATARIO_CIDADE}", c.getLocatario().getEndereco().getCidade());
			contratoGerado = contratoGerado.replace("{LOCATARIO_UF}", c.getLocatario().getEndereco().getUf());
			contratoGerado = contratoGerado.replace("{LOCATARIO_CEP}", c.getLocatario().getEndereco().getCep());
		}
		
		if (c.getLocatarioPJ() != null) {
			
			contratoGerado = contratoGerado.replace("{EMPRESA_CNPJ}", c.getLocatarioPJ().getCnpj());
			contratoGerado = contratoGerado.replace("{EMPRESA_NOMEFANTASIA}", c.getLocatarioPJ().getNomeFantasia());
			contratoGerado = contratoGerado.replace("{EMPRESA_RAMOATIVIDADE}", c.getLocatarioPJ().getRamoAtividade());
			contratoGerado = contratoGerado.replace("{EMPRESA_TIPOLOGRADOURO}", c.getLocatarioPJ().getEndereco().getTipoLogradouro());
			contratoGerado = contratoGerado.replace("{EMPRESA_LOGRADOURO}", c.getLocatarioPJ().getEndereco().getLogradouro());
			contratoGerado = contratoGerado.replace("{EMPRESA_NUMERO}", c.getLocatarioPJ().getEndereco().getNumero());
			contratoGerado = contratoGerado.replace("{EMPRESA_COMPLEMENTO}", c.getLocatarioPJ().getEndereco().getComplemento());
			contratoGerado = contratoGerado.replace("{EMPRESA_BAIRRO}", c.getLocatarioPJ().getEndereco().getBairro());
			contratoGerado = contratoGerado.replace("{EMPRESA_CIDADE}", c.getLocatarioPJ().getEndereco().getCidade());
			contratoGerado = contratoGerado.replace("{EMPRESA_UF}", c.getLocatarioPJ().getEndereco().getUf());
			contratoGerado = contratoGerado.replace("{EMPRESA_CEP}", c.getLocatarioPJ().getEndereco().getCep());
		
			/*if (c.getLocatarioPJ().getSocios() != null && c.getLocatarioPJ().getSocios().size() >=1 ) {
				contratoGerado = contratoGerado.replace("{SOCIO_CPF}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getCpf());
				contratoGerado = contratoGerado.replace("{SOCIO_CPFCONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getCpfConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_RG}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getRg());
				contratoGerado = contratoGerado.replace("{SOCIO_RGCONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getRgConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_ORGAOEMISSOR}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getOrgaoEmissor());
				contratoGerado = contratoGerado.replace("{SOCIO_ORGAOEMISSORCONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getOrgaoEmissorConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_DATAEMISSAO}", Util.formataDataHora(c.getLocatarioPJ().getSocios().get(0).getClientePF().getDataEmissao(), Util.DATA));
				contratoGerado = contratoGerado.replace("{SOCIO_DATAEMISSAOCONJUGE}", Util.formataDataHora(c.getLocatarioPJ().getSocios().get(0).getClientePF().getDataEmissaoConjuge(), Util.DATA));
				contratoGerado = contratoGerado.replace("{SOCIO_NOME}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNome());
				contratoGerado = contratoGerado.replace("{SOCIO_NOMECONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNomeConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_DATANASCIMENTO}", Util.formataDataHora(c.getLocatarioPJ().getSocios().get(0).getClientePF().getDataNascimento(), Util.DATA));
				contratoGerado = contratoGerado.replace("{SOCIO_DATANASCIMENTOCONJUGE}", Util.formataDataHora(c.getLocatarioPJ().getSocios().get(0).getClientePF().getDataNascimentoConjuge(), Util.DATA));
				contratoGerado = contratoGerado.replace("{SOCIO_GENERO}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getGenero());
				contratoGerado = contratoGerado.replace("{SOCIO_GENEROCONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getGeneroConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_NACIONALIDADE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNacionalidade());
				contratoGerado = contratoGerado.replace("{SOCIO_NACIONALIDADECONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNacionalidadeConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_NATURALIDADE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNaturalidade());
				contratoGerado = contratoGerado.replace("{SOCIO_NATURALIDADECONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getNaturalidadeConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_FILIACAOPAI}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getFiliacaoPai());
				contratoGerado = contratoGerado.replace("{SOCIO_FILIACAOPAICONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getFiliacaoPaiConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_FILIACAOMAE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getFiliacaoMae());
				contratoGerado = contratoGerado.replace("{SOCIO_FILIACAOMAECONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getFiliacaoMaeConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_ESTADOCIVIL}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEstadoCivil());
				contratoGerado = contratoGerado.replace("{SOCIO_PROFISSAO}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getProfissao());
				contratoGerado = contratoGerado.replace("{SOCIO_PROFISSAOCONJUGE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getProfissaoConjuge());
				contratoGerado = contratoGerado.replace("{SOCIO_TIPOLOGRADOURO}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getTipoLogradouro());
				contratoGerado = contratoGerado.replace("{SOCIO_LOGRADOURO}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getLogradouro());
				contratoGerado = contratoGerado.replace("{SOCIO_NUMERO}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getNumero());
				contratoGerado = contratoGerado.replace("{SOCIO_COMPLEMENTO}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getComplemento());
				contratoGerado = contratoGerado.replace("{SOCIO_BAIRRO}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getBairro());
				contratoGerado = contratoGerado.replace("{SOCIO_CIDADE}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getCidade());
				contratoGerado = contratoGerado.replace("{SOCIO_UF}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getUf());
				contratoGerado = contratoGerado.replace("{SOCIO_CEP}", c.getLocatarioPJ().getSocios().get(0).getClientePF().getEndereco().getCep());
				contratoGerado = contratoGerado.replace("{SOCIO_FUNCAO}", c.getLocatarioPJ().getSocios().get(0).getFuncao());
			}*/
			
		}
		
		if (c.getFiador() != null) {
			contratoGerado = contratoGerado.replace("{FIADOR_CPF}", c.getFiador().getCpf());
			contratoGerado = contratoGerado.replace("{FIADOR_CPFCONJUGE}", c.getFiador().getCpfConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_RG}", c.getFiador().getRg());
			contratoGerado = contratoGerado.replace("{FIADOR_RGCONJUGE}", c.getFiador().getRgConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_ORGAOEMISSOR}", c.getFiador().getOrgaoEmissor());
			contratoGerado = contratoGerado.replace("{FIADOR_ORGAOEMISSORCONJUGE}", c.getFiador().getOrgaoEmissorConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_DATAEMISSAO}", Util.formataDataHora(c.getFiador().getDataEmissao(), Util.DATA));
			contratoGerado = contratoGerado.replace("{FIADOR_DATAEMISSAOCONJUGE}", Util.formataDataHora(c.getFiador().getDataEmissaoConjuge(), Util.DATA));
			contratoGerado = contratoGerado.replace("{FIADOR_NOME}", c.getFiador().getNome());
			contratoGerado = contratoGerado.replace("{FIADOR_NOMECONJUGE}", c.getFiador().getNomeConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_DATANASCIMENTO}", Util.formataDataHora(c.getFiador().getDataNascimento(), Util.DATA));
			contratoGerado = contratoGerado.replace("{FIADOR_DATANASCIMENTOCONJUGE}", Util.formataDataHora(c.getFiador().getDataNascimentoConjuge(), Util.DATA));
			contratoGerado = contratoGerado.replace("{FIADOR_GENERO}", c.getFiador().getGenero());
			contratoGerado = contratoGerado.replace("{FIADOR_GENEROCONJUGE}", c.getFiador().getGeneroConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_NACIONALIDADE}", c.getFiador().getNacionalidade());
			contratoGerado = contratoGerado.replace("{FIADOR_NACIONALIDADECONJUGE}", c.getFiador().getNacionalidadeConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_NATURALIDADE}", c.getFiador().getNaturalidade());
			contratoGerado = contratoGerado.replace("{FIADOR_NATURALIDADECONJUGE}", c.getFiador().getNaturalidadeConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_FILIACAOPAI}", c.getFiador().getFiliacaoPai());
			contratoGerado = contratoGerado.replace("{FIADOR_FILIACAOPAICONJUGE}", c.getFiador().getFiliacaoPaiConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_FILIACAOMAE}", c.getFiador().getFiliacaoMae());
			contratoGerado = contratoGerado.replace("{FIADOR_FILIACAOMAECONJUGE}", c.getFiador().getFiliacaoMaeConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_ESTADOCIVIL}", c.getFiador().getEstadoCivil());
			contratoGerado = contratoGerado.replace("{FIADOR_PROFISSAO}", c.getFiador().getProfissao());
			contratoGerado = contratoGerado.replace("{FIADOR_PROFISSAOCONJUGE}", c.getFiador().getProfissaoConjuge());
			contratoGerado = contratoGerado.replace("{FIADOR_TIPOLOGRADOURO}", c.getFiador().getEndereco().getTipoLogradouro());
			contratoGerado = contratoGerado.replace("{FIADOR_LOGRADOURO}", c.getFiador().getEndereco().getLogradouro());
			contratoGerado = contratoGerado.replace("{FIADOR_NUMERO}", c.getFiador().getEndereco().getNumero());
			contratoGerado = contratoGerado.replace("{FIADOR_COMPLEMENTO}", c.getFiador().getEndereco().getComplemento());
			contratoGerado = contratoGerado.replace("{FIADOR_BAIRRO}", c.getFiador().getEndereco().getBairro());
			contratoGerado = contratoGerado.replace("{FIADOR_CIDADE}", c.getFiador().getEndereco().getCidade());
			contratoGerado = contratoGerado.replace("{FIADOR_UF}", c.getFiador().getEndereco().getUf());
			contratoGerado = contratoGerado.replace("{FIADOR_CEP}", c.getFiador().getEndereco().getCep());
		}
		
		contratoGerado = contratoGerado.replace("{IMOVEL_VALOR}", c.getImovel().getValor().toString());
		contratoGerado = contratoGerado.replace("{IMOVEL_VALOR_EXTENSO}", extenso.write(BigDecimal.valueOf(c.getImovel().getValor())));
		contratoGerado = contratoGerado.replace("{IMOVEL_NUMREGISTRO}", c.getImovel().getNumRegistro());
		contratoGerado = contratoGerado.replace("{IMOVEL_TIPOIMOVEL}", c.getImovel().getTipoImovel());
		contratoGerado = contratoGerado.replace("{IMOVEL_SITUACAOIMOVEL}", c.getImovel().getSituacaoImovel());
		contratoGerado = contratoGerado.replace("{IMOVEL_TIPOLOGRADOURO}", c.getImovel().getEndereco().getTipoLogradouro());
		contratoGerado = contratoGerado.replace("{IMOVEL_LOGRADOURO}", c.getImovel().getEndereco().getLogradouro());
		contratoGerado = contratoGerado.replace("{IMOVEL_NUMERO}", c.getImovel().getEndereco().getNumero());
		contratoGerado = contratoGerado.replace("{IMOVEL_COMPLEMENTO}", c.getImovel().getEndereco().getComplemento());
		contratoGerado = contratoGerado.replace("{IMOVEL_BAIRRO}", c.getImovel().getEndereco().getBairro());
		contratoGerado = contratoGerado.replace("{IMOVEL_CIDADE}", c.getImovel().getEndereco().getCidade());
		contratoGerado = contratoGerado.replace("{IMOVEL_UF}", c.getImovel().getEndereco().getUf());
		contratoGerado = contratoGerado.replace("{IMOVEL_CEP}", c.getImovel().getEndereco().getCep());
		
		contratoGerado = contratoGerado.replace("{CONTRATO_NUMCONTRATO}", c.getNumContrato());
		contratoGerado = contratoGerado.replace("{CONTRATO_INICIOCONTRATO}", Util.formataDataHora(c.getInicioContrato(), Util.DATA));
		contratoGerado = contratoGerado.replace("{CONTRATO_TERMINOCONTRATO}", Util.formataDataHora(c.getTerminoContrato(), Util.DATA));
		contratoGerado = contratoGerado.replace("{CONTRATO_VALOR}", c.getValor().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_VALOR_EXTENSO}", extenso.write(BigDecimal.valueOf(c.getValor())));
		contratoGerado = contratoGerado.replace("{CONTRATO_DEPOSITO}", c.getDeposito().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_DEPOSITO_EXTENSO}", extenso.write(BigDecimal.valueOf(c.getDeposito())));
		contratoGerado = contratoGerado.replace("{CONTRATO_DEPOSITO_VEZES}", Double.toString((c.getDeposito()/c.getValor())));
		contratoGerado = contratoGerado.replace("{CONTRATO_DEPOSITO_VEZES_EXTENSO}", extenso.writeNoCurrency(BigDecimal.valueOf((c.getDeposito()/c.getValor()))));
		contratoGerado = contratoGerado.replace("{CONTRATO_INDICE_DESCRICAO}", c.getIndiceReajuste().getDescricao());
		contratoGerado = contratoGerado.replace("{CONTRATO_INDICE_PERCENTUAL}", c.getIndiceReajuste().getPercentual().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_INDICE_PERCENTUAL_EXTENSO}", extenso.writeNoCurrency(BigDecimal.valueOf(c.getIndiceReajuste().getPercentual())));
		contratoGerado = contratoGerado.replace("{CONTRATO_INDICE_PERIODO}", c.getIndiceReajuste().getPeriodo());
		contratoGerado = contratoGerado.replace("{CONTRATO_DIAVENCIMENTO}", c.getDiaVencimento().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_DIAVENCIMENTO_EXTENSO}", extenso.writeNoCurrency(BigDecimal.valueOf(c.getDiaVencimento())));
		contratoGerado = contratoGerado.replace("{CONTRATO_PRAZO}", c.getPrazo().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_PRAZO_EXTENSO}", extenso.writeNoCurrency(BigDecimal.valueOf(c.getPrazo())));
		contratoGerado = contratoGerado.replace("{CONTRATO_SITUACAOCONTRATO}", c.getSituacao().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_DESCONTO}", c.getDesconto().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_DESCONTO_EXTENSO}", extenso.write(BigDecimal.valueOf(c.getDesconto())));
		contratoGerado = contratoGerado.replace("{CONTRATO_PERIODODESCONTO}", c.getPeriodoDesconto().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_PERIODODESCONTO_EXTENSO}", extenso.writeNoCurrency(BigDecimal.valueOf(c.getPeriodoDesconto())));
		contratoGerado = contratoGerado.replace("{CONTRATO_JUROS}", c.getJuros().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_JUROS_EXTENSO}", extenso.writeNoCurrency(BigDecimal.valueOf(c.getJuros())));
		contratoGerado = contratoGerado.replace("{CONTRATO_MULTA}", c.getMulta().toString());
		contratoGerado = contratoGerado.replace("{CONTRATO_MULTA_EXTENSO}", extenso.writeNoCurrency(BigDecimal.valueOf(c.getMulta())));
		contratoGerado = contratoGerado.replace("{CONTRATO_SEGURADORA}", c.getSeguradora().getDescricao());
		contratoGerado = contratoGerado.replace("{CONTRATO_FORO}", c.getForo());
		
		return contratoGerado;
	}
}
