using System;

namespace PokerApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Deck deck = new Deck();
            deck.Deal_One_Card();
            deck.shuffle();
            for (int i = 0; i < 52; i++)
            {
                deck.Deal_One_Card();
            }
        }
    }
}
