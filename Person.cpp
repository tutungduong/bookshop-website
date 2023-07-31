#include "Person.h"

Person::Person(std::string name, std::string phone):
	name{name},phone{phone}{}


void Person::update_information(std::string name, std::string phone) {
	set_name(name);
	set_phone(phone);
}

		