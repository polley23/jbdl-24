package com.gfg.jdbl24minorproject2.entities;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorColumn(name = "designation")
@DiscriminatorValue(value = "HR")
public class Hr extends Employee{
}
