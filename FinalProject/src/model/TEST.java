package model;

import application.SwitchSceneController;

class TEST{
	public static void main(String[] args) {
		
		String time ="15mins";
		String timeformat=time.replaceAll("m", " m").replaceAll("hour", " hour").replaceAll("rs", "rs ");
		String[] array1 = timeformat.split(" ");
	    int minutes = 0;
	    if(array1.length>2){  // if it has minute and hour content 
	        minutes = Integer.parseInt(array1[0])*60 + Integer.parseInt(array1[2]);
	    } else{  // if time has only minute content
	        minutes = Integer.parseInt(array1[0]);
	    }
	    System.out.println(minutes);

		
}
}