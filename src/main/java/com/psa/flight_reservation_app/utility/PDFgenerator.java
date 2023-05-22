package com.psa.flight_reservation_app.utility;

import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.util.Date;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.psa.flight_reservation_app.entity.Reservation;

@Component
public class PDFgenerator {

//	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
//	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	public void generateItinery(Reservation reservation, String filePath) {
		Document document = new Document();
		try {

			PdfWriter.getInstance(document, new FileOutputStream(filePath));// here filepath every time will be
																			// different bcz of id

			document.open();
			document.add(generateTable(reservation));
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PdfPTable generateTable(Reservation reservation) {
//		Paragraph preface = new Paragraph();
//		document.add(preface);// that u r moving to new line
//		document.add(Chunk.NEWLINE);
//		document.add(Chunk.NEWLINE);

		PdfPTable table = new PdfPTable(2);
		PdfPCell cell;
//		table.setWidthPercentage(100); // means cover complete page
		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		cell = new PdfPCell(new Phrase("Flight Itinerary"));
		cell.setColspan(2);// means merge two collumn give this in center as passenger details
		table.addCell(cell);

//		table.addCell("Passenger name");
//		table.addCell(name);
//		table.addCell("Email Id");
//		table.addCell(emailId);
//		table.addCell("Phone Number");
//		table.addCell(phone);
//
//		document.add(table);

//		PdfPTable table = new PdfPTable(2);
//		table.setWidthPercentage(100);
		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		cell = new PdfPCell(new Phrase("Flight Detail"));
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(2);
		table.addCell(cell);

		table.addCell("Departure City");
		table.addCell(reservation.getFlight().getDepartureCity());// reservation.getFlight() by this we will get flight
																	// address then we can call here oneToOne mapping

		table.addCell("Arrival City");
		table.addCell(reservation.getFlight().getArrivalCity());

		table.addCell("Flight Number");
		table.addCell(reservation.getFlight().getFlightNumber());

		table.addCell("Operating Airlines");
		table.addCell(reservation.getFlight().getOperatingAirlines());

		table.addCell("Departure Date");
		table.addCell(reservation.getFlight().getDateOfDeparture().toString());

		cell = new PdfPCell(new Phrase("Passenger Deatails"));
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(2);
		table.addCell(cell);

		table.addCell("Passenger name");
		table.addCell(reservation.getPassenger().getFirstName());
		table.addCell("Email Id");

		table.addCell(reservation.getPassenger().getEmail());
		table.addCell("Phone Number");

		table.addCell(reservation.getPassenger().getPhone());

		return table;

	}

}
