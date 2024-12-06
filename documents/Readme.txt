Readme.txt
CS3343 Software Engineer Practice (Group 14)

=====================================================================================================

Project: Slay The Dragon

=====================================================================================================
<< Game Background >>
=====================================================================================================

Slay the Dragon is a single-player deck-building game with a rouge-like gaming style.

The dragon, the final boss of the tower, kidnaps the princess and puts the princess on the top of the tower.
The player role is that of a hero who needs to rescue the princess. 
The tower has different levels; to reach the top, the hero has to face different monsters along the way. 
There will be elite and ordinary monsters waiting for the hero. 
Finally, if the hero successfully reaches the top, he has to defeat the dragon to take back the princess.

=====================================================================================================
<< Installation >>
=====================================================================================================

Step 1. Open project in Eclipse
Step 2. Right-click the project -> Export​
Step 3. Java -> JAR file​
Step 4. Choose the project as the source​
Step 1. Choose the destination of the JAR file​
Step 6. Click next
Step 7. Make sure the Main class is selected as the application entry point
Step 8. Open the terminal and go to the directory that saves the .jar file
Step 9. Run the command ”java -jar <your file name>.jar ”
Step 10. Enjoy the game

=====================================================================================================
<< User Guideline >>
=====================================================================================================

< Type of Enemy >
Normal: Orc, Goblin, Slime, Skeleton, Lich, Beholder
Elite: Berserker, Death Knight, Mind Flayer
Boss: Dragon

< Type of Cards >
Attack Card
(Deal damages can be blocked by blocks)
Defense Card
(Gain blocks to block damage)
Skill Card -- Heal, Poison, Attack Buff, Defense Buff, Draw
Heal Card
(Restore health points)
Poison Card
(Poison damage can accumulate and pierce through blocks. Once poison damage is inflicted, the accumulated poison damage will reduce by 1 each turn.)
Attack Buff Card
(Buff next played attack card)
Defense Buff Card
(buff next played defense card)
Draw Card
(Players can draw additional cards without counting against their card usage limit for the turn.)
**Both three types have basic and advanced levels**

< Basic Instruction >
1. The system will first display the enemy's status and action. 
(Enemy action will apply after player action ends, which means if the player kills the enemy during player action,
enemy action will not apply to the player)
2. The player will get five cards to hand in each round, and there will be 3 times to play the card in hand.
(Except using Skill Card: Draw, this skill card will provide one more chance for the player)
3. The player will do the action immediately when the player plays a card
4. The player will get a reward after defeating the enemy
   Reward:
   -- Normal enemy -> Advance Card
   -- Elite enemy -> Recover all health points / permanent effect
5. Player Status will remain until the next battle, like remaining health point
6. Player once the health point becomes 0, the player will die, and game is over, the player must play again from level 1

< Draw Card Mech >
1. At the beginning of every battle, the deck is initialized with a predefined set of cards.
2. After initialization, the deck is shuffled once before the battle starts.
3. At the start of every turn, 5 cards are drawn from the deck into the player's or foe's hand.
4. Cards are drawn in a queue format, popping from the deck to the hand.
5. During the player's or foe's turn, 3 cards must be used from the hand.
6. After using the cards, the turn ends.
7. At the end of the turn, all cards in hand (used or unused) are moved to the discard queue.
8. If the deck contains fewer than 5 cards at the start of a turn.
   Recycling Cards:
   -- All cards in the discard queue are pushed back -> deck.
   -- The deck is reshuffled once.
9. This cycle of drawing, using, discarding, and recycling continues until the battle ends.
=====================================================================================================

< Game Flow >
_____________________________________________  
|Choose an effect:                            |  <-- Console
|1. Extra Max Health (Permanent)              |
|2. Extra Defense (Permanent)                 |
|Enter the number of your choice:             |
|                                             |
|_____________________________________________|
 _____________________________________________
|1                                            |  <-- Input Command
|_____________________________________________|

 ______________________________________________________________________________
|You have chosen: Extra Max Health (Permanent)                                 |  
|======== Level 1 : Normal Battle begins between player and Orc ========       |  <-- Level
|                                                                              |
|=========================== Round 1 ===========================               |  <-- Round in Battle
|Orc Stats: Health - 30.0/30.0, Defense - 1.0, Strength - 2.0                  |  <-- Enemy Status
|Orc played cards:                                                             |  <-- Enemy Actions
|Basic Attack Card - Deal 5 damage.                                            |  
|Basic Defend Card - Gain 5 block.                                             |
|Basic Heal Card - Heal 5 HP.                                                  |
|Damage To Player: 7 ,Block: 6                                                 |  <-- Data After Calculation  
|______________________________________________________________________________|            
 ______________________________________________________________________________       
|=========================== Your Action: ===========================          |  
|Player Stats: Health - 160.0/160.0, Defense - 0.0, Strength - 0.0             |  <-- Player Status
|Choose a card:                                                                |      __
|1. Basic Attack Card - Deal 5 damage.                                         |     |
|2. Basic Draw Card - Drawn 2 cards.                                           |     |
|3. Basic Heal Card - Heal 5 HP.                                               |  <--|  Player cards in hand
|4. Basic Draw Card - Drawn 2 cards.                                           |     |
|5. Basic Defend Card - Gain 5 block.                                          |     |__
|Enter the number of your choice:                                              |      
|______________________________________________________________________________|
 _____________________________________________
|1                                            |  <-- Input Command (Which card player would like to play)
|_____________________________________________|      **Player will play the card immediately


 ______________________________________________________________________________
|player choose: Basic Attack Card                                              |  <-- Player played card
|Player Damage To Orc: 0                                                       |  <-- Result of player action
|Orc Status: Health: 30.0, Attack: 7, Remain Block: 1                          |  <-- Updated enemy status    
|============================                                                  |     
|Choose a card:                                                                |      __
|1. Basic Draw Card - Drawn 2 cards.                                           |     |  
|2. Basic Heal Card - Heal 5 HP.                                               |  <--|  Player cards in hand
|3. Basic Draw Card - Drawn 2 cards.                                           |     |
|4. Basic Defend Card - Gain 5 block.                                          |     |__
|Enter the number of your choice:                                              |      
|______________________________________________________________________________|
 _____________________________________________
|1                                            |  <-- Input Command (Which card player would like to play)
|_____________________________________________|


 ______________________________________________________________________________
|player choose: Basic Draw Card                                                |  <-- Player played card
|Player apply skill: Drawn 2 cards.                                            |  <-- Result of player action
|============================                                                  |      
|Choose a card:                                                                |      __
|1. Basic Heal Card - Heal 5 HP.                                               |     |  
|2. Basic Draw Card - Drawn 2 cards.                                           |     |  
|3. Basic Defend Card - Gain 5 block.                                          | <-- |   Player cards in hand
|4. Basic Defend Card - Gain 5 block.                                          |     |
|5. Basic Attack Card - Deal 5 damage.                                         |     |__
|Enter the number of your choice:                                              |      
|______________________________________________________________________________|
 _____________________________________________
|5                                            |  <-- Input Command (Which card player would like to play)
|_____________________________________________|


 ______________________________________________________________________________
|player choose: Basic Attack Card                                              |  <-- Player played card
|Player Damage To Orc: 4                                                       |  <-- Result of player action
|Orc Status: Health: 26, Attack: 7, Remain Block: 0                            |  <-- Updated enemy status    
|============================                                                  |     
|Choose a card:                                                                |      __
|1. Basic Heal Card - Heal 5 HP                                                |     |  
|2. Basic Draw Card - Drawn 2 cards.                                           |  <--|  Player cards in hand
|3. Basic Defend Card - Gain 5 block.                                          |     |
|4. Basic Defend Card - Gain 5 block..                                         |     |__
|Enter the number of your choice:                                              |      
|______________________________________________________________________________|

 _____________________________________________
|3                                            |  <-- Input Command (Which card player would like to play)
|_____________________________________________|


 ______________________________________________________________________________
|player choose: Basic Defend Card                                              |  <-- Player played card
|Player Gain 5 block.                                                          |  <-- Result of player action
|Current block in round: 5                                                     |  <-- Updated player status    
|============================                                                  |     
|Orc Damage To Player: 7                                                       |  
|Player block: 5                                                               |      
|Player take 2 damage                                                          |  <-- Enemy action to player
|Player Status: Health: 158                                                    |  <-- Update player status
|=========================== End Round 1 ===========================           |
|                                                                              |
|=========================== Round 2 ===========================               |  <-- Go to next round
|______________________________________________________________________________|

 ** cycle until player or enemy die **

 Reward
 ______________________________________________________________________________
|Orc is defeated                                                               |  
|============================                                                  |  
|Player wins!                                                                  |      
|Normal Reward: (Choose one of the reward below)                               |
|1. Advanced Attack Buff - Attack buffed 2.0 times.                            |
|2. Advanced Draw Card - Drawn 3 cards.                                        |
|3. Advance Heal Card - Heal 10 HP.                                            |
|4. Advance Defend Card - Gain 8 block.                                        |
|5. Advance Poison Card - Deal 10 damage.                                      |
|6. Advance Attack Card - Deal 10 damage.                                      |
|7. Advanced Defense Buff Card - Defense buffed 2.0 times.                     |
|Enter the number of your choice:                                              |
|______________________________________________________________________________|


=====================================================================================================
<< Team Information >>
=====================================================================================================
 ___________________________________________
| Position                  | Member        |
|===========================================|
|Project Manager            | Tsoi Yiu Chik |
|___________________________|_______________|
|Assistant Project Manager  | Wong Kin Name |
|___________________________|_______________|
|Development and Test Led   | Li Chun Wai   |
|___________________________|_______________|
|Programmer                 | Luk Yin Wai   |
|___________________________|_______________|
|Programmer                 | Wat Ho Yin    |
|___________________________|_______________|