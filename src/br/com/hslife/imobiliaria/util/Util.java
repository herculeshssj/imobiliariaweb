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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitária com métodos comuns usados em todo o sistema
 * 
 * @author Hércules
 */
public class Util {
	
	/** Seção de constantes **/
	
	public static final String DATAHORA = "datahora";
	public static final String DATA = "data";
	public static final String HORA = "hora";
	public static final String DATABASE = "database";

    public static String MD5(String texto) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(texto.getBytes()));
        sen = hash.toString(16);
        return sen;
    }

    public static String SHA1(String texto) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(texto.getBytes()));
        sen = hash.toString(16);
        return sen;
    }

    public static boolean validEmail(String email) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\\.+[a-z]{2,4}");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }
    
    // Verifica se o CEP informado é válido, retornando true caso seja
    public static boolean validCep(String cep) {
        Pattern p = Pattern.compile("^\\d{5,5}-?\\d{3,3}$");
        Matcher m = p.matcher(cep);
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }

    // Verifica se o valor passado só possui números
    // Retorna true se o campo só for composto de números
    public static boolean onlyNumber(String value) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(value);
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }

    // Verifica se o valor passado só possui números
    // Retorna true se o campo só for composto de números
    public static boolean onlyLetter(String value) {
        Pattern p = Pattern.compile("[a-zA-Z]*");
        Matcher m = p.matcher(value);
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }

    public static String formataDataHora(Date dataHora, String opcao) {
    	SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    	if (dataHora == null) return "";
    	if (opcao.equals(Util.DATAHORA)) {
    		formata = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    	}
    	if (opcao.equals(Util.DATA)) {
    		formata = new SimpleDateFormat("dd/MM/yyyy");
    	}
    	if (opcao.equals(Util.HORA)) {
    		formata = new SimpleDateFormat("hh:mm");
    	}
    	if (opcao.equals(Util.DATABASE)) {
    		formata = new SimpleDateFormat("yyyy-MM-dd");
    	}
        return formata.format(dataHora);
    }

    // Verifica se o CNPJ informado é válido
    // Retorna true se o CNPJ é válido
    public static boolean validCnpj(String cnpj) {
        int soma = 0, aux, dig;

        if (cnpj.length() != 14) {
            return false;
        }

        String cnpj_calc = cnpj.substring(0, 12);

        char[] chr_cnpj = cnpj.toCharArray();

        /* Primeira parte */
        for (int i = 0; i < 4; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);

        cnpj_calc += (dig == 10 || dig == 11)
                ? "0" : Integer.toString(dig);

        /* Segunda parte */
        soma = 0;
        for (int i = 0; i < 5; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);
        cnpj_calc += (dig == 10 || dig == 11)
                ? "0" : Integer.toString(dig);

        return cnpj.equals(cnpj_calc);
    }

    // Verifica se o CPF informado é válido
    // Retorna true se o CPF é válido
    public static boolean validCPF(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }
        if (cpf.equals("11111111111") ||
            cpf.equals("22222222222") ||
            cpf.equals("33333333333") ||
            cpf.equals("44444444444") ||
            cpf.equals("55555555555") ||
            cpf.equals("66666666666") ||
            cpf.equals("77777777777") ||
            cpf.equals("88888888888") ||
            cpf.equals("99999999999") ||
            cpf.equals("00000000000")) {
            return false;
        }
        if (!Util.onlyNumber(cpf)) {
            return false;
        }
        String numDig = cpf.substring(0, 9);
        return calcDigVerif(numDig).equals(cpf.substring(9, 11));
    }

    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = new Integer(0);
        } else {
            primDig = new Integer(11 - (soma % 11));
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = new Integer(0);
        } else {
            segDig = new Integer(11 - (soma % 11));
        }

        return primDig.toString() + segDig.toString();
    }

    /*
     * Fonte: http://www.guj.com.br/java/211420-calcular-diferenca-de-meses-entre-duas-datas/2
     * 
     */
    public static int quantMonths(Date dateStart, Date dateEnd) {  
        int count = 0;  
        if (dateStart != null && dateEnd != null && dateStart.before(dateEnd)) {  
  
            Calendar clStart = Calendar.getInstance();  
            clStart.setTime(dateStart);  
  
            Calendar clEnd = Calendar.getInstance();  
            clEnd.setTime(dateEnd);  
  
            while (clStart.get(Calendar.MONTH) != clEnd.get(Calendar.MONTH) || clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
                clStart.add(Calendar.MONTH, 1);  
                count++;  
            }  
        }  
        return count;  
    }  
    
    
    public static Date calculaDataTermino(Date dataInicio, int prazo) {
    	Calendar termino = Calendar.getInstance();
    	termino.setTime(dataInicio);
    	while (prazo > 0) {
    		termino.add(Calendar.MONTH, 1);
    		prazo--;
    	}
    	return termino.getTime();
    }
    
    /**
	 * eg: number = 1234, returns { 1, 2, 3, 4 }
	 * 
	 * Obtido em http://wormholebr.blogspot.com/2010/01/gerador-cpf-cnpj-cartao-de-credito.html no dia 15/11/2011
	 *
	 * @param number
	 *            a long, see Long.MAX_VALUE and Long.MIN_VALUE
	 * @return one array of digits
	 */
	public static int[] longToArray(final long number) {
		long n = number;
		List<Long> list = new ArrayList<Long>(10);
		do {
			list.add(n % 10);
			n /= 10;
		} while (n != 0);
		int length = list.size();
		int[] digits = new int[length];
		for (int i = length - 1, j = 0; i >= 0; i--, j++)
			digits[j] = list.get(i).intValue();
		return digits;
	}

	/**
	 * eg: number = { 1, 2, 3, 4 }, returns a long = 1234 if the number
	 * correspondent of digits is bigger than Long.MAX_VALUE, so Long.MAX_VALUE
	 * is returned.
	 * 
	 * Obtido em http://wormholebr.blogspot.com/2010/01/gerador-cpf-cnpj-cartao-de-credito.html no dia 15/11/2011
	 *
	 * @param number
	 *            an array of digits [0-9]
	 * @return a long correspondent to the digits
	 */
	public static long arrayToLong(final int[] digits) {
		long result = 0;
		long pot = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			long partial = digits[i] * pot;
			if (Long.MAX_VALUE - result - partial > 0) {
				result += partial;
			} else
				return Long.MAX_VALUE;
			pot *= 10;
		}
		return result;
	}
}