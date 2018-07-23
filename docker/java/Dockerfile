# From java image, version : 8
FROM java:8


# 挂载app目录
VOLUME /app

# COPY or ADD to image
COPY ../../target/JavaTest3-jar-with-dependencies.jar app.jar
COPY ../../target/JavaTest3.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]