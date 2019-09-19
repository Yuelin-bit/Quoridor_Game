using System.Collections.Generic;
using TwoPlayerAi.Games;

namespace TwoPlayerAi.Quoridor
{
    public class QuoridorState
    {
        public Board Board { get; set; }

        public IEnumerable<QuoridorPlayer> Players { get; set; }

        public int Round { get; set; }

        public FenceAction PlaceFence(Fence fence)
        {

        }

        public PlayerAction MovePlayer(QuoridorPlayer player, Vector from, Vector to)
        {

        }
    }
}