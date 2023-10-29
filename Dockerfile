FROM azul/zulu-openjdk:17 as builder

COPY server/ /server/

RUN chmod +x -R /server/gradlew

WORKDIR /server
RUN ./gradlew bootJar -x test

COPY web/ /web/
WORKDIR /web

RUN apt-get update && \
    apt-get install -y curl && \
    curl -sL https://deb.nodesource.com/setup_16.x | bash - && \
    apt-get install -y nodejs

RUN npm run build


FROM ubuntu:20.04

RUN apt-get update &&  \
    apt-get install openvpn -y &&  \
    apt-get install openssh-server -y && \
    apt-get install ssh -y && \
    apt-get install expect -y && \
    apt-get install curl -y && \
    apt-get install jq -y && \
    apt-get install iptables -y && \
    apt-get install vim -y

RUN curl -sL https://deb.nodesource.com/setup_16.x | bash - && \
        apt-get install -y nodejs

RUN wget -P ~/ https://github.com/OpenVPN/easy-rsa/releases/download/v3.0.8/EasyRSA-3.0.8.tgz
RUN tar xvf ~/EasyRSA-3.0.8.tgz -C ~/

RUN chmod +x -R ~/EasyRSA-3.0.8

WORKDIR /root/EasyRSA-3.0.8
RUN ./easyrsa init-pki

COPY docker_assets/easy_rsa/vars.example /root/EasyRSA-3.0.8/pki/vars
COPY docker_assets/easy_rsa/build-ca.exp /root/EasyRSA-3.0.8/build-ca.exp
RUN chmod 755 ~/EasyRSA-3.0.8/build-ca.exp
RUN expect /root/EasyRSA-3.0.8/build-ca.exp

COPY docker_assets/easy_rsa/gen-req.exp /root/EasyRSA-3.0.8/gen-req.exp
RUN chmod 755 ~/EasyRSA-3.0.8/gen-req.exp
RUN expect /root/EasyRSA-3.0.8/gen-req.exp

RUN cp ~/EasyRSA-3.0.8/pki/private/server.key /etc/openvpn/

COPY docker_assets/easy_rsa/sign-req.exp /root/EasyRSA-3.0.8/sign-req.exp
RUN chmod 755 ~/EasyRSA-3.0.8/sign-req.exp
RUN expect /root/EasyRSA-3.0.8/sign-req.exp

RUN cp ~/EasyRSA-3.0.8/pki/issued/server.crt /etc/openvpn/
RUN cp ~/EasyRSA-3.0.8/pki/ca.crt /etc/openvpn/
RUN /root/EasyRSA-3.0.8/easyrsa gen-dh
RUN openvpn --genkey --secret ta.key

RUN cp ~/EasyRSA-3.0.8/ta.key /etc/openvpn/
RUN cp ~/EasyRSA-3.0.8/pki/dh.pem /etc/openvpn/

RUN mkdir -p ~/client-configs/keys
RUN chmod -R 755 ~/client-configs

RUN cp ~/EasyRSA-3.0.8/ta.key ~/client-configs/keys/
RUN cp /etc/openvpn/ca.crt ~/client-configs/keys/

COPY docker_assets/openvpn/server.conf /etc/openvpn

RUN chmod 400 /etc/openvpn/server.key && \
    chmod 400 /etc/openvpn/ca.crt && \
    chmod 400 /etc/openvpn/server.crt && \
    chmod 400 /etc/openvpn/dh.pem && \
    chmod 400 /etc/openvpn/ta.key

RUN mkdir -p /root/client-configs/files
RUN cp /root/client-configs/keys/ta.key /root/client-configs/files/
RUN chmod 400 /root/client-configs/files/ta.key

COPY docker_assets/openvpn/client.conf /root/client-configs/base.conf

COPY docker_assets/openvpn/make_config.sh /root/client-configs/make_config.sh
RUN chmod 755 /root/client-configs/make_config.sh

RUN mkdir -p /root/ovpn-sysctl

COPY docker_assets/sysctl/start.sh /root/ovpn-sysctl/start.sh
RUN chmod 755 /root/ovpn-sysctl/start.sh

COPY docker_assets/sysctl/stop.sh /root/ovpn-sysctl/stop.sh
RUN chmod 755 /root/ovpn-sysctl/stop.sh

RUN apt-get install openjdk-17-jdk -y

COPY docker_assets/sign_new_user/01_gen-req.exp /root/EasyRSA-3.0.8/01_gen-req.exp
COPY docker_assets/sign_new_user/02_sign-req.exp /root/EasyRSA-3.0.8/02_sign-req.exp
COPY docker_assets/sign_new_user/sign_new_user.sh /root/EasyRSA-3.0.8/sign_new_user.sh
RUN chmod 755 /root/EasyRSA-3.0.8/sign_new_user.sh

RUN mkdir -p /root/server
COPY --from=builder /server/build/libs/server-0.0.1-SNAPSHOT.jar /root/server/server.jar

RUN mkdir -p /root/web
COPY --from=builder /web/build /root/web/build

RUN npm install -g serve

ENTRYPOINT ["/bin/bash", "/root/ovpn-sysctl/start.sh"]
