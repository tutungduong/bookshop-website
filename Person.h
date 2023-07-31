#pragma once
#include <iostream>
#include <string>
class Person
{
private:
	static constexpr const char *def_name = "Non-existent";
	static constexpr const char *def_phone = "Non-existent";
protected:
	std::string name;
	std::string phone;
public:
	Person(std::string name = def_name, std::string phone = def_phone);

};

