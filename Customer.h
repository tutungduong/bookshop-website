#pragma once
#include <string>

class Customer
{
private:
	// Data type default
	static constexpr const char *def_name = "Unnamed";
	static constexpr const char *def_address = "Unadress";
	static constexpr const char *def_phone = "Unphone";
	static constexpr const char *def_email = "Unemail";
	// Method function 
	friend bool check(std::string, std::string);
	friend bool update(std::string, std::string, std::string, std::string);

protected:
	std::string name;
	std::string address;
	std::string phone;
	std::string email;
	int customer_ID;
	bool VIP_member;
public:
	// Constructor
	Customer(std::string name = def_name, std::string address = def_address, std::string phone = def_phone
		, std::string email = def_email);
	// Deconstructor
	~Customer();

};

