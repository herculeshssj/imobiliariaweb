ImobiliariaWeb
===============

Gerência de Contratos de Aluguel de Imóveis
-------------------------------------------

Sistema de gerenciamento de imóveis para venda e locação e geração de contratos. 
O sistema gerencia os imóveis e seus proprietários, fiadores, contratos e os 
funcionários da empresa e suas permissões no sistema. 
Permite a inclusão de modelos de contratos e o controle sobre a situação dos mesmos. 
Tecnologias usadas: JavaServer Faces e Hibernate, rodando sobre o PostgreSQL. 
Compatível com servidor Tomcat e GlassFish.

Informações de contato: herculeshssj@outlook.com

### Ambiente de desenvolvimento

*Requisitos:*

* Java JDK SE 7 ou superior;
* Eclipse Juno 4.2 ou superior;
* Git 1.7 ou superior;
* Tomcat 7 ou superior;
* PostgreSQL 8.4 ou superior;
* astah Community 6 ou superior;
* SQL Power Architect; 
* Pencil 1.4 ou superior;
* Windows, Linux ou Mac, qualquer versão capaz de rodar os softwares acima;
* LibreOffice ou ferramenta equivalente para ler arquivos em formato .doc;
* Leitor de PDF.

*Links de download:*

* *Java JDK SE 7*: http://www.oracle.com/technetwork/java/javase/downloads/index.html
* *Eclipse Juno*: http://www.eclipse.org/downloads
* *Github for Windows/Mac*: https://github.com/ 
* *Tomcat 7*: http://tomcat.apache.org/download-70.cgi
* *PostgreSQL*: http://www.postgresql.org/download/
* *astah Community*: http://astah.net/editions/community
* *SQL Power Architect:* http://www.sqlpower.ca/page/architect_download_os 
* *Pencil*: http://pencil.evolus.vn/Downloads.html

### Instalação do Java

A instalação do Java no Windows e Mac OS X não tem mistério. Basta baixar o pacote de acordo com a versão do SO e realizar a instalação através do assistente.

Para o Ubuntu, utilize os seguintes comandos:

sudo add-apt-repository ppa:webupd8team/java && sudo apt-get update
sudo apt-get install oracle-java8-installer


### Instalação do Eclipse Juno

A instalação do Eclipse Juno não tem mistério. Após realizar a instalação do Java, baixe o arquivo compactado do Eclipse Juno JavaEE de acordo com o SO e descompacte em uma pasta de sua preferência.

### Instalação do GIT

Para realizar a instalação do Git no Windows utiliza-se geralmente a ferramenta disponibiliza pelo Github.

Para realizar a instalação do Git no Linux, execute o seguinte comando:

sudo apt-get install git

Para realizar a instalação do Git no Mac, pode-se instalar a ferramenta disponibilizada pelo Github, ou utilizar o Xcode.

### Instalação do Tomcat

A instalação do Tomcat não tem mistério, basta baixar e descompactar o arquivo zipado para o diretório desejado.

Adicione no Eclipse pelo menu Window -> Preferences. Nas opções da lateral, vai em Server -> Runtime Environment. Clique em Add, selecione o Tomcat 7, selecione o diretório de instalação de Tomcat e clique em Finnish.

Depois clique na view Server e clique em New -> Server. Selecione o Tomcat 7 e clique em Finnish.

### Instalação do PostgreSQL

O projeto utiliza o PostgreSQL como servidor de banco de dados. Para realizar a instalação no Windows ou Mac, baixe e instale a versão disponível no site da EnterpriseDB, que oferece um instalador para realizar a instalação e configuração do servidor.

Para realizar a instalação do PostgreSQL no Linux baseado em Debian, execute os seguintes comandos:

```
sudo apt-get install postgresql-server pgadmin3
```

Finalizado a instalação, entre com o usuário *postgres*: 

```
sudo su postgres
```

Após isso entre no console do PostgreSQL executando o comando *psql*.

Uma vez dentro do console do Postgres, execute o comando 

```sql
alter user postgres with encrypted password='postgres';
```

A linha acima altera a senha do usuário postgres.

### Configuração do projeto

Segue abaixo os passos para poder baixar e executar o projeto.

### Configuração do PostgreSQL

Acesse o pgAdmin e conecte no servidor local. Crie um novo usuário chamada **imobiliaria**. Defina como senha a mesma que está contida no arquivo jdbc.properties. Crie uma nova base de dados chamada **imobiliaria** e atribua como dono o usuário **imobiliaria**.

Entre na janela de SQL logado com o usuário **imobiliaria** e roda o conteúdos dos seguintes arquivos:

* docs/create-script.sql
* docs/update-script.sql

Dados da credencial do usuário *admin* do projeto: 

*Usuário:* admin
*Senha:* admin

### Configuração do projeto no Eclipse

Efetue o clone do projeto para um diretório de sua preferência usando o comando:

git clone https://github.com/herculeshssj/imobiliariaweb

No Eclipse, abra a perspectiva Git. Clique em Add an existing Git repository. Selecione o diretório onde foi realizado o clone do repositório, e clique em Finnish.

Após isso clique no repositório e selecione Import Projects from Git Repository. Clique em Next, e na próxima tela em Finnish.

Para executar o projeto, clique com botão direito em cima do projeto, escolha Run As -> Run on Server. Na tela que se abre, selecione o Tomcat configurado e clique em Finnish.