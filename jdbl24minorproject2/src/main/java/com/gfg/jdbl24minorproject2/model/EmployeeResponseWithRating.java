package com.gfg.jdbl24minorproject2.model;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EmployeeResponseWithRating extends EmployeeResponse{
    private Integer rating;
}
