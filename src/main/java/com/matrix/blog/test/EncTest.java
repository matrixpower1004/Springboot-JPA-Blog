package com.matrix.blog.test;

import org.apache.tomcat.websocket.WsRemoteEndpointAsync;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
	
	@Test
	public void hash_encryption() {
		String encPassword = new BCryptPasswordEncoder().encode("1234");
		System.out.println("1234 해쉬 : " + encPassword);
	}
}
