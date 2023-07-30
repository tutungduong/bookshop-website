#include <iostream>
#include "Management.h"
#include <vector>

using namespace std;

int main() {
	cout.precision(2);
	cout << fixed;
	std::vector<Customer> cus;
	cus.push_back(Customer{});
	cus.push_back(Customer{});
	cus.push_back(Customer{});
	cus.push_back(Customer{});
	cus.push_back(Customer{});
	cus[2].update_information("A", "B", "C", "D");
	display(cus);
}