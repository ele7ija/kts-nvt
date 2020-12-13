# Modified application.properties file

# mysql datasoruce string for jdbc driver connection
# For Docker and Docker compose:
# 1. database host must be the name of the database service in docker-compose.
# 2. database name must be the value of the environment variable `MYSQL_DATABASE`.
spring.datasource.url =jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true&useSSL=false

# username and password for mysql server
spring.datasource.username = user

#If you use docker-compose or docker, password should be same as the value of `MYSQL_ROOT_PASSWORD` env var in docker compose
spring.datasource.password = password

# Important for jdbc driver to understand version of mysql server
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect

# DDL operation run by this parameter. it will update the database fields and update changes in database
spring.jpa.hibernate.ddl-auto = create-drop

# Execute initialization script, always or never
spring.datasource.initialization-mode = always 

# Logged in command line to see sql error and jdbc exception
logging.level.org.hibernate.SQL= DEBUG
logging.level.org.hibernate.type=TRACE

# Expose the embdded server port, port mapping in docker-compose must align with this!
server.port = 8080

# Url prefix
server.servlet.context-path=/api

#JWT config
application.jwt.secretKey=securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure
application.jwt.tokenPrefix=Bearer 
application.jwt.tokenExpirationAfterDays=10

#Verification token config
application.verification-token.expiryTimeInMinutes=120

#email configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=ivanin.office@gmail.com
spring.mail.password=N0v!0ff!ceZaProj
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
