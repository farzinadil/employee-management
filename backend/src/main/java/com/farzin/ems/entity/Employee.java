package com.farzin.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Getter/@Setter are Lombok annotations that generate getter/setter methods at compile time.
@Getter
@Setter

// Required by JPA. Hibernate needs a no-argument constructor to create entity objects.
@NoArgsConstructor

// Useful for quickly creating Employee objects with all fields.
@AllArgsConstructor

// @Entity tells Spring Data JPA/Hibernate that this class maps to a database table.
@Entity

// @Table lets us explicitly name the table. If omitted, Hibernate would use the class name.
@Table(name = "employees")
public class Employee {

    // @Id marks this field as the primary key.
    @Id

    // IDENTITY means MySQL will auto-generate the primary key using auto-increment.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column maps this Java field to a specific database column.
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;
}
