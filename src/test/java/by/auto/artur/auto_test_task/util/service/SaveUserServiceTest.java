package by.auto.artur.auto_test_task.util.service;

import by.auto.artur.entity.User;
import by.auto.artur.repository.UserRepository;
import by.auto.artur.service.implementation.UserServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaveUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void saveUsersTest() {
        User user = new User();
        user.setUsername("Test Name");
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User created = userService.saveUser(user);
        assertThat(created.getUsername()).isSameAs(user.getUsername());
        verify(userRepository).save(user);

    }
}

