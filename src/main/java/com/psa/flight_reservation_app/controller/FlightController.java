package com.psa.flight_reservation_app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psa.flight_reservation_app.entity.AbstractEntity;
import com.psa.flight_reservation_app.entity.Flight;
import com.psa.flight_reservation_app.repositories.FlightRepository;

@Controller
public class FlightController extends AbstractEntity {
	
	@Autowired
	private FlightRepository flightRepository;
	
	
	// model attribute can be use when entity attribute matches with form
//		using this	@DateTimeFormat bcz we dont want to read data in form of string so.... i want to read in date format ----Date ->import from java util
	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from,@RequestParam("to") String to, @RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate, ModelMap modelMap) {
		  List<Flight> findFlights = flightRepository.findFlights(from, to, departureDate);
		modelMap.addAttribute("findFlights", findFlights);
		return "displayFlights";
	}
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {//we fetch the id with the help of @RequestParam
		//anything u want to share to jsp page Step1->ModelMap
		Flight flight = flightRepository.findById(flightId).get();
		modelMap.addAttribute("flight",flight);
		return "showReservation";
		
	}
}
