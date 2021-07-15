using System;
using System.Collections.Generic;
using System.Text;

namespace PokerApp
{
    class Card
    {
        private String suit;
        private String rank;
        private String name;

        public Card(int s, int r)
        {
            int i = 2;
            switch (s)
            {
                case 0: this.suit = "Hearts"; break;
                case 1: this.suit = "Diamonds"; break;
                case 2: this.suit = "Clubs"; break;
                case 3: this.suit = "Spades"; break;
            }

            switch (r)
            {
                default: this.rank = i.ToString(); i++; break;
                case 0: this.rank = "Ace"; break;
                case 10: this.rank = "Jack"; break;
                case 11: this.rank = "Queen"; break;
                case 12: this.rank = "King"; break;
            }

            this.name = "The " + rank + " of " + suit + "!";
        }

        public String GetName()
        {
            return name;
        }
    }
}
