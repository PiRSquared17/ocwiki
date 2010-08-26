package oop.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sqlPath = getServletContext().getRealPath("/WEB-INF/setup.sql");
		Config config = Config.get();
		response.setContentType("text/html; charset=UTF-8");
		
		File sqlFile = new File(sqlPath);
		if (sqlFile.exists()) {
			String[] command = { config.getMysqlCommand(),
					"--database=" + config.getDatabaseName(), 
					"--verbose",
					"--user=" + config.getUserName(),
					"--password=" + config.getPassword(), 
					"--execute=source " + sqlFile.getName()};
			openDiv(response);
			Process process = new ProcessBuilder(command).directory(sqlFile.getParentFile()).start();
			int status = -1;
			try {
				pump(process.getInputStream(), response.getOutputStream());
				status = process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				closeDiv(response);
			}
			if (status == 0) {
				response.getOutputStream().println("<h3>Installation completed successful!</h3>");
			} else {
				response.getOutputStream().println("Error: " + status);
			}
		} else {
			response.sendRedirect(config.getHomeDir());
		}
	}

	private void closeDiv(HttpServletResponse response) throws IOException {
		response.getOutputStream().print("</pre>");
		response.getOutputStream().print("</div>");
	}

	private void openDiv(HttpServletResponse response) throws IOException {
		response.getOutputStream().print("<div style=" +
				"\"width: 100%; height: 400px; border: solid black 2px; overflow: scroll;\">");
		response.getOutputStream().print("<pre>");
	}

	private void pump(InputStream inp, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = inp.read(buffer)) > 0) {
			out.write(buffer, 0, read);
			out.flush();
		}
	}

}
