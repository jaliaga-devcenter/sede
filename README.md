# sede

## Integración Continua

### Estado master
[![Master Status](https://travis-ci.org/jaliaga-devcenter/sede.svg?branch=master)](https://travis-ci.org/jaliaga-devcenter/sede)


![Quality gate](https://sonarcloud.io/api/project_badges/measure?project=teralco%3ASedeElectronica&metric=alert_status "Quality gate") ![Maintainability](https://sonarcloud.io/api/project_badges/measure?project=teralco%3ASedeElectronica&metric=sqale_rating "Maintainability") ![Technical debt](https://sonarcloud.io/api/project_badges/measure?project=teralco%3ASedeElectronica&metric=sqale_index "Technical debt") 

### Estado branches

[Travis CI](https://travis-ci.org/jaliaga-devcenter/sede/branches "Travis CI") 

[SonarCloud](https://sonarcloud.io/organizations/devcenter-es/projects "Sonar") 



## Parametros arranque 
Para activar el perfil de dev es necesario el siguiente parametro:

*-Dspring.profiles.active=dev* 

Para permitir recibir por request parameter Json es necesario añadir:

*-Dtomcat.util.http.parser.HttpParser.requestTargetAllow=|{}*