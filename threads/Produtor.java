/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 20/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: Classe de Produtor do Problema do Produtor X Consumidor
* Funcao...........: A classe Produtor garante que os socos sejam recarregados
* quando necessário, considerando a velocidade configurada.
****************************************************************/

package threads;

import controller.Controle;
import javafx.application.Platform;
import model.Semaforo;

public class Produtor extends Thread {

	private boolean running = true;
	private Controle controle;

	// Classe construtora de Consumidor
	public Produtor(Controle c) {
		this.controle = c;
	}

	/**
	 * Metodo: run
	 * Starta a thread e é uma parte crítica do controle da simulação do sistema e
	 * garante que os "socos" sejam recarregados no momento apropriado e que o
	 * estado da interface gráfica seja atualizado em conformidade com a execução da
	 * thread.
	 * 
	 * @return void
	 */
	public void run() {
		while (running == true) {
			if (controle.getVelocidadeRecarregar() == 1000) {
				// 'Pausa' a thread e a animação caso o valor do slider seja igual a 1000, ou
				// seja quando o tempo para execução seje mais lenta
				while (controle.getVelocidadeRecarregar() == 1000) {
					try {
						Thread.sleep(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			try {
				// Verifica se há espaço vazio para recarregar um "soco"
				Semaforo.vazio.acquire();
				// Adquire o semáforo de exclusão mútua (mutex)
				Semaforo.mutex.acquire();
				// Chama o método "recarregar()" para animar o soco de munição
				recarregar();
				// Libera o semáforo de exclusão mútua
				Semaforo.mutex.release();
				// Libera o semáforo "cheio" para indicar que a área crítica está ocupada
				Semaforo.cheio.release();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo: recarregar
	 * Este método é chamado antes de executar um soco na interface. Ele verifica se
	 * cada um dos socos (Soco1, Soco2, Soco3, Soco4, Soco5) na interface não estão
	 * visíveis e, se não estiverem, os torna visíveis.
	 * 
	 * @return void
	 */
	private void recarregar() {

		if (running) {
			// Atualiza a interface gráfica para mostrar o recarregamento
			Platform.runLater(() -> controle.carregar());
			// Aguarda o tempo definido para recarregar
			try {
				Thread.sleep(controle.getVelocidadeRecarregar());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Verifica qual dos "socos" não está visível e o torna visível
			if (!controle.getSoco1().isVisible()) {
				controle.getSoco1().setVisible(true);
			} else if (!controle.getSoco2().isVisible()) {
				controle.getSoco2().setVisible(true);
			} else if (!controle.getSoco3().isVisible()) {
				controle.getSoco3().setVisible(true);
			} else if (!controle.getSoco4().isVisible()) {
				controle.getSoco4().setVisible(true);
			} else if (!controle.getSoco5().isVisible()) {
				controle.getSoco5().setVisible(true);
			}
			// Atualiza a interface gráfica para a posição normal
			Platform.runLater(() -> controle.voltarAoNormal());
		}
	}
}
