CREATE TABLE 'users' (
  'userId' INT UNSIGNED NOT NULL AUTO_INCREMENT,
  'email' VARCHAR(45) NOT NULL UNIQUE,
  'password' VARCHAR(45) NOT NULL,
  'displayName' VARCHAR(45) NOT NULL,
  PRIMARY KEY ('userId'));

CREATE TABLE 'preferencetypes' (
  'preferenceId' INT UNSIGNED NOT NULL AUTO_INCREMENT,
  'preferenceName' VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY ('preferenceId'));

CREATE TABLE 'events' (
  'eventId' INT UNSIGNED NOT NULL AUTO_INCREMENT,
  'eventTitle' VARCHAR(45),
  'eventDescription' VARCHAR(768),
  'userId' VARCHAR(45) NOT NULL,
  'postedDateTime' DATETIME DEFAULT GETDATE(),
  'eventDateTime' DATETIME NOT NULL,
  'expired' BOOLEAN NOT NULL,
  'preferenceId' INT UNSIGNED,
  'eventLocation' VARCHAR(45),
  PRIMARY KEY ('eventId'));

CREATE TABLE 'rsvp' (
  'userId' INT UNSIGNED NOT NULL,
  'eventId' INT UNSIGNED NOT NULL,
  'reminded' BOOLEAN NOT NULL,
  PRIMARY KEY ('userId', 'eventId'));

CREATE TABLE 'preferences' (
  'userId' INT UNSIGNED NOT NULL,
  'preferenceId' INT UNSIGNED NOT NULL,
  'alert' BOOLEAN NOT NULL,
  PRIMARY KEY ('userId', 'preferenceId'));
