curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d '{"white":"Fischer","black":"Spassky","description":"Reykjavik"}' http://chess.webinar.cloudbees.net/chess/game/new ; echo
curl -H "Accept: application/json" http://chess.webinar.cloudbees.net/chess/game/1; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":1,"white":"e2-e4","move":1}' ; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":1,"black":"e7-e5","move":1}' ; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":1,"white":"g1-f3","move":2}' ; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":1,"black":"b8-c6","move":2}' ; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":1,"white":"f1-b5","move":3}' ; echo
curl -H "Accept: application/json" http://chess.webinar.cloudbees.net/chess/game/1; echo
curl -H "Accept: application/json" http://chess.webinar.cloudbees.net/chess/moves/1; echo

