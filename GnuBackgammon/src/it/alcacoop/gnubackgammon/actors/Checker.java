package it.alcacoop.gnubackgammon.actors;


import it.alcacoop.gnubackgammon.GnuBackgammon;
import it.alcacoop.gnubackgammon.layers.Board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Scaling;



public class Checker extends Group {

  private TextureRegion region;
  private int color;
  private Board board;
  public int boardX; 
  public int boardY;
  private Label label;
  private Image img;

  public Checker(Board _board, int _color) {
    super();

    boardX = boardY= 0;
    color = _color;
    board = _board;

    if (color==0) //WHITE
      region = GnuBackgammon.atlas.findRegion("cw");
    else 
      region = GnuBackgammon.atlas.findRegion("cb");

    region.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    img = new Image(region);
    img.x = 0;
    img.y = 0;
    
    label = new Label("1", GnuBackgammon.style);
    label.x = 18;
    label.y = 8;
    label.setAlignment(Align.CENTER, Align.CENTER);
    //label.scaleX = 0.01f;
    //label.scaleY = 0.01f;
    
    addActor(img);
    addActor(label);
  }

  public void moveTo(int x){
    moveToDelayed(x, 0);
  }

  public void moveToDelayed(int x, float delay){
	board.removeActor(this);
	board.addActor(this);
	int y = board._board[color][x];
    Vector2 _p = board.getBoardCoord(color, x, y);
    setPosition(x, y);
    action(Sequence.$(Delay.$(delay), MoveTo.$(_p.x, _p.y, 0.5f)));
  }
  
  public void moveToDelayed(int x, int y, float delay){
	Vector2 _p = board.getBoardCoord(color, x, y);
	setPosition(x, y);
	action(Sequence.$(Delay.$(delay), MoveTo.$(_p.x, _p.y, 0.5f)));
  }
  
  public void setPosition(int x, int y) {
	  boardX = x;
	  boardY = y;
  }
}
