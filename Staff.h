#pragma once
#include <string>
class Staff
{
private:
	static constexpr const char *def_name = "Noname";
	static constexpr const char *def_position = "NoPosition";
	static constexpr const char *def_phone = "NoPhone";
	static constexpr const char *def_email = "NoEmail";
protected:
	std::string name;
	std::string position;
	std::string phone;
	std::string email;
	int staff_ID;
public:
	Staff(std::string name = def_name, std::string position = def_position, std::string phone = def_phone, std::string email = def_email);
	~Staff();
};

