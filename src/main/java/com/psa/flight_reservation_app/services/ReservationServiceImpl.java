package com.psa.flight_reservation_app.services;

import java.util.Optional;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.flight_reservation_app.dto.ReservationRequest;
import com.psa.flight_reservation_app.entity.Flight;
import com.psa.flight_reservation_app.entity.Passenger;
import com.psa.flight_reservation_app.entity.Reservation;
import com.psa.flight_reservation_app.repositories.FlightRepository;
import com.psa.flight_reservation_app.repositories.PassengerRepository;
import com.psa.flight_reservation_app.repositories.ReservationRepository;
import com.psa.flight_reservation_app.utility.EmailUtil;
import com.psa.flight_reservation_app.utility.PDFgenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private EmailUtil emailUtil;

	@Override // all 3 operation perform in service layer
	public Reservation bookFlight(ReservationRequest request) {
//        String firstName = request.getFirstName();
//        String lastName = request.getLastName();
//        String middleName = request.getMiddleName();
//        String email = request.getEmail();
//        String phone = request.getPhone();
	    
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		
		Passenger savePassenger = passengerRepository.save(passenger);//here passenger data get saved
		
//		==================== Flight details==================================
// we r not saving flight details here bcz flight details are already present in table we
// are saving passenger details and getting flight details then passenger and flight details saving in reservation table
		long flightId = request.getFlightId();
		Optional<Flight> findById = flightRepository.findById(flightId);
		Flight flight = findById.get(); //flight data already in database we r getting Flight object now passenger and flight goes to reservation
		
		Reservation reservation=new Reservation();
		reservation.setFlight(flight);// here we r giving flight obj automatically flightId will get saved into table
		reservation.setPassenger(savePassenger);//here we r giving savePassenger obj automatically passengerId will get saved into table
		reservation.setCheckedIn(false);//status false bcz we just booking the ticket after that checkin module will be build
		reservation.setNumberOfBags(0);
		
		 String filePath="C:\\Users\\hp\\Documents\\workspace-spring-tool-suite-4-4.18.0.RELEASE\\flight_reservation_app\\tickets"+reservation.getId()+".pdf"; // we cannot use access modifier inside the method only final is permitted
			//savedReservation.getId()+".pdf_> for every ticket booked reservation id will be different
		 reservationRepository.save(reservation);
//	once we save data into database then we can generate pdf of ticket
		 
		
		PDFgenerator pdf=new PDFgenerator();
		pdf.generateItinery(reservation,filePath);
		emailUtil.sendItinerary(passenger.getEmail(), filePath); // passenger.getEmail() -> whatever email address I am entering into the form that email address go to the toAddress in sendItinerary method
		
	
		return reservation; 
	}

}

