package com.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

        private int customerNumber;
        private String customerName;
        private String contactLastName;
        private String contactFirstName;
        private String phone;
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private int salesRepEmployeeNumber;
        private double creditLimit;

}
