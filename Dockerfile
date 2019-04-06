FROM openjdk:11-jdk

USER root
# Install custom tools, runtime, etc.
RUN apt-get update && apt-get install -y \
        maven \
    && apt-get clean && rm -rf /var/cache/apt/* && rm -rf /var/lib/apt/lists/* && rm -rf /tmp/*

# add your tools here
