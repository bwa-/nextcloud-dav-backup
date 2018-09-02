# NextcloudDavBackup
Creates backups of Nextcloud calendars and addressbooks and stores them as Caldav/Carddav exports

# Build

Create far jar with gradle task 'fatJar':

./gradlew fatJar

If you do not have a test configuration 'testconf.xml' file in src/test/resources you need to skip the tests:

./gradlew fatJar -x test

# Run

java -jar build/libs/NextcloudDavBackup-1.2-SNAPSHOT.jar conffile.xml

An example config file can be found in the root directory of the project.
