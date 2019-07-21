# Progetto-E19

Realizzazione di un clone del gioco Flappy Bird con alcune modifiche alle meccaniche di gioco e aggiunta di multiplayer a 2 giocatori. Altri aspetti:

* Resa grafica del gioco (libreria slick2d)
* Architettura peer to peer per il multigiocatore
* Diversi livelli di difficoltà per il singleplayer
* Implementazione di una classifica per i punteggi del singleplayer
* Scelta di temi grafici

Istruzioni per l'installazione e l'esecuzione:
- Clonare il progetto E19 da GitHub a IntelliJ
- click su File -> Project Structure -> libraries
- click su + -> selezionare Java -> selezionare la cartella "slick2d"  -> apply
- rimanendo su Project structure, nella sezione Modules, selezionare la cartella src all' interno della directory "flappy" e selezionare mark directory as source. ->apply ->ok
- all' interno di src, nel package "main" fare click destro sulla classe "Launcher" e selezionare l' opzione "create Launcher.main..."
- nel pannello apertosi nella riga "VM OPTIONS" incollare la stringa seguente:  
- Per piattaforme Windows: "-Djava.library.path=nativesWindows/" (testato)
- Per piattaforme Linux: "-Djava.library.path=nativesLinux/" (non testato)
- dallo stesso pannello, nella riga "Working directory" al termine del path già inserito aggiungere "\flappy" -> Apply -> ok
- eseguire il programma

Istruzioni di gioco:
- Per saltare premere la barra spaziatrice.
- Nel multiplayer per usare un powerUp premere Q, W o E in base al powerUp desiderato.
- Per uscire dal gioco premere il pulsate "esc" dal menu principale.
