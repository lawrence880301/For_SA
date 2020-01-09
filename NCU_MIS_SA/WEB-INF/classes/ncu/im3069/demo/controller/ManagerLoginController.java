package ncu.im3069.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import ncu.im3069.demo.app.ManagerHelper;
import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.MemberHelper;
import ncu.im3069.tools.JsonReader;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/api/ManagerLogin.do")
public class ManagerLoginController extends HttpServlet {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private ManagerHelper mh =  ManagerHelper.getHelper();       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		email不為空則代表要藉由email取的會員資料並登入
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        String email = jso.getString("email");
        String pwd= jso.getString("pwd");



		JSONObject query = mh.getByEmail(email,pwd);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "該會員資料取得成功");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);	
	}

}
