/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.controller;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.PawnBehavior.MoveDirection;
import ca.mcgill.ecse223.quoridor.model.*;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;

// line 4 "../../../../../StateMachine.ump"
public class PawnBehavior
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum MoveDirection {East, South, West, North}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PawnBehavior State Machines
  public enum PawnSM { Playing, Finished }
  public enum PawnSMPlayingNorthSouth { Null, NorthSouth }
  public enum PawnSMPlayingNorthSouthNorthSouth { Null, Setup, SouthEdge, SouthBorder, MiddleNS, NorthBorder, NorthEdge }
  public enum PawnSMPlayingEastWest { Null, EastWest }
  public enum PawnSMPlayingEastWestEastWest { Null, Setup, WestEdge, WestBorder, MiddleEW, EastBorder, EastEdge }
  private PawnSM pawnSM;
  private PawnSMPlayingNorthSouth pawnSMPlayingNorthSouth;
  private PawnSMPlayingNorthSouthNorthSouth pawnSMPlayingNorthSouthNorthSouth;
  private PawnSMPlayingEastWest pawnSMPlayingEastWest;
  private PawnSMPlayingEastWestEastWest pawnSMPlayingEastWestEastWest;

  //PawnBehavior Associations
  private Game currentGame;
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PawnBehavior()
  {
    setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth.Null);
    setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
    setPawnSMPlayingEastWest(PawnSMPlayingEastWest.Null);
    setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
    setPawnSM(PawnSM.Playing);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getPawnSMFullName()
  {
    String answer = pawnSM.toString();
    if (pawnSMPlayingNorthSouth != PawnSMPlayingNorthSouth.Null) { answer += "." + pawnSMPlayingNorthSouth.toString(); }
    if (pawnSMPlayingNorthSouthNorthSouth != PawnSMPlayingNorthSouthNorthSouth.Null) { answer += "." + pawnSMPlayingNorthSouthNorthSouth.toString(); }
    if (pawnSMPlayingEastWest != PawnSMPlayingEastWest.Null) { answer += "." + pawnSMPlayingEastWest.toString(); }
    if (pawnSMPlayingEastWestEastWest != PawnSMPlayingEastWestEastWest.Null) { answer += "." + pawnSMPlayingEastWestEastWest.toString(); }
    return answer;
  }

  public PawnSM getPawnSM()
  {
    return pawnSM;
  }

  public PawnSMPlayingNorthSouth getPawnSMPlayingNorthSouth()
  {
    return pawnSMPlayingNorthSouth;
  }

  public PawnSMPlayingNorthSouthNorthSouth getPawnSMPlayingNorthSouthNorthSouth()
  {
    return pawnSMPlayingNorthSouthNorthSouth;
  }

  public PawnSMPlayingEastWest getPawnSMPlayingEastWest()
  {
    return pawnSMPlayingEastWest;
  }

  public PawnSMPlayingEastWestEastWest getPawnSMPlayingEastWestEastWest()
  {
    return pawnSMPlayingEastWestEastWest;
  }

  public boolean finishGame()
  {
    boolean wasEventProcessed = false;
    
    PawnSM aPawnSM = pawnSM;
    switch (aPawnSM)
    {
      case Playing:
        exitPawnSM();
        setPawnSM(PawnSM.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startGame()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case Setup:
        if (getPlayer().getGameAsWhite().equals(getCurrentGame()))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 19 "../../../../../StateMachine.ump"
          startGame();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    switch (aPawnSMPlayingEastWestEastWest)
    {
      case Setup:
        exitPawnSMPlayingEastWestEastWest();
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean stepUp() throws CloneNotSupportedException
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case SouthEdge:
        if (isLegalStep(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 23 "../../../../../StateMachine.ump"
          movePlayer (MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      case SouthBorder:
        if (isLegalStep(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 28 "../../../../../StateMachine.ump"
          movePlayer (MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleNS:
        if (isLegalStep(MoveDirection.North)&&getCurrentPawnRow()==3)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 34 "../../../../../StateMachine.ump"
          movePlayer (MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalStep(MoveDirection.North)&&getCurrentPawnRow()>=4&&getCurrentPawnRow()<=7)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 37 "../../../../../StateMachine.ump"
          movePlayer (MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthBorder:
        if (isLegalStep(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 47 "../../../../../StateMachine.ump"
          movePlayer (MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean jumpUp()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case SouthEdge:
        if (isLegalJump(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 24 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case SouthBorder:
        if (isLegalJump(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 30 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleNS:
        if (isLegalJump(MoveDirection.North)&&getCurrentPawnRow()==3)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 35 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthEdge);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.North)&&getCurrentPawnRow()==4)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 36 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.North)&&getCurrentPawnRow()>=5&&getCurrentPawnRow()<=7)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 42 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.North);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean stepDown() throws CloneNotSupportedException
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case SouthBorder:
        if (isLegalStep(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 29 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleNS:
        if (isLegalStep(MoveDirection.South)&&getCurrentPawnRow()==7)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 38 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalStep(MoveDirection.South)&&getCurrentPawnRow()>=3&&getCurrentPawnRow()<=6)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 41 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthBorder:
        if (isLegalStep(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 48 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthEdge:
        if (isLegalStep(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 53 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean jumpDown()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case MiddleNS:
        if (isLegalJump(MoveDirection.South)&&getCurrentPawnRow()==7)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 39 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthEdge);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.South)&&getCurrentPawnRow()==6)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 40 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.South)&&getCurrentPawnRow()>=3&&getCurrentPawnRow()<=5)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 43 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthBorder:
        if (isLegalJump(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 49 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthEdge:
        if (isLegalJump(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
        // line 54 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.South);
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean stepRight() throws CloneNotSupportedException
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingEastWestEastWest)
    {
      case WestEdge:
        if (isLegalStep(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 67 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      case WestBorder:
        if (isLegalStep(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 72 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleEW:
        if (isLegalStep(MoveDirection.East)&&getCurrentPawnColumn()==7)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 84 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalStep(MoveDirection.East)&&getCurrentPawnColumn()>=3&&getCurrentPawnColumn()<=6)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 87 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastBorder:
        if (isLegalStep(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 94 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean jumpRight()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingEastWestEastWest)
    {
      case WestEdge:
        if (isLegalJump(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 68 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case WestBorder:
        if (isLegalJump(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 73 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleEW:
        if (getCurrentPawnColumn()>=3&&getCurrentPawnColumn()<=5)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 82 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.East)&&getCurrentPawnColumn()==7)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 85 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastEdge);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.East)&&getCurrentPawnColumn()==6)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 86 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.East);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean stepLeft() throws CloneNotSupportedException
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingEastWestEastWest)
    {
      case WestBorder:
        if (isLegalStep(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 74 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleEW:
        if (isLegalStep(MoveDirection.West)&&getCurrentPawnColumn()==3)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 79 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalStep(MoveDirection.West)&&getCurrentPawnColumn()>=4&&getCurrentPawnColumn()<=7)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 88 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastBorder:
        if (isLegalStep(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 93 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastEdge:
        if (isLegalStep(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 99 "../../../../../StateMachine.ump"
          movePlayer(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean jumpLeft()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingEastWestEastWest)
    {
      case MiddleEW:
        if (isLegalJump(MoveDirection.West)&&getCurrentPawnColumn()==3)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 80 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestEdge);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.West)&&getCurrentPawnColumn()==4)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 81 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.West)&&isLegalJump(MoveDirection.West)&&getCurrentPawnColumn()>=5&&getCurrentPawnColumn()<=7)
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 83 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastBorder:
        if (isLegalJump(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 95 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastEdge:
        if (isLegalJump(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
        // line 100 "../../../../../StateMachine.ump"
          jumpPawn(MoveDirection.West);
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitPawnSM()
  {
    switch(pawnSM)
    {
      case Playing:
        exitPawnSMPlayingNorthSouth();
        exitPawnSMPlayingEastWest();
        break;
    }
  }

  private void setPawnSM(PawnSM aPawnSM)
  {
    pawnSM = aPawnSM;

    // entry actions and do activities
    switch(pawnSM)
    {
      case Playing:
        if (pawnSMPlayingNorthSouth == PawnSMPlayingNorthSouth.Null) { setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth.NorthSouth); }
        if (pawnSMPlayingEastWest == PawnSMPlayingEastWest.Null) { setPawnSMPlayingEastWest(PawnSMPlayingEastWest.EastWest); }
        break;
    }
  }

  private void exitPawnSMPlayingNorthSouth()
  {
    switch(pawnSMPlayingNorthSouth)
    {
      case NorthSouth:
        exitPawnSMPlayingNorthSouthNorthSouth();
        setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth.Null);
        break;
    }
  }

  private void setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth aPawnSMPlayingNorthSouth)
  {
    pawnSMPlayingNorthSouth = aPawnSMPlayingNorthSouth;
    if (pawnSM != PawnSM.Playing && aPawnSMPlayingNorthSouth != PawnSMPlayingNorthSouth.Null) { setPawnSM(PawnSM.Playing); }

    // entry actions and do activities
    switch(pawnSMPlayingNorthSouth)
    {
      case NorthSouth:
        if (pawnSMPlayingNorthSouthNorthSouth == PawnSMPlayingNorthSouthNorthSouth.Null) { setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Setup); }
        break;
    }
  }

  private void exitPawnSMPlayingNorthSouthNorthSouth()
  {
    switch(pawnSMPlayingNorthSouthNorthSouth)
    {
      case Setup:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case SouthEdge:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case SouthBorder:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case MiddleNS:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case NorthBorder:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case NorthEdge:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
    }
  }

  private void setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth)
  {
    pawnSMPlayingNorthSouthNorthSouth = aPawnSMPlayingNorthSouthNorthSouth;
    if (pawnSMPlayingNorthSouth != PawnSMPlayingNorthSouth.NorthSouth && aPawnSMPlayingNorthSouthNorthSouth != PawnSMPlayingNorthSouthNorthSouth.Null) { setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth.NorthSouth); }
  }

  private void exitPawnSMPlayingEastWest()
  {
    switch(pawnSMPlayingEastWest)
    {
      case EastWest:
        exitPawnSMPlayingEastWestEastWest();
        setPawnSMPlayingEastWest(PawnSMPlayingEastWest.Null);
        break;
    }
  }

  private void setPawnSMPlayingEastWest(PawnSMPlayingEastWest aPawnSMPlayingEastWest)
  {
    pawnSMPlayingEastWest = aPawnSMPlayingEastWest;
    if (pawnSM != PawnSM.Playing && aPawnSMPlayingEastWest != PawnSMPlayingEastWest.Null) { setPawnSM(PawnSM.Playing); }

    // entry actions and do activities
    switch(pawnSMPlayingEastWest)
    {
      case EastWest:
        if (pawnSMPlayingEastWestEastWest == PawnSMPlayingEastWestEastWest.Null) { setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Setup); }
        break;
    }
  }

  private void exitPawnSMPlayingEastWestEastWest()
  {
    switch(pawnSMPlayingEastWestEastWest)
    {
      case Setup:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case WestEdge:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case WestBorder:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case MiddleEW:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case EastBorder:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case EastEdge:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
    }
  }

  private void setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest)
  {
    pawnSMPlayingEastWestEastWest = aPawnSMPlayingEastWestEastWest;
    if (pawnSMPlayingEastWest != PawnSMPlayingEastWest.EastWest && aPawnSMPlayingEastWestEastWest != PawnSMPlayingEastWestEastWest.Null) { setPawnSMPlayingEastWest(PawnSMPlayingEastWest.EastWest); }
  }
  /* Code from template association_GetOne */
  public Game getCurrentGame()
  {
    return currentGame;
  }

  public boolean hasCurrentGame()
  {
    boolean has = currentGame != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setCurrentGame(Game aNewCurrentGame)
  {
    boolean wasSet = false;
    currentGame = aNewCurrentGame;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setPlayer(Player aNewPlayer)
  {
    boolean wasSet = false;
    player = aNewPlayer;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    currentGame = null;
    player = null;
  }

  // line 108 "../../../../../StateMachine.ump"
  public void jumpPawn(MoveDirection dir){
	  
    
  }

  // line 111 "../../../../../StateMachine.ump"
  public boolean movePlayer(MoveDirection dir) throws CloneNotSupportedException{
	String playerSide;
	Player player = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
	if (player.hasGameAsBlack()) {
		playerSide = "black";
	} else {
		playerSide = "white";
	}
    return QuoridorController.movePlayer(playerSide, determineDirection(dir));
  }

  // line 113 "../../../../../StateMachine.ump"
  public void startTheGame(){
    
  }


  /**
   * Returns the current row number of the pawn
   * @author Zirui He
   * @return int
   */
  // line 117 "../../../../../StateMachine.ump"

  public int getCurrentPawnRow(){
	  currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
	  Player player = currentGame.getCurrentPosition().getPlayerToMove();
	  int row;
	  if (player.hasGameAsBlack()) {
		  row = currentGame.getCurrentPosition().getBlackPosition().getTile().getRow();
	  } else {
		  row = currentGame.getCurrentPosition().getWhitePosition().getTile().getRow();
	  }
    return row;
  }


  /**
   * Returns the current column number of the pawn
   * @author Zirui He
   * @return int
   */
  // line 119 "../../../../../StateMachine.ump"
  public int getCurrentPawnColumn(){
	  currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
	  Player player = currentGame.getCurrentPosition().getPlayerToMove();
	  int col;
	  if (player.hasGameAsBlack()) {
		  col = currentGame.getCurrentPosition().getBlackPosition().getTile().getColumn();
	  } else {
		  col = currentGame.getCurrentPosition().getWhitePosition().getTile().getColumn();
	  }
    return col;
  }


  /**
   * Returns if it is legal to step in the given direction
   * @author Zirui He
   * @param dir
   * @return boolean
   */
  // line 121 "../../../../../StateMachine.ump"
  public boolean isLegalStep(MoveDirection dir){
	  boolean result = true;
	  currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
	  if(!currentGame.getGameStatus().equals(GameStatus.Running)) {
		 return false; 
	  }
	  Player player = currentGame.getCurrentPosition().getPlayerToMove();
	  List<Wall> blackWalls = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard();
	  List<Wall> whiteWalls = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard();
	  List<Wall> wallList = new ArrayList<Wall>();
	  wallList.addAll(blackWalls);
	  wallList.addAll(whiteWalls);
	  int prow = getCurrentPawnRow();
	  int pcol = getCurrentPawnColumn();
	  int orow;
	  int ocol;
	  
	  if (player.hasGameAsBlack()) {
		  orow = currentGame.getCurrentPosition().getWhitePosition().getTile().getRow();
		  ocol = currentGame.getCurrentPosition().getWhitePosition().getTile().getColumn();
	  } else {
		  orow = currentGame.getCurrentPosition().getBlackPosition().getTile().getRow();
		  ocol = currentGame.getCurrentPosition().getBlackPosition().getTile().getColumn();
	  }
	  
	  if (dir == MoveDirection.West) {
		  if ((ocol == pcol-1) && (orow == prow)) {
			  result = false;
		  }
	  }
	  
	  if (dir == MoveDirection.East) {
		  if ((ocol == pcol+1) && (orow == prow)) {
			  result = false;
		  }
	  }
	  
	  if (dir == MoveDirection.North) {
		  if ((ocol == pcol) && (orow == prow-1)) {
			  result = false;
		  }
	  }

	  if (dir == MoveDirection.South) {
		  if ((ocol == pcol) && (orow == prow+1)) {
			  result = false;
		  }
	  }
	  
		for (Wall wall : wallList) {
			int wallColumn = wall.getMove().getTargetTile().getColumn();
			int wallRow = wall.getMove().getTargetTile().getRow();
			Direction wallDirection = wall.getMove().getWallDirection();
			
			if (dir == MoveDirection.West) {
				if ((wallDirection == Direction.Vertical) && (wallColumn == pcol-1) && 
						((wallRow == prow-1) || (wallRow == prow))) {
					result = false;
					break;
				}
			}
			
			if (dir == MoveDirection.East) {
				if ((wallDirection == Direction.Vertical) && (wallColumn == pcol) && 
						((wallRow == prow-1) || (wallRow == prow))) {
					result = false;
					break;
				}
			}
			
			if (dir == MoveDirection.North) {
				if ((wallDirection == Direction.Horizontal) && (wallRow == prow-1) && 
						((wallColumn== pcol-1) || (wallColumn == pcol))) {
					result = false;
					break;
				}
			}
			
			if (dir == MoveDirection.South) {
				if ((wallDirection == Direction.Horizontal) && (wallRow == prow) && 
						((wallColumn== pcol-1) || (wallColumn == pcol))) {
					result = false;
					break;
				}
			}				
			
		}
		
    return result;
  }


  /**
   * Returns if it is legal to jump in the given direction
   * @author Pengnan Fan
   * @param dir
   * @return boolean
   */
  // line 123 "../../../../../StateMachine.ump"
  public boolean isLegalJump(MoveDirection dir){
	  currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
	  if(!currentGame.getGameStatus().equals(GameStatus.Running)) {
		  return false;
	  }
	  GamePosition currentPosition = currentGame.getCurrentPosition();
	  Player player = currentGame.getCurrentPosition().getPlayerToMove();
	  List<Wall> blackWalls = currentPosition.getBlackWallsOnBoard();
	  List<Wall> whiteWalls = currentPosition.getWhiteWallsOnBoard();
	  PlayerPosition playerPosition, opponentPosition;
	  if (player.hasGameAsBlack()) {
		  playerPosition = currentPosition.getBlackPosition();
		  opponentPosition = currentPosition.getWhitePosition();
	  } else {
		  playerPosition = currentPosition.getWhitePosition();
		  opponentPosition = currentPosition.getBlackPosition();
	  }
	  int[] playerCoord = new int[]{playerPosition.getTile().getColumn(), playerPosition.getTile().getRow()};
	  int[] opponentCoord = new int[]{opponentPosition.getTile().getColumn(), opponentPosition.getTile().getRow()};
	  int xDiff = playerCoord[0] - opponentCoord[0];
	  int yDiff = playerCoord[1] - opponentCoord[1];
	  int[] targetCoord;
	  if (dir.equals(MoveDirection.North)) {
		  targetCoord = new int[] {playerCoord[0], playerCoord[1]-2};
	  } else if (dir.equals(MoveDirection.South)) {
		  targetCoord = new int[] {playerCoord[0], playerCoord[1]+2};
	  } else if (dir.equals(MoveDirection.West)) {
		  targetCoord = new int[] {playerCoord[0]-2, playerCoord[1]};
	  } else if (dir.equals(MoveDirection.East)) {
		  targetCoord = new int[] {playerCoord[0]+2, playerCoord[1]};
	  } else {
		return false;  
	  }
	  
	  //Check if it is applicable to jump
	  if ((xDiff==1 && yDiff==0 && dir.equals(MoveDirection.West) && playerCoord[0]>2) 
			  || (xDiff==-1 && yDiff==0 && dir.equals(MoveDirection.East) && playerCoord[0]<8)
			  || (xDiff==0 && yDiff==1 && dir.equals(MoveDirection.North) && playerCoord[1]>2) 
			  || (xDiff==0 && yDiff==-1 && dir.equals(MoveDirection.South)) && playerCoord[1]<8) {
		  		//Check if it is out of index
			 	//Check if there is wall blocking
			  	int bWallSize = blackWalls.size();
			  	int wWallSize = whiteWalls.size();
			  	WallMove currentWallMove;
			  	Tile currentWallTile;
			  	Direction currentWallDirection;
			  	for(int i = 0; i<bWallSize; i++) {
			  		currentWallMove = blackWalls.get(i).getMove();
			  		currentWallTile = currentWallMove.getTargetTile();
			  		currentWallDirection = currentWallMove.getWallDirection();
			  		if (dir.equals(MoveDirection.North)
			  				&&currentWallDirection.equals(Direction.Horizontal)
			  				&&currentWallTile.getRow()==playerCoord[1]-1
			  				&&(currentWallTile.getColumn()==playerCoord[0]||currentWallTile.getColumn()==playerCoord[0]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.South)
			  				&&currentWallDirection.equals(Direction.Horizontal)
			  				&&currentWallTile.getRow()==playerCoord[1]
			  				&&(currentWallTile.getColumn()==playerCoord[0]||currentWallTile.getColumn()==playerCoord[0]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.West)
			  				&&currentWallDirection.equals(Direction.Vertical)
			  				&&currentWallTile.getColumn()==playerCoord[0]-1
			  				&&(currentWallTile.getRow()==playerCoord[1]||currentWallTile.getRow()==playerCoord[1]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.East)
			  				&&currentWallDirection.equals(Direction.Vertical)
			  				&&currentWallTile.getColumn()==playerCoord[0]
			  				&&(currentWallTile.getRow()==playerCoord[1]||currentWallTile.getRow()==playerCoord[1]-1)) {
			  			return false;
			  		}
			  		
			  		if (dir.equals(MoveDirection.North)
			  				&&currentWallDirection.equals(Direction.Horizontal)
			  				&&currentWallTile.getRow()==targetCoord[1]
			  				&&(currentWallTile.getColumn()==targetCoord[0]||currentWallTile.getColumn()==targetCoord[0]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.South)
			  				&&currentWallDirection.equals(Direction.Horizontal)
			  				&&currentWallTile.getRow()==targetCoord[1]-1
			  				&&(currentWallTile.getColumn()==targetCoord[0]||currentWallTile.getColumn()==targetCoord[0]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.West)
			  				&&currentWallDirection.equals(Direction.Vertical)
			  				&&currentWallTile.getColumn()==targetCoord[0]
			  				&&(currentWallTile.getRow()==targetCoord[1]||currentWallTile.getRow()==targetCoord[1]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.East)
			  				&&currentWallDirection.equals(Direction.Vertical)
			  				&&currentWallTile.getColumn()==targetCoord[0]-1
			  				&&(currentWallTile.getRow()==targetCoord[1]||currentWallTile.getRow()==targetCoord[1]-1)) {
			  			return false;
			  		}
			  	}
			  	for(int i = 0; i<wWallSize; i++) {
			  		currentWallMove = whiteWalls.get(i).getMove();
			  		currentWallTile = currentWallMove.getTargetTile();
			  		currentWallDirection = currentWallMove.getWallDirection();
			  		if (dir.equals(MoveDirection.North)
			  				&&currentWallDirection.equals(Direction.Horizontal)
			  				&&currentWallTile.getRow()==playerCoord[1]-1
			  				&&(currentWallTile.getColumn()==playerCoord[0]||currentWallTile.getColumn()==playerCoord[0]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.South)
			  				&&currentWallDirection.equals(Direction.Horizontal)
			  				&&currentWallTile.getRow()==playerCoord[1]
			  				&&(currentWallTile.getColumn()==playerCoord[0]||currentWallTile.getColumn()==playerCoord[0]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.West)
			  				&&currentWallDirection.equals(Direction.Vertical)
			  				&&currentWallTile.getColumn()==playerCoord[0]-1
			  				&&(currentWallTile.getRow()==playerCoord[1]||currentWallTile.getRow()==playerCoord[1]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.East)
			  				&&currentWallDirection.equals(Direction.Vertical)
			  				&&currentWallTile.getColumn()==playerCoord[0]
			  				&&(currentWallTile.getRow()==playerCoord[1]||currentWallTile.getRow()==playerCoord[1]-1)) {
			  			return false;
			  		}
			  		
			  		if (dir.equals(MoveDirection.North)
			  				&&currentWallDirection.equals(Direction.Horizontal)
			  				&&currentWallTile.getRow()==targetCoord[1]
			  				&&(currentWallTile.getColumn()==targetCoord[0]||currentWallTile.getColumn()==targetCoord[0]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.South)
			  				&&currentWallDirection.equals(Direction.Horizontal)
			  				&&currentWallTile.getRow()==targetCoord[1]-1
			  				&&(currentWallTile.getColumn()==targetCoord[0]||currentWallTile.getColumn()==targetCoord[0]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.West)
			  				&&currentWallDirection.equals(Direction.Vertical)
			  				&&currentWallTile.getColumn()==targetCoord[0]
			  				&&(currentWallTile.getRow()==targetCoord[1]||currentWallTile.getRow()==targetCoord[1]-1)) {
			  			return false;
			  		} else if (dir.equals(MoveDirection.East)
			  				&&currentWallDirection.equals(Direction.Vertical)
			  				&&currentWallTile.getColumn()==targetCoord[0]-1
			  				&&(currentWallTile.getRow()==targetCoord[1]||currentWallTile.getRow()==targetCoord[1]-1)) {
			  			return false;
			  		}
			  	}
			  	return true;
	  } else {
		  return false;
	  }
  }


 /**
  * this method will print out the error message when try to move pawn out of board
  * @auther Zirui He
  */
  // line 126 "../../../../../StateMachine.ump"
  public void illegalMove(){
	  System.out.println("Can't exceed boundary");
  }
  
  /**
   * this method will change MoveDirection to corresponding string
   * @author Zirui He
   * @param dir
   * @return String
   */
  private String determineDirection(MoveDirection dir) {
		if(dir == MoveDirection.South) {
			return "down";
		}
		if(dir == MoveDirection.North) {
			return "up";
		}
		if(dir == MoveDirection.East) {
			return "right";
		}
		if(dir == MoveDirection.West) {
			return "left";
		}
		else {
			return null;
		}
	}
}
