create table CommerceQualificationRel (
	mvccVersion LONG default 0 not null,
	commerceQualificationRelId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	sourceClassNameId LONG,
	sourceClassPK LONG,
	targetClassNameId LONG,
	targetClassPK LONG
);