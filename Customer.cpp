#include "Customer.h"
// Constructor
Customer::Customer(std::string name, std::string phone, std::string address, std::string email)
	     :Person{ name,phone,address,email }, VIP_member {0}{}


bool Customer::update_information(std::string name, std::string phone, std::string address, std::string email) {
	for (auto &customer : customers) {
		if (customer.get_name() == name && customer.get_phone() == phone) {
			return false;
		}
	}
	Person::update_information(name, phone, address, email);
	return true;
}
/*
void Customer::update_information(std::string name, std::string phone, std::string address, std::string email) {
	Person::update_information(name, phone, address, email);
}
*/
std::ostream &operator<<(std::ostream &os, const Customer &customer) {
	std::cout << "[Customer: " << customer.name << ": " << customer.phone << ": " << customer.address << ": " << customer.email << ": " << customer.VIP_member << "]";
	return os;
}
// Destructor
Customer::~Customer(){}