package com.epsm.epsdweb.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="users")
public class User implements Serializable {

	@Id
    @Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_user_id_gen")
	@SequenceGenerator(name = "users_user_id_gen", sequenceName = "users_user_id_seq", allocationSize = 1)
    private long userId;

	@NotEmpty
    private String name;

	@NotEmpty
    private String password;
    
	@NotEmpty
    private String email;

	@NotEmpty
    private String role;
}
