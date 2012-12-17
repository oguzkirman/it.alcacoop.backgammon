package it.alcacoop.gnubackgammon.fsm;

import it.alcacoop.gnubackgammon.actors.Board;
import it.alcacoop.gnubackgammon.fsm.BaseFSM.Events;

// SIMULATED GAME STATE MACHINE
// From: http://vanillajava.blogspot.com/2011/06/java-secret-using-enum-as-state-machine.html

interface Context {
  Board board();
  int getMoves();
  void setMoves(int n);
  State state();
  void state(State state);
}

interface State {
  boolean processEvent(Context ctx, Events evt, Object params);
  void enterState(Context ctx);
  void exitState(Context ctx);
}


// MAIN FSM
public class BaseFSM implements Context {

  private int nMoves; 

  public enum Events {
    NOOP,
    ACCEPT_DOUBLE,
    ACCEPT_RESIGN,
    ASK_FOR_DOUBLING,
    ASK_FOR_RESIGNATION,
    EVALUATE_BEST_MOVE,
    INITIALIZE_ENVIRONMENT,
    ROLL_DICE,
    DICES_ROLLED,
    SET_BOARD,
    SET_GAME_TURN,
    SET_MATCH_SCORE,
    SET_MATCH_TO,
    UPDATE_MS_CUBEINFO,
    PERFORMED_MOVE,
    NO_MORE_MOVES,
    POINT_TOUCHED,
    GENERATE_MOVES,
    DICE_CLICKED,
    STARTING_SIMULATION,
    START_GAME,
    SIMULATED_TURN,
    BUTTON_CLICKED,
    CHECKER_RESETTED,
    DOUBLING_RESPONSE,
    CONTINUE,
    STOPPED,
    DOUBLE_REQUEST,
    HUMAN_DOUBLE_RESPONSE,
    CPU_DOUBLE_ACCEPTED,
    CPU_DOUBLE_NOT_ACCEPTED,
    SHOW_DOUBLE_DIALOG,
    CPU_RESIGNED,
    HUMAN_RESIGNED, 
    ABANDON_MATCH, 
    LEVEL_ALERT
  }

  public enum States implements State {
    STOPPED {
      @Override
      public void enterState(Context ctx) {
      }
    };

    //DEFAULT IMPLEMENTATION
    public boolean processEvent(Context ctx, BaseFSM.Events evt, Object params) {return false;}
    public void enterState(Context ctx) {}
    public void exitState(Context ctx) {}

  };

  public State currentState;
  public State previousState;


  public void start() {
  }

  public void stop() {
    state(States.STOPPED);
  }

  public void restart() {
    stop();
    start();
  }

  public boolean processEvent(Events evt, Object params) {
    System.out.println("PROCESS EVENT: "+evt+" ON "+state());
    boolean res = state().processEvent(this, evt, params);
    //System.out.println("DST STATE: "+state());
    return res;
  }

  public State state() {
    return currentState;
  }
  
  public void back() {
    if(previousState != null)
      state(previousState);
  }

  public void state(State state) {
    if(currentState != null)
      currentState.exitState(this);
    previousState = currentState;
    currentState = state;
    if(currentState != null)
      currentState.enterState(this);        
  }

  public boolean isStopped() {
    return currentState == States.STOPPED;
  }

  public Board board() {
    return null;
  }

  @Override
  public int getMoves() {
    return nMoves;
  }
  @Override
  public void setMoves(int n) {
    nMoves = n;
  }

}