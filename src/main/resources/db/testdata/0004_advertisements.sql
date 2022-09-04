-- subcategory audio
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,state,owner_id,subcategory_id)
VALUES
    ('Słuchawki Phillips, używane','Super słuchawki Phillips','2022-07-12',true,null,'AUDIO',100,'USED',1,1),
    ('Głośniki Sony, nowe','Głośniki bezprzewodowe Sony','2022-07-12',false,null,'AUDIO',200,'NEW',2,1),
    ('Głośniki JBL','Głośniki przewodowe JBL','2022-07-12',false,null,'AUDIO',600,'NEW',2,1),
    ('Słuchawki Samsung, uszkodzone','Słuchawki przewodowe Samsung. Uszkodzone','2022-07-12',false,null,'AUDIO',80,'DAMAGED',1,1);
-- subcategory telephone
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,state,owner_id,subcategory_id)
VALUES
    ('Samsung Galaxy S20, nowy','Samsung Galaxy S20, nowy','2022-07-12',true,null,'TEL',2100,'NEW',2,2),
    ('iPhone 12, nowy','Telefon iPhone 12, nowy','2022-07-12',false,null,'TEL',2200,'NEW',1,2);
-- subcategory women fashion
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,state,owner_id,subcategory_id,size,fashion_brand)
VALUES
    ('Spodnie Jeans Zara, nowe,  rozmiar L','Spodnie Jeans Zara, nowe,  rozmiar L','2022-07-12',false,null,'CLT-WOM',210,'NEW',2,3,'L','ZARA'),
    ('Sukienka Reserved, używana, rozmiar S','Sukienka Reserved, używana, rozmiar S','2022-07-12',false,null,'CLT-WOM',110,'USED',1,3,'S','RESERVED');
-- subcategory men fashion
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,state,owner_id,subcategory_id,size,fashion_brand)
VALUES
    ('Spodnie dresowe Adidas, nowe, rozmiar L','Spodnie dresowe Adidas L','2022-07-12',false,null,'CLT-MEN',210,'NEW',2,4,'L','ADIDAS'),
    ('Koszula Reserved M, Używana','Koszula Reserved M, Używana','2022-07-12',false,null,'CLT-MEN',310,'USED',1,4,'M','RESERVED'),
    ('Koszula Zara S, Uszkodzona','Koszula Zara S, Uszkodzona','2022-07-12',true,null,'CLT-MEN',100,'DAMAGED',1,4,'S','ZARA'),
    ('T-Shirt Nike S, Nowy','T-Shirt Nike S, Nowy','2022-07-12',false,null,'CLT-MEN',310,'NEW',1,4,'S','NIKE');
-- subcategory home and garden furniture
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,state,owner_id,subcategory_id,furniture_type)
VALUES
    ('Krzesło do jadalni, nowe','Krzesło do jadalni','2022-07-12',false,null,'FURN',510,'NEW',2,5,'CHAIR'),
    ('Fotel do salonu, nowy','Fotel do salonu','2022-07-12',true,null,'FURN',810,'NEW',1,5,'ARMCHAIR');
-- subcategory home and garden tool
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,state,owner_id,subcategory_id,tool_type)
VALUES
    ('Młotek, nowy','Młotek','2022-07-12',true,null,'TOOL',50,'NEW',2,6,'HAMMER'),
    ('Szlifierka Bosch, uszkodzona','Szlifierka Bosch, uszkodzona','2022-07-12',false,null,'TOOL',810,'DAMAGED',1,6,'POWER');
-- subcategory moto car
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,state,owner_id,subcategory_id,mileage,engine_power,prod_year,car_brand)
VALUES
    ('BMW 3 seria E30, używane,przebieg 80000km, rok prod 2006, moc:150kM','BMW 3 seria E30','2022-07-12',true,null,'CAR',25000,'USED',2,7,80000,150,2006,'BMW'),
    ('Renault Megane, nowe,rok prod 2022, moc: 100kM','Renault Megane','2022-07-12',false,null,'CAR',90000,'NEW',2,7,0,100,2022,'RENAULT');
-- subcategory moto motorbike
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,state,owner_id,subcategory_id,mileage,engine_power,prod_year,motorbike_brand)
VALUES
    ('Honda CBR 600, używana, przebieg 15000km, moc: 70kM','Honda CBR 600','2022-07-12',true,null,'MOTO',15000,'USED',2,8,15000,70,2017,'HONDA'),
    ('Yamaha YZF R-3 nowa, moc: 90kM','Yamaha YZF R-3','2022-07-12',false,null,'MOTO',25000,'NEW',1,8,0,90,2022,'YAMAHA');
-- subcategory real estate flat
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,owner_id,subcategory_id,area,room_count)
VALUES
    ('Mieszkanie Hoża 15/44 Warszawa, powierzchnia 80m, 3 pokoje','Mieszkanie Hoża 15/44 Warszawa, powierzchnia 80m, 3 pokoje','2022-07-12',false,null,'FLAT',750000,1,9,80,3),
    ('Mieszkanie Poznańska 34/14, Warszawa, powierzchnia 110m, 3 pokoje','Mieszkanie Poznańska 34/14, Warszawa, powierzchnia 110m, 3 pokoje','2022-07-12',true,null,'FLAT',950000,2,9,110,3);
-- subcategory real estate house
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,price,owner_id,subcategory_id,area,floor_count)
VALUES
    ('Dom Mickiewicza 10, powierzchnia 180m, liczba pięter: 2','Dom Mickiewicza 10, powierzchnia 180m, liczba pięter: 2','2022-07-12',true,null,'HOUSE',1750000,1,10,180,2),
    ('Dom Słowackiego 1 powierzchnia 110m, liczba pięter: 1','Dom Słowackiego 1 powierzchnia 110m, liczba pięter: 1','2022-07-12',false,null,'HOUSE',950000,2,10,110,1);
-- subcategory work
INSERT INTO
    advertisement (name,body,created,promoted,img,ad_type,owner_id,subcategory_id,work_from_home,working_time)
VALUES
    ('IT Java developer, praca stacjonarna, niepełny etat','IT Java developer, praca stacjonarna, niepełny etat','2022-07-12',true,null,'WORK-IT',1,11,false,'PART_TIME'),
    ('IT tester, praca zdalna, pełny etat','IT tester','2022-07-12',false,null,'WORK-IT',2,11,true,'FULL_TIME'),
    ('Nauczyciel języka angielskiego, pełny etat, praca stacjonarna','Nauczyciel języka angielskiego, pełny etat, praca stacjonarna','2022-07-12',false,null,'WORK-EDU',2,12,false,'FULL_TIME'),
    ('Nauczyciel języka niemieckiego, niepełny etat, praca stacjonarna','Nauczyciel języka niemieckiego, niepełny etat, praca stacjonarna','2022-07-12',true,null,'WORK-EDU',2,12,false,'PART_TIME');




