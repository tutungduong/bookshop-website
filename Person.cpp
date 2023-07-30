#include "Person.h"
#include <iostream>
// Constructor
Person::Person(std::string name, std::string phone, std::string address, std::string email)
	:name{ name }, phone{ phone }, address{ address }, email{ email }{}

// Copy Constructor
Person::Person(const Person &source)
	   :Person{source.name,source.phone,source.address,source.email}{}


// Update information method
void Person::update_information(std::string name, std::string phone, std::string address, std::string email) {
	set_name(name);
	set_phone(phone);
	set_address(address);
	set_email(email);
}

// Display Operator
std::ostream &operator<<(std::ostream &os, const Person &person) {
	os << "[Person: " << person.name << " - " << person.phone << " - " << person.address << " - " << person.email << "]";
	return os;
}

Person::~Person(){}
		