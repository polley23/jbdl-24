package com.gfg.jdbl24minorproject2.entities;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorColumn(name = "designation")
@DiscriminatorValue(value = "Manager")
public class Manager extends Employee{
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "manager_id")
    private List<Employee> subordinates;

}
