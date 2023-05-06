package br.com.estudos.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class RegisterApplicationTests {

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserService userService;

	@Test
	void should_register_user_successfully() {
		User userBeforeSave = new User("Rodolpho", "1234", LocalDate.of(1978, 8, 12));
		User userAfterSave = userBeforeSave;
		userAfterSave.setId("1");
		Mockito.when(userRepository.save(userBeforeSave)).thenReturn(userAfterSave);

		var user = userService.register(userBeforeSave);

		Mockito.verify(userRepository, Mockito.times(1)).save(userBeforeSave);
		Assertions.assertEquals(userAfterSave, user);
	}

}
