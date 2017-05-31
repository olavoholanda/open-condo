package com.opencondo.accountservice.model.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Put a description of the class here.
 *
 * @author: olavo
 */
@Getter
@Setter
public class Address {

    private boolean house;
    private String street;
    private Integer number;
    private Integer floor;

    @Override
    public String toString() {
        if(this.isHouse()){
            return String.format("Address[street=%s, number='%s']",
                    street, number);
        } else {
            return String.format(
                    "Address[floor=%s, number='%s']",
                    floor, number);
        }


    }
}
