to build as native:
```shell
source ~/bin/g22.sh
./mvnw native:compile -Pnative
```

### notes 22-05-2025
Has to use GraalVM22, as 23+ seem broken with Hibernate, see https://stackoverflow.com/questions/78286253/caused-by-java-lang-nosuchmethodexception-org-hibernate-bytecode-internal-byte (unclear which version to revert to)
