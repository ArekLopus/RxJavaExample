package abc;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestIT {

	@Before
	public void init() {
	}
	@After
	public void clean() {
	}

	
	@Test
	public void test() {
		assertThat(1, is(1));
	}
	
	

}
