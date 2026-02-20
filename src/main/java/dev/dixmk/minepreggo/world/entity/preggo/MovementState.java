package dev.dixmk.minepreggo.world.entity.preggo;

public enum MovementState {
	WAITING,
	FOLLOWING,
	WANDERING;
	
	public static final String NBT_KEY = "MovementState"; 
}
