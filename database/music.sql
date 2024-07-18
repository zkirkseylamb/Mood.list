begin transaction;
drop table if exists track,mood,playlist,users,roles;

create table track (
	track_id serial PRIMARY KEY,
	title varchar(255),
	description varchar(255),
	artist varchar(255),
	energy int,
    valence int
);

create table mood (
	mood_id serial PRIMARY KEY,
	mood varchar(255) not null,
	track_id int,
	energy int,
    valence int,
	FOREIGN KEY (track_id) references track(track_id)

);

create table playlist (
    playlist_id serial PRIMARY KEY,
    track_id int,
	mood_id int,
	mood varchar(255),
    FOREIGN KEY (track_id) references track(track_id),
	FOREIGN KEY (mood_id) references mood(mood_id)
);

create table users (
    username varchar(255) PRIMARY KEY,
    password varchar(255)
);

create table roles (
    username varchar(255) references users,
    role varchar(255) not null,
    PRIMARY KEY(username,role)
);

insert into users(username,password) values('admin','$2a$10$H1K3D.4YpnZZcCOLxI5OQOYb7NHddVS6gl7QiiMNn/HvHtIpBMPWm');
insert into users(username,password) values('test','$2a$10$.AK9lTWvxxlIHhJaO6D4KuqH8ZC46500C6CcRfpId1YFYbYeMm8Ue');

insert into roles(username,role) values('admin','ADMIN');
insert into roles(username,role) values('test','ANONYMOUS');


insert into track(title, description, artist, energy, valence) values('What a Wonderful World','Genre: Jazz, Released: 1967','Louis Armstrong',3, 0);
insert into track(title, description, artist, energy, valence) values('Fast Car','Genre: Folk Rock , Released: 1988 ','Tracy Chapman',3, 0);
insert into track(title, description, artist, energy, valence) values('Fake Plastic Trees','Genre: Alt. Rock , Released: 1995','Radiohead',3, 0);
insert into track(title, description, artist, energy, valence) values('Nothing Compares 2 U','Genre: Pop, Released: 1990','Sinéad OConnor',3, 0);
insert into track(title, description, artist, energy, valence) values('Angel','Genre: Adult Contemporary, Released: 1998','Sarah McLachlan',3, 0);
insert into track(title, description, artist, energy, valence) values('Fix You','Genre: Alt. Rock, Released: 2005','Coldplay',3, 0);
insert into track(title, description, artist, energy, valence) values('Wild Horses','Genre: Country Rock, Released: 1971','The Rolling Stones',3, 0);
insert into track(title, description, artist, energy, valence) values('Wake Me Up When September Ends','Genre: Emo, Released: 2005','Green Day',3, 0);
insert into track(title, description, artist, energy, valence) values('The Sound of Silence','Genre: Folk Rock, Released: 1966','Simon & Garfunkel',3, 0);
insert into track(title, description, artist, energy, valence) values('Mad World','Genre: Soft Rock, Released: 2003','Gary Jules',3, 0);
insert into track(title, description, artist, energy, valence) values('Girl From the North Country ','Genre: Folk, Released: 1963','Bob Dylan & Johnny Cash',0, 7);
insert into track(title, description, artist, energy, valence) values('How Ya Doin’ ','Genre: Folk, Released: 2022','Neil Young & Crazy Horse',0, 7);
insert into track(title, description, artist, energy, valence) values('Skyline Pigeon','Genre: Soft Rock, Released: 1969','Elton John',0, 7);
insert into track(title, description, artist, energy, valence) values('Cinderella','Genre: Rap, Released: 2016','Mac Miller & Ty Dolla Sign',0, 7);
insert into track(title, description, artist, energy, valence) values('Aqueous Transmission','Genre: Alt. Metal, Released: 2001','Incubus',0, 7);
insert into track(title, description, artist, energy, valence) values('Harvest Moon','Genre: Folk Rock, Released: 1992','Neil Young',0, 7);
insert into track(title, description, artist, energy, valence) values('An Ending (Ascent)','Genre: Ambient, Released: 1982','Brian Eno',0, 7);
insert into track(title, description, artist, energy, valence) values('Gravity','Genre: Prog. Rock, Released: 2003','A Perfect Circle',0, 7);
insert into track(title, description, artist, energy, valence) values('Aruarian Dance','Genre: Lo-fi Hip Hop, Released: 2004','Nujabes',0, 7);
insert into track(title, description, artist, energy, valence) values('Puffy Cloud','Genre: Punk Rock, Released: 1990','Ween',0, 7);
insert into track(title, description, artist, energy, valence) values('Eye of the Tiger','Genre: Hard Rock, Released: 1982','Survivor',10, 6);
insert into track(title, description, artist, energy, valence) values('We Will Rock You','Genre: Arena Rock, Released: 1977','Queen',10, 6);
insert into track(title, description, artist, energy, valence) values('Get Lucky','Genre: Disco, Released: 2013','Daft Punk',10, 6);
insert into track(title, description, artist, energy, valence) values('Pump It','Genre: Dance, Released: 2006','Black Eyed Peas',10, 6);
insert into track(title, description, artist, energy, valence) values('Stronger','Genre: Hip Hop, Released: 2007','Kanye West',10, 6);
insert into track(title, description, artist, energy, valence) values('Night of Fire','Genre: Eurobeat, Released: 2000','N.I.K.O',10, 6);
insert into track(title, description, artist, energy, valence) values('Pump Up the Jam','Genre: House, Released: 1989','Technotronic',10, 6);
insert into track(title, description, artist, energy, valence) values('Highway Star','Genre: Hard Rock, Released: 1972','Deep Purple',10, 6);
insert into track(title, description, artist, energy, valence) values('Dont Start Now','Genre: Nu-Disco, Released: 2019','Dua Lipa',10, 6);
insert into track(title, description, artist, energy, valence) values('Alterlife','Genre: Pop, Released: 2017','Rina Sawayama',10, 6);
insert into track(title, description, artist, energy, valence) values('Hey Ya!','Genre: Pop, Released: 2003','OutKast',3, 7);
insert into track(title, description, artist, energy, valence) values('Walking on Sunshine','Genre: Bubblegum, Released: 1985','Katrina and the Waves',3, 7);
insert into track(title, description, artist, energy, valence) values('Lovely Day','Genre: R&B, Released: 1977','Bill Whithers',3, 7);
insert into track(title, description, artist, energy, valence) values('Shiny Happy People','Genre: Pop Rock, Released: 1991','R.E.M.',3, 7);
insert into track(title, description, artist, energy, valence) values('Shake It Off','Genre: Dance-Pop, Released: 2014','Taylor Swift',3, 7);
insert into track(title, description, artist, energy, valence) values('Good as Hell','Genre: Hip Pop, Released: 2016','Lizzo',3, 7);
insert into track(title, description, artist, energy, valence) values('Three Little Birds','Genre: Reggae, Released: 1980','Bob Marley & The Wailers',3, 7);
insert into track(title, description, artist, energy, valence) values('September','Genre: Funk, Released: 1978','Earth, Wind, and Fire',3, 7);
insert into track(title, description, artist, energy, valence) values('Candy','Genre: Pop, Released: 1986','Cameo',3, 7);
insert into track(title, description, artist, energy, valence) values('Dancing Queen','Genre: Disco, Released: 1976','ABBA',3, 7);
insert into track(title, description, artist, energy, valence) values('You Oughta Know','Genre: Alt. Rock, Released: 1995','Alanis Morissette',6, 0);
insert into track(title, description, artist, energy, valence) values('Were Not Gonna Take It','Genre: Glam Metal, Released: 1984','Twisted Sister',6, 0);
insert into track(title, description, artist, energy, valence) values('Chop Suey!','Genre: Nu Metal, Released: 2001','System of a Down',6, 0);
insert into track(title, description, artist, energy, valence) values('Last Resort','Genre: Nu Metal, Released: 2000','Papa Roach',6, 0);
insert into track(title, description, artist, energy, valence) values('Break Stuff','Genre: Nu Metal, Released: 2000','Limp Bizkit',6, 0);
insert into track(title, description, artist, energy, valence) values('The Way I Am','Genre: Hip Hop, Released: 2000','Eminem',6, 0);
insert into track(title, description, artist, energy, valence) values('Before He Cheats','Genre: Country, Released: 2006','Carrie Underwood',6, 0);
insert into track(title, description, artist, energy, valence) values('Rage','Genre: Trap, Released: 2018','Rico Nasty',6, 0);
insert into track(title, description, artist, energy, valence) values('St. Anger','Genre: Alt. Metal, Released: 2003','Metallica',6, 0);
insert into track(title, description, artist, energy, valence) values('Ambitionz az a Ridah','Genre: Gangsta Rap, Released: 1996','2Pac',6, 0);
insert into track(title, description, artist, energy, valence) values('Geeks In Love','Genre: Geek Rock, Released: 2005','Lemon Demon',7, 9);
insert into track(title, description, artist, energy, valence) values('Unforgettable','Genre: Pop, Released: 1951','Nat King Cole',7, 9);
insert into track(title, description, artist, energy, valence) values('Sweet Love','Genre: R&B, Released: 1986','Anita Baker',7, 9);
insert into track(title, description, artist, energy, valence) values('Unchained Melody','Genre: Blue-eyed Soul, Released: 1965','The Righteous Brothers',7, 9);
insert into track(title, description, artist, energy, valence) values('By Your Side','Genre: Soul, Released: 2000','Sade',7, 9);
insert into track(title, description, artist, energy, valence) values('Cigarettes & Coffee','Genre: Soul, Released: 1966','Otis Redding',7, 9);
insert into track(title, description, artist, energy, valence) values('I Will Always Love You','Genre: Soul, Released: 1992','Whitney Houston',7, 9);
insert into track(title, description, artist, energy, valence) values('At Last','Genre: Jazz, Released: 1960','Etta James',7, 9);
insert into track(title, description, artist, energy, valence) values('La-La Means I Love You','Genre: R&B, Released: 1968','The Delfonics',7, 9);
insert into track(title, description, artist, energy, valence) values('SSultan Qalbam Tu Asti','Genre: Romantic, Released: 1970','Ahmed Zahir',7, 9);

insert into mood(mood, energy, valence) values ('sad', 3, 0);
insert into mood(mood, energy, valence) values ('calm', 0, 7);
insert into mood(mood, energy, valence) values ('energetic', 10, 6);
insert into mood(mood, energy, valence) values ('happy', 3, 7);
insert into mood(mood, energy, valence) values ('angry', 6, 0);
insert into mood(mood, energy, valence) values ('love', 7, 9);

insert into playlist(track_id,mood_id,mood) values (1,1,'sad');
insert into playlist(track_id,mood_id,mood) values (2,1,'sad');
insert into playlist(track_id,mood_id,mood) values (3,1,'sad');
insert into playlist(track_id,mood_id,mood) values (4,1,'sad');
insert into playlist(track_id,mood_id,mood) values (5,1,'sad');
insert into playlist(track_id,mood_id,mood) values (6,1,'sad');
insert into playlist(track_id,mood_id,mood) values (7,1,'sad');
insert into playlist(track_id,mood_id,mood) values (8,1,'sad');
insert into playlist(track_id,mood_id,mood) values (9,1,'sad');
insert into playlist(track_id,mood_id,mood) values (10,1,'sad');
insert into playlist(track_id,mood_id,mood) values (11,2,'calm');
insert into playlist(track_id,mood_id,mood) values (12,2,'calm');
insert into playlist(track_id,mood_id,mood) values (13,2,'calm');
insert into playlist(track_id,mood_id,mood) values (14,2,'calm');
insert into playlist(track_id,mood_id,mood) values (15,2,'calm');
insert into playlist(track_id,mood_id,mood) values (16,2,'calm');
insert into playlist(track_id,mood_id,mood) values (17,2,'calm');
insert into playlist(track_id,mood_id,mood) values (18,2,'calm');
insert into playlist(track_id,mood_id,mood) values (19,2,'calm');
insert into playlist(track_id,mood_id,mood) values (20,2,'calm');
insert into playlist(track_id,mood_id,mood) values (21,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (22,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (23,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (24,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (25,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (26,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (27,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (28,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (29,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (30,3,'energetic');
insert into playlist(track_id,mood_id,mood) values (31,4,'happy');
insert into playlist(track_id,mood_id,mood) values (32,4,'happy');
insert into playlist(track_id,mood_id,mood) values (33,4,'happy');
insert into playlist(track_id,mood_id,mood) values (34,4,'happy');
insert into playlist(track_id,mood_id,mood) values (35,4,'happy');
insert into playlist(track_id,mood_id,mood) values (36,4,'happy');
insert into playlist(track_id,mood_id,mood) values (37,4,'happy');
insert into playlist(track_id,mood_id,mood) values (38,4,'happy');
insert into playlist(track_id,mood_id,mood) values (39,4,'happy');
insert into playlist(track_id,mood_id,mood) values (40,4,'happy');
insert into playlist(track_id,mood_id,mood) values (41,5,'angry');
insert into playlist(track_id,mood_id,mood) values (42,5,'angry');
insert into playlist(track_id,mood_id,mood) values (43,5,'angry');
insert into playlist(track_id,mood_id,mood) values (44,5,'angry');
insert into playlist(track_id,mood_id,mood) values (45,5,'angry');
insert into playlist(track_id,mood_id,mood) values (46,5,'angry');
insert into playlist(track_id,mood_id,mood) values (47,5,'angry');
insert into playlist(track_id,mood_id,mood) values (48,5,'angry');
insert into playlist(track_id,mood_id,mood) values (49,5,'angry');
insert into playlist(track_id,mood_id,mood) values (50,5,'angry');
insert into playlist(track_id,mood_id,mood) values (51,6,'love');
insert into playlist(track_id,mood_id,mood) values (52,6,'love');
insert into playlist(track_id,mood_id,mood) values (53,6,'love');
insert into playlist(track_id,mood_id,mood) values (54,6,'love');
insert into playlist(track_id,mood_id,mood) values (55,6,'love');
insert into playlist(track_id,mood_id,mood) values (56,6,'love');
insert into playlist(track_id,mood_id,mood) values (57,6,'love');
insert into playlist(track_id,mood_id,mood) values (58,6,'love');
insert into playlist(track_id,mood_id,mood) values (59,6,'love');
insert into playlist(track_id,mood_id,mood) values (60,6,'love');

commit;