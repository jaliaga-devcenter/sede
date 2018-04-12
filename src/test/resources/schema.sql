DROP SCHEMA IF EXISTS PUBLIC
CREATE SCHEMA IF NOT EXISTS PUBLIC;

create table FICHERO (
   ID                   SERIAL 		 not null,
   UUID			        VARCHAR(255)     null,
   NOMBRE_ORIGINAL		VARCHAR(255)	 null,
   TAMANYO				FLOAT			 null,
   TIPO					SMALLINT		 null,
   constraint PK_FICHERO primary key (ID)
);

create table LENGUAJE (
   ID                   SERIAL 		 not null,
   CODIGO		VARCHAR(255) null UNIQUE,
   IDIOMA				TEXT			 null,
   constraint PK_LENGUAJE primary key (ID)
);

create table LICITACION (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   DESCRIPCION			TEXT 			null,
   PRESUPUESTO			DECIMAL 		null,
   FIN_PLAZO			DATE 			null, 
   MEDIO				SMALLINT 		null,
   constraint PK_LICITACION primary key (ID)
 );

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
   constraint PK_APERTURA primary key (ID)
 );

create table APERTURA_LENGUAJE (
  APERTURA_id SERIAL not null, 
  DENOMINACION TEXT,
  PLICA			TEXT 			null, 
  idioma varchar(255)
);

alter table APERTURA_LENGUAJE
 add constraint FK_APERTURA_APERTURA_LENGUAJE foreign key (apertura_id) 
 references APERTURA(ID)
 on delete restrict on update restrict;
      
create table ADJUDICACION (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   HORA					TIME 			null,
   FECHA_FORMALIZACION	DATE 			null,
   PRESUPUESTO			DECIMAL 		null,
   FECHA_ADJUDICACION	DATE 			null,   
   constraint PK_ADJUDICACION primary key (ID)
 );

create table ADJUDICACION_LENGUAJE (
    ADJUDICACION_id SERIAL not null, 
    DENOMINACION			TEXT 			null,
    PLICA				TEXT 			null,
    EMPRESA_ADJUDICACION	TEXT 			null,
    idioma varchar(255)
  );

alter table ADJUDICACION_LENGUAJE
 add constraint FK_ADJUDICACION_ADJUDICACION_LENGUAJE foreign key (ADJUDICACION_id) 
 references ADJUDICACION(ID)
 on delete restrict on update restrict;
      
create table DOCUMENTACION (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   ESTADO				SMALLINT 		null,
   constraint PK_DOCUMENTACION primary key (ID)
 );

create table DOCUMENTACION_LENGUAJE (
   DOCUMENTACION_id SERIAL not null,
   DESCRIPCION			TEXT 			null,   
   idioma varchar(255)
 );

alter table DOCUMENTACION_LENGUAJE
 add constraint FK_DOCUMENTACION_DOCUMENTACION_LENGUAJE foreign key (DOCUMENTACION_id) 
 references DOCUMENTACION(ID)
 on delete restrict on update restrict;      

create table MODELO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   constraint PK_MODELO primary key (ID)
 );

create table MODELO_LENGUAJE (
   MODELO_id SERIAL not null, 
   DESCRIPCION			TEXT 			null,    
   idioma varchar(255)
 );

alter table MODELO_LENGUAJE
 add constraint FK_MODELO_MODELO_LENGUAJE foreign key (MODELO_id) 
 references MODELO(ID)
 on delete restrict on update restrict;      
      
      
create table AVISO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,   
   constraint PK_AVISO primary key (ID)
 );

create table AVISO_LENGUAJE (
   AVISO_id SERIAL not null,
   DESCRIPCION			TEXT 			null,    
   idioma varchar(255)
 );

alter table AVISO_LENGUAJE
 add constraint FK_AVISO_AVISO_LENGUAJE foreign key (AVISO_id) 
 references AVISO(ID)
 on delete restrict on update restrict;      

create table ANUNCIO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA_DE            DATE  null,
   FECHA_HASTA            DATE  null,   

   constraint PK_ANUNCIO primary key (ID)
 );

 create table ANUNCIO_LENGUAJE (
   ANUNCIO_id SERIAL not null,
   TITULO			TEXT 			null,
   DESCRIPCION			TEXT 			null,    
   idioma varchar(255)
 );

alter table ANUNCIO_LENGUAJE
 add constraint FK_ANUNCIO_ANUNCIO_LENGUAJE foreign key (ANUNCIO_id) 
 references ANUNCIO(ID)
 on delete restrict on update restrict;      

create table NORMATIVA (
   ID                   SERIAL 		not null,
   URL_NORMA           TEXT            null,
   constraint PK_NORMATIVA primary key (ID)
 );

create table NORMATIVA_LENGUAJE (
   NORMATIVA_id SERIAL not null, 
   NORMA            TEXT  null,   
   ARTICULO			TEXT 			null,
   TEXTO			TEXT 			null,
   idioma varchar(255)   
  );

alter table NORMATIVA_LENGUAJE
 add constraint FK_NORMATIVA_NORMATIVA_LENGUAJE foreign key (NORMATIVA_id) 
 references NORMATIVA(ID)
 on delete restrict on update restrict;

create table PARADA (
   ID                   SERIAL 		not null,
   FECHA            DATE  null,
   constraint PK_PARADA primary key (ID)
 );

create table PARADA_LENGUAJE (
    PARADA_id SERIAL not null, 
    DESCRIPCION			TEXT 			null,    
    idioma varchar(255)
  );

alter table PARADA_LENGUAJE
 add constraint FK_PARADA_PARADA_LENGUAJE foreign key (PARADA_id) 
 references PARADA(ID)
 on delete restrict on update restrict;

 create table NOTICIA (
   ID                   SERIAL 		not null,
   FECHA            TIMESTAMP  null,
   constraint PK_NOTICIA primary key (ID)
 );

  create table NOTICIA_LENGUAJE (
    NOTICIA_id SERIAL not null,
    TITULO			TEXT 			null,
    DESCRIPCION			TEXT 			null,     
    idioma varchar(255)
  );

alter table NOTICIA_LENGUAJE
 add constraint FK_NOTICIA_NOTICIA_LENGUAJE foreign key (NOTICIA_id) 
 references NOTICIA(ID)
 on delete restrict on update restrict; 

INSERT INTO lenguaje(CODIGO, IDIOMA) VALUES ('es','Castellano');
 

 