INSERT INTO tb_usuario (nome, email, login, senha, nivel_acesso) VALUES
('admin', 'admin@domain.com', 'admin123', '123456', 'ADMIN'),
('user', 'user@domain.com', 'user123', '123456', 'USER');

INSERT INTO tb_artista (nome, biografia) VALUES
('Artist 1', 'Biography of Artist 1'),
('Artist 2', 'Biography of Artist 2');

INSERT INTO tb_musica (titulo, artista_id) VALUES
('Song 1', 1, 'rap', 'www'),
('Song 2', 2, 'rock', 'wwww');

INSERT INTO tb_playlist (nome, usuario_id) VALUES
('Playlist 1', 'true', 1, 9, 1),
('Playlist 2', 'false', 2, 8.5, 2);

INSERT INTO tb_musica_playlist (musica_id, playlist_id) VALUES
(1, 1),
(2, 2);