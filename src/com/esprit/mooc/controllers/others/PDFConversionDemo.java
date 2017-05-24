package com.esprit.mooc.controllers.others;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFConversionDemo {
	    public static void main(String[] args) {
	        try {
	            OutputStream file = new FileOutputStream(new File("SamplePDF.pdf"));

	            Document document = new Document();
	            PdfWriter.getInstance(document, file);

	            document.open();
	            document.add(new Paragraph("First iText PDF"));
	            document.add(new Paragraph(new Date().toString()));

	            document.addAuthor("Krishna Srinivasan");
	            document.addCreationDate();
	            document.addCreator("JavaBeat");
	            document.addTitle("Sample PDF");

	            //Create Paragraph
	            Paragraph paragraph = new Paragraph("Title 1",new Font(Font.FontFamily.TIMES_ROMAN, 18,
	            	      Font.BOLD));

	            //New line
	            paragraph.add(new Paragraph(" "));
	            paragraph.add("Test Paragraph");
	            paragraph.add(new Paragraph(" "));
	            document.add(paragraph);

	            //Create a table in PDF
	            PdfPTable pdfTable = new PdfPTable(3);
	            PdfPCell cell1 = new PdfPCell(new Phrase("Table Header 1"));
	            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	            pdfTable.addCell(cell1);

	            cell1 = new PdfPCell(new Phrase("Table Header 2"));
	            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	            pdfTable.addCell(cell1);

	            cell1 = new PdfPCell(new Phrase("Table Header 3"));
	            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	            pdfTable.addCell(cell1);
	            pdfTable.setHeaderRows(1);

	            pdfTable.addCell("Row 1 Col 1");
	            pdfTable.addCell("Row 1 Col 2");
	            pdfTable.addCell("Row 1 Col 3");

	            pdfTable.addCell("Row 2 Col 1");
	            pdfTable.addCell("Row 2 Col 2");
	            pdfTable.addCell("Row 2 Col 3");

	            document.add(pdfTable);

	            document.close();
	            file.close();

	        } catch (Exception e) {

	            e.printStackTrace();
	        }
	    }

}