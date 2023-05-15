package com.example.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Date-5/8/2023
 * Time-7:09 AM
 */
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @SequenceGenerator(name = "supplier_sequence", sequenceName = "supplier_sequence", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_sequence")
    @Column(name = "supplier_id")
    private Long id;
    @Size(min = 2, max = 50, message = "firstName have to be 2< x <50")
    @Column(name = "first_name")
    private String firstName;
    @Size(min = 2, max = 50, message = "last_name have to be 2< x <50")
    @Column(name = "last_name")
    private String lastName;
    @Email(message = "email not valid")
    private String email;
    @NotEmpty
    @Column(name = "card_number")
    private String cardNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private List<Product> products;

    public Supplier() {
    }


    public Supplier(String firstName, String lastName, String email, String cardNumber, List<Product> products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cardNumber = cardNumber;
        this.products = products;
    }


    public Supplier(String firstName, String lastName, String email, String cardNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cardNumber = cardNumber;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
    }
}
