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
	// Method update information for Class Person use pure virtual function
	virtual void update_information(std::string, std::string) = 0;

	void set_name(std::string name) {
		this->name = name;
	}

	void set_phone(std::string phone) {
		this->phone = phone;
	}

	std::string get_name(std::string name) {
		return name;
	}
	virtual ~Person() = default;
};

