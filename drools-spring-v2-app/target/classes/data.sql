INSERT INTO authority(
	id, authority_name)
	VALUES (1, 'ROLE_ADMIN');

INSERT INTO authority(
	id, authority_name)
	VALUES (2, 'ORDINARY_USER');

INSERT INTO user(
	id, password, username, doctor_name ,surname , specialist)
	VALUES (1, '$2a$10$S3rxpwjnJUrmgMrnMCJo8eIRCFvCcmzuPi5Y3Okz67i/2sj6xMfau', 'a', 'Milan' , 'Salic', 'dijagnosticar');

INSERT INTO user(
	id, password, username, doctor_name ,surname , specialist)
	VALUES (3, '$2a$10$S3rxpwjnJUrmgMrnMCJo8eIRCFvCcmzuPi5Y3Okz67i/2sj6xMfau', 'a1', 'Marko' , 'Markovic', 'hirurg');


INSERT INTO user(
	id, password, username, doctor_name ,surname , specialist)
	VALUES (4, '$2a$10$S3rxpwjnJUrmgMrnMCJo8eIRCFvCcmzuPi5Y3Okz67i/2sj6xMfau', 'a2', 'Nikola' , 'Nikolic', 'ortoped');

INSERT INTO user(
	id, password, username, doctor_name, surname, specialist)
	VALUES (2, '$2a$10$S3rxpwjnJUrmgMrnMCJo8eIRCFvCcmzuPi5Y3Okz67i/2sj6xMfau', 'admin', 'Janko', 'Jankovic', 'admin');


INSERT INTO user_authority(
	id, authority_id, user_id)
	VALUES (1, 2, 1);

INSERT INTO user_authority(
  id, authority_id, user_id)
  VALUES (2, 1 , 2);

INSERT INTO user_authority(
  id, authority_id, user_id)
  VALUES (3, 2 , 3);

INSERT INTO user_authority(
  id, authority_id, user_id)
  VALUES (4, 2 , 4);

