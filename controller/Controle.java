/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 20/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: Classe de controle do Problema do Produtor X Consumidor
* Funcao...........: A classe `Controle` é responsável por
 controlar a interface gráfica e a lógica do aplicativo. Ela
 implementa a interface `Initializable` para inicializar
 componentes de interface quando o FXML é carregado.
****************************************************************/

package controller;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import threads.Consumidor;
import threads.Produtor;

public class Controle {

	// Componentes de interface gráfica
	@FXML
	private ImageView Inicio;

	@FXML
	private ImageView SegundaTela;

	@FXML
	private ImageView Cenario;

	@FXML
	private ImageView Lutador;

	@FXML
	private ImageView LutadorSocando;

	@FXML
	private ImageView LutadorSocando2;

	@FXML
	private Button botaoIniciar;

	@FXML
	private Button botaoAvancar;

	@FXML
	private Slider velocidadeSoco;

	@FXML
	private Slider velocidadeRecarregar;

	@FXML
	private ImageView Soco1;

	@FXML
	private ImageView Soco2;

	@FXML
	private ImageView Soco3;

	@FXML
	private ImageView Soco4;

	@FXML
	private ImageView Soco5;

	@FXML
	private ImageView LutadorVelocidade;

	@FXML
	private ImageView SocoVelocidade;

	@FXML
	private ImageView LutadorCarregando;

	@FXML
	private ImageView LutadorCarregando2;

	// Variáveis para controle do carregamento de soco e tipo de soco
	private Consumidor c;
	private Produtor p;
	private int TipoDeSoco;
	private int TipoDeCarregamento;

	/**
	 * Metodo: initialize
	 * Método chamado durante a inicialização do FXML.
	 * Configura as opções iniciais dos ChoiceBox e visibilidade dos componentes.
	 * 
	 * @return void
	 */
	public void initialize() {

		// Configuração inicial da interface
		Inicio.setVisible(true);
		Cenario.setVisible(false);
		SegundaTela.setVisible(false);
		botaoAvancar.setVisible(true);
		botaoAvancar.setDisable(false);
		botaoIniciar.setVisible(false);
		botaoIniciar.setDisable(true);
		Lutador.setVisible(false);
		LutadorSocando.setVisible(false);
		LutadorSocando2.setVisible(false);
		LutadorCarregando.setVisible(false);
		LutadorCarregando2.setVisible(false);
		LutadorVelocidade.setVisible(false);
		SocoVelocidade.setVisible(false);
		velocidadeSoco.setVisible(false);
		velocidadeRecarregar.setVisible(false);
		Soco1.setVisible(false);
		Soco2.setVisible(false);
		Soco3.setVisible(false);
		Soco4.setVisible(false);
		Soco5.setVisible(false);

	}

	/**
	 * Metodo: avancar
	 * Método chamado ao clicar no botão "Avançar"
	 * Passa para a segunda tela guia da aplicação
	 * 
	 * @param event O evento de clique do mouse no botão
	 * @return void
	 */
	public void avancar(ActionEvent event) {

		// Configuração da segunda tela da interface
		botaoAvancar.setVisible(false);
		botaoAvancar.setDisable(true);
		botaoIniciar.setVisible(true);
		botaoIniciar.setDisable(false);
		Inicio.setVisible(false);
		SegundaTela.setVisible(true);
		Cenario.setVisible(false);
	}

	/**
	 * Metodo: iniciar
	 * Método chamado quando o botão "Iniciar" é clicado.
	 * Configura a posição inicial da munição (socos), e a visualização do
	 * personagem principal
	 * 
	 * @param event O evento de clique do mouse no botão
	 * @return void
	 */
	@FXML
	public void iniciar(ActionEvent event) {

		// Configuração inicial do cenário de jogo
		Soco1.setX(10);
		Soco2.setX(70);
		Soco3.setX(130);
		Soco4.setX(190);
		Soco5.setX(250);
		SocoVelocidade.setVisible(true);
		LutadorVelocidade.setVisible(true);
		Inicio.setVisible(false);
		SegundaTela.setVisible(false);
		Cenario.setVisible(true);
		botaoIniciar.setVisible(false);
		botaoIniciar.setDisable(true);
		velocidadeSoco.setVisible(true);
		velocidadeRecarregar.setVisible(true);

		// Inicialização das threads de produtor e consumidor
		p = new Produtor(this);
		c = new Consumidor(this);
		c.setDaemon(true);
		p.setDaemon(true);
		c.start();
		p.start();

		// Configuração inicial do lutador e soco
		Lutador.setVisible(true);
		Lutador.setX(430);
		Lutador.setY(410);
		LutadorSocando.setX(430);
		LutadorSocando.setY(410);
		LutadorSocando2.setX(424);
		LutadorSocando2.setY(414);
		LutadorCarregando.setX(430);
		LutadorCarregando.setY(410);
		LutadorCarregando2.setX(430);
		LutadorCarregando2.setY(410);
		LutadorVelocidade.setX(120);
		LutadorVelocidade.setY(590);
		SocoVelocidade.setX(1060);
		SocoVelocidade.setY(620);
	}

	/**
	 * Metodo: getVelocidadeSoco
	 * Obtém a velocidade do soco a partir do Slider
	 * 
	 * @return Valor atual em inteiro do Slider do soco
	 */
	public int getVelocidadeSoco() {
		double aux = velocidadeSoco.getValue();// pega o valor do slider e armazena em uma variável auxiliar
		int retorno = (int) aux; // transforma o valor em inteiro
		return retorno; // retorna o valor
	}

	/**
	 * Metodo: getVelocidadeRecarregar
	 * Obtém a velocidade de recarregamento do soco a partir do Slider
	 * 
	 * @return Valor atual em inteiro do Slider do Munição
	 */
	public int getVelocidadeRecarregar() {
		double aux = velocidadeRecarregar.getValue();// pega o valor do slider e armazena em uma variável auxiliar
		int retorno = (int) aux; // transforma o valor em inteiro
		return retorno; // retorna o valor
	}

	/**
	 * Metodo: socar
	 * Função para simular o soco do lutador
	 * 
	 * @return void
	 */
	public void socar() {

		Lutador.setVisible(false);

		// Chama o método para definir qual soco será dado
		GerarSoco();

		// Torna a imagem do soco visível a partir do valor do 'TipoDeSoco'
		if (TipoDeSoco == 1) {
			LutadorSocando.setVisible(true);
		} else {
			LutadorSocando2.setVisible(true);
		}
	}

	/**
	 * Metodo: voltarAoNormal
	 * Função para voltar ao estado normal do lutador
	 * 
	 * @return void
	 */
	public void voltarAoNormal() {
		// Torna a imagem do soco não visível a partir do valor do 'TipoDeSoco'
		if (TipoDeSoco == 1) {
			LutadorSocando.setVisible(false);
		} else {
			LutadorSocando2.setVisible(false);
		}

		// Torna a imagem do carregamento não visível a partir do valor do
		// 'TipoDeCarregamento'
		if (TipoDeCarregamento == 1) {
			LutadorCarregando.setVisible(false);
		} else {
			LutadorCarregando2.setVisible(false);
		}

		// Torna a imagem do lutador normal visível
		Lutador.setVisible(true);
	}

	/**
	 * Metodo: carregar
	 * Função para simular o carregamento do lutador
	 * 
	 * @return void
	 */
	public void carregar() {
		// Torna a imagem do lutador normal não visível
		Lutador.setVisible(false);

		// Chama o método para definir qual carregamento será usado
		GerarCarregamento();

		// Torna a imagem de carregamento visível a partir do valor do
		// 'TipoDeCarregamento'
		if (TipoDeCarregamento == 1) {
			LutadorCarregando.setVisible(true);
		} else {
			LutadorCarregando2.setVisible(true);
		}
	}

	/**
	 * Metodo: GerarSoco
	 * Função para gerar aleatoriamente o tipo de soco
	 * 
	 * @return O valor 1 ou 2 para o tipo de soco
	 */
	private int GerarSoco() {
		Random gerador = new Random();
		TipoDeSoco = gerador.nextInt(2) + 1;
		return TipoDeSoco;
	}

	/**
	 * Metodo: GerarCarregamento
	 * Função para gerar aleatoriamente o tipo de carregamento
	 * 
	 * @return O valor 1 ou 2 para o tipo de carregamento
	 */
	private int GerarCarregamento() {
		Random gerador = new Random();
		TipoDeCarregamento = gerador.nextInt(2) + 1;
		return TipoDeCarregamento;
	}

	// Getters abaixo

	public ImageView getInicio() {
		return Inicio;
	}

	public ImageView getCenario() {
		return Cenario;
	}

	public ImageView getLutador() {
		return Lutador;
	}

	public ImageView getLutadorSocando() {
		return LutadorSocando;
	}

	public Button getBotaoIniciar() {
		return botaoIniciar;
	}

	public ImageView getSoco1() {
		return Soco1;
	}

	public ImageView getSoco2() {
		return Soco2;
	}

	public ImageView getSoco3() {
		return Soco3;
	}

	public ImageView getSoco4() {
		return Soco4;
	}

	public ImageView getSoco5() {
		return Soco5;
	}

}
