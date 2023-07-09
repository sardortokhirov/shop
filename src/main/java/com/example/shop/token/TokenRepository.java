package com.example.shop.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Date-7/5/2023
 * Time-9:36 AM
 */
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
            select t from Token t inner join Supplier s on t.supplier.id = s.id where s.id = :supplierId and (t.expired =false or t.revoked =false)
            """)
    List<Token> findByValidTokensBySupplier(Integer supplierId);

    Optional<Token> findByToken(String token);
}
