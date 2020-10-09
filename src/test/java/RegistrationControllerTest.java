import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.projectOOD3.controller.RegistrationController;
import com.fdmgroup.projectOOD3.model.FileReadCommand;
import com.fdmgroup.projectOOD3.model.UserFactory;
import com.fdmgroup.projectOOD3.model.WriteCommand;
import com.fdmgroup.projectOOD3.model.FileWriteCommand;
import com.fdmgroup.projectOOD3.model.ReadCommand;

public class RegistrationControllerTest {

	private ReadCommand mockReadCom;
	private WriteCommand mockWriteCom;
	private UserFactory mockUserFac;
	private RegistrationController regCon;
	@Before
	public void init() {
		mockReadCom = mock(FileReadCommand.class);
		mockWriteCom = mock(FileWriteCommand.class);
		mockUserFac = mock(UserFactory.class);
		regCon = new RegistrationController(mockReadCom,mockWriteCom,mockUserFac);
	}
	@Test
	public void test_registerNewUserCallsCreateUser() {
		regCon.registerNewUser("Victor", "Pass", "Student");
		verify(mockUserFac).createUser("Victor", "Pass", "Student");
	}
	@Test
	public void test_registerNewUserCallsWriteUser() {
		regCon.registerNewUser("Victor", "Pass", "Student");
		verify(mockWriteCom).writeUser(mockUserFac.createUser("Victor", "Pass", "Student"));
	}

	
}
