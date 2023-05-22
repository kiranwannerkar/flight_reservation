package com.psa.flight_reservation_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psa.flight_reservation_app.dto.ReservationRequest;
import com.psa.flight_reservation_app.entity.Reservation;
import com.psa.flight_reservation_app.services.ReservationService;
import com.psa.flight_reservation_app.utility.PDFgenerator;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@RequestMapping("/confirmReservation")
	public String confirmReservation(ReservationRequest request, ModelMap modelMap) {
		Reservation reservationId = reservationService.bookFlight(request);
		modelMap.addAttribute("reservationId", reservationId.getId());// so that I can give reservationId to the customer
//		by using reservationId u can track your ticket
		return "confirmReservation";
		
	}
}
