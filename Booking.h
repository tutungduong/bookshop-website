#pragma once
#include <iostream>
#include <string>

enum class BookingStatus { Active, Completed, Canceled };

class Booking
{
private:
	static constexpr const int def_bookingID = 0;
	static constexpr const char *def_bookingDate = "Non-existent";
	static constexpr const char *def_checkInDate = "Non-existent";
	static constexpr const char *def_checkOutDate = "Non-existent";
	static constexpr const int def_numberOfGuests = 0;
	static constexpr const double def_totalPrice = 0.0;
	static constexpr const BookingStatus def_bookingStatus = BookingStatus::Canceled;
protected:
	int bookingID;
	std::string bookingDate;
	std::string checkInDate;
	std::string checkOutDate;
	int numberOfGuests;
	double totalPrice;
	BookingStatus bookingStatus;
public:
Booking(int bookingID = def_bookingID,std::string bookingDate = def_bookingDate,std::string checkInDate = def_checkInDate
	,std::string checkOutDate = def_checkOutDate,int numberOfGuests = def_numberOfGuests,double totalPrice = def_totalPrice,BookingStatus bookingStatus = def_bookingStatus);

void update_information(std::string, std::string, int);

BookingStatus getBookingStatus() const;

~Booking();
};

