package dev.dixmk.minepreggo.network.capability;

public interface ILoopedAnimation {
    boolean isAnimating();
    void setAnimating(boolean animating);
    String getAnimationType();
    void startAnimation(String type, int durationTicks);
    void stopAnimation();
    void tick();
}
