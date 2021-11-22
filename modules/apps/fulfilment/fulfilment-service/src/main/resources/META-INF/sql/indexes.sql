create index IX_DE288C8A on FFRequest (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_5FBF50EF on FFRequest (userId);

create index IX_2E2B00DA on FFRequestParameter (ffRequestId);

create index IX_6DBC431D on FFRule (workflowDefinitionLinkId);

create index IX_8A8BA1CF on FFTask (ffRequestId);

create index IX_4C7A3E5E on FFTaskParameter (ffTaskId);

create index IX_CA2F5220 on FulfilmentRequest (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_74CEE605 on FulfilmentRequest (userId);

create index IX_D94F5978 on FulfilmentTask (correlationId);
create index IX_152E821B on FulfilmentTask (fulfilmentRequestId);