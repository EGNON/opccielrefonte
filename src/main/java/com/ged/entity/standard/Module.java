package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.security.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Module", schema = "Parametre")
@Getter
@Setter
public class Module extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq")
    private Long idModule;
    @Column(length = 8000)
    private String nomModule;
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();
}
