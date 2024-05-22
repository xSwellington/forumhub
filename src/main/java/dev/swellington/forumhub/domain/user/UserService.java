package dev.swellington.forumhub.domain.user;

import dev.swellington.forumhub.exception.UserAlreadyRegisteredWithEmailException;
import dev.swellington.forumhub.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import dev.swellington.forumhub.domain.role.RoleRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public User createUser(UserRegisterDto dto) {
        if (userRepository.existsByEmail(dto.email())) throw new UserAlreadyRegisteredWithEmailException();

        var defaultRoles = List.of(
                "TOPIC_CREATE",
                "TOPIC_EDIT",
                "TOPIC_DELETE",
                "TOPIC_SHOW",
                "TOPIC_LIST",
                "TOPIC_UPDATE"
        );

        val rolesIds = roleRepository.findByNameInIgnoreCase(defaultRoles);


        User user = User.builder()
                .id(null)
                .email(dto.email())
                .password(new BCryptPasswordEncoder().encode(dto.password()))
                .name(dto.name())
                .build();

        user.getRoles().addAll(rolesIds);

        return userRepository.save(user);
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAllByDeletedAtIsNull(pageable);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User updateUser(Long id, UserUpdateDto dto) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setName(dto.name());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setAsInactive(true);
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }
}
