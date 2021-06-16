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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void whenGivenId_shouldDeleteUser_ifFound(){
        User user = new User();
        user.setUsername("Test Name");
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.deleteUser(user.getId());
        verify(userRepository).deleteById(user.getId());
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() {
        User user = new User();
        user.setId(89L);
        user.setUsername("Test Name");
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        userService.deleteUser(user.getId());
    }
}
