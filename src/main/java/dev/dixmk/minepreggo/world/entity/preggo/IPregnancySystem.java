package dev.dixmk.minepreggo.world.entity.preggo;

public interface IPregnancySystem {
	
	int getDaysByStage();
	void setDaysByStage(int days);
	
	int getPregnancyHealth();
	void setPregnancyHealth(int health);
	
	int getDaysPassed();
	void setDaysPassed(int days);
	
	int getDaysToGiveBirth();
	void setDaysToGiveBirth(int days);
	
    int getPregnancyTimer();
    void setPregnancyTimer(int ticks);
	
	PregnancyStage getLastPregnancyStage();
	void setLastPregnancyStage(PregnancyStage stage);

	PregnancyStage getCurrentPregnancyStage();
    
	PregnancySymptom getPregnancySymptom();
	void setPregnancySymptom(PregnancySymptom symptom);
	
	PregnancyPain getPregnancyPain();
	void setPregnancyPain(PregnancyPain pain);
	
    int getPregnancyPainTimer();
    void setPregnancyPainTimer(int ticks);
	 
	public Baby getTypeOfBaby();
	
	boolean isIncapacitated();
}
