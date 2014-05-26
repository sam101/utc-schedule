package com.slepetit.utc.schedule.data;

public class SlotData {
	public static final SlotData EMPTY_SLOT = new SlotData();

	public String type;
	public int groupNumber;
	public String uv;
	public String room;
	public int day;
	public int bh;
	public int bm;
	public int eh;
	public int em;
		
	public SlotData() {
		
	}
	
	public SlotData(String uv, String room, String type, int groupNumber) {
		this.uv = uv;
		this.room = room;
		this.type = type;
		this.groupNumber = groupNumber;
	}

	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getUv() {
		return uv;
	}

	public void setUv(String uv) {
		this.uv = uv;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "SlotData [type=" + type + ", groupNumber=" + groupNumber
				+ ", uv=" + uv + ", room=" + room + "]";
	}
	
	
	
}
