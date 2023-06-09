package com.example.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/**
 * Date-5/8/2023
 * Time-7:26 AM
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(name = "customer_id")
    private Long id;
    @Size(min = 2, max = 50, message = "firstName have to be 2< x <50")
    @Column(name = "first_name")
    private String firstName;
    @Size(min = 2, max = 50, message = "lastName have to be 2< x <50")
    @Column(name = "last_name")
    private String lastName;
    @Email(message = "email not valid")
    private String email;
    @Size(min = 7, max = 150)
    private String address;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
