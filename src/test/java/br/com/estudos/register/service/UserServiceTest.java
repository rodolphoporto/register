package br.com.estudos.register.service;

import br.com.estudos.register.domain.User;
import br.com.estudos.register.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserService userService;

	@Test
	void should_register_user_successfully() {
		User userBeforeSave = new User(null, "Rodolpho", "1234", LocalDate.of(1978, 8, 12));
		User userAfterSave = userBeforeSave;
		userAfterSave.setId("1");
		Mockito.when(userRepository.save(userBeforeSave)).thenReturn(userAfterSave);

		var user = userService.register(userBeforeSave);

		Mockito.verify(userRepository, Mockito.times(1)).save(userBeforeSave);
		Assertions.assertEquals(userAfterSave, user);
	}

	@Test
	public void should_return_error_when_age_is_less_then_eighteen() {
		User user = new User(null, "Rodolpho", "1234", LocalDate.of(2010, 8, 12));

		var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.register(user));

//		Mockito.verify(userRepository, Mockito.never()).save(user);
		Mockito.verifyNoInteractions(userRepository);
		Assertions.assertEquals("Idade não permitida", exception.getMessage());
	}

	@Test
	public void should_return_error_when_age_is_granter_then_sixty() {
		User user = new User(null, "Rodolpho", "1234", LocalDate.of(1950, 8, 12));

		var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.register(user));

		Mockito.verifyNoInteractions(userRepository);
		Assertions.assertEquals("Idade não permitida", exception.getMessage());
	}

	@ParameterizedTest
	@ValueSource(ints = { 2010, 1950 })
	public void should_return_error_when_age_is_invalid(Integer year) {
		User user = new User(null, "Rodolpho", "1234", LocalDate.of(year, 8, 12));

		var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.register(user));

		Mockito.verifyNoInteractions(userRepository);
		Assertions.assertEquals("Idade não permitida", exception.getMessage());
	}

}
