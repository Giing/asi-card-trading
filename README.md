# asi-card-trading
## Architecture
![Architecture image](./Ressources/Architecture.drawio.png)


## Lancement du docker postgres

newgrp docker
docker run --name pg-asi -p 5432:5432 -e POSTGRES_PASSWORD=pwd -e POSTGRES_DB=bd -e POSTGRES_USER=login -d postgres:14-alpine
