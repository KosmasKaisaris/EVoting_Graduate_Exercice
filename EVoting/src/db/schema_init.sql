
CREATE TABLE "USERS"

(    
   "ID" BIGINT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   "USER_TOKEN" VARCHAR(80)
);

CREATE TABLE "VOTES"
(    
   "ID" BIGINT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   "USER_ID" BIGINT not null,
   "VOTE" INTEGER,
 constraint FK_VOTE_USER_ID foreign key (USER_ID) references USERS(ID) on delete cascade
);
