package it.alcacoop.gnubackgammon.fsm;

import it.alcacoop.gnubackgammon.GnuBackgammon;
import it.alcacoop.gnubackgammon.actors.Board;


// MENU FSM
public class MenuFSM extends BaseFSM implements Context {

  private Board board;
  public State currentState;

  public enum States implements State {

    MAIN_MENU {
      @Override
      public void enterState(Context ctx) {
        GnuBackgammon.Instance.goToScreen(2);
      }
      
      @Override
      public boolean processEvent(Context ctx, Events evt, Object params) {
        if (evt==Events.BUTTON_CLICKED) {
          System.out.println(params);
          if (params.toString().equals("SINGLE PLAYER")) {
            ctx.state(States.MATCH_OPTIONS);
          }
          if (params.toString().equals("TWO PLAYERS")) {
          }
          if (params.toString().equals("STATISTICS")) {
          }
          if (params.toString().equals("OPTIONS")) {
          }
          return true;
        }
        return false;
      }
    },
    
    MATCH_OPTIONS {
      @Override
      public void enterState(Context ctx) {
        GnuBackgammon.Instance.goToScreen(3);
      }
      
      @Override
      public boolean processEvent(Context ctx, Events evt, Object params) {
        if (evt==Events.BUTTON_CLICKED) {
          System.out.println(params);
          if (params.toString().equals("PLAY")) {
            GnuBackgammon.Instance.setFSM("GAME_FSM");
          }
          if (params.toString().equals("BACK")) {
            ctx.state(States.MAIN_MENU);
          }
          return true;
        }
        return false;
      }
    },
    
    OPTIONS {
    },
    
    STOPPED {
      @Override
      public void enterState(Context ctx) {
        System.out.println("MENU FSM STOPPED");
      }
    };
    
    //DEFAULT IMPLEMENTATION
    public boolean processEvent(Context ctx, BaseFSM.Events evt, Object params) {return false;}
    public void enterState(Context ctx) {}
    public void exitState(Context ctx) {}
    
  };


  public MenuFSM(Board _board) {
    board = _board;
  }

  public void start() {
    state(States.MAIN_MENU);
  }

  public void stop() {
    state(States.STOPPED);
  }
  
  public Board board() {
    return board;
  }
  
}