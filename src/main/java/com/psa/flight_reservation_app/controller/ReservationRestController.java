package com.psa.flight_reservation_app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.psa.flight_reservation_app.dto.ReservationUpdateRequest;
import com.psa.flight_reservation_app.entity.Reservation;
import com.psa.flight_reservation_app.repositories.ReservationRepository;

@RestController // it converts java object into json object and json object back to java object
public class ReservationRestController {

	@Autowired
	private ReservationRepository reservationRepository;

	@RequestMapping("/reservation/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		Optional<Reservation> findById = reservationRepository.findById(id);
		Reservation reservation = findById.get();
		return reservation;

	}

	@RequestMapping("/reservation") // http://localhost:8080/flights/reservation -> using this url we can update
									// data
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Optional<Reservation> findById = reservationRepository.findById(request.getId());
		Reservation reservation = findById.get();
		reservation.setCheckedIn(request.isCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		return reservationRepository.save(reservation);

	}
}
