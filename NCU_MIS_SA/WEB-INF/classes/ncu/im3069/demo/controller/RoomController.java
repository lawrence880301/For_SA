package ncu.im3069.demo.controller;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.*;
import ncu.im3069.demo.app.Room;
import ncu.im3069.demo.app.RoomHelper;
import ncu.im3069.demo.app.JoinedRoomList;
import ncu.im3069.demo.app.JoinedRoomListHelper;
import ncu.im3069.tools.JsonReader;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class RoomController<br>
 * RoomController類別（class）主要用於處理Room相關之Http請求（Request），繼承HttpServlet
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */
@WebServlet("/api/room.do")
public class RoomController extends HttpServlet {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** mh，RoomHelper之物件與Member相關之資料庫方法（Sigleton） */
    private RoomHelper rh =  RoomHelper.getHelper();
    private JoinedRoomListHelper jrlh =  JoinedRoomListHelper.getHelper();
    
    /**
     * 處理Http Method請求POST方法（新增資料）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
//        int Id_member =jso.getInt("Id_member") ;
        int Id_member =jso.getInt("ID");/*LOCALSTORAGE*/     
        String Name = jso.getString("Name");
        String Place = jso.getString("Place");
        String Type = jso.getString("Type");
        String Maxmember = jso.getString("Maxmember");
        String GenderRestriction = jso.getString("GenderRestriction");
        String AgeUpperlimit = jso.getString("AgeUpperlimit");
        String AgeLowerlimit = jso.getString("AgeLowerlimit");
        
        /** Date字串轉成日期的方法*/
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date Date = null;
        try {
        	Date = (Date)formatter.parse(jso.getString("Date"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	
        /** 建立一個新的房間物件 */
        Room r = new Room(Name, Date, Place, Type, Maxmember, GenderRestriction, AgeUpperlimit, AgeLowerlimit);
        JoinedRoomList jrl = new JoinedRoomList(Id_member,Name ,Date ,Type ,Place);
//        System.out.println(Name);
        
        /** 後端檢查是否有欄位為空值，若有則回傳錯誤訊息 */
        if(Name.isEmpty() || Place.isEmpty() || Type.isEmpty() || Maxmember.isEmpty() 
        		|| GenderRestriction.isEmpty() || AgeUpperlimit.isEmpty() || AgeLowerlimit.isEmpty() ) 
        {
            /** 以字串組出JSON格式之資料 */
            String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
            jsr.response(resp, response);
        }
//        /** 透過MemberHelper物件的checkDuplicate()檢查該會員電子郵件信箱是否有重複 */
//        else if (!rh.checkDuplicate(r)) {
//        	
//        }
        
            /** 透過RoomHelper.JoinedRoomListHelper物件的create()方法新建一筆資料至資料庫 */
            JSONObject data = rh.create(r); /*丟進房間資料庫建檔*/ 
            JSONObject data2 = jrlh.create(jrl); /*丟進已加入房間資料庫建檔*/
//            System.out.println(123);
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "成功! 建立房間資料...");
            resp.put("response", data);
            resp.put("response", data2);
//            resp.put("jrl-response", data2);
            
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
//        else {
//            /** 以字串組出JSON格式之資料 */
//            String resp = "{\"status\": \'400\', \"message\": \'新增帳號失敗，此E-Mail帳號重複！\', \'response\': \'\'}";
//            /** 透過JsonReader物件回傳到前端（以字串方式） */
//            jsr.response(resp, response);
//        }
    

    /**
     * 處理Http Method請求GET方法（取得資料）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        String id = jsr.getParameter("id");
     
        /** 判斷該字串是否存在，若存在代表要取回個別房間之資料，否則代表要取回全部資料庫內房間之資料 */
        if (id.isEmpty()) {
            /** 透過RoomHelper物件之getAll()方法取回所有房間之資料，回傳之資料為JSONObject物件 */
//        	和資料庫相關的動作都在RoomHelper裡面做
            JSONObject query = rh.getAll();
          
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "所有會員資料取得成功");
            resp.put("response", query);
    
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
        else {
            /** 透過RoomHelper物件的getByID()方法自資料庫取回該房間之資料，回傳之資料為JSONObject物件 */
            JSONObject query = rh.getByID(id);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "會員資料取得成功");
            resp.put("response", query);
    
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
    }

    /**
     * 處理Http Method請求DELETE方法（刪除）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int id = jso.getInt("id");
        
        /** 透過RoomHelper物件的deleteByID()方法至資料庫刪除該名會員，回傳之資料為JSONObject物件 */
        JSONObject query = rh.deleteByID(id);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "會員移除成功！");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }

    /**
     * 處理Http Method請求PUT方法（更新）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
//    public void doPut(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
//        JsonReader jsr = new JsonReader(request);
//        JSONObject jso = jsr.getObject();
//        
//        /** 取出經解析到JSONObject之Request參數 */
//        int Id = jso.getInt("Id");
//        String Name = jso.getString("Name");
//        String Place = jso.getString("Place");
//        String Type = jso.getString("Type");
//        String Maxmember = jso.getString("Maxmember");
//        String GenderRestriction = jso.getString("GenderRestriction");
//        String AgeUpperlimit = jso.getString("AgeUpperlimit");
//        String AgeLowerlimit = jso.getString("AgeLowerlimit");
//        String Description = jso.getString("Description");
//
//        /** Date字串轉成日期的方法*/
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
//        Date Date = null;
//        try {
//        	Date = (Date)formatter.parse(jso.getString("Date"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        /** Createtime 字串轉成日期的方法 */
//        Date Createtime = null;
//		try {
//			Createtime = (Date)formatter.parse(jso.getString("Createtime"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//		
//        /** 透過傳入之參數，新建一個以這些參數之會員Room物件 */
//        Room r = new Room(Id, Name, Date, Place, Type, Createtime, Maxmember, GenderRestriction, 
//				  AgeUpperlimit, AgeLowerlimit, Description);
//        
//        /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
//        JSONObject data = r.update();
//        
//        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
//        JSONObject resp = new JSONObject();
//        resp.put("status", "200");
//        resp.put("message", "成功! 更新房間資料...");
//        resp.put("response", data);
//        
//        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
//        jsr.response(resp, response);
//    }
}