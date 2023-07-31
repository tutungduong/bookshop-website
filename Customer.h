#pragma once
#include "Person.h"
#include <string.h>
#include <iostream>

class Customer : public Person
{
private:
	static constexpr const char *def_name = "Non-existent";
	static constexpr const char *def_phone = "Non-existent";
	static constexpr const int def_customerID = 0;
	static constexpr const bool def_isVIP = false;
protected:
	int customerID;
	bool isVIP;
public:
	Customer(std::string name = def_name, std::string phone = def_phone, int customerID = def_customerID, bool isVIP = def_isVIP);
	
	virtual void update_information(std::string, std::string) override;

	void createVIPAccount();

	virtual ~Customer() = default;
};