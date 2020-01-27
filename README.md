# gomoku
gomoku game online desktop game build with java  

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
