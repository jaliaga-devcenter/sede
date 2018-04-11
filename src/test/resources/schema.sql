CREATE SCHEMA IF NOT EXISTS "public";

CREATE TABLE IF NOT EXISTS FICHERO (
   ID                   SERIAL 		 not null,
   UUID			        VARCHAR(255)     null,
   NOMBRE_ORIGINAL		VARCHAR(255)	 null,
   TAMANYO				FLOAT			 null,
   TIPO					SMALLINT		 null,
);

CREATE TABLE IF NOT EXISTS LENGUAJE (
   ID                   SERIAL 		 not null,
   CODIGO		VARCHAR(255) null UNIQUE,
   IDIOMA				TEXT			 null,
);

CREATE TABLE IF NOT EXISTS LICITACION (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   DESCRIPCION			TEXT 			null,
   PRESUPUESTO			DECIMAL 		null,
   FIN_PLAZO			DATE 			null, 
   MEDIO				SMALLINT 		null,
 );
 
 ALTER TABLE LICITACION DROP CONSTRAINT IF EXISTS FK_LICITACION_FICHERO;
 alter table LICITACION
   add constraint FK_LICITACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;

CREATE TABLE IF NOT EXISTS LICITACION_LENGUAJE (
  licitacion_id SERIAL not null, 
  DESCRIPCION TEXT, 
  idioma varchar(255)
  );

ALTER TABLE LICITACION_LENGUAJE DROP CONSTRAINT IF EXISTS FK_LICITACION_LICITACION_LENGUAJE;
alter table LICITACION_LENGUAJE
 add constraint FK_LICITACION_LICITACION_LENGUAJE foreign key (licitacion_id) 
 references LICITACION(ID)
 on delete restrict on update restrict;

CREATE TABLE IF NOT EXISTS APERTURA (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   DENOMINACION			TEXT 			null,
   HORA			TIME 		null,
   PLICA			TEXT 			null, 
 );

 ALTER TABLE APERTURA DROP CONSTRAINT IF EXISTS FK_APLICACION_FICHERO;
 alter table APERTURA
   add constraint FK_APLICACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;
      
CREATE TABLE IF NOT EXISTS ADJUDICACION (
   ID2                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   DENOMINACION			TEXT 			null,
   HORA					TIME 			null,
   PLICA				TEXT 			null,
   FECHA_FORMALIZACION	DATE 			null,
   EMPRESA_ADJUDICACION	TEXT 			null,
   PRESUPUESTO			DECIMAL 		null,
   FECHA_ADJUDICACION	DATE 			null,   
 );
 
 ALTER TABLE ADJUDICACION DROP CONSTRAINT IF EXISTS FK_ADJUDICACION_FICHERO;
 alter table ADJUDICACION
   add constraint FK_ADJUDICACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;      
      
CREATE TABLE IF NOT EXISTS DOCUMENTACION (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   DESCRIPCION			TEXT 			null,
   ESTADO				SMALLINT 		null,
 );
 
 ALTER TABLE DOCUMENTACION DROP CONSTRAINT IF EXISTS FK_DOCUMENTACION_FICHERO;
 alter table DOCUMENTACION
   add constraint FK_DOCUMENTACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;

CREATE TABLE IF NOT EXISTS MODELO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   DESCRIPCION			TEXT 			null,
 );

ALTER TABLE MODELO DROP CONSTRAINT IF EXISTS FK_MODELO_FICHERO;
alter table MODELO
  add constraint FK_MODELO_FICHERO foreign key (ID_FICHERO)
     references FICHERO (ID)
     on delete restrict on update restrict;  
      
      
CREATE TABLE IF NOT EXISTS AVISO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   DESCRIPCION			TEXT 			null,
   FECHA				DATE 			null,   
 );
 
ALTER TABLE AVISO DROP CONSTRAINT IF EXISTS FK_AVISO_FICHERO;
alter table AVISO
  add constraint FK_AVISO_FICHERO foreign key (ID_FICHERO)
     references FICHERO (ID)
     on delete restrict on update restrict;    

CREATE TABLE IF NOT EXISTS ANUNCIO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA_DE            DATE  null,
   FECHA_HASTA            DATE  null,   
   TITULO			TEXT 			null,
   DESCRIPCION			TEXT 			null,
 );
 
ALTER TABLE ANUNCIO DROP CONSTRAINT IF EXISTS FK_ANUNCIO_FICHERO;
alter table ANUNCIO
  add constraint FK_ANUNCIO_FICHERO foreign key (ID_FICHERO)
     references FICHERO (ID)
     on delete restrict on update restrict;  

CREATE TABLE IF NOT EXISTS NORMATIVA (
   ID                   SERIAL 		not null,
   URL_NORMA           TEXT            null,
   NORMA            TEXT  null,   
   ARTICULO			TEXT 			null,
   TEXTO			TEXT 			null,
 );

CREATE TABLE IF NOT EXISTS PARADA (
   ID                   SERIAL 		not null,
   FECHA            DATE  null,
   DESCRIPCION			TEXT 			null,
 );

 CREATE TABLE IF NOT EXISTS NOTICIA (
   ID                   SERIAL 		not null,
   FECHA            TIMESTAMP  null,
   TITULO			TEXT 			null,
   DESCRIPCION			TEXT 			null,
 );
 
INSERT INTO lenguaje(CODIGO, IDIOMA) VALUES ('es','Castellano');

 