package ncu.im3069.demo.app;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.json.JSONObject;
//import ncu.im3069.demo.util.Arith;
//import ncu.im3069.demo.app.Room;
//import ncu.im3069.demo.app.Member;
//import java.util.Date;

public class JoinedRoomList {

	/** r，房間 */
    private JSONObject rr;
//    private JSONObject mm;
    private Room r;	
    /** id，建立房間清單編號 */
    private int Id;
    private int Id_member;
    private int Id_room; 
    private String Name;
    private Date Date;
    private String Type;
    private String Place;
    private Date JoinedDate;

    /** ph，ProductHelper 之物件與 OrderItem 相關之資料庫方法（Sigleton） */
    private RoomHelper rh =  RoomHelper.getHelper();
    private MemberHelper mh =  MemberHelper.getHelper();

    /**
     * 實例化（Instantiates）一個新的（new）OrderItem 物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立訂單細項時
     *????????????????????????????????????????????????????????????????
     * @param pd 會員電子信箱
     * @param quantity 會員密碼
     */
    public JoinedRoomList(int Id_member,int Id_room ,String Name ,
    		Date Date ,String Type ,String Place,Date JoinedDate ) {
        this.Id_member = Id_member;
        this.Id_room = r.getID();
        this.Name = r.getName();
        this.Date = r.getDate();
        this.Type = r.getType();
        this.Place = r.getPlace();
        this.JoinedDate = Timestamp.valueOf(LocalDateTime.now());/**/
    }

    /**
     * 實例化（Instantiates）一個新的（new）OrderItem 物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改訂單細項時
     *
     * @param order_product_id 訂單產品編號
     * @param order_id 會員密碼
     * @param product_id 產品編號
     * @param price 產品價格
     * @param quantity 產品數量
     * @param subtotal 小計
     */
    /**public CreateRoomList(int order_product_id, int order_id, int product_id, double price, int quantity, double subtotal) {
        this.id = order_product_id;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
        getProductFromDB(product_id);
    }

    /**
     * 從 DB 中取得房間(待改)
     */
    private void getRoomFromDB(int Member_id, int Room_id) {
//      String List_Member_id = String.valueOf(Member_id);
        String List_Room_id = String.valueOf(Room_id);
//      this.mm= mh.getByID(List_Member_id);
        this.rr= rh.getByID(List_Room_id);
    }

    
    public Room getRoom() {
        return this.r;
    }
    public JSONObject getRoomDB() {
        return this.rr;
    }
//  public JSONObject getMember() {
//      return this.mm;
//  }
    public int getId() {
        return this.Id;
    }
    public int getId_member() {
        return this.Id_member;
    }
    public int getId_room() {
        return this.Id_room;
    }
    public String getName() {
        return this.Name;
    }
    public Date getDate() {
        return this.Date;
    }
    public String getType() {
        return this.Type;
    }
    public String getPlace() {
        return this.Place;
    }
    public Date getJoinedDate() {
        return this.JoinedDate;
    }
    
    public JSONObject getData() {
        JSONObject data = new JSONObject();
        data.put("id", getId());
        data.put("Id_member", getId_member());
        data.put("Id_room", getId_room());
        data.put("Name", getName());
        data.put("Date", getDate());
        data.put("Type", getType());
        data.put("Place", getPlace());
        data.put("JoinedDate", getJoinedDate());
        return data;
    }
}
