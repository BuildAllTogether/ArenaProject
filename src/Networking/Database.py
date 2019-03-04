import sqlite3

conn = sqlite3.connect('game.db')
cur = conn.cursor()

cur.execute('CREATE TABLE IF NOT EXISTS players (name, playerid)')

def insert(name, playerid):
    cur.execute('INSERT INTO players VALUES (?,?)', (name, playerid))

insert("Can", "9")
