#pragma once
#include "Person.h"
#include <vector>
#include <string>
#include <iostream>
class Customer : public Person
{
	friend std::ostream &operator<<(std::ostream &os, const Customer &customer);
private:
	// Data type default
	static constexpr const char *def_name = "Unnamed";
	static constexpr const char *def_address = "Unadress";
	static constexpr const char *def_phone = "Unphone";
	static constexpr const char *def_email = "Unemail";
protected:
	int customer_ID;
	bool VIP_member;
	std::vector<Person> customers;
public:
	// Constructor
	Customer(std::string name = def_name, std::string phone = def_phone, std::string address = def_address, std::string email = def_email);
	// Method function
	bool account_VIP();
	bool update_information(std::string, std::string, std::string, std::string);
	// bool check_information(std::string, std::string, std::string, std::string);
	// Deconstructor
	~Customer();
};

