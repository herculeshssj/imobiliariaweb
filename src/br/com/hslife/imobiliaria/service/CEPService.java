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

package br.com.hslife.imobiliaria.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import br.com.hslife.imobiliaria.exception.ServiceException;
import br.com.hslife.imobiliaria.model.Endereco;

public class CEPService {
	
	private Connection conn = null;
	private ResultSet result = null;
	
	private void getConnection() throws Exception {
		// Inicializa a conexão
		Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://mysql.hslife.com.br:3306/hslife01","hslife01","l0gr4d0vr0s");
	}
	
	public Endereco getEndereco(String cep) throws ServiceException {
		// Instancia um novo objeto Endereco
		Endereco endereco = new Endereco();
		
		// SQL de consulta
		String sqlQuery = "select * from enderecos where cep = " + cep + " limit 1";
		
		try {
			// Inicialização da conexão
			getConnection();
			
			// Executa a consulta
			result = conn.createStatement().executeQuery(sqlQuery);
			
			// Carrega os dados no objeto Endereco
			while(result.next()) {
				endereco.setTipoLogradouro(result.getString("tipoLogradouro"));
				endereco.setLogradouro(result.getString("logradouro"));
				endereco.setBairro(result.getString("bairro"));
				endereco.setCidade(result.getString("cidade"));
				endereco.setUf(result.getString("uf"));
				endereco.setCep(result.getString("cep"));
			}
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return endereco;
		
	}

}
