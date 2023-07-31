#include "Booking.h"

Booking::Booking(int bookingID,std::string bookingDate,std::string checkInDate,std::string checkOutDate,int numberOfGuests,double totalPrice,BookingStatus bookingStatus)
	:bookingID{ bookingID }, bookingDate{ bookingDate }, checkInDate{ checkInDate }, checkOutDate{ checkOutDate }, numberOfGuests{ numberOfGuests }, totalPrice{ totalPrice }, bookingStatus{ bookingStatus }{}

void Booking::update_information(std::string newCheckInDate, std::string newCheckOutDate, int newNumberOfGuests) {

	checkInDate = newCheckInDate;
	checkOutDate = newCheckOutDate;
	numberOfGuests = newNumberOfGuests;
	
};

BookingStatus Booking::getBookingStatus() const {
	return bookingStatus;
}

