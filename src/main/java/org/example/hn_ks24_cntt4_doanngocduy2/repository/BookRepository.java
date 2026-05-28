package org.example.hn_ks24_cntt4_doanngocduy2.repository;

import org.example.hn_ks24_cntt4_doanngocduy2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
