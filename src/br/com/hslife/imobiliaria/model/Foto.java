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

package br.com.hslife.imobiliaria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Foto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6657760540488847083L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	@Column
	String arquivo;
	
	@Column
	String extensao;
	
	@Column
	int largura;
	
	@Column
	int altura;
	
	@Column
	String caminho;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dataInclusao;
	
	@Column
	long tamanho; // tamanho em bytes
	
	@Column
	String mime;
	
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(length=1048657)
	byte[] dados;
	
	public Foto() {
		dataInclusao = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
		// O código abaixo foi obtido do demo oficial do RichFaces
		int extDot = arquivo.lastIndexOf('.');
		if (extDot > 0) {
			String extension = arquivo.substring(extDot+1);
			if ("bmp".equals(extension)) {
				mime = "image/bmp";
				extensao = "bmp";
			} else if ("jpg".equals(extension) || "jpeg".equals(extension)) {
				mime = "image/jpeg";
				extensao = "jpg";
			} else if ("gif".equals(extension)) {
				mime = "image/gif";
				extensao = "gif";
			} else if ("png".equals(extension)) {
				mime = "image/png";
				extensao = "png";
			} else {
				extensao = extension;
				mime = "image/unknown";
			}
		}
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public long getTamanho() {
		return tamanho;
	}

	public void setTamanho(long tamanho) {
		this.tamanho = tamanho;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}
	
}
