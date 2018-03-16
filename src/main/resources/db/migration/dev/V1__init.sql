create table FICHERO (
   ID                   SERIAL 		 not null,
   UUID			        VARCHAR(255)     null,
   NOMBRE_ORIGINAL		VARCHAR(255)	 null,
   TAMANYO				FLOAT			 null,
   TIPO					SMALLINT		 null,
   constraint PK_FICHERO primary key (ID)
);

create table LICITACION (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   DESCRIPCION			TEXT 			null,
   PRESUPUESTO			DECIMAL 		null,
   FIN_PLAZO			DATE 			null, 
   MEDIO				TEXT 		null,
   constraint PK_LICITACION primary key (ID)
 );
 
 alter table LICITACION
   add constraint FK_LICITACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;
      

create table APERTURA (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   DENOMINACION			TEXT 			null,
   HORA			TIME 		null,
   PLICA			TEXT 			null, 
   constraint PK_APERTURA primary key (ID)
 );
 
 alter table APERTURA
   add constraint FK_APLICACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;
      
create table ADJUDICACION (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   FECHA				DATE 			null,
   DENOMINACION			TEXT 			null,
   HORA					TIME 			null,
   PLICA				TEXT 			null,
   FECHA_FORMALIZACION	DATE 			null,
   EMPRESA_ADJUDICACION	TEXT 			null,
   PRESUPUESTO			DECIMAL 		null,
   FECHA_ADJUDICACION	DATE 			null,   
   constraint PK_ADJUDICACION primary key (ID)
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
   constraint PK_DOCUMENTACION primary key (ID)
 );
 
 alter table DOCUMENTACION
   add constraint FK_DOCUMENTACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;

create table MODELO (
   ID                   SERIAL 		not null,
   ID_FICHERO           INT8            null,
   DESCRIPCION			TEXT 			null,
   constraint PK_MODELO primary key (ID)
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
   constraint PK_AVISO primary key (ID)
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
   constraint PK_ANUNCIO primary key (ID)
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
   constraint PK_NORMATIVA primary key (ID)
 );

create table PARADA (
   ID                   SERIAL 		not null,
   FECHA            DATE  null,
   DESCRIPCION			TEXT 			null,
   constraint PK_PARADA primary key (ID)
 );

 create table NOTICIA (
   ID                   SERIAL 		not null,
   FECHA            TIMESTAMP  null,
   TITULO			TEXT 			null,
   DESCRIPCION			TEXT 			null,
   constraint PK_NOTICIA primary key (ID)
 );
 

 