package dev.swellington.forumhub;

import dev.swellington.forumhub.domain.user.User;
import dev.swellington.forumhub.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryFunctionTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        entityManager.getEntityManager().createNativeQuery("ALTER TABLE `users` AUTO_INCREMENT=1").executeUpdate();

        User user = User.builder()
                .id(null)
                .email("swellington.santos@gmail.com")
                .name("Swellington Soares")
                .password("hello")
                .build();
        entityManager.persist(user);
        entityManager.flush();
    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }


    @Test
    public void testIfUserSavedCorrectlyAndReturnThisFromDatabase() {
        User userFound = userRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(userFound);
        Assertions.assertEquals("swellington.santos@gmail.com", userFound.getEmail());
    }

    @Test
    @Transactional
    public void testSoftDeleteAction() {
        User user = userRepository.getReferenceById(1L);
        Assertions.assertNotNull(user);
        user.setAsInactive(true);
        User dbuser = userRepository.getReferenceById(1L);
        Assertions.assertFalse(dbuser.isEnabled());
    }


    @Test
    @Transactional
    public void testHardDeleteAction(){
        User user = userRepository.getReferenceById(1L);
        Assertions.assertNotNull(user);
        userRepository.delete(user);
        user = userRepository.findById(1L).orElse(null);
        Assertions.assertNull(user);

    }

}
