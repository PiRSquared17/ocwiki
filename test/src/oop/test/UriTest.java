package oop.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Test;

public class UriTest {

	@Test
	public void actionUri() throws IOException {
		URL url = new URL("http://localhost:8080/ocwiki/action/article.print?id=93");
		InputStream stream = url.openStream();
		InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[1024];
		int read = 0;
		while ((read = reader.read(buffer)) > 0) {
			System.out.println(new String(buffer, 0, read));
		}
		stream.close();
	}
	
}
