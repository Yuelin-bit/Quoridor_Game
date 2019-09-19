using TwoPlayerAi.DataStructures;

namespace TwoPlayerAi.Quoridor
{
    public class Fence
    {
        public Vector StartVector { get; }

        public Vector EndVector { get; }

        public Fence(Vector startVector, Vector endVector)
        {
            StartVector = startVector;
            EndVector = endVector;
        }

        public bool Horizontal
        {
            get
            {
                return this.StartVector.X == this.EndVector.X;
            }
        }

        public bool Vertical
        {
            get
            {
                return this.StartVector.Y == this.EndVector.Y;
            }
        }

        public Vertices
        {

        }
    }
}