import sqlite3

conn = sqlite3.connect('atest.db')

cur = conn.cursor()

cur.execute(
    'CREATE TABLE IF NOT EXISTS movies (title, director, year)')

cur.execute(
    'INSERT INTO movies VALUES ("Jaws", "Spielberg", 1975)')