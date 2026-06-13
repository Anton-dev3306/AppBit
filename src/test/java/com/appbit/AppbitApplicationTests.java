package com.appbit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppbitApplicationTests {

	@Test
	@Disabled("Requiere infraestructura completa — DB, Redis, Flowise")
	void contextLoads() {
	}

}
