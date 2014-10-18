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

import java.util.Random;

/**
 * Brazilian CPF: The purpose of this class is to facilitate the writing of
 * automated testing by testers well intentioned, the author is not responsible
 * for any misuse of these algorithms
 * 
 * Obtido em http://wormholebr.blogspot.com/2010/01/gerador-cpf-cnpj-cartao-de-credito.html no dia 15/11/2011
 */
public class CPFGenerator {
	static Long generate(Random random) {
		int[] digits = NumberGenerator.generateArray(random, 11, 9);
		// First digit
		int total = 0;
		for (int i = 1, j = 10; i <= 9; i++, j--)
			total += digits[i - 1] * j;
		int rest = total % 11;
		if (rest < 2)
			digits[9] = 0;
		else
			digits[9] = 11 - rest;
		// Second digit
		total = 0;
		for (int i = 1, j = 11; i <= 10; i++, j--)
			total += digits[i - 1] * j;
		rest = total % 11;
		if (rest < 2)
			digits[10] = 0;
		else
			digits[10] = 11 - rest;
		return Util.arrayToLong(digits);
	}

	/**
	 * @return 11 digits ############
	 */
	public static Long generateRandom() {
		return generate(new Random());
	}

	/**
	 * @param seed
	 *            an indice
	 * @return 11 digits ############
	 */
	public static Long generate(long seed) {
		return generate(new Random(seed));
	}

	/**
	 * Put zeros in the beginning if the cpf has less than 11 digits.
	 *
	 * @return "###.###.###-##"
	 */
	public static String format(Long cpf) {
		String str = cpf.toString();
		if (str.length() < 11)
			for (int i = str.length(); i < 11; i++)
				str = "0" + str;
		return str.substring(0, 3) + "." + str.substring(3, 6) + "." + str.substring(6, 9) + "-"
				+ str.substring(9);
	}

	/**
	 * @return "###.###.###-##"
	 */
	public static String generateRandomFormatted() {
		return format(generateRandom());
	}
}