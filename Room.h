#pragma once
#include "Person.h"

// Enum for Room type and Room status
enum class RoomType { Undefinition,Single, Double, Suite };
enum class RoomStatus { Available, Occupied, Reserved, UnderMaintenance };

class Room {
private:
	static constexpr const int def_numberID = 0;
	static constexpr const RoomStatus def_roomStatus = RoomStatus::Available;
	static constexpr const RoomType def_roomType = RoomType::Undefinition;
	static constexpr const double def_price = 0.0;
	static constexpr const int def_numberOfBeds = 0;
protected:
	int numberID;
	RoomType roomType;
	double price;
	RoomStatus roomStatus;
	int numberOfBeds;
public:

	Room(int numberID = def_numberID, RoomType roomType = def_roomType, double price = def_price,
		RoomStatus roomStatus = def_roomStatus, int numberOfBeds = def_numberOfBeds);

	// Method Check room status
	RoomStatus get_room() const;
	// Method Book room
	bool bookRoom();
	// Method Cancel room
	bool cancelRoom();
	// Method NotifyMaintenance Room
	bool notifyRoom();
	~Room();
};