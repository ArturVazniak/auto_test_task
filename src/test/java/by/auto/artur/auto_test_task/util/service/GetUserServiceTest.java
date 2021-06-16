package by.auto.artur.auto_test_task.util.service;

import by.auto.artur.entity.User;
import by.auto.artur.repository.UserRepository;
import by.auto.artur.service.implementation.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void whenGivenId_shouldReturnUser_ifFound() {
        User user = new User();
        user.setId(2L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User expected = userService.getUser(user.getId());
        assertThat(expected).isSameAs(user);
        verify(userRepository).findById(user.getId());
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() {
        User user = new User();
        user.setId(89L);
        user.setUsername("Test Name");
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        userService.getUser(user.getId());
    }
}
