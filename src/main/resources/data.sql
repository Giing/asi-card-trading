
-- Families
insert into public.family (id_family, name_family)
values (0, 'DC COMICS');

insert into public.family (id_family, name_family)
values (1, 'Marvel');

-- Cards
insert into public.card (id_card, affinity_card, description_card, energy_card, name_card, family_card_id_family, attack_card, hp_card, defence_card,source_url_card)
values 
(0, 'DC COMICS', 'Bruce Wayne, alias Batman, est un héros de fiction appartenant à l''univers de DC Comics', 80, 'Batman', 0, 170, 50, 80,'https://static.fnac-static.com/multimedia/Images/8F/8F/7D/66/6716815-1505-1540-1/tsp20171122191008/Lego-lgtob12b-lego-batman-movie-lampe-torche-batman.jpg'),
(1, 'MARVEL', 'C dedpool', 100, 'Deadpool', 1, 15, 999999, 15,'https://static.hitek.fr/img/actualite/2017/06/27/i_deadpool-2.jpg'),
(2, 'DC COMICS', 'Description de superman [insérer un jeu de mot]', 100, 'Superman', 0, 50, 500, 50,'http://www.superherobroadband.com/app/themes/superhero/assets/img/superhero.gif');

-- Cards Instances
--insert into public.card_instance (id_instance, card_instance_id_card, user_instance_id_user)
--values (0, 0, 0);

commit;