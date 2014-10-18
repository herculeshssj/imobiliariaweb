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
 * Obtido em http://wormholebr.blogspot.com/2010/01/gerador-cpf-cnpj-cartao-de-credito.html no dia 15/11/2011
 *  
 * @author Hércules
 *
 */

public class CNPJGenerator {

	static Long generate(Random random) {
		int[] digits = NumberGenerator.generateArray(random, 14, 12);
		digits[12] = 0;
		digits[13] = 0;
		// First digit
		int[] weight = new int[] { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		int total = 0;
		for (int i = 0; i < 12; i++)
			total += digits[i] * weight[i + 1];
		int rest = total % 11;
		if (rest < 2)
			digits[12] = 0;
		else
			digits[12] = 11 - rest;
		// Second digit
		total = 0;
		for (int i = 0; i < 13; i++)
			total += digits[i] * weight[i];
		rest = total % 11;
		if (rest < 2)
			digits[13] = 0;
		else
			digits[13] = 11 - rest;
		return Util.arrayToLong(digits);
	}

	/**
	 * @return 14 digits ##############
	 */
	public static Long generate(long seed) {
		return generate(new Random(seed));
	}

	/**
	 * @return 14 digits ##############
	 */
	public static Long generateRandom() {
		return generate(new Random());
	}

	/**
	 * Put zeros in the beginning if the cnpj has less than 14 digits.
	 * Format: "##.###.###/####-##"
	 * @param cnpj a cpnj
	 * @return "##.###.###/####-##"
	 */
	public static String format(Long cnpj) {
		String str = cnpj.toString();
		if(str.length() < 14)
			for(int i = str.length(); i < 14; i++)
				str = "0" + str;
		return
			str.substring(0, 2) + "." + str.substring(2, 5) + "." + str.substring(5, 8) + "/"
			+ str.substring(8, 12) + "-" + str.substring(12);
	}

	/**
	 * @return random formatted: "##.###.###/####-##"
	 */
	public static String generateRandomFormatted() {
		return format(generateRandom());
	}
}