#include "Management.h"

void display(const std::vector<Customer> &accounts) {
	std::cout << "\n=== Person ==========================================" << std::endl;
	for (const auto &acc : accounts)
		std::cout << acc << std::endl;
}

void update_information(std::vector<Customer> &customer, std::string name, std::string phone, std::string address, std::string email) {
	for (auto &cus : customer) {
		if (cus.update_information(name, phone, address, email)) {
			std::cout << "Chua ton tai va da update" << std::endl;
		}
		else {
			std::cout << "Da ton tai" << std::endl;
		}
	}
}
