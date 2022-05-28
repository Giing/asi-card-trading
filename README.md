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