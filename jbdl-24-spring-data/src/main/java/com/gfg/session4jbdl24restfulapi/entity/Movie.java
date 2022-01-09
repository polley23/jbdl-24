package com.gfg.session4jbdl24restfulapi.entity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/*
Many to Many - 1 student have N teacher. and 1 teacher has N students.

Many to One -(emplyee to manager) 1 emploeey reports to 1 manger and 1 manager has N reportees

One to Many - (manager to employee)

One to One - 1 citizen to 1 adhaar card number / social security number

*/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "movies")
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name = "movie_name", nullable = false, length = 1024)
    private String name;
    private Integer rating;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Cast> castList;
}
