package com.lee.myproject.dto;

import com.lee.myproject.entity.Member;
import com.lee.myproject.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private String str;

    public OrderDto() {

    }

    public OrderDto(Long id, String srt) {
        this.id = id;
        this.str = srt;

    }

    public static OrderDto form(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setStr(order.getStr());
        return orderDto;
    }


}
