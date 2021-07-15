using System;
using System.Collections.Generic;
using System.Text;

namespace PokerApp
{
    class Deck
    {
        private List<Card> cards = new List<Card>();

        public Deck()
        {
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 13; j++) {
                    cards.Add(new Card(i, j));
                }
            }
            Console.WriteLine("The Deck has " + cards.Count + " cards in it!\n");
        }

        //Displays a random card before removing it from the deck. If there are no cards then it displays a different message.
        public void Deal_One_Card()
        {
            if (cards.Count < 1)
            {
                Console.WriteLine("Sorry! There are no more cards to be dealt.");
            }
            else
            {
                Random r = new Random();
                int card = r.Next(0, (cards.Count - 1));
                Console.WriteLine("Dealt " + cards[card].GetName());
                cards.RemoveAt(card);
                Console.WriteLine("The Deck has " + cards.Count + " cards in it!\n");
            }
        }

        //Shuffles the deck randomly, displays the new first card to prove it was randomly shuffled.
        public void shuffle()
        {
            Random r = new Random();
            for (int i = 0; i < cards.Count; i++)
            {
                int rand = r.Next(0, i);
                Card c = cards[rand];
                cards[rand] = cards[i];
                cards[i] = c;
            }
            Console.WriteLine("The Deck has been shuffled! First card is now: " + cards[0].GetName() + "\n");
        }
      }
    }
