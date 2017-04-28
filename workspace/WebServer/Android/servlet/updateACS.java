package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Dao.ACSDao;

@WebServlet("/updateACS")
public class updateACS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateACS() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		Writer out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		String Object = request.getParameter("Object");
		if (Object != null) {
			try {
				System.out.println("**updateACS**");
				JSONObject jsonObject = new JSONObject(Object);
				JSONObject info = jsonObject.getJSONObject("info");
				System.out.println("ID:" + jsonObject.get("ID"));
				String ID = jsonObject.getString("ID");
				System.out.println("type:" + jsonObject.get("type"));
				ACSDao dao = new ACSDao();
				if (jsonObject.get("type").equals("reset"))// 复位
				{
					System.out.println("reset:" + info.get("reset"));
					if (info.get("reset").equals("1")) {
						out.write(dao.reset(ID,1) + "");
					} else if (info.get("reset").equals("0")) {
						out.write(dao.reset(ID,0) + "");
					} else
						out.write("0");
				} else if (jsonObject.get("type").equals("rename"))// 更名
				{
					System.out.println("rename:" + info.get("rename"));
					String name = info.getString("rename");
					out.write(dao.rename(ID, name) + "");
				} else {
					out.write("0");
				}

			} catch (Exception e) {
				System.out.println("!!updateIbeacon--error");
				out.write("0");
			}
			out.close();
		}
	}
}
