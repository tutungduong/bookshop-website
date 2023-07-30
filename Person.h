#pragma once
#include <iostream>
#include <string>
class Person
{
	friend std::ostream &operator<<(std::ostream &os, const Person &person);
private:
	static constexpr const char *def_name = "Unnamed";
	static constexpr const char *def_address = "Unadress";
	static constexpr const char *def_phone = "Unphone";
	static constexpr const char *def_email = "Unemail";
protected:
	std::string name;
	std::string address;
	std::string phone;
	std::string email;

public:
	//Constructor
	Person(std::string name = def_name, std::string phone = def_phone, std::string address = def_address, std::string email = def_email);
	// Copy Constructor
	Person(const Person &source); 
	// Update information
	void update_information(std::string, std::string, std::string, std::string);
	// Default
	void set_name(std::string name) {
		this->name = name;
	}
	void set_phone(std::string phone) {
		this->phone = phone;
	}
	void set_address(std::string address) {
		this->address = address;
	}
	void set_email(std::string email) {
		this->email = email;
	}
	std::string get_name() const {
		return name;
	}
	std::string get_phone() const {
		return phone;
	}
	//Destructor
	~Person();
};

