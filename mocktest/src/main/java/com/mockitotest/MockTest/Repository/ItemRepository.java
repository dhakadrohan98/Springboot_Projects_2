package com.mockitotest.MockTest.Repository;

import com.mockitotest.MockTest.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public Item findByNameIgnoreCase(String name);
}
