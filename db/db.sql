INSERT INTO users (username, password, is_admin)
VALUES ('admin', 'adminpass', 1);

-- Regular user: username `user`, password `userpass`
INSERT INTO users (username, password, is_admin)
VALUES ('user', 'userpass', 0);