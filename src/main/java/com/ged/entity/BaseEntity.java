package com.ged.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BaseEntity {
    @Id
    private Long id;
}
