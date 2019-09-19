using System;
using System.Linq;
using System.Collections.Generic;
using TwoPlayerAi.AdversarialSearch;
using TwoPlayerAi.Games;
using TwoPlayerAi.DataStructures;

namespace TwoPlayerAi.Quoridor
{
    public class Quoridor : IGame<QuoridorState>
    {
        public QuoridorState InitialState()
        {
        
            Board board = new Board(5);
            QuoridorPlayer MinPlayer = new MinPlayer("Min");
            MinPlayer.FencesCount = 10;
            MinPlayer.Position = new Vector(4, 8); 

            QuoridorPlayer MaxPlayer = new MaxPlayer("Max");
            MaxPlayer.FencesCount = 10;
            MaxPlayer.Position = new Vector(4, 0);

            QuoridorState state = new QuoridorState();
            state.Board = board;
            state.Players = new List<QuoridorPlayer>{MaxPlayer, MinPlayer};
            state.Round = 0;

            return state;
        }

        public QuoridorPlayer Player(QuoridorState state)
        {
            if( state.Round % 2 == 0)
            {
                return state.Players.First(p => p.IsMax);
            }
            else 
            {
                return state.Players.First(p => p.IsMin);
            }    
        }

        public QuoridorState Result(QuoridorState state, QuoridorAction<QuoridorState> action)
        {

        }

        public bool TerminalTest(QuoridorState state)
        {
            return true;
        }

        public int UtilityFunction(QuoridorState state, QuoridorPlayer player)
        {
            return 1;
        }

        public int HeuristicFunction(QuoridorState state, QuoridorPlayer player)
        {
            return 1;
        }
    }
}