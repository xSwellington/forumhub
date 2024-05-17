package dev.swellington.forumhub.domain.user;

import dev.swellington.forumhub.exception.UserAlreadyRegisteredWithEmailException;
import dev.swellington.forumhub.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(UserRegisterDto dto) {
        if (userRepository.existsByEmail(dto.email())) throw new UserAlreadyRegisteredWithEmailException();
        User user = User.builder()
                .id(null)
                .email(dto.email())
                .password(new BCryptPasswordEncoder().encode(dto.password()))
                .name(dto.name())
                .build();
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
}
