from debian:unstable
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update \
    && apt-get install -y curl gnupg gcc git python3 make

# Install OpenJDK-8 and fix certificate issues
RUN   apt-get install -y openjdk-8-jdk ant ca-certificates-java && \
    apt-get clean &&\
    update-ca-certificates -f;

# Install Bazel
RUN curl -f https://bazel.build/bazel-release.pub.gpg | apt-key add - \
 && echo "deb [arch=amd64] https://storage.googleapis.com/bazel-apt stable jdk1.8" | tee /etc/apt/sources.list.d/bazel.list

RUN apt update && apt install -y bazel-3.4.1 \
  && rm -rf /var/lib/apt/lists/*

ENV WORKSPACE /src
WORKDIR $WORKSPACE

COPY . .

RUN bazel-3.4.1 build //cpp/rpc_service:run-server
CMD ["./bazel-bin/cpp/rpc_service/run-server"]

