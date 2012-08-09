curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d '{"white":"Spassky","black":"Fischer","description":"Reykjavik2"}' http://chess.webinar.cloudbees.net/chess/game/new ; echo
curl -H "Accept: application/json" http://chess.webinar.cloudbees.net/chess/game/2; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":2,"white":"e2-e4","move":1}' ; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":2,"black":"e7-e5","move":1}' ; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":2,"white":"f2-f4","move":2}' ; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":2,"black":"e5xf4","move":2}' ; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":2,"white":"g1-f3","move":3}' ; echo
curl -H "Accept: application/json" -H "Content-Type: application/json" http://chess.webinar.cloudbees.net/chess/moves/new -X POST -d '{"game":2,"black":"g7-g5","move":3}' ; echo
curl -H "Accept: application/json" http://chess.webinar.cloudbees.net/chess/game/2; echo
curl -H "Accept: application/json" http://chess.webinar.cloudbees.net/chess/moves/2; echo

