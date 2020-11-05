package senac.estoque.helpers;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JTable;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;

public class GerarPdf {

    public GerarPdf() {
    }

    public void gerar(JTable table, String nome, String[] columnsNames) {
        String path = "";
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = fc.showSaveDialog(null);

        if (x == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();

        }

        Document document = new Document();

        try {
            try {
                PdfWriter.getInstance(document, new FileOutputStream(path + "/" + nome + ".pdf"));

                document.open();

                PdfPTable pdfPTable = new PdfPTable(columnsNames.length);

                try {
                    URL url = new URL("https://raw.githubusercontent.com/amazingbits/projeto-final-senac-dd-2020-2/master/gerenciamento-estoque/imagens/estoque.png");
                
                    try {
                        Image image = Image.getInstance(url);                        
                       
                        String descricao = "Gestão de Estoque";
                                            
                        image.scaleToFit(100, 100);

                        document.add(image);
                        document.add(new Phrase("\n"));

                        document.add(new Phrase(descricao));
                        
                        document.addTitle("Relatório");

                        document.add(new Phrase("\n\n"));
                        

                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }

                

                for (int i = 0; i < columnsNames.length; i++) {
                    pdfPTable.addCell(columnsNames[i]);
                }

                for (int i = 0; i < table.getRowCount(); i++) {

                    for (int j = 0; j < columnsNames.length; j++) {
                        pdfPTable.addCell(table.getValueAt(i, j).toString());
                    }

                }

                document.add(pdfPTable);

            } catch (DocumentException e) {
                System.out.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        document.close();

    }

}
