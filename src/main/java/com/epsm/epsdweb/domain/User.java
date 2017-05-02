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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
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
