create table FFRequest (
	mvccVersion LONG default 0 not null,
	externalReferenceCode VARCHAR(75) null,
	ffRequestId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	endDate DATE null,
	originalFFRequest VARCHAR(75) null,
	parameters VARCHAR(75) null,
	replyTo VARCHAR(75) null,
	startDate DATE null,
	type_ VARCHAR(75) null,
	workflowDefinitionLinkId LONG,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table FFRequestParameter (
	mvccVersion LONG default 0 not null,
	ffRequestParameterId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	ffRequestId LONG
);

create table FFRule (
	mvccVersion LONG default 0 not null,
	ffRuleId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	condition_ VARCHAR(75) null,
	priority DOUBLE,
	workflowDefinitionLinkId LONG,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table FFTask (
	mvccVersion LONG default 0 not null,
	ffTaskId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	endDate DATE null,
	ffRequestId LONG,
	index_ LONG,
	parameters VARCHAR(75) null,
	startDate DATE null,
	type_ VARCHAR(75) null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table FFTaskParameter (
	mvccVersion LONG default 0 not null,
	ffTaskParameterId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	ffTaskId LONG
);

create table FulfilmentRequest (
	mvccVersion LONG default 0 not null,
	externalReferenceCode VARCHAR(75) null,
	fulfilmentRequestId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	endDate DATE null,
	originalFulfilmentRequest TEXT null,
	inputParameters TEXT null,
	outputParameters TEXT null,
	replyTo VARCHAR(75) null,
	startDate DATE null,
	type_ VARCHAR(75) null,
	workflowDefinitionLinkId LONG,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table FulfilmentTask (
	mvccVersion LONG default 0 not null,
	fulfilmentTaskId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	correlationId LONG,
	endDate DATE null,
	fulfilmentRequestId LONG,
	index_ LONG,
	inputParameters TEXT null,
	outputParameters TEXT null,
	startDate DATE null,
	type_ VARCHAR(75) null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);