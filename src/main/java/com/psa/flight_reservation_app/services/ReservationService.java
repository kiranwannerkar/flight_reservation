package com.psa.flight_reservation_app.services;

import com.psa.flight_reservation_app.dto.ReservationRequest;
import com.psa.flight_reservation_app.entity.Reservation;

public interface ReservationService {

	Reservation bookFlight(ReservationRequest request);
}
