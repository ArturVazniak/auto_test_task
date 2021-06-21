package by.auto.artur.auto_test_task.util.service;

import by.auto.artur.entity.User;
import by.auto.artur.repository.UserRepository;
import by.auto.artur.service.implementation.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

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
    public void when_user_doesnt_exist() {
        User user = new User();
        user.setId(89L);
        user.setUsername("Test Name");
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        userService.deleteUser(user.getId());
    }

    @Test
    public void getUsersTest() {
        List<User> users = new ArrayList();
        users.add(new User());
        given(userRepository.findAll()).willReturn(users);
        List<User> expected = userService.getAllUsers();
        assertEquals(expected, users);
        verify(userRepository).findAll();

    }

    @Test
    public void whenGivenId_shouldReturnUser_ifFound() {
        User user = new User();
        user.setId(2L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User expected = userService.getUser(user.getId());
        Assertions.assertThat(expected).isSameAs(user);
        verify(userRepository).findById(user.getId());
    }

}
