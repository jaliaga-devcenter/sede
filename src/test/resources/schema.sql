CREATE SCHEMA IF NOT EXISTS "public";

create table FICHERO (
   ID                   SERIAL 		 not null,
   UUID			        VARCHAR(255)     null,
   NOMBRE_ORIGINAL		VARCHAR(255)	 null,
   TAMANYO				FLOAT			 null,
   TIPO					SMALLINT		 null,
);

create table LENGUAJE (
   ID                   SERIAL 		 not null,
   CODIGO		VARCHAR(255) null UNIQUE,
   IDIOMA				TEXT			 null,
);

create table LICITACION (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   DESCRIPCION			TEXT 			null,
   PRESUPUESTO			DECIMAL 		null,
   FIN_PLAZO			DATE 			null, 
   MEDIO				SMALLINT 		null,
 );
 
 alter table LICITACION
   add constraint FK_LICITACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;

create table LICITACION_LENGUAJE (
  licitacion_id SERIAL not null, 
  DESCRIPCION TEXT, 
  idioma varchar(255)
  );

alter table LICITACION_LENGUAJE
 add constraint FK_LICITACION_LICITACION_LENGUAJE foreign key (licitacion_id) 
 references LICITACION(ID)
 on delete restrict on update restrict;

create table APERTURA (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   DENOMINACION			TEXT 			null,
   HORA			TIME 		null,
   PLICA			TEXT 			null, 
 );
 
 alter table APERTURA
   add constraint FK_APLICACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;
      
create table ADJUDICACION (
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
 
 alter table ADJUDICACION
   add constraint FK_ADJUDICACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;      
      
create table DOCUMENTACION (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   DESCRIPCION			TEXT 			null,
   ESTADO				SMALLINT 		null,
 );
 
 alter table DOCUMENTACION
   add constraint FK_DOCUMENTACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;

create table MODELO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   DESCRIPCION			TEXT 			null,
 );
 
 alter table MODELO
   add constraint FK_MODELO_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;  
      
      
create table AVISO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   DESCRIPCION			TEXT 			null,
   FECHA				DATE 			null,   
 );
 
 alter table AVISO
   add constraint FK_AVISO_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;    

create table ANUNCIO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA_DE            DATE  null,
   FECHA_HASTA            DATE  null,   
   TITULO			TEXT 			null,
   DESCRIPCION			TEXT 			null,
 );
 
 alter table ANUNCIO
   add constraint FK_ANUNCIO_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;  

create table NORMATIVA (
   ID                   SERIAL 		not null,
   URL_NORMA           TEXT            null,
   NORMA            TEXT  null,   
   ARTICULO			TEXT 			null,
   TEXTO			TEXT 			null,
 );

create table PARADA (
   ID                   SERIAL 		not null,
   FECHA            DATE  null,
   DESCRIPCION			TEXT 			null,
 );

 create table NOTICIA (
   ID                   SERIAL 		not null,
   FECHA            TIMESTAMP  null,
   TITULO			TEXT 			null,
   DESCRIPCION			TEXT 			null,
 );
 

 