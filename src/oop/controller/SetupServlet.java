package oop.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.conf.Config;

/**
 * Servlet implementation class SetupServlet
 */
public class SetupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sqlPath = getServletContext().getRealPath("/WEB-INF/setup.sql");
		Config config = Config.get();
		if (new File(sqlPath).exists()) {
			String[] command = { config.getMysqlCommand(),
					config.getDatabaseName(), 
					"-u", config.getUserName(),
					"-p" + config.getPassword(), 
					"-e", "source " + sqlPath};
			Process process = new ProcessBuilder(command).start();
			try {
				pump(process.getInputStream(), response.getOutputStream());
				int status = process.waitFor();
				if (status == 0) {
					response.getOutputStream().println("OK!");
				} else {
					response.getOutputStream().println("Error: " + status);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(config.getHomeDir());
		}
	}

	private void pump(InputStream inp, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = inp.read(buffer)) > 0) {
			out.write(buffer, 0, read);
		}
	}

}
