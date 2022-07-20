package br.com.curso.alura.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulador {

	public static void main(String[] args) throws IOException {
		String login;
		System.out.println("************Teste de leitura do arquivo de propriedades************");
		Properties prop = getProp();
		
		login = prop.getProperty("prop.login.imdb");
		System.out.println("Login = " + login);
	}
	
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Argen\\segundo-semestre-poo\\alura-stickers\\src\\br\\com\\curso\\alura\\properties\\dados.properties");
		props.load(file);
		return props;

	}

}
