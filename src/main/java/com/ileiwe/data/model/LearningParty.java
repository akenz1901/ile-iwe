package com.ileiwe.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @NotNull @NotBlank
    private String email;
    @Column(unique = true, nullable = false)
    @NotNull @NotBlank
    private String password;
    private boolean enabled;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Authority> authorities;

    public LearningParty(String email, String password, Authority authority){
//        if(email.strip().isEmpty() || password.strip().isEmpty()){
//            throw new IllegalArgumentException("email and password is required");
//        }
        this.email = email;
        this.password = password;
        addAuthority(authority);
        this.enabled = false;
    }

    public void addAuthority(Authority authority){
        if(this.authorities == null){
            this.authorities = new ArrayList<>();
        }
        this.authorities.add(authority);
    }
}
