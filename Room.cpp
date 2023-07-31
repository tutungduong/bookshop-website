#include "Room.h"

Room::Room(int numberID,RoomType roomType,double price,RoomStatus roomStatus,int numberOfBeds)
	:numberID{numberID}, roomType{ roomType },price{price}, roomStatus{ roomStatus }, numberOfBeds{ numberOfBeds }{}

// Method Check room status
RoomStatus Room::get_room() const {
	return roomStatus;
}
// Method Book room
bool Room::bookRoom() {
	if (roomStatus != RoomStatus::Available) {
		return false;
	}
	return true;
}
// Method Cancel room
bool Room::cancelRoom() {
	if (roomStatus != RoomStatus::Reserved) {
		return false;
	}
	return true;
}
// Method NotifyMaintenance Room
bool Room::notifyRoom() {
	if (roomStatus != RoomStatus::UnderMaintenance) {
		return false;
	}
	return true;
}