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

import java.util.Random;

/**
 * Random generator of numbers.
 * 
 * Obtido em http://wormholebr.blogspot.com/2010/01/gerador-cpf-cnpj-cartao-de-credito.html no dia 15/11/2011
 */
public class NumberGenerator {

	// TODO generate array with range

	static int[] generateArray(Random random, int length, int quantityOfZeros) {
		if (quantityOfZeros > length)
			throw new IllegalArgumentException("quantityOfZeros must be less or equal than length.");
		if (length <= 0)
			throw new IllegalArgumentException("length must be bigger than zero.");
		int[] digits = new int[length];
		int i;
		for (i = 0; i < quantityOfZeros; i++)
			digits[i] = random.nextInt(10);
		if (i < length)
			digits[i++] = 1 + random.nextInt(9); // != 0
		for (; i < length; i++)
			digits[i] = random.nextInt(10);
		return digits;
	}

	/**
	 * Return a random array[0..length-1] of digits [0-9].
	 *
	 * @param seed
	 *            seed to generate always the same array.
	 * @param quantityOfZeros
	 *            Quantity of zeros that can be the beginning of number.
	 *            quantityOfZeros must be <= length
	 * @param length
	 *            the length of the array returned.
	 * @return a random array of digits [0-9]
	 */
	public static int[] generateArray(long seed, int length, int quantityOfZeros) {
		return generateArray(new Random(seed), length, quantityOfZeros);
	}

	/**
	 * Return a random array[0..length-1] of digits [0-9].
	 *
	 * @param quantityOfZeros
	 *            Quantity of zeros that can be the beginning of number.
	 *            quantityOfZeros must be <= length
	 * @param length
	 *            the length of the array returned.
	 * @return a random array of digits [0-9]
	 */
	public static int[] generateRandomArray(int length, int quantityOfZeros) {
		return generateArray(new Random(), length, quantityOfZeros);
	}

	/**
	 * if the random number is bigger than Integer.MAX_VALUE, so
	 * Integer.MAX_VALUE is returned.
	 *
	 * @param maxLength
	 *            maybe the first digit is 0, so the number has maxLength-1
	 *            length. If the second digit is 0 too, so the number has
	 *            maxLength-2 length, and so on.
	 * @return return a random positive number with length <= maxLength
	 */
	public static int generateRandom(int maxLength) {
		long l = Util.arrayToLong(generateRandomArray(maxLength, maxLength));
		if (l > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return (int) l;
	}

	/**
	 * if the random number is bigger than Integer.MAX_VALUE, so
	 * Integer.MAX_VALUE is returned.
	 *
	 * @param length
	 *            The number returned always has length digits.
	 * @return a random number with length digits.
	 */
	public static int generateRandomForce(int length) {
		long l = Util.arrayToLong(generateRandomArray(length, 0));
		if (l > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return (int) l;
	}
}
