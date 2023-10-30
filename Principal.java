/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 20/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: Classe Main do Programa Produtor consumidor
* Funcao...........: Importa o que é necessario para iniciar e
cria a interface gráfica.
****************************************************************/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controller.Controle;

public class Principal extends Application {

	// O método principal, ponto de entrada da aplicação Java
	public static void main(String[] args) {
		launch(args);
	}

	// Sobrescreve o método start, que é chamado quando a aplicação JavaFX inicia
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Cria um carregador de FXML para carregar o arquivo FXML "PrincipalFXML.fxml"
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PrincipalFXML.fxml"));

		// Carrega o conteúdo definido no arquivo FXML em um AnchorPane
		AnchorPane root = loader.load();

		// Cria uma cena (scene) com o AnchorPane como nó raiz e define suas dimensões
		// para 1280x720 pixels
		Scene cena = new Scene(root, 1280, 720);

		// Define a cena como o conteúdo da janela principal (primaryStage)
		primaryStage.setScene(cena);

		// Define o título da janela principal como "202010643 - Produtor X Consumidor"
		primaryStage.setTitle("202010643 - Produtor X Consumidor");

		// Desabilita a capacidade de redimensionar a janela principal
		primaryStage.setResizable(false);

		// Exibe a janela principal
		primaryStage.show();

	}

}
