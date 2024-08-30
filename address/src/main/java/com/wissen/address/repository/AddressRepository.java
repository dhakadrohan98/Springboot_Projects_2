package com.wissen.address.repository;

import com.wissen.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT a.id, a.city, a.state FROM address a " +
                    "join employee e on e.id = a.employee_id where a.employee_id=:employeeId")
    Optional<Address> findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
