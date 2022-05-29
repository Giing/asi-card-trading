# asi-atelier-1

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

|  | Web statique + microservices | Web dynamique |
|---|---|---|
| Définition | Une page web statique charge un fichier HTML statique chez le client. Le client va ensuite faire une ou plusieur requêtes aux webservices pour récupérer les données et construire la page chez le client. Le rendu se fait côté client. | La page est construite côté serveur grâce un moteur de templating.  Elle est renvoyée au client en une seule fois au client avec les données déjà chargée. |
| Avantages | Très rapide une fois que le site a été chargé côté client. Les microservices permettent une scalabitlité plus optimable. | Moins de temps pour charger les pages. Moins coûteux en développement. |
| Inconvénients | Plus lent au démarrage (car pas de mise en cache) car on doit charger l'intégralité du site chez le client (SPA). | On doit recharger la page pour modifier les données de la vue. Moins adapté aux besoins actuels. |
| Sécurité | Moins sécurisé. On expose plus de chose chez le client (on expose du code javascript) | Plus sécurisé. Le code reste sur le serveur. |
| Technos | Vue.js, Javascript, Ajax | Blade, PHP, Twig, Thymleaf |

## Questions

Qu’est-ce que le pattern MVC ? Quels avantages présente-t-il ? 
> Le pattern MVC est une architecture logicielle qui découpe une application en 3 blocs. Le modèle qui contient les données à afficher. La view qui contient l'interface graphique. Et le controller qui contient les traitement des requetes faîtes par l'utilisateur.


| Avantages |
|---|
| - Facilement scalable<br>- Processus de développement plus rapide car il est plus simple de répartir la charge de travail au sein d'une équipe de dev.<br>- Le modèle est découplé de la vue<br>- Facilement maintenable |


Qu’est-ce que le Web dynamique ? pourquoi est-il intéressant ? 
> Le web dynamique consiste à faire créer une page par le serveur grace à un moteur de template et à la renvoyer à l'utilisateur.

Comment sont récupérés les fichiers par le Web Browser en Web
statique ? 

> En Web statique le web browser fait une requête au serveur qui lui renvoie les fichier tel quel.

Quels sont les avantages d’utiliser du Web Statique avec des services REST ?

> On peut récupérer les données des ressources exposées et mettre à jour la page en conséquences.


Qu’est que les architectures N-Tiers ? Quelles avantages apportent-elles ?

> Les architecures N-tier découpent la couche des services métier en plusieurs blocs. Cela offre plusieurs avantages :
> - Offre une meilleure scalabilité. On peut allouer plus de ressource à un service en particulier. 
> - La distibution est plus simple.
> - Les évolutions sont plus facile car on peut cibler un seul service à faire évoluer qui est découplé des autres services.


Comment fonctionne l’AJAX ?
> L'AJAX est une requête HTTP programmée pour être lancée après une action. Cette requête s'envoie sans rechargement de la page.

Qu’est-ce que JEE ? Comment fonctionne un serveur JEE ? Qu’est-ce qu’un Web Container en JEE ?
> JEE est un ensemble de norme pour la plateforme Java.

Qu’est ce que Springboot ? quelles différences avec JEE ?
> Spring Boot est un framework de développement JAVA. C'est une déclinaison du framework classique de Spring qui permet essentiellement de réaliser des microservices (ce sont la majeure partie du temps des services web qui sont regroupés en API). Springboot s'affranchit de certaines règles de JEE afin d'avoir un temps d'avance notamment sur les fonctionnalités qu'il propose.

Qu’apport Thymeleaf à Springboot ?
> Thymeleaf est un template engine (moteur de rendu de document) écrit en Java. Principalement conçu pour produire des vues Web, il fournit un support pour la génération de documents HTML, XHTML, JavaScript et CSS (voire de n’importe quel format texte).

Que signifie l’annotation @Controller, pourquoi est-elle importante lors de la génération de pas coté serveur ?
> L'annotation @Controller de Spring est une spécialisation de l'annotation @Component. L'annotation @Controller indique qu'une classe particulière joue le rôle d'un contrôleur. Il est utilisé pour marquer une classe comme un gestionnaire de requête web. Elle est surtout utilisée avec les applications Spring MVC. Cette annotation agit comme un stéréotype pour la classe annotée, indiquant son rôle.

Que représente le répertoire ‘src/main/resources’ dans un projet SpringBoot ? Quelles sont les
informations stockées à cet endroit ?
> Le répertoire représente des ressources statiques. Des fichiers de config comme: ```application.properties```, ce fichier est utilisé pour paramétrer le comportement par défaut de l’application. En fonction des dépendances déclarées dans notre projet et en fonction de la valeur des propriétés présentes dans ce fichier, Spring Boot va adapter la création du contexte d’application.