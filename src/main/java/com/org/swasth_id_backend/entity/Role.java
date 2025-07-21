package com.org.swasth_id_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "roles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{

    private String role;

}
