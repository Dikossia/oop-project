package academic;

import enums.RoomType;

public class Room {
	public String roomNumber;
	public RoomType roomType;

	public Room(){
		roomNumber = "0";
	}
	public Room(String roomNumber){
		this.roomNumber = roomNumber;
		this.roomType = RoomType.CLASSROOM;
	}
	public Room(String roomNumber, RoomType roomType){
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}

	public void setRoomType(RoomType roomType){
		this.roomType = roomType;
	}

	public void setRoomNumber(String roomNumber){
		this.roomNumber = roomNumber;
	}

	public RoomType getRoomType(){
		return roomType;
	}
	public String getRoomNumber(){
		return roomNumber;
	}


}
