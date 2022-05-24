insert into public.user (id_user, money_user, name_user, password_user, surname_user)
values (0, 5000, 'bat', 'pwd', 'bat');

insert into public.family (id_family, name_family)
values (0, 'DC COMICS');

insert into public.card (id_card, affinity_card, description_card, energy_card, name_card, family_card_id_family)
values (0, 'DC COMICS', 'Bruce Wayne, alias Batman, est un héros de fiction appartenant à l''univers de DC Comics', 100, 'Batman', 0);

insert into public.card_instance (id_instance, card_instance_id_card, user_instance_id_user)
values (0, 0, 0);
