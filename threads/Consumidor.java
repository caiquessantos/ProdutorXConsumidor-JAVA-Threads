/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 20/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: Classe de Produtor do Problema do Produtor X Consumidor
* Funcao...........: A classe Semaforo é um utilitário para configurar semáforos 
* e controlar o acesso concorrente a uma região crítica, como parte da solução para
* o problema do produtor-consumidor, em que threads produzem e consomem dados de um
* buffer compartilhado.
****************************************************************/

package threads;

import controller.Controle;
import javafx.application.Platform;
import model.Semaforo;

public class Consumidor extends Thread {

	private boolean running = true;
	private Controle controle;

	// Classe construtora de Consumidor
	public Consumidor(Controle controle) {
		this.controle = controle;
	}

	/**
	 * Metodo: run
	 * Starta a thread
	 * 
	 * @return void
	 */
	public void run() {
		executarSoco();
	}

	/**
	 * Metodo: executarSoco
	 * Este método realiza a lógica de socar e controla o acesso concorrente ao
	 * buffer de socos, usando semáforos.
	 * 
	 * @return void
	 */
	private void executarSoco() {

		if (running) {
			// Verifica se a thread deve continuar executando e entra em um loop infinito
			// para continuar a execução
			while (true) {
				// Verifica se o lutador na interface é visível
				if (controle.getLutador().isVisible()) {
					if (controle.getVelocidadeSoco() == 400) {
						// 'Pausa' a thread e a animação caso o valor do slider seja igual a 400, ou
						// seja quando o tempo para execução seje mais lenta
						while (controle.getVelocidadeSoco() == 400) {
							try {
								Thread.sleep(1);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					try {
						// Tenta adquirir um "permite" do semáforo de "cheio" (regiões ocupadas do
						// buffer)
						Semaforo.cheio.acquire();
						// Tenta adquirir um semáforo de exclusão mútua (mutex) para garantir acesso
						// exclusivo à região crítica
						Semaforo.mutex.acquire();
						// Chama o método "Remover()" para remover um soco da interface
						Remover();
						// Chama o método "socar()" para animar o soco
						socar();
						// Libera o semáforo de exclusão mútua (mutex) após concluir a operação
						Semaforo.mutex.release();
						// Libera um "permite" no semáforo de "vazio" (regiões vazias do buffer) para
						// indicar que há espaço vazio disponível no buffer
						Semaforo.vazio.release();

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Metodo: Remover
	 * Este método é chamado antes de executar um soco na interface. Ele verifica se
	 * cada um dos socos (Soco1, Soco2, Soco3, Soco4, Soco5) na interface está
	 * visível e, se estiver, os torna invisíveis (remove-os da visualização).
	 * 
	 * @return void
	 */
	private void Remover() {

		// Verifica se a thread deve continuar executando
		if (running) {

			// Verifica se os socos na interface são visíveis e, se forem, os torna
			// invisíveis
			if (controle.getSoco5().isVisible()) {
				controle.getSoco5().setVisible(false);
			} else if (controle.getSoco4().isVisible()) {
				controle.getSoco4().setVisible(false);
			} else if (controle.getSoco3().isVisible()) {
				controle.getSoco3().setVisible(false);
			} else if (controle.getSoco2().isVisible()) {
				controle.getSoco2().setVisible(false);
			} else if (controle.getSoco1().isVisible()) {
				controle.getSoco1().setVisible(false);
			}

		}

	}

	/**
	 * Metodo: socar
	 * é responsável por realizar a ação de "socar" na interface gráfica, alternando
	 * entre a visualização do lutador socando e voltando ao estado normal.
	 * 
	 * @return void
	 */
	private void socar() {

		// Verifica se a thread deve continuar executando
		if (running) {
			// Atualiza a interface para exibir o lutador socando
			Platform.runLater(() -> controle.socar());
			// Aguarda um período de tempo com base na velocidade configurada
			// (Ajuste o tempo conforme necessário)
			try {
				Thread.sleep(controle.getVelocidadeSoco()); // Ajuste o tempo conforme necess�rio
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Retorna a interface para o estado normal (lutador parado)
			Platform.runLater(() -> controle.voltarAoNormal());

			// Aguarda um período de tempo com base na velocidade configurada
			// (Ajuste o tempo conforme necessário)
			try {
				Thread.sleep(controle.getVelocidadeSoco());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
