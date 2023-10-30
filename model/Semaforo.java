/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 20/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: Classe de semáforo do Problema do Produtor X Consumidor
* Funcao...........: A classe Semaforo é um utilitário para configurar semáforos 
* e controlar o acesso concorrente a uma região crítica, como parte da solução para
* o problema do produtor-consumidor, em que threads produzem e consomem dados de um
* buffer compartilhado.
****************************************************************/

package model;

import java.util.concurrent.Semaphore;

public class Semaforo {

	/**
	 * Metodo: buffer
	 * Tamanho do buffer, no caso será 5. A produção e inserção de 5 produtos enche
	 * o buffer.
	 * 
	 * @return void
	 */
	private static int buffer = 5;

	/**
	 * Metodo: mutex
	 * realiza o controle do acesso a regiao critica
	 * 
	 * @return void
	 */
	public static Semaphore mutex = new Semaphore(1);

	/**
	 * Metodo: vazio
	 * faz a contagem das regioes vazias do buffer
	 * 
	 * @return void
	 */
	public static Semaphore vazio = new Semaphore(buffer);

	/**
	 * Metodo: cheio
	 * Faz a contagem das regioes ocupadas do buffer
	 * 
	 * @return void
	 */
	public static Semaphore cheio = new Semaphore(0);

	/**
	 * Metodo: setMutex
	 * metodo para instanciar um novo semaforo mutex
	 * 
	 * @return void
	 */
	public static void setMutex(int valor) {
		mutex = new Semaphore(valor);
	}

	/**
	 * Metodo: setVazio
	 * metodo para instanciar um novo semaforo vazio
	 * 
	 * @return void
	 */
	public static void setVazio() {
		buffer = 5;
		vazio = new Semaphore(buffer);
	}

	/**
	 * Metodo: setCheio
	 * metodo para instanciar um novo semaforo cheio
	 * 
	 * @return void
	 */
	public static void setCheio(int i) {
		cheio = new Semaphore(i);
	}

}
