# notes

## ports
SEC_Commandes: 8080/8090
Service_Boissons: 8081/8091
Service_Repas: 8082/8092
CQRSReader: 8083/8093
CQRSReader2: 8084/8094
PubSubHubService: 8085/8095

## Exercice 1

1. Au sujet du format de données à utiliser pour la communication entre le SEC et les services: l'énoncé dit qu'il faut utiliser Atom, le PDF avec les diagrammes dit "DTO". Quel est la bonne information?

1. Ce n'est pas clair quels champs il faut mettre dans le DTOCommande. L'énoncé dit "les urls de repas, boisson et client" mais l'énoncé dit aussi qu'il faut coder les repas et boissons en dur, comme si on allait les mettre dans le DTO... Pourquoi les coderait-on en dur dans le client si on n'envoie que leur URL, et que les repas et boissons sont connus dans les services pertinents?

