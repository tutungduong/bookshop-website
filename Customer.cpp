#include "Customer.h"


Customer::Customer(std::string name, std::string phone, int customerID, bool isVIP)
	:Person{name,phone},customerID{customerID},isVIP{isVIP}{}


void Customer::update_information(std::string name, std::string phone) {
	Person::update_information(name, phone);
}

void Customer::createVIPAccount() {
	isVIP = true;
}