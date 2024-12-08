package com.example.limit_service.persistence.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "limits")
public class Limit {

    /**
     * Идентификатор пользователя
     */
    @Column("user_id")
    private String userId;

    /**
     * Дневной лимит
     */
    @Column("day_limit")
    private long dayLimit;
}
