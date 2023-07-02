package com.example.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Date-5/8/2023
 * Time-7:09 AM
 */
@Entity
@Table(name = "supplier")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supplier implements UserDetails {
    @Id
    @SequenceGenerator(name = "supplier_sequence", sequenceName = "supplier_sequence", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_sequence")
    @Column(name = "supplier_id")
    private Long id;
    @Size(min = 2, max = 50, message = "firstName have to be 2 < x < 50")
    @Column(name = "first_name")
    private String firstName;
    @Size(min = 2, max = 50, message = "last_name have to be 2 < x < 50")
    @Column(name = "last_name")
    private String lastName;
    @Email(message = "email not valid")
    private String email;
    @NotEmpty
    @Column(name = "card_number")
    private String cardNumber;

    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private List<Product> products;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
