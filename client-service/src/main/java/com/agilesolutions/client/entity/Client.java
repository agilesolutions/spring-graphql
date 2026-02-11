package com.agilesolutions.client.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "client")
public class Client extends BaseEntity {

    @Id
    @Column(value="id")
    private Long id;
    @Column(value="first_name")
    private String firstName;
    @Column(value="middle_name")
    private String middleName;
    @Column(value="last_name")
    private String lastName;
}