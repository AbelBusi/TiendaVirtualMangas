package com.example.wbm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona", nullable = false)
    private Persona persona;

    @Column(name = "nombre",nullable = false,unique = true,length = 45)
    private String nombre;

    @Column(name = "correo",nullable = false,unique = true,length = 45)
    private String correo;

    @Column(name = "password",nullable = false,length = 300)
    private String password;

    @CreationTimestamp
    @Column(name = "fecha_creacion",nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "estado",nullable = false)
    private Integer estado;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    private List<Perfil> perfiles;

    //Se devolvera un estado true o false si es que se encuentra el rol del usuario
    public boolean hasRole(String roleName) {

        Iterator<Perfil> iterator=this.perfiles.iterator();

        while (iterator.hasNext()) {
            Perfil role= iterator.next();
            if(role.getRol().getNombre().equals(roleName)) {
                return true;
            }
        }

        return false;

    }

}
