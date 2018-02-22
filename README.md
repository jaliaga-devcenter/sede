# sede

## Parametros arranque 
Para activar el perfil de dev es necesario el siguiente parametro:

*-Dspring.profiles.active=dev* 

Para permitir recibir por request parameter Json es necesario añadir:

*-Dtomcat.util.http.parser.HttpParser.requestTargetAllow=|{}*