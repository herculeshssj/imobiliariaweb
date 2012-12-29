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
import java.util.Date;

import org.junit.Test;

import br.com.hslife.imobiliaria.util.CurrencyWriter;
import br.com.hslife.imobiliaria.util.Util;

public class UtilTest {

	@Test
	public void testCalculaDataTermino() {
		Date hoje = new Date();
		int prazo = 30;
		System.out.println("Hoje: " + hoje);
		System.out.println("Daqui a 30 meses: " + Util.calculaDataTermino(hoje, prazo));
	}

	@Test
	public void testValoresPorExtenso() {
		CurrencyWriter extenso = new CurrencyWriter();
		final double numero = 150392.47;
		System.out.println("Número: " + numero);
		System.out.println("Número por extenso: " + extenso.write(BigDecimal.valueOf(numero)));
	}
}
