#include "Management.h"

void display(const std::vector<Customer> &accounts) {
	std::cout << "\n=== Person ==========================================" << std::endl;
	for (const auto &acc : accounts)
		std::cout << acc << std::endl;
}