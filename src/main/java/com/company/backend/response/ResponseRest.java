package com.company.backend.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
	
	private ArrayList<HashMap<String, String>> metadata = new ArrayList<HashMap<String,String>>();
	
	public ArrayList<HashMap<String, String>> getMetadata(){
		return metadata;
	}
	
	public void setMetadata(String type, String code, String data){
		HashMap<String , String> mapa = new HashMap<String, String>();
		
		mapa.put("Type", type);
		mapa.put("Code", code);
		mapa.put("Data", data);
		
		metadata.add(mapa);
		
	
	}
	
	
	
	

}
