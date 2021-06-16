package by.auto.artur.auto_test_task.util.service;

import by.auto.artur.entity.User;
import by.auto.artur.repository.UserRepository;
import by.auto.artur.service.implementation.UserServiceImpl;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetAllUsersServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void saveUsersTest() {
        List<User> users = new ArrayList();
        users.add(new User());
        given(userRepository.findAll()).willReturn(users);
        List<User> expected = userService.getAllUsers();
        assertEquals(expected, users);
        verify(userRepository).findAll();

    }
}
