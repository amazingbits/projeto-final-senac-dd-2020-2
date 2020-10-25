package senac.estoque.helpers;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;

public class Uri {

	/**
	 * abrir uma url da internet passada como parâmetro
	 * @param uri
	 * @return
	 */
	public static boolean openWebpage(String uri) {
		try {
	        Desktop.getDesktop().browse(new URL(uri).toURI());
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return false;
	}
}
