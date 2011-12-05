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

/*
 * Classe utilitária dos controllers para exibição de informações na tela
 */
public class ViewUtil {

	public static final int LOGRADOURO = 1;
	public static final int ESTADOS = 2;
	public static final int SITUACAO_IMOVEL = 3;
	public static final int TIPO_IMOVEL = 4;
	public static final int HORAS = 5;
	public static final int MODULOS = 6;

	private static String[] tiposLogradouros = { "Aeroporto", "Alameda",
			"Apartamento", "Avenida", "Beco", "Bloco", "Caminho", "Escadinho",
			"Estação", "Estrada", "Fazenda", "Fortaleza", "Galeria", "Ladeira",
			"Largo", "Praça", "Parque", "Praia", "Quadra", "Quilômetro",
			"Quinta", "Rodovia", "Rua", "Super Quadra", "Travessa", "Viaduto",
			"Vila" };

	private static String[] estados = { "AC", "AL", "AM", "AP", "BA", "CE",
			"DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI",
			"PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };

	// Lista de imóveis obtida do programa Immobis
	private static String[] tipoImoveis = { "Apartamento", "Bar", "Cafeteria",
			"Casa", "Clube de Lazer", "Cobertura", "Depósito de Gás",
			"Depósito", "Drogaria", "Escritório", "Galpão", "Gráfica",
			"Igreja", "Kitinete", "Lava Jato", "Loja", "Quiosque",
			"Restaurante e Pizzaria", "Sacolão", "Sala Comercial",
			"Salão de Cabelereiro", "Terreno" };

	// Horas do dia
	private static String[] horas = { "00", "01", "02", "03", "04", "05", "06",
			"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23" };
	
	// Lista de módulos existentes no sistema
	private static String[] modulos = {"agenda","cliente","clientepj","conta","contrato","corretor","funcionario","grupo","imovel","indiceReajuste","lancamento","modeloContrato","relatorio","usuario"};

	private ViewUtil() {

	}

	public static String[] getData(int option) {
		switch (option) {
		case ViewUtil.ESTADOS:
			return estados;
		case ViewUtil.LOGRADOURO:
			return tiposLogradouros;
		//case ViewUtil.SITUACAO_IMOVEL:
			//return situacaoImoveis;
		case ViewUtil.TIPO_IMOVEL:
			return tipoImoveis;
		case ViewUtil.HORAS:
			return horas;
		case ViewUtil.MODULOS :
			return modulos;
		default:
			return null;
		}
	}

	public static String getData(int index, int option) {
		switch (option) {
		case ViewUtil.ESTADOS:
			return estados[index];
		case ViewUtil.LOGRADOURO:
			return tiposLogradouros[index];
		//case ViewUtil.SITUACAO_IMOVEL:
			//return situacaoImoveis[index];
		case ViewUtil.TIPO_IMOVEL:
			return tipoImoveis[index];
		case ViewUtil.HORAS:
			return horas[index];
		case ViewUtil.MODULOS:
			return modulos[index];
		default:
			return "";
		}
	}

	public static int getSize(int option) {
		switch (option) {
		case ViewUtil.ESTADOS:
			return estados.length;
		case ViewUtil.LOGRADOURO:
			return tiposLogradouros.length;
		//case ViewUtil.SITUACAO_IMOVEL:
			//return situacaoImoveis.length;
		case ViewUtil.TIPO_IMOVEL:
			return tipoImoveis.length;
		case ViewUtil.HORAS:
			return horas.length;
		case ViewUtil.MODULOS:
			return modulos.length;
		default:
			return 0;
		}
	}

}
