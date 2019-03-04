
from bottle import get, run, post, request

players = [{"name": "Winson", "playerid": "1"},
           {"name": "Kyle", "playerid": "2"},
           {"name": "Ben", "playerid": "3"}]

newplayer = {"name": "HEHE", "playerid": "4"}
gameplayers = []


@get("/player")
def getAll():
    return {"players": gameplayers}


@get('/player/<name>')
def getOne(name):
    the_player = [player for player in players if player['name'] == name]
    return {'player' : the_player[0]}


@post('/join')
def addOne(name):
    add_player = {'name' : request.json.get('name'), 'type' : request.json.get('type')}
    gameplayers.append(add_player)
    return {'players' : gameplayers}

@post('/leave')
def remove_player(name):
    the_player = [player for player in players if player['name'] == name]
    gameplayers.remove(the_player[0])
    return {'players' : gameplayers}


run(host="0.0.0.0", port=8080, debug=True)