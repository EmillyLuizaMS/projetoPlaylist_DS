-- Usuários iniciais. Lembre-se que estas senhas em texto plano não servem para login.
INSERT INTO tb_usuario (nome, email, senha, login, nivel_acesso) VALUES ('Admin User', 'admin@email.com', '123456', 'admin', 'ADMIN');
INSERT INTO tb_usuario (nome, email, senha, login, nivel_acesso) VALUES ('Normal User', 'user@email.com', '123456', 'normal_user', 'USER');

-- Artistas
INSERT INTO tb_artista (nome, descricao) VALUES ('Queen', 'Banda de rock britânica formada em 1970.');
INSERT INTO tb_artista (nome, descricao) VALUES ('AC/DC', 'Banda de hard rock australiana formada em 1973.');

-- Músicas
INSERT INTO tb_musica (nome, url_musica, id_artista) VALUES ('Bohemian Rhapsody', 'https://www.youtube.com/watch?v=fJ9rUzIMcZQ', 1);
INSERT INTO tb_musica (nome, url_musica, id_artista) VALUES ('Thunderstruck', 'https://www.youtube.com/watch?v=v2AC41dglnM', 2);
INSERT INTO tb_musica (nome,  url_musica, id_artista) VALUES ('Another One Bites the Dust', 'https://www.youtube.com/watch?v=rY0WxgSXdEE', 1);

-- Playlists
INSERT INTO tb_playlist (nome, visibilidade, nota_media, usuario_id) VALUES ('Rock Classics', true, 9.5, 1);
INSERT INTO tb_playlist (nome, visibilidade, nota_media, usuario_id) VALUES ('My Favorite Songs', false, 8.0, 2);

-- Associando músicas às playlists
INSERT INTO tb_musicas_playlist (id_musica, id_playlist) VALUES (1, 1);
INSERT INTO tb_musicas_playlist (id_musica, id_playlist) VALUES (2, 1);
INSERT INTO tb_musicas_playlist (id_musica, id_playlist) VALUES (3, 2);