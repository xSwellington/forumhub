package dev.swellington.forumhub.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByNameInIgnoreCase(Collection<String> names);
}