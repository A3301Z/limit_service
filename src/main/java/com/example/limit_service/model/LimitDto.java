package com.example.limit_service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Dto объекта лимита")
public class LimitDto {

    @Schema(description = "Идентоификатор пользователя")
    public long userId;

    @Schema(description = "Размер платежа")
    public long paymentAmount;

}
