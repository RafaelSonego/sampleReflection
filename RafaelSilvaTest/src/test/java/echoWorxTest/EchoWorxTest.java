package echoWorxTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rafael.echoWorx.ArgumentFactory;

public class EchoWorxTest {
	private List<String> linesInstructions = null;
	
	@Before
	public void openFile() {
		try {
			linesInstructions = new ArgumentFactory().getInstructions("teste.txt");
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void testSample() {
		try {
			ArgumentFactory.calculateValueArguments(linesInstructions);
			System.out.println(ArgumentFactory.getRegisterA());
			System.out.println(ArgumentFactory.getRegisterB());
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
}
