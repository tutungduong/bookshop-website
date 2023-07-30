#pragma once
class Room
{
private:
	// Data type default
	static constexpr const int def_room_type = 0;
	static constexpr const int def_price = 0;
	static constexpr const int def_number_bed = 0;
	// friend
	friend bool check_room_status();
	friend bool booking();
	friend bool cancel();

protected:
	int room_number;
	int room_type; // enum
	double price;
	bool room_status; // enum
	int number_bed;
public:
	// Constructor
	Room(int room_type = def_room_type, double price = def_price, int number_bed = def_number_bed);
	// Destructor
	~Room();
};

