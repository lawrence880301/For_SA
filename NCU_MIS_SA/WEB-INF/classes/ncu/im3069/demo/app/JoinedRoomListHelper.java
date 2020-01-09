
package ncu.im3069.demo.app;

import java.sql.*;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.json.*;

import ncu.im3069.demo.util.DBMgr;

public class JoinedRoomListHelper {
    
    private JoinedRoomListHelper() {
        
    }
    
    private static JoinedRoomListHelper jrlh;
    private Connection conn = null;
    private PreparedStatement pres = null;
    
    
    public static JoinedRoomListHelper getHelper() {
        /** Singleton檢查是否已經有MemberHelper物件，若無則new一個，若有則直接回傳 */
        if(jrlh == null) jrlh = new JoinedRoomListHelper();
        
        return jrlh;
    }
    /**
     * 靜態方法<br>
     * 實作Singleton（單例模式），僅允許建立一個MemberHelper物件
     *
     * @return the helper 回傳MemberHelper物件
     */
    public JSONObject getAll(int IN_Id_member) {
        /** 新建一個 Room 物件之 r 變數，用於紀錄每一位查詢回之房間資料 */
    	JoinedRoomList jrl = null;
        /** 用於儲存所有檢索回之房間，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `missa`.`joinedroomlist` WHERE `Id_member` = ? ;";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, IN_Id_member);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
//                int Id = rs.getInt("Id");
                
                int Id_room = rs.getInt("Id_room");
                String Name = rs.getString("Name");
                Date Date = rs.getDate("Date");
                String Type = rs.getString("Type");
                String Place = rs.getString("Place");
               
                /** 將每一筆會員資料產生一名新Member物件 */
                jrl = new JoinedRoomList(Id_room, Name, Date, Type, Place);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
//                System.out.println(Id_room);
                jsa.put(jrl.getData());
            }

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    public JSONObject deleteByID(int Id_room) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "DELETE FROM `missa`.`joinedroomlist` WHERE `Id_room` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, Id_room);
            /** 執行刪除之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }

        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);

        return response;
    }
    
    public JSONObject create(JoinedRoomList jrl) {
        JSONObject response = new JSONObject();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
//        for(int i=0 ; i < orderproduct.size() ; i++) {
//            OrderItem op = orderproduct.get(i);
            
            /** 取得所需之參數 */
        	int Id_member = jrl.getId_member();/*localstorage*/
        	int Id_room = jrl.getId_room();/*固定值 僅供當作FK欄位(?)*/
            String Name = jrl.getName();
            Date Date= jrl.getDate();
            String Place = jrl.getPlace();
            String Type = jrl.getType();
//            Date JoinedDate = jrl.getJoinedDate();
            java.sql.Date DateSQL = new java.sql.Date(Date.getTime());
            
            try {
                /** 取得資料庫之連線 */
                conn = DBMgr.getConnection();
                /** SQL指令 */
                String sql = "INSERT INTO `missa`.`joinedroomlist`(`Id_member`, `Id_room`, `Name`"
                		+ ", `Date`, `Place`, `Type`)"
                        + " VALUES(?, ?, ?, ?, ?, ?)";
                
                /** 將參數回填至SQL指令當中 */
                pres = conn.prepareStatement(sql);/*, Statement.RETURN_GENERATED_KEYS*/
                pres.setInt(1, Id_member);
                pres.setInt(2, Id_room);
                pres.setString(3, Name);
                pres.setDate(4, DateSQL);
                pres.setString(5, Place);
                pres.setString(6, Type);
                
                /** 執行新增之SQL指令並記錄影響之行數 */
                pres.executeUpdate();
                
                /** 紀錄真實執行的SQL指令，並印出 **/
                exexcute_sql = pres.toString();
                System.out.println(exexcute_sql);
                
//                ResultSet rs = pres.getGeneratedKeys();
//
//                if (rs.next()) {
//                    long rsid = rs.getLong(1);
//                    jsa.put(rsid);
//                }
            } catch (SQLException e) {
                /** 印出JDBC SQL指令錯誤 **/
                System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                /** 若錯誤則印出錯誤訊息 */
                e.printStackTrace();
            } finally {
                /** 關閉連線並釋放所有資料庫相關之資源 **/
                DBMgr.close(pres, conn);
            }
            response.put("sql", exexcute_sql);
        return response;
    	}
    
    public JSONObject getByID(String id) {
        /** 新建一個 Room 物件之 r 變數，用於紀錄每一位查詢回之房間資料 */
     	JoinedRoomList jrl = null;
    	
        /** 用於儲存所有檢索回之房間，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `missa`.`rooms` WHERE `id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            /** 正確來說資料庫只會有一筆該房間編號之資料，因此其實可以不用使用 while 迴圈 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int room_id = rs.getInt("Id_room");                
                String Name = rs.getString("Name");
                Date Date = rs.getDate("Date");
                String Place = rs.getString("Place");
                String Type = rs.getString("Type");
//                Date Createtime = rs.getDate("Createtime");
              //  String Maxmember = rs.getString("Maxmember");
               // String GenderRestriction = rs.getString("GenderRestriction");
               // String AgeUpperlimit = rs.getString("AgeUpperlimit");
               // String AgeLowerlimit = rs.getString("AgeLowerlimit");
//                String Creator = rs.getString("Creator");
//                String Description = rs.getString("Description");
                
                /** 將每一筆會員資料產生一名新Member物件 */
                jrl = new JoinedRoomList(room_id,Name,Date,Place,Type);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                jsa.put(jrl.getData());;
            }
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    public ArrayList<OrderItem> getOrderProductByOrderId(int order_id) {
        ArrayList<OrderItem> result = new ArrayList<OrderItem>();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        ResultSet rs = null;
        OrderItem op;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `missa`.`order_product` WHERE `order_product`.`order_id` = ?";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, order_id);
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            rs = pres.executeQuery();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                
                /** 將 ResultSet 之資料取出 */
                int order_product_id = rs.getInt("id");
                int product_id = rs.getInt("product_id");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                double subtotal = rs.getDouble("subtotal");
                
                /** 將每一筆會員資料產生一名新Member物件 */
                op = new OrderItem(order_product_id, order_id, product_id, price, quantity, subtotal);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                result.add(op);
            }
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
        
        return result;
    }
}
