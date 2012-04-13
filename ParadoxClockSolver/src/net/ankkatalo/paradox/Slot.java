package net.ankkatalo.paradox;

public class Slot {

	private int value;
	private boolean available;
	
	public Slot(int value) {
		this.value = value;
		this.available = true;		
	}
		
	public Slot(Slot slot) {	
		this.value = slot.value();
		this.available = slot.available();		
	}
			
	public int value() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean available() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
		
}
