# Skill Test

The purpose of this repo is to verify yours skills as a developer and 
to show how you think and implement things

---
### How to do this test
1. Fork this repo
2. Create a develop branch on your fork
3. Create branches and commits for your implementations using git flow guidelines
4. After finishing, create a PR from your develop to the main branch of this repo
5. Build an APK and attach it on the PR as well

---
### Feature Goals

Implement a simple application that consumes the [Deck of Cards API](https://deckofcardsapi.com/),
and with it displays two screens:

1. A screen to select from the list of all cards on the deck, the number of players and the maximum amount of cards on each player's hand.
2. A screen to show each player's hand randomized from the selected cards, and the remaining cards if any.

### Features
Feel free to implement the best UI/UX you can think of. It is not mandatory for it to have a good design, but try to show your skills when building it.

#### First Screen
1. List/Grid with all cards for the user to select
   - Show selected cards in a different way, so it is easy to spot selected from unselected ones
   - Make use of the API to load the card image and display them on a grid or list
   
2. A form to get the number of players and the maximum amount of cards for each player
   - The form should validate if the inputs are bigger than zero and if the amount of selected cards can supply at least one card per player
3. After everything is set, create a new dack on the API with only the selected cards
   - Create each player's hand and the draw pile using the API piles

#### Second Screen
1. List of players showing theirs hands
2. List/Grid of the remaining cards if any
3. A way to go back to change the game parameters

### Test Goals
1. Test your ability to create a responsive UI and the layers of code architecture needed to make the code clean and encapsulated
2. Verify your ability to understand and solve problems. So feel free to ask questions if you have any doubts 
3. Implement tests! You don't need to test every single class, but at least one for each layer of the architecture
