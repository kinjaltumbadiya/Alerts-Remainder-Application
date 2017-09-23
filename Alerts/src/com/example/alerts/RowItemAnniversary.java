package com.example.alerts;

public class RowItemAnniversary {
	 private int imageId;
	    private String name;
	    private String patnername;
	    private String datee;
	    private String path,mobile,idd;
	    public RowItemAnniversary(int imageId, String name, String patnername,String mobile ,String datee,String path,String id) {
	        this.imageId = imageId;
	        this.name = name;
	        this.patnername = patnername;
	        this.datee=datee;
	        this.path=path;
	        this.mobile=mobile;
	        this.idd=id;
	        

	        
	    }
	  
	  
	    public int getImageId() {
	        return imageId;
	    }
	    public void setImageId(int imageId) {
	        this.imageId = imageId;
	    }

	    public String getId() {
	        return idd;
	    }
	    public void setId(String idd) {
	        this.idd = idd;
	    }
	    public String getpath() {
	        return path;
	    }
	    public String getname() {
	        return name;
	    }
	    public void setname(String name) {
	        this.name = name;
	    }
	    public String getpatner() {
	        return patnername;
	    }
	    public void setpatner(String patner) {
	        this.patnername = patner;
	    }
	    public String getmobile() {
	        return mobile;
	    }
	    public void setmobile(String mobile) {
	        this.mobile = mobile;
	    }
	    
	    public String getdatee() {
	        return datee;
	    }
	    public void setdatee(String datee) {
	        this.datee = datee;
	    }
		
}
