# asi-card-trading
## Architecture
![Architecture image](./Ressources/Architecture.drawio.png)


## Lancement du docker postgres

newgrp docker
docker run --name pg-asi -p 5432:5432 -e POSTGRES_PASSWORD=pwd -e POSTGRES_DB=bd -e POSTGRES_USER=login -d postgres:14-alpine

### Initilisation Sql
Exécuter le script src/main/resources/data.sql

### Créer un utilisateur
Rendez-vous sur la page register.html, puis sur login.html pour accéder à l'application

### Vendre une carte
Pour vendre une carte, rendez-vous la page sell.html puis sélectionnez et entrez un prix.

## Sujet 

Après analyse de vos premiers prototypes vous décidez de partir sur une architecture Web Javascript
+ Web Services Full Rest. La prochaine étape est de bâtir une plateforme d’achat/vente de cartes.
Après inscription des utilisateurs, ces derniers recevront aléatoirement 5 cartes de jeu. Après s’être
connectés, les utilisateurs pourront acheter ou vendre des cartes.
Après avoir bien analyser les principes des Web Services Full Rest, votre équipe devra assurer la
réalisation des fonctionnalités décrites à savoir :
- Création d’utilisateur et connexion
- Achat/vente de cartessur la plateforme
Votre client souhaite dans un premier temps une authentification sommaire (ne pas utiliser les
modules Security de Spring boot) et surtout une application fonctionnelle bien commentée
respectant les standards FULL REST.
Développer ces fonctionnalités en respectant les contraintes de votre client en termes de
technologie SpringBoot. Comprenez l’intérêt de Maven et l’usage d’Entité pour développer votre
application.
Des modifications de la base de données seront nécessaires afin de permettre de stocker les
particularités des utilisateurs de l’application.

## Tableau récapitulatif

|  	| MVC 	| SOA 	| MicroServices 	|
|---	|---	|---	|---	|
| Définition 	| Modèle-vue-contrôleur ou MVC est un motif d'architecture logicielle destiné aux interfaces graphiques lancé en 1978 et très populaire pour les applications web. Le motif est composé de trois types de modules ayant trois responsabilités différentes : les modèles, les vues et les contrôleurs. 	| L'architecture orientée services ou SOA est une forme d'architecture de médiation qui est un modèle d'interaction applicative qui met en œuvre des services : avec une forte cohérence interne 	| L'architecture microservices est considérée comme une évolution de SOA car ses services sont plus fins et fonctionnent indépendamment les uns des autres. Par conséquent, si l'un des services échoue au sein d'une application, l'application continuera de fonctionner, car chaque service a un objectif distinct. Les microservices communiquent via des interfaces de programmation d'applications (API) et sont organisés autour d'un domaine d'activité particulier. Ensemble, ces services se combinent pour former des applications complexes. 	|
| Avantages 	| - Maintenance et ajout de fonctionnalités faciles. <br>- Réduit le temps de développement des projets.<br>- Aide à construire des logiciels fiables avec des architectures testées.<br>- Facilite la réutilisation du code.<br>- Meilleure intégration des équipes et répartition des tâches.<br>- Réduction de la complexité du code. 	| - Une modularité permettant de remplacer facilement un composant (service) par un autre - Une réutilisabilité possible des composants (par opposition à une système tout-en-un fait sur mesure pour une organisation). - De meilleures possibilités d'évolution (il suffit de faire évoluer un service ou d'ajouter un nouveau service) - Une plus grande tolérance aux pannes - Une maintenance facilitée 	| - Rend possible le déploiement continu et automatisé.<br>- Permet aux développeurs de prendre des décisions appropriées et spécifiques au service.<br>- Chaque microservice dispose de sa propre base de données.<br>- Permet aux entreprises d'optimiser les ressources pour le développement et les applications.<br>- Optimise le dimensionnement et facilite l'intégration avec des services tiers. 	|                                                                                                                                            |

### SOA vs MicroServices

Dans une SOA les développeurs doivent avoir des connaissances des autres services pour leur développement. Dans une architecture microservice chaque équipe travaille sur un seul service et peut ne pas connaître les autres services.

L'infrastructure de chaque service est indépendante dans une archi. microservices. C'est à dire que l'on peut faire évoluer la répartition des charges de façon ciblée.

Les architectures microservices sont plus faciles à déployer car on déploie service par service. On peut déployer uniquement les services mis à jour plutôt que l'intégrité de l'application.

Les microservices peuvent se trouver n'importe où et faire parti d'une application cohérente en même temps. Par exemple, le service d'authentification peut se trouver dans un datacenter à Paris et d'autres services dans un datacenter à Lyon.

Les microservices nécessite en revanche beaucoup plus de surveillance par rapport à une SOA. Le versionning, les tests de non régression doivent être plus robustes et les équipes de dev plus diciplinée.

La technologie SOA s'arrête à l'application alors que la stratégie des microservices s'ffranchit aussi de l'infrastructure.