import sqlite3

conn = sqlite3.connect("Games.db")
cur = conn.cursor()

cur.execute("INSERT INTO sqlite_master VALUES ('Kyle', 100)")

conn.commit()

conn.close()