package dev.dixmk.minepreggo.world.entity.preggo;

public interface IBreedable {
	
    int getPregnancyInitializerTimer();
    void setPregnancyInitializerTimer(int ticks);
    
    boolean isPregnant();
    boolean canGetPregnant();
    
    
    void impregnate();
}
