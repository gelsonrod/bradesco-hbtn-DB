# Projeto Admin Demo

Maven + JPA/Hibernate + SQLite.

## Execução rápida
```bash
mvn -q -f jpa_hibernate/pom.xml clean compile
mvn -q -f jpa_hibernate/pom.xml exec:java -Dexec.mainClass=demo.AdministrativoApp
