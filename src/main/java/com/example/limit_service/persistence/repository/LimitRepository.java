package com.example.limit_service.persistence.repository;

import com.example.limit_service.persistence.entity.Limit;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitRepository extends CrudRepository<Limit, Long> {

    @Query("select * from limits where user_id = :userId")
    Optional<Limit> checkLimit(long userId);

    @Modifying
    @Query("update limits set day_limit = (day_limit - :paymentAmount) where user_id = :userID")
    void updateLimit(long userID, long paymentAmount);

    @Modifying
    @Query("update limits set day_limit = 10000")
    void resetLimit();
}
