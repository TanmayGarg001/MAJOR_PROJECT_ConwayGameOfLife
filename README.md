# ConwayGameOfLife             

•	This is GAME OF LIFE. Created in 1970 by John Conway. It is implemented/played on infinte grid of square cells each of which has 2 states at a single given point :- alive or dead. Beside this there are only 3 rules :                        
1.	Birth Rule: An empty, or “dead”, cell with precisely 3 “live” neighbors (full cells) becomes live                      
2.	Death Rule: A live cell with 0 or 1 neighbors dies of isolation, a live cell with 4 or more neighbors dies of overcrowding                    
3.	Survival Rule:  A live cell with 2 or 3 neighbors remains alive.                  
                       
•	With each iteration, some cells live, some die and “life-forms” evolve, one  generation to the next.      
               
•	Here a cell’s neighbour can be at these 8 spots :- North,East,West,South,NE,NW,SE,SW.       
                        
•	Now start simulation and we see wide varities of behaviours some patters go for 10seconds and then vanish or keep repeating(oscilate).  Some keep growing for hours. Some travel across the grid infintely.                        
                        
•	Now by seeing a pattern we can’t predict that what would happen to it, it is impossible to answer. So it is UNDICEDABLE ,hence there is no algorithm that is guaranteed to answer the question in finite time. We could of course run the pattern itself but still we won’t be able to tell if the pattern will stop after 1000years or 1million years there is no certainity. It is TURING COMPLETE.                  
                 

