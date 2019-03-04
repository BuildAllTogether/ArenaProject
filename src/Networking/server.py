import bottle

players = [{"name": "Winson", "playerid": "1"},
           {"name": "Kyle", "playerid": "2"},
           {"name": "Ben", "playerid": "3"}]

newplayer = {"name": "HEHE", "playerid": "4"}
gameplayers = []


@bottle.get("/player")
def getAll():
    return {"players": gameplayers}


@get('/player/<name>')
def getOne(name):
    the_player = [player for player in players if player['name'] == name]
    return {'player' : the_player[0]}


@bottle.post('/join')
def addOne(name):
    add_player = bottle.request.body.read().decode()
    add_player = json.loads(add_player)
    gameplayers.append(add_player['name'])
    return {'players' : gameplayers}

@bottle.post('/leave')
def remove_player(name):
    the_player = [player for player in players if player['name'] == name]
    gameplayers.remove(the_player[0])
    return {'players' : gameplayers}


run(host="0.0.0.0", port=8080, debug=True)