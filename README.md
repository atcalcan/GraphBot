# GraphBot
### Calcan Teodor Alexandru

<p align="center">
<br/>
  <img src="pfp.png" width="15%" alt="GraphBotProfilePicture">
<br/>
</p>

GraphBot este un bot de Discord scris în Java, cu misiunea de a ajuta studenţii să lucreze cu grafuri.

## Ce poate GraphBot să facă

 - să creeze grafuri noi într-o bază de date, pe care doar utilizatorul care le-a creat le poate accesa
 - să îi arate unui utilizator ce grafuri are, oricând
 - să genereze reprezentări grafice pentru orice graf din memorie
 - să editeze grafuri deja existente în memorie
 - să trimită definiţii pentru concepte de bază din teoria grafurilor
 - să ruleze algoritmi pe grafurile din memorie
 - să ghideze utilizatorul prin folosirea botului

## Ce pachete externe foloseşte GraphBot

 - [JDA (Java Discord API)](https://github.com/DV8FromTheWorld/JDA) - pentru comunicarea cu Discord
 - [graphviz-java](https://github.com/nidi3/graphviz-java) - pentru generarea reprezentărilor grafurilor
 - [jackson-databind](https://github.com/FasterXML/jackson-databind) - pentru lucrul cu date în format JSON în baza de date
 - [mssql-jdbc](https://mvnrepository.com/artifact/com.microsoft.sqlserver) - pentru comunicarea cu baza de date

## Cum se "instalează" GraphBot

Utilizatorul care vrea să folosească GraphBot ar trebui, în primul rând, să aibă un server de Discord. Apoi, accesând [linkul acesta](discord-link), va putea invita botul pe serverul său. GraphBot foloseşte o bază de date, deci oriunde s-ar întâlni un utilizator cu GraphBot, acesta va şti exact ce grafuri a creat acel utilizator.

## Comenzile lui GraphBot

 - ```!graph``` sau ```!graph help``` va trimite explicaţii despre comenzile principale, similare cu cele de aici
 - ```!graph new; (directed /undirected ); node1 node2 ... ; edge1, edge2, ...;``` va crea un graf nou; GraphBot primeşte orice String ca nume pentru noduri
 - ```!graph add graphName nodeName``` va adăuga un nod nou într-un graf existent
 - ```!graph add graphName (nodeName1-nodeName2)``` va adăuga o muchie nouă într-un graf existent *(doar dacă nodurile există deja!)*
 - ```!graph rmv/remove graphName``` va **şterge din memorie un graf existent**
 - ```!graph rmv/remove graphName nodeName``` va elimina un nod dintr-un graf existent
 - ```!graph rmv/remove graphName (nodeName1-nodeName2)``` va elimina o muchie dintr-un graf existent
 - ```!graph show``` va trimite o listă cu numele grafurilor la care are utilizatorul acces
 - ```!graph display graphName``` va genera şi va trimite o reprezentare grafică a grafului vizat, numită special după numele utilizatorului care a cerut-o
 - ```!graph run``` va trimite informaţii despre programele disponibile, într-o formulă similară cu cea prezentă mai jos
 - ```!graph run command...``` va rula unul dintre programele disponibile pe un graf vizat
 - ```!graph def query...``` va căuta definiţii bazate pe query-ul trimis de utilizator, va formata informaţia primită şi o va trimite

## Programele pe care le poate rula GraphBot

 - ```!graph run dfs graphName startNode``` va rula algoritmul Depth First Search/Transversal pe graful *graphName*, pornind din nodul *startNode*
 - ```!graph run bfs graphName startNode``` va rula algoritmul Breadth First Search/Transversal pe graful *graphName*, pornind din nodul *startNode*
 - ```!graph run hamCycle graphName``` va căuta un ciclu hamiltonian în *graphName* şi, dacă există, îl va trimite
 - ```!graph run hamPath graphName``` va căuta un parcurs hamiltonian în *graphName* şi, dacă există, îl va trimite
 - ```!graph run minColoring graphName``` va afla care este numărul minim de culori pentru a colora *graphName* şi va trimite distribuţia culorilor
 - ```!graph run mColoring graphName m``` va afla dacă *graphName* este *m*-colorabil şi va trimite distribuţia culorilor dacă aceasta există

[discord-link]: <https://discord.com/api/oauth2/authorize?client_id=982334706374811670&permissions=8&scope=bot>