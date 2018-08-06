package net.amygdalum.tanteemmas.testutils;

import java.io.IOException;
import java.net.ServerSocket;

public class TestHelper {
	public static final int generateRandomPort() {
		try (ServerSocket socket = new ServerSocket(0)){
			return socket.getLocalPort();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
