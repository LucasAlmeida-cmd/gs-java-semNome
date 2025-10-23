package com.example.gs_java.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
@PrimaryKeyJoinColumn(name = "id_admin", referencedColumnName = "id")
@Table(name = "tb_user_admin_gs_2sem")
public class Administrador extends User{

    @Column(name = "codigo_admin", unique = true)
    private String codigo;

}