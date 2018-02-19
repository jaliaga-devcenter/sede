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
   MEDIO				SMALLINT 		null,
   constraint PK_LICITACION primary key (ID)
 );
 
 alter table LICITACION
   add constraint FK_LICITACION_FICHERO foreign key (ID_FICHERO)
      references FICHERO (ID)
      on delete restrict on update restrict;
     