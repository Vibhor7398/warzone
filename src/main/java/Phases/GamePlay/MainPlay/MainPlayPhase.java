package Phases.GamePlay.MainPlay;
public interface MainPlayPhase {
      abstract public void deploy();
      abstract public void advance();
      abstract public void bomb();
      abstract public void blockade();
      abstract public void airlift();
      abstract public void negotiate();
}
