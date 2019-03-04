import sqlite3

conn = sqlite3.connect("Game.db")

players_table = "players"

cur = conn.cursor()

cur.execute('CREATE IF TABLE NOT EXISTS Game (name,playerid)')


