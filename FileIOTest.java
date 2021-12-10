package FileHandler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

class FileIOTest {

	protected FileIO file;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
		@SuppressWarnings("unused")
		FileIO file = new FileIO();
	}

	@AfterEach
	void tearDown() throws Exception {
		file = null;
	}

	@Test
	void testFileIO() {
		try {
			@SuppressWarnings("unused")
			FileIO file = new FileIO();
		} catch (Exception e) {
			fail("FileIO constructor is broken");
		}
	}

	@Test
	void testCheckFileExists() {
		if (!file.checkFileExists()) {
			fail("LoginCredentials does not exist");
		}
	}

	@Test
	void testNewFile() {
		file.newFile("Test New File");
		// if fails, stack trace will be printed
	}

	@Test
	void testNewFileLoginCredentials() {
		
		// creates a new file called LoginCredentials.json
		file.newFileLoginCredentials();
	}

	@Test
	void testIsEmpty() {
		
		file.newFile("TestFile");
		try {
			boolean result = file.isEmpty("TestFile");
			if (!result) {
				fail("New File is not Empty");
			}
		} catch (FileNotFoundException e){
			fail("File Not Found");
		}
	}

	@Test
	void testNewUserLogin() {
		try {
			file.newUserLogin("username", "password", "Question?", "Answer");
		} catch (IOException e) {
			fail("IO Exception");
		}
	}

	@Test
	void testCheckLoginCredentialsExists() {
		try {
			file.newUserLogin("testUsername", "testPassword", "testQuestion?", "testAnswer");
			
			boolean result = file.checkLoginCredentials("testUsername", "testPassword");
			if (!result) {
				fail("Test Login Credentials not found");
			}
		} catch (IOException e) {
			fail("IO Exception");
		}
	}
	
	@Test
	void testCheckLoginCredentialsNotExists() {
		try {
			file.newUserLogin("testUsername", "testPassword", "testQuestion?", "testAnswer");
			
			boolean result = file.checkLoginCredentials("notUser", "notPassword");
			if (result) {
				fail("Test login credentials found and shouldn't be");
			}
		} catch (IOException e) {
			fail("IO Exception");
		}
	}

	@Test
	void testCheckUsername() {
		fail("Not yet implemented");
	}

	@Test
	void testAddYear() {
		
	}

	@Test
	void testAddMonth() {
		fail("Not yet implemented");
	}

	@Test
	void testLoad() {
		fail("Not yet implemented");
	}

	@Test
	void testAddCategory() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBudget() {
		fail("Not yet implemented");
	}

	@Test
	void testSetBudget() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTotalExpenses() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTotalTransactions() {
		fail("Not yet implemented");
	}

	@Test
	void testAddTransaction() {
		fail("Not yet implemented");
	}

	@Test
	void testDelTransaction() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllTransactions() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCategoryTransactions() {
		fail("Not yet implemented");
	}

}
