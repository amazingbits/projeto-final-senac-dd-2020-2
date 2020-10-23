package senac.estoque.helpers;

public class Caracteres {
	
	/**
	 * retira todos os caracteres que não sejam letras
	 * @param caracter
	 * @return
	 */
	public String permitirSomenteLetras(String caracter) {
		String regex = "[^A-Za-z ]+";
		caracter = caracter.trim();
		return caracter.replaceAll(regex, "");
	}

}
