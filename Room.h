#pragma once
#include <iostream>
#include "Person.h"
#include <vector>
class Room : public Person
{
	// Friend constructor
	friend std::ostream &operator<<(std::ostream &os, const Room &room);
private:
	// Data type default
	static constexpr const int def_room_type = 0;
	static constexpr const double def_price_room = 0;
	//static constexpr const bool def_room_status = false;
	static constexpr const int def_number_bed = 0;
protected:
	int room_ID;
	int room_type; // enum
	double price;
	bool room_status; // enum
	int number_bed;
public:
	// Constructor
	Room(int room_type = def_room_type, double price = def_price_room, int number_bed = def_number_bed);

	bool check_room();
	void book_room();
	void cancel_rool();
	bool noti_repair();
	// Destructor
	~Room();
};

