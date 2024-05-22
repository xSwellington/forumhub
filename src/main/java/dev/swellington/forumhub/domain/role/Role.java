package dev.swellington.forumhub.domain.role;

import dev.swellington.forumhub.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.beans.ConstructorProperties;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    @Builder.Default
    Set<User> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}