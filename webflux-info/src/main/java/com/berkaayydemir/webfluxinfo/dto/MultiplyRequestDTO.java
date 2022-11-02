package com.berkaayydemir.webfluxinfo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MultiplyRequestDTO {
    private int first;
    private int second;

    public MultiplyRequestDTO(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
