package senac.estoque.helpers;

import java.text.DecimalFormat;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class Formatos {
	
	/**
	 * factory de formato moeda
	 * @return
	 */
	public DefaultFormatterFactory money() {
		DecimalFormat decimal = new DecimalFormat("#,###,###.00");
		decimal.setMaximumIntegerDigits(10);
        NumberFormatter numberFormatter = new NumberFormatter(decimal);
        numberFormatter.setFormat(decimal);
        numberFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory defaultFormatterFactory = new DefaultFormatterFactory(numberFormatter);
        return defaultFormatterFactory;
	}
}
