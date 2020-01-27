# gomoku

gomoku game online desktop game build with java  

Gomoku is an abstract strategy board game and is also called Five in a Row. It is traditionally played on a board with size 19x19. However, because once placed, pieces are not moved or removed from the board, Gomoku may also be played as a paper and pencil game. This game is known in several countries under different names

requiements
    - java 
		

Execution 

    - server
        - to lunch the game you will need to lunch server  based on socket 
            cd server 
            javac Main.java
            java Main  --ipInterface  --port
       - exemple 
            java Main 127.0.0.1 8000
    - client
		
         - now let play  we have to connect to server and start playing max players 2
         - to lunch to client 
            cd Client
            javac Main.java 
            java Main --name host port
        - exemple
            java Main jamal 127.0.0.0.1 8000
