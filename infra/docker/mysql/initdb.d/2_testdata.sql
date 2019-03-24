USE twitter_db;

INSERT INTO users (id, email, password, name) VALUES (1, "a@example.com", "password", "一太郎");

INSERT INTO tweets (id, user_id, message) VALUES (1, 1, "一太郎はじめてのツイート！");