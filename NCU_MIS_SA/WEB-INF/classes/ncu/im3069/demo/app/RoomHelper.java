package ncu.im3069.demo.app;

import java.sql.*;
//import java.time.LocalDateTime;
import org.json.*;
//import java.util.Date;

import ncu.im3069.demo.util.DBMgr;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class MemberHelper<br>
 * MemberHelper類別（class）主要管理所有與Member相關與資料庫之方法（method）
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */

public class RoomHelper {
    
    /**
     * 實例化（Instantiates）一個新的（new）MemberHelper物件<br>
     * 採用Singleton不需要透過new
     */
    private RoomHelper() {
        
    }
    
    /** 靜態變數，儲存MemberHelper物件 */
    private static RoomHelper rh;
    
    /** 儲存JDBC資料庫連線 */
    private Connection conn = null;
    
    /** 儲存JDBC預準備之SQL指令 */
    private PreparedStatement pres = null;
    
    /**
     * 靜態方法<br>
     * 實作Singleton（單例模式），僅允許建立一個RoomHelper物件
     *
     * @return the helper 回傳RoomHelper物件
     */
    public static RoomHelper getHelper() {
        /** Singleton檢查是否已經有RoomHelper物件，若無則new一個，若有則直接回傳 */
        if(rh == null) rh = new RoomHelper();
        
        return rh;
    }
    
    /**
     * 透過房間編號（ID）刪除房間
     *
     * @param id 房間編號
     * @return the JSONObject 回傳SQL執行結果
     */
    public JSONObject deleteByID(int id) {
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
            String sql = "DELETE FROM `missa`.`rooms` WHERE `id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
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
    
    /**
     * 取回所有房間資料
     *
     * @return the JSONObject 回傳SQL執行結果與自資料庫取回之所有資料
     */
    public JSONObject getAll() {
        /** 新建一個 Room 物件之 r 變數，用於紀錄每一位查詢回之房間資料 */
        Room r = null;
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
            String sql = "SELECT * FROM `missa`.`rooms`";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
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
                int room_id = rs.getInt("Id");
                String Name = rs.getString("Name");
                Date Date = rs.getDate("Date");
                String Place = rs.getString("Place");
                String Type = rs.getString("Type");
                Date RoomDeadline = rs.getDate("RoomDeadline");
                int Maxmember = rs.getInt("Maxmember");
                String GenderRestriction = rs.getString("GenderRestriction");
                int AgeUpperlimit = rs.getInt("AgeUpperlimit");
                int AgeLowerlimit = rs.getInt("AgeLowerlimit");
                String Creator = rs.getString("Creator");
                String Description = rs.getString("Description");
                
                /** 將每一筆會員資料產生一名新Member物件 */
                r = new Room(room_id, Name, Date, Place, Type, RoomDeadline, Maxmember, 
                			 GenderRestriction, AgeUpperlimit, AgeLowerlimit, Creator, Description);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                jsa.put(r.getData());
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
    
    /**
     * 透過會員編號（ID）取得會員資料
     *
     * @param id 房間編號
     * @return the JSON object 回傳SQL執行結果與該房間編號之房間資料
     */
    public JSONObject getByID(String id) {
        /** 新建一個 Room 物件之 r 變數，用於紀錄每一位查詢回之房間資料 */
        Room r = null;
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
                int room_id = rs.getInt("Id");
                String Name = rs.getString("Name");
                Date Date = rs.getDate("Date");
                String Place = rs.getString("Place");
                String Type = rs.getString("Type");
                Date RoomDeadline = rs.getDate("RoomDeadline");
                int Maxmember = rs.getInt("Maxmember");
                String GenderRestriction = rs.getString("GenderRestriction");
                int AgeUpperlimit = rs.getInt("AgeUpperlimit");
                int AgeLowerlimit = rs.getInt("AgeLowerlimit");
                String Creator = rs.getString("Creator");
                String Description = rs.getString("Description");
                
                /** 將每一筆會員資料產生一名新Member物件 */
                r = new Room(room_id, Name, Date, Place, Type, RoomDeadline, Maxmember, 
                			 GenderRestriction, AgeUpperlimit, AgeLowerlimit, Creator, Description);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                jsa.put(r.getData());;
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
    
    /**
     * 取得該名會員之更新時間與所屬之會員組別
     *
     * @param m 一名會員之Member物件
     * @return the JSON object 回傳該名會員之更新時間與所屬組別（以JSONObject進行封裝）
    
    public JSONObject getLoginTimesStatus(Room r) {
        /** 用於儲存該名會員所檢索之更新時間分鐘數與會員組別之資料 
        JSONObject jso = new JSONObject();
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 
        ResultSet rs = null;

        try {
            /** 取得資料庫之連線 
            conn = DBMgr.getConnection();
            /** SQL指令 
            String sql = "SELECT * FROM `missa`.`members` WHERE `id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 
            pres = conn.prepareStatement(sql);
            pres.setInt(1, r.getID());
            /** 執行查詢之SQL指令並記錄其回傳之資料 
            rs = pres.executeQuery();
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            /** 正確來說資料庫只會有一筆該電子郵件之資料，因此其實可以不用使用 while迴圈 
            while(rs.next()) {
                /** 將 ResultSet 之資料取出 
                int login_times = rs.getInt("login_times");
                String status = rs.getString("status");
                /** 將其封裝至JSONObject資料 
                jso.put("login_times", login_times);
                jso.put("status", status);
            }
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 
            DBMgr.close(rs, pres, conn);
        }

        return jso;
    }
    
    /**
     * 檢查該房間之名稱是否重複
     *
     * @param m 一個房間之Room物件
     * @return boolean 若重複建立回傳False，若該房間不存在則回傳True
     */
    public boolean checkDuplicate(Room r){
        /** 紀錄SQL總行數，若為「-1」代表資料庫檢索尚未完成 */
        int row = -1;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT count(*) FROM `missa`.`rooms` WHERE `Name` = ?";
            
            /** 取得所需之參數 */
            String Name = r.getName();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, Name);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 讓指標移往最後一列，取得目前有幾行在資料庫內 */
            rs.next();
            row = rs.getInt("count(*)");
            System.out.print(row);

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
        
        /** 
         * 判斷是否已經有一筆該電子郵件信箱之資料
         * 若無一筆則回傳False，否則回傳True 
         */
        return (row == 0) ? false : true;
    }
    
    /**
     * 建立該房間至資料庫
     *
     * @param r 一個房間之Room物件
     * @return the JSON object 回傳SQL指令執行之結果
     */
    public JSONObject create(Room r) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "INSERT INTO `missa`.`rooms`(`Name`, `Date`, `Place`, `Type`, `RoomDeadline`,"
            		+ " `Maxmember`, `GenderRestriction`, `AgeUpperlimit`, `AgeLowerlimit`, `Creator`, "
            		+ "`Description` )"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            /** 取得所需之參數 */
            String Name = r.getName();
            Date Date = new java.sql.Date(r.getDate().getTime()); 
            String Place = r.getPlace();
            String Type = r.getType();
            Date RoomDeadline = new java.sql.Date(r.getRoomDeadline().getTime()); 
            int Maxmember = r.getMaxmember();
            String GenderRestriction = r.getGenderRestriction();
            int AgeUpperlimit = r.getAgeUpperlimit();
            int AgeLowerlimit = r.getAgeLowerlimit();
            String Creator = r.getCreator();
            String Description = r.getDescription();
            /**將util的Date轉為sql的date以寫入
            Date Dob = new java.sql.Date(r.getDob().getTime());
            */
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, Name);
            pres.setDate(2, Date);
            pres.setString(3, Place);
            pres.setString(4, Type);
            pres.setDate(5, RoomDeadline);
            pres.setInt(6, Maxmember);
            pres.setString(7, GenderRestriction);
            pres.setInt(8, AgeUpperlimit);
            pres.setInt(9, AgeLowerlimit);
            pres.setString(10, Creator);
            pres.setString(11, Description);
            /** 執行新增之SQL指令並記錄影響之行數 */
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
            DBMgr.close(pres, conn);
        }

        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);

        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("row", row);

        return response;
    }
    
    /**
     * 更新一個房間之資料 (目前新增的功能)
     * 關於ID出現在這裡 我當初是跟MemberHelper來做比較 覺得哪裡怪怪的
     * 應該說再SQL最後的Where除了用ID來對應以外 我想不到可以用甚麼"固定不會變的欄位"來確認要改哪個房間的資料
     * 所以才把ID拉進來
     * @param m 一個房間之Room物件
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject update(Room r) {
        /** 紀錄回傳之資料 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "Update `missa`.`rooms` SET `Name` = ?,`Date` = ? ,`Place` = ? , "
            		    	  + "`Type` = ? , `RoomDeadline` = ? , `Maxmember` = ? , `GenderRestriction` = ? ,"
            		          + "`AgeUpperlimit` = ? , `AgeLowerlimit` = ? , `Description` = ? WHERE `Id` = ?";
            /** 最後的WHERE `Id` = ? 就是這塊不太確定QQ
             * 取得所需之參數 */
            String Name = r.getName();
            Date Date = new java.sql.Date(r.getDate().getTime()); 
            String Place = r.getPlace();
            String Type = r.getType();
            Date RoomDeadline = new java.sql.Date(r.getRoomDeadline().getTime()); 
            int Maxmember = r.getMaxmember();
            String GenderRestriction = r.getGenderRestriction();
            int AgeUpperlimit = r.getAgeUpperlimit();
            int AgeLowerlimit = r.getAgeLowerlimit();
            String Description = r.getDescription();
            int Id = r.getID();
           
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, Name);
            pres.setDate(2, Date);
            pres.setString(3, Place);
            pres.setString(4, Type);
            pres.setDate(5, RoomDeadline);
            pres.setInt(6, Maxmember);
            pres.setString(7, GenderRestriction);
            pres.setInt(8, AgeUpperlimit);
            pres.setInt(9, AgeLowerlimit);
            pres.setString(10, Description);
            pres.setInt(11, Id);
            
            /** 執行更新之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 */
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 */
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 */
            DBMgr.close(pres, conn);
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
        response.put("data", jsa);

        return response;
    }
    
    /**
     * 更新會員更新資料之分鐘數
     *
     * @param m 一名會員之Member物件
     
    public void updateLoginTimes(Room r) {
        /** 更新時間之分鐘數 
        int new_times = r.getLoginTimes();
        
        /** 記錄實際執行之SQL指令 
        String exexcute_sql = "";
        
        try {
            /** 取得資料庫之連線 
            conn = DBMgr.getConnection();
            /** SQL指令 
            String sql = "Update `missa`.`members` SET `login_times` = ? WHERE `id` = ?";
            /** 取得會員編號 
            int id = r.getID();
            
            /** 將參數回填至SQL指令當中 
            pres = conn.prepareStatement(sql);
            pres.setInt(1, new_times);
            pres.setInt(2, id);
            /** 執行更新之SQL指令 
            pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 
            DBMgr.close(pres, conn);
        }
    }
    
    /**
     * 更新會員之會員組別
     *
     * @param m 一名會員之Member物件
     * @param status 會員組別之字串（String）
     
    public void updateStatus(Member m, String status) {      
        /** 記錄實際執行之SQL指令 
        String exexcute_sql = "";
        
        try {
            /** 取得資料庫之連線 
            conn = DBMgr.getConnection();
            /** SQL指令 
            String sql = "Update `missa`.`members` SET `status` = ? WHERE `id` = ?";
            /** 取得會員編號 
            int id = m.getID();
            
            /** 將參數回填至SQL指令當中 
            pres = conn.prepareStatement(sql);
            pres.setString(1, status);
            pres.setInt(2, id);
            /** 執行更新之SQL指令 
            pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            	若錯誤則印出錯誤訊息 
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 */
            /** DBMgr.close(pres, conn);
        }*/
    }


