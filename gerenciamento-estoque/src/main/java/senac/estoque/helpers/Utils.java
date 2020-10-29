package senac.estoque.helpers;

import java.text.Normalizer;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class Utils {
		
	/**
	 * método que formata data BRL para SQL
	 * @param data
	 * @return
	 */
		public String formatarDataParaSQL(String data) {
			String[] lista = new String[3];
			lista = data.split("/");
			return lista[2] + "-" + lista[1] + "-" + lista[0];
		}
		
		/**
		 * método que remove acentos de uma string
		 * @param valor
		 * @return
		 */
		public String removerAcentos(String valor) {
			valor = valor.trim();
			String nfdNormalizedString = Normalizer.normalize(valor, Normalizer.Form.NFD);
	        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	        return pattern.matcher(nfdNormalizedString).replaceAll("");
		}
		
		/**
		 * função para criar máscaras para campos de texto
		 * @param msk String com a máscara
		 * @param txt campo do tipo JFormattedTextField
		 */
		public void mascara(String msk, JFormattedTextField txt) {
			try {
				MaskFormatter mask = new MaskFormatter(msk);
				mask.install(txt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Erro ao formatar campo", "Erro de Formatação", JOptionPane.ERROR);
			}
		}
		
		/**
		 * método para validar uma data no formato BRL
		 * @param data
		 * @return
		 */
		public boolean validarData(String data) {
			
			data = data.trim();
			data = data.replace("-", "/").replaceAll("[^0-9/]", "");
			
			if(data.length() != 10) return false;
			String[] arrData = data.split("/");
			if(arrData.length != 3 ) return false;
			int dia = Integer.parseInt(arrData[0]);
			int mes = Integer.parseInt(arrData[1]);
			int ano = Integer.parseInt(arrData[2]);
			
			if(dia < 0 || dia > 31) return false;
			if(mes < 0 || mes > 12) return false;
			if(ano < 1500) return false;
			
			return true;
		}
		
		/**
		 * verifica se a primeira data é menor ou igual à segunda data,
		 * ambas BRL String como parâmetro
		 * @param dt1
		 * @param dt2
		 * @return
		 */
		public boolean compararDatas(String dt1, String dt2) {
			if(!this.validarData(dt1)) return false;
			if(!this.validarData(dt2)) return false;
			
			String[] arrDt1 = dt1.split("/");
			int diaDt1 = Integer.parseInt(arrDt1[0]);
			int mesDt1 = Integer.parseInt(arrDt1[1]);
			int anoDt1 = Integer.parseInt(arrDt1[2]);
			LocalDate data1 = LocalDate.of(anoDt1, mesDt1, diaDt1);
			
			String[] arrDt2 = dt2.split("/");
			int diaDt2 = Integer.parseInt(arrDt2[0]);
			int mesDt2 = Integer.parseInt(arrDt2[1]);
			int anoDt2 = Integer.parseInt(arrDt2[2]);
			LocalDate data2 = LocalDate.of(anoDt2, mesDt2, diaDt2);
			
			if(data1.isBefore(data2)) return true;
			if(data1.isEqual(data2)) return true;
			
			return false;
		}
		
	}
