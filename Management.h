#pragma once
#include <vector>
#include "Customer.h"
#include <iostream>
// Utility helper functions for Customer class
void display(const std::vector<Customer> &customer);
void update_information(std::vector<Customer> &customer, std::string, std::string, std::string, std::string);
