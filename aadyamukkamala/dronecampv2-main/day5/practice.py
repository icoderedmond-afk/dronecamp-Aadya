def main():
    difficulty = input("Difficult or Casual?")
    if not (difficulty == "Difficult" or difficulty == "Single-player"):
        print("Enter a valid difficulty")
        return

    players = input("Multiplayer or Single-player?")
    if not (players == "Difficult" or difficulty == "Casual"):
            print("Enter a valid nuber of players")
            return

    if difficulty == "Difficult":
        recommend("Poker")
    elif difficulty == "Difficult" and players == "Single-player":
        recommend("Klondike")
    elif difficulty == "Casual" and players == "Multiplayer":
         recommend("Hearts")
    else:
         recommend("Clock")

def recommend(game):
    print("You might like", game)

main()