package com.example.alerts;

public class RowItemCustom {
	
			private int imageId;
		    private String nameofevent;
		    private String dateofevent;
		    private String id;
		   
		    public RowItemCustom(int imageId,String nameofevent, String dateofevent,String id) {
		    	this.imageId = imageId;
		        this.nameofevent = nameofevent;
		        this.dateofevent = dateofevent;
		        this.id=id;
		      
		    }
		    
		    public String getid() {
		        return id;
		    }
		 
		    public void setid(String id) {
		        this.id = id;
		    }
		    
		    
		    public int getImageId() {
		        return imageId;
		    }
		    public void setImageId(int imageId) {
		        this.imageId = imageId;
		    }
		    public String getname() {
		        return nameofevent;
		    }
		 
		    public void setname(String nameofevent) {
		        this.nameofevent = nameofevent;
		    }
		    public String getdt() {
		        return dateofevent;
		    }
		    public void setdt(String dateofevent) {
		        this.dateofevent = dateofevent;
		    }
		  
			
	

}
