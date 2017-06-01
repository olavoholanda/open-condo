package com.opencondo.accountservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Put a description of the class here.
 *
 * @author: olavo
 */
@Getter
@Setter
public class Address {

    @NotNull(message = "Must know if this address belong to a house or not")
    private boolean house;

    private String street;

    @NotNull(message = "House or Apt number can not be null")
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
