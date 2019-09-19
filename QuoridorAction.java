using TwoPlayerAi.Search;

namespace TwoPlayerAi.Quoridor
{
    public class QuoridorAction : IAction<QuoridorState>
    {
        public QuoridorState FromState { get; }

        public QuoridorState ToState { get; }

        public int Cost { get; }

        public QuoridorAction(QuoridorState fromState, QuoridorState toState)
        {
            FromState = fromState;
            ToState = toState;
            Cost = 1;
        }
    }

    public class FenceAction
    {
        public FenceAction(QuoridorState state, Fence fence)
        {
            state.Board.RemoveEdge()
        }
    }

    public class PlayerAction
    {

    }
}