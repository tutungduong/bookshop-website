#pragma once
#include <iostream>
class Booking
{
private:
	// Data type default
	static constexpr const char *def_booking_date = "29/07/2023";
	static constexpr const char *def_checkin_date = "29/07/2023";
	static constexpr const char *def_checkout_date = "30/07/2023";
	static constexpr const int def_number_guest = 1;
	// friend
	friend double total_price();
	friend bool update_booking();
	friend bool check_booking();
protected:
	int booking_ID;
	std::string booking_date;
	std::string checkin_date;
	std::string checkout_date;
	int number_guest;
	// List room services 
	double total_price;
public:
	Booking(std::string booking_date = def_booking_date, std::string checkin_date = def_checkin_date, std::string checkout_date = def_checkout_date);
	~Booking();
};

