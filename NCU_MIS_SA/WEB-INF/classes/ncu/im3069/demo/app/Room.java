package ncu.im3069.demo.app;

import org.json.*;

//import java.util.ArrayList;
/*import java.util.Calendar;*/
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Room
 * Room類別（class）具有房間所需要之屬性與方法，並且儲存與房間相關之商業判斷邏輯<br>
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */

public class Room {
    
    /** id，房間編號 */
    private int Id;
    
    private String Name;
    
    private Date Date;
    
    private String Place;
    
    private String Type;
    
    private Date Createtime;
    
    private String Maxmember;
    
    private String GenderRestriction;
    
    private String AgeUpperlimit;
    
    private String AgeLowerlimit;
    
//  private String Member;
    
    private String Description="";
    
//    private static ArrayList<Room> RoomList = new ArrayList<Room>();
    
    
    /** rh，RoomHelper之物件與Room相關之資料庫方法（Sigleton） */
    private RoomHelper rh =  RoomHelper.getHelper();
    
    /**
     * 實例化（Instantiates）一個新的（new）Room物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立房間資料時，產生一個新的房間
     *
     * @param Name 房間名稱
     * @param Date 活動開始日期
     * @param Place 活動地點
     * @param Type 運動類別
     * @param Createtime 房間建立時間
     * @param Maxmember 最大可容納會員數量
     * @param GenderRestriction 性別限制
     * @param AgeUpperlimit 年齡上限
     * @param AgeLowerlimit 年齡下限
     * @param Description 房間敘述 
     */
    public Room(String Name, Date Date, String Place, String Type, 
    		String Maxmember, String GenderRestriction, String AgeUpperlimit, String AgeLowerlimit 
    			 ) {
        this.Name = Name;
        this.Date = Date;
        this.Place = Place;
        this.Type = Type;
//        this.Createtime = Createtime;
        this.Maxmember = Maxmember;
        this.GenderRestriction = GenderRestriction;
        this.AgeUpperlimit = AgeUpperlimit;
        this.AgeLowerlimit = AgeLowerlimit;
//        this.Member = Member;/*LocalStorage方法*/
//        this.Description = Description;
        update();
    }

    /**
     * 實例化（Instantiates）一個新的（new）Room物件<br>
     * 採用多載（overload）方法進行，此建構子用於更新房間資料時，產生一名會員同時需要去資料庫檢索原有更新時間分鐘數與會員組別
     * 
     * @param Id 房間編號
     * @param Name 房間名稱
     * @param Date 活動開始日期
     * @param Place 活動地點
     * @param Type 運動類別
     * @param Createtime 房間建立時間
     * @param Maxmember 最大可容納會員數量
     * @param GenderRestriction 性別限制
     * @param AgeUpperlimit 年齡上限
     * @param AgeLowerlimit 年齡下限
     * @param Description 房間敘述
     */
    public Room(int Id, String Name, Date Date, String Place, String Type, 
    		String Maxmember, String GenderRestriction, String AgeUpperlimit, String AgeLowerlimit, 
			 String Description) {
    	this.Id = Id;
        this.Name = Name;
        this.Date = Date;
        this.Place = Place;
        this.Type = Type;
        this.Maxmember = Maxmember;
        this.GenderRestriction = GenderRestriction;
        this.AgeUpperlimit = AgeUpperlimit;
        this.AgeLowerlimit = AgeLowerlimit;
        this.Description = Description;
        /** 取回原有資料庫內該名會員之更新時間分鐘數與組別 
        getLoginTimesStatus();
        /** 計算會員之組別 
        calcAccName();*/
    }
    
    public Room(int Id, String Name, Date Date, String Place, String Type, 
    		String Maxmember, String GenderRestriction, String AgeUpperlimit, String AgeLowerlimit
			 ) {
    	this.Id = Id;
        this.Name = Name;
        this.Date = Date;
        this.Place = Place;
        this.Type = Type;
        this.Maxmember = Maxmember;
        this.GenderRestriction = GenderRestriction;
        this.AgeUpperlimit = AgeUpperlimit;
        this.AgeLowerlimit = AgeLowerlimit;

        /** 取回原有資料庫內該名會員之更新時間分鐘數與組別 
        getLoginTimesStatus();
        /** 計算會員之組別 
        calcAccName();*/
    }
    
    
    /**
     * 實例化（Instantiates）一個新的（new）Room物件<br>
     * 採用多載（overload）方法進行，此建構子用於查詢房間資料時，將每一筆資料新增為一個房間物件
     *
     * @param Id 房間編號
     * @param Name 房間名稱
     * @param Date 活動開始日期
     * @param Place 活動地點
     * @param Type 運動類別
     * @param Createtime 建立房間時間
     * @param Maxmember 最大可容納會員數量
     * @param GenderRestriction 性別限制
     * @param AgeUpperlimit 年齡上限
     * @param AgeLowerlimit 年齡下限
     * @param Creator 開房者名稱
     * @param Description 房間敘述
     */
//    public Room(int Id, String Name, Date Date, String Place, String Type, Date Createtime, 
//    			int Maxmember, String GenderRestriction, int AgeUpperlimit, int AgeLowerlimit, 
//    			String Creator, String Description) {
//        this.Id = Id;
//        this.Name = Name;
//        this.Date = Date;
//        this.Place = Place;
//        this.Type = Type;
//        this.Createtime = Createtime;
//        this.Maxmember = Maxmember;
//        this.GenderRestriction = GenderRestriction;
//        this.AgeUpperlimit = AgeUpperlimit;
//        this.AgeLowerlimit = AgeLowerlimit;
//     this.Member = Member;/*localstorage*/
//        this.Description = Description;     
//    }
    
   

	/**
     * 取得房間之編號
     *
     * @return the Id 回傳房間編號
     */
    public int getID() {
        return this.Id;
    }
	public String getName() {
		return this.Name;
	}	
    public Date getDate() {
        return this.Date;
    }	
    public String getPlace() {
        return this.Place;
    }   
	public String getType() {
		return this.Type;
	}   
	public Date getCreatetime() {
		return this.Createtime;
	}
    public String getMaxmember() {
        return this.Maxmember;
    } 
    public String getGenderRestriction() {
        return this.GenderRestriction;
    }   
    public String getAgeUpperlimit() {
//    	if(Integer.toString(this.AgeUpperlimit)=="")
//    	{
//    		this.AgeUpperlimit=999;
//    	}
        return this.AgeUpperlimit;
    }  
    public String getAgeLowerlimit() {
//    	if(Integer.toString(this.AgeLowerlimit)=="")
//    	{
//    		this.AgeLowerlimit=0;
//    	}
        return this.AgeLowerlimit;
    } 
//	public String getMember() {
//		return this.Member;
//	}
//	   
	public String getDescription() {
		return this.Description;
	}
	
	
	
//	public ArrayList<Room> getRoom() {
//        return this.RoomList;
//    }
//	
	
    /**
     * 更新會員資料
     *
     * @return the JSON object 回傳SQL更新之結果與相關封裝之資料
     */
	
    public JSONObject update() {
        /** 新建一個JSONObject用以儲存更新後之資料 */
        JSONObject data = new JSONObject();
        
        /** 取得更新資料時間（即現在之時間）之分鐘數 
        Calendar calendar = Calendar.getInstance();
        
        this.login_times = calendar.get(Calendar.MINUTE);
        
        /** 計算帳戶所屬之組別 
        calcAccName(); */
        
        /** 檢查該房間是否已經在資料庫 */
        if(this.Id != 0) {
            /** 若有則將目前更新後之資料更新至資料庫中 
            rh.updateLoginTimes(this);
            /** 透過RoomHelper物件，更新目前之會員資料置資料庫中 */
            data = rh.update(this);
        }
        
        return data;
    }
    
    /**
     * 取得該房間所有資料
     *
     * @return the data 取得該名會員之所有資料並封裝於JSONObject物件內
     */
    public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("Id", getID());
        jso.put("Name", getName());
        jso.put("Date", getDate());
        jso.put("Place", getPlace());
        jso.put("Type", getType());
        jso.put("Createtime", getCreatetime());
        jso.put("Maxmember", getMaxmember());
        jso.put("GenderRestriction", getGenderRestriction());
        jso.put("AgeUpperlimit",getAgeUpperlimit());
        jso.put("AgeLowerlimit",getAgeLowerlimit());
//      jso.put("Creator", getCreator());
        
        return jso;
    }
    
    
    /**
     * 取得資料庫內之更新資料時間分鐘數與會員組別
     *
     
    private void getLoginTimesStatus() {
        /** 透過MemberHelper物件，取得儲存於資料庫的更新時間分鐘數與會員組別 
        JSONObject data = rh.getLoginTimesStatus(this);
        /** 將資料庫所儲存該名會員之相關資料指派至Member物件之屬性 
        this.login_times = data.getInt("login_times");
        this.Status = data.getString("Status");
    }
    
    /**
     * 計算會員之組別<br>
     * 若為偶數則為「偶數會員」，若為奇數則為「奇數會員」
     
    private void calcAccName() {
        /** 計算目前分鐘數為偶數或奇數 
        String curr_status = (this.login_times % 2 == 0) ? "偶數會員" : "奇數會員";
        /** 將新的會員組別指派至Member之屬性 
        this.status = curr_status;
        /** 檢查該名會員是否已經在資料庫，若有則透過MemberHelper物件，更新目前之組別狀態 
        if(this.id != 0) rh.updateStatus(this, curr_status);
    }
    */







}